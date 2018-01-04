package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.entpay.*;
import com.github.binarywang.wxpay.bean.request.WxPayDefaultRequest;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.EntPayService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.provider.JCERSAPublicKey;
import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PasswordFinder;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Security;

/**
 * <pre>
 *  Created by BinaryWang on 2017/12/19.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class EntPayServiceImpl implements EntPayService {
  private WxPayService payService;

  public EntPayServiceImpl(WxPayService payService) {
    this.payService = payService;
  }

  @Override
  public EntPayResult entPay(EntPayRequest request) throws WxPayException {
    request.checkAndSign(this.payService.getConfig());
    String url = this.payService.getPayBaseUrl() + "/mmpaymkttransfers/promotion/transfers";

    String responseContent = this.payService.post(url, request.toXML(), true);
    EntPayResult result = BaseWxPayResult.fromXML(responseContent, EntPayResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public EntPayQueryResult queryEntPay(String partnerTradeNo) throws WxPayException {
    EntPayQueryRequest request = new EntPayQueryRequest();
    request.setPartnerTradeNo(partnerTradeNo);
    request.checkAndSign(this.payService.getConfig());

    String url = this.payService.getPayBaseUrl() + "/mmpaymkttransfers/gettransferinfo";
    String responseContent = this.payService.post(url, request.toXML(), true);
    EntPayQueryResult result = BaseWxPayResult.fromXML(responseContent, EntPayQueryResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public String getPublicKey() throws WxPayException {
    WxPayDefaultRequest request = new WxPayDefaultRequest();
    request.setMchId(this.payService.getConfig().getMchId());
    request.setNonceStr(String.valueOf(System.currentTimeMillis()));
    request.setSign(SignUtils.createSign(request, null, this.payService.getConfig().getMchKey(),
      true));

    String url = "https://fraud.mch.weixin.qq.com/risk/getpublickey";
    String responseContent = this.payService.post(url, request.toXML(), true);
    GetPublicKeyResult result = BaseWxPayResult.fromXML(responseContent, GetPublicKeyResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result.getPubKey();
  }

  @Override
  public EntPayBankResult payBank(EntPayBankRequest request) throws WxPayException {
    File publicKeyFile = this.buildPublicKeyFile();
    request.setEncBankNo(this.encryptRSA(publicKeyFile, request.getEncBankNo()));
    request.setEncTrueName(this.encryptRSA(publicKeyFile, request.getEncTrueName()));
    publicKeyFile.deleteOnExit();

    request.checkAndSign(this.payService.getConfig());

    String url = this.payService.getPayBaseUrl() + "/mmpaysptrans/pay_bank";
    String responseContent = this.payService.post(url, request.toXML(), true);
    EntPayBankResult result = BaseWxPayResult.fromXML(responseContent, EntPayBankResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  @Override
  public EntPayBankQueryResult queryPayBank(String partnerTradeNo) throws WxPayException {
    EntPayBankQueryRequest request = new EntPayBankQueryRequest();
    request.setPartnerTradeNo(partnerTradeNo);
    request.checkAndSign(this.payService.getConfig());

    String url = this.payService.getPayBaseUrl() + "/mmpaysptrans/query_bank";
    String responseContent = this.payService.post(url, request.toXML(), true);
    EntPayBankQueryResult result = BaseWxPayResult.fromXML(responseContent, EntPayBankQueryResult.class);
    result.checkResult(this.payService, request.getSignType(), true);
    return result;
  }

  private String encryptRSA(File publicKeyFile, String srcString) throws WxPayException {
    try {
      Security.addProvider(new BouncyCastleProvider());
      Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
      PasswordFinder passwordFinder = new PasswordFinder() {
        @Override
        public char[] getPassword() {
          return "".toCharArray();
        }
      };

      try (PEMReader reader = new PEMReader(new FileReader(publicKeyFile), passwordFinder)) {
        JCERSAPublicKey publicKey = (JCERSAPublicKey) reader.readObject();
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypt = cipher.doFinal(srcString.getBytes());
        return Base64.encodeBase64String(encrypt);
      }
    } catch (Exception e) {
      throw new WxPayException("加密出错", e);
    }
  }

  private File buildPublicKeyFile() throws WxPayException {
    try {
      String publicKeyStr = this.getPublicKey();
      Path tmpFile = Files.createTempFile("payToBank", ".pem");
      Files.write(tmpFile, publicKeyStr.getBytes());
      return tmpFile.toFile();
    } catch (Exception e) {
      throw new WxPayException("生成加密公钥文件时发生异常", e);
    }
  }

  public static void main(String[] args) throws WxPayException, IOException {
    String key = "-----BEGIN RSA PUBLIC KEY-----\n" +
      "MIIBCgKCAQEAtEeUSop/YGqZ53Y++R9NapFSZmorj+SL/brmJUU7+hyClEnPOeG/\n" +
      "v6/ZrX9qo25JAojrBDbqaW9L+HtzI141vusarRYIGPvVqTV30L5db0Yq7AmX7Hs9\n" +
      "s+nEtoMAwMWUzQPXLUs2mt6rpu85HwAIK3F4Xb+OFIbXCJTbDvWYtQssn07lr+IY\n" +
      "jPA00sON71egmuRrCoQClkhf0vgrhj7eHUCRZRJ2zf4UU31fHv+kO441hVD5TTP8\n" +
      "bjJvFm6TW3sgQE8aCDbomtu+syk4Tv/4ONCqxG8d/kF1TlU+idGWEU179uR/KSjP\n" +
      "p7kM7BoaY2goFgYAe4DsI8Fh33dCOiKyVwIDAQAB\n" +
      "-----END RSA PUBLIC KEY-----";
    Path tmpFile = Files.createTempFile("payToBank", ".pem");
    Files.write(tmpFile, key.getBytes());
    System.out.println(new EntPayServiceImpl(null).encryptRSA(tmpFile.toFile(), "111111"));
  }

}

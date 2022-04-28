package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.ecommerce.SignatureHeader;
import com.github.binarywang.wxpay.bean.profitsharingV3.*;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.ProfitSharingV3Service;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.AesUtils;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Objects;

/**
 * 微信支付V3-资金应用-分账Service
 *
 * @author pg 2021-6-23
 * @version 1.0
 */
@Slf4j
@RequiredArgsConstructor
public class ProfitSharingV3ServiceImpl implements ProfitSharingV3Service {
  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  @Override
  public ProfitSharingResult profitSharing(ProfitSharingRequest request) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/orders", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ProfitSharingResult.class);
  }

  @Override
  public ProfitSharingResult getProfitSharingResult(String outOrderNo, String transactionId) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/orders/%s?transaction_id=%s", this.payService.getPayBaseUrl(), outOrderNo, transactionId);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ProfitSharingResult.class);
  }

  @Override
  public ProfitSharingReturnResult profitSharingReturn(ProfitSharingReturnRequest request) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/return-orders", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ProfitSharingReturnResult.class);
  }

  @Override
  public ProfitSharingReturnResult getProfitSharingReturnResult(String outOrderNo, String outReturnNo) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/return-orders/%s?out_order_no=%s", this.payService.getPayBaseUrl(), outReturnNo, outOrderNo);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ProfitSharingReturnResult.class);
  }

  @Override
  public ProfitSharingUnfreezeResult profitSharingUnfreeze(ProfitSharingUnfreezeRequest request) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/orders/unfreeze", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ProfitSharingUnfreezeResult.class);
  }

  @Override
  public ProfitSharingUnsplitResult getProfitSharingUnsplitAmount(String transactionId) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/transactions/%s/amounts", this.payService.getPayBaseUrl(), transactionId);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, ProfitSharingUnsplitResult.class);
  }

  @Override
  public ProfitSharingReceiver addProfitSharingReceiver(ProfitSharingReceiver request) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/receivers/add", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ProfitSharingReceiver.class);
  }

  @Override
  public ProfitSharingReceiver deleteProfitSharingReceiver(ProfitSharingReceiver request) throws WxPayException {
    String url = String.format("%s/v3/profitsharing/receivers/delete", this.payService.getPayBaseUrl());
    RsaCryptoUtil.encryptFields(request, this.payService.getConfig().getVerifier().getValidCertificate());
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, ProfitSharingReceiver.class);
  }

  @Override
  public ProfitSharingNotifyData getProfitSharingNotifyData(String notifyData, SignatureHeader header) throws WxPayException {
    ProfitSharingNotifyData response = parseNotifyData(notifyData,header);
    ProfitSharingNotifyData.Resource resource = response.getResource();
    String cipherText = resource.getCipherText();
    String associatedData = resource.getAssociatedData();
    String nonce = resource.getNonce();
    String apiV3Key= this.payService.getConfig().getApiV3Key();
    try {
      String result = AesUtils.decryptToString(associatedData, nonce,cipherText, apiV3Key);
      ProfitSharingNotifyData notifyResult = GSON.fromJson(result, ProfitSharingNotifyData.class);
      return notifyResult;
    } catch (GeneralSecurityException | IOException e) {
      throw new WxPayException("解析报文异常！", e);
    }
  }

  private ProfitSharingNotifyData parseNotifyData(String data, SignatureHeader header) throws WxPayException {
    if(Objects.nonNull(header) && !this.verifyNotifySign(header, data)){
      throw new WxPayException("非法请求，头部信息验证失败");
    }
    return GSON.fromJson(data, ProfitSharingNotifyData.class);
  }

  /**
   * 校验通知签名
   * @param header 通知头信息
   * @param data 通知数据
   * @return true:校验通过 false:校验不通过
   */
  private boolean verifyNotifySign(SignatureHeader header, String data) {
    String beforeSign = String.format("%s\n%s\n%s\n",
      header.getTimeStamp(),
      header.getNonce(),
      data);
    return this.payService.getConfig().getVerifier().verify(header.getSerialNo(),
      beforeSign.getBytes(StandardCharsets.UTF_8), header.getSigned());
  }
}

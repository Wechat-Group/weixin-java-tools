package com.github.binarywang.wxpay.v3.auth;

import me.chanjar.weixin.common.error.WxRuntimeException;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class CertificatesVerifier implements Verifier {
  private final HashMap<BigInteger, X509Certificate> certificates = new HashMap<>();

  public CertificatesVerifier(List<X509Certificate> list) {

    for (X509Certificate item : list) {
      certificates.put(item.getSerialNumber(), item);
    }
  }

  private boolean verify(X509Certificate certificate, byte[] message, String signature) {
    try {
      Signature sign = Signature.getInstance("SHA256withRSA");
      sign.initVerify(certificate);
      sign.update(message);
      return sign.verify(Base64.getDecoder().decode(signature));
    } catch (NoSuchAlgorithmException e) {
      throw new WxRuntimeException("当前Java环境不支持SHA256withRSA", e);
    } catch (SignatureException e) {
      throw new WxRuntimeException("签名验证过程发生了错误", e);
    } catch (InvalidKeyException e) {
      throw new WxRuntimeException("无效的证书", e);
    }
  }

  @Override
  public boolean verify(String serialNumber, byte[] message, String signature) {
    BigInteger val = new BigInteger(serialNumber, 16);
    return certificates.containsKey(val) && verify(certificates.get(val), message, signature);
  }


  @Override
  public X509Certificate getValidCertificate() {
    for (X509Certificate x509Cert : certificates.values()) {
      try {
        x509Cert.checkValidity();

        return x509Cert;
      } catch (CertificateExpiredException | CertificateNotYetValidException e) {
        continue;
      }
    }

    throw new NoSuchElementException("没有有效的微信支付平台证书");
  }

}

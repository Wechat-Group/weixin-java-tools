package com.github.binarywang.wxpay.config;

import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * 微信支付配置
 *
 * @author Binary Wang (https://github.com/binarywang)
 */
public class WxPayConfig {
  private String appId;
  private String subAppId;
  private String mchId;
  private String mchKey;
  private String subMchId;
  private String notifyUrl;
  private String tradeType;
  private SSLContext sslContext;
  private String keyPath;

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }

  public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
  }

  /**
   * 设置证书
   * @param keyPath apiclient_cert.p12的文件的绝对路径
   */
  public void setKeyPath(String keyPath) {
    this.keyPath = keyPath;
  }

  public String getKeyPath() {
    return keyPath;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public void setSubAppId(String subAppId) {
    this.subAppId = subAppId;
  }

  public void setMchId(String mchId) {
    this.mchId = mchId;
  }

  public void setMchKey(String mchKey) {
    this.mchKey = mchKey;
  }

  public void setSubMchId(String subMchId) {
    this.subMchId = subMchId;
  }

  public void setSslContext(SSLContext sslContext) {
    this.sslContext = sslContext;
  }

  /**
   * 商户号
   */
  public String getMchId() {
    return this.mchId;
  }

  /**
   * 商户密钥
   */
  public String getMchKey() {
    return this.mchKey;
  }

  /**
   * 公众号appid
   */
  public String getAppId() {
    return this.appId;
  }

  /**
   * 服务商模式下的子商户公众账号ID
   */
  public String getSubAppId() {
    return this.subAppId;
  }

  /**
   * 服务商模式下的子商户号
   */
  public String getSubMchId() {
    return this.subMchId;
  }

  /**
   * 微信支付异步回掉地址，通知url必须为直接可访问的url，不能携带参数。
   */
  public String getNotifyUrl() {
    return this.notifyUrl;
  }

  /**
   * 交易类型
   * <pre>
   * JSAPI--公众号支付
   * NATIVE--原生扫码支付
   * APP--app支付
   * </pre>
   */
  public String getTradeType() {
    return this.tradeType;
  }

  public SSLContext getSslContext() {
    return this.sslContext;
  }

  /**
   * 微信支付是否使用仿真测试环境
   * 默认不使用
   */
  public boolean useSandboxForWxPay() {
    return false;
  }

  public SSLContext initSSLContext() {
    if (null == mchId) {
      throw new IllegalArgumentException("请确保商户号mch_id已设置");
    }

    File file = new File(this.keyPath);
    if (!file.exists()) {
      throw new RuntimeException("证书文件：【" + file.getPath() + "】不存在！");
    }

    try {
      FileInputStream inputStream = new FileInputStream(file);
      KeyStore keystore = KeyStore.getInstance("PKCS12");
      char[] partnerId2charArray = mchId.toCharArray();
      keystore.load(inputStream, partnerId2charArray);
      this.sslContext = SSLContexts.custom().loadKeyMaterial(keystore, partnerId2charArray).build();
      return this.sslContext;
    } catch (Exception e) {
      throw new RuntimeException("证书文件有问题，请核实！", e);
    }
  }
}

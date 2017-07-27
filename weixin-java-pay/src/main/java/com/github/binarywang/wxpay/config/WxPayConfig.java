package com.github.binarywang.wxpay.config;

import com.github.binarywang.wxpay.exception.WxPayException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * 微信支付配置
 *
 * @author Binary Wang (https://github.com/binarywang)
 */
public class WxPayConfig {

  /**
   * http请求连接超时时间
   */
  private int httpConnectionTimeout = 5000;

  /**
   * http请求数据读取等待时间
   */
  private int httpTimeout = 10000;

  private String appId;
  private String subAppId;
  private String mchId;
  private String mchKey;
  private String subMchId;
  private String notifyUrl;
  private String tradeType;
  private SSLContext sslContext;
  private String keyPath;
  private boolean useSandboxEnv = false;

  public String getKeyPath() {
    return keyPath;
  }

  /**
   * 设置证书
   *
   * @param keyPath apiclient_cert.p12的文件的绝对路径
   */
  public void setKeyPath(String keyPath) {
    this.keyPath = keyPath;
  }

  /**
   * 商户号
   */
  public String getMchId() {
    return this.mchId;
  }

  public void setMchId(String mchId) {
    this.mchId = mchId;
  }

  /**
   * 商户密钥
   */
  public String getMchKey() {
    return this.mchKey;
  }

  public void setMchKey(String mchKey) {
    this.mchKey = mchKey;
  }

  /**
   * 公众号appid
   */
  public String getAppId() {
    return this.appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  /**
   * 服务商模式下的子商户公众账号ID
   */
  public String getSubAppId() {
    return this.subAppId;
  }

  public void setSubAppId(String subAppId) {
    this.subAppId = subAppId;
  }

  /**
   * 服务商模式下的子商户号
   */
  public String getSubMchId() {
    return this.subMchId;
  }

  public void setSubMchId(String subMchId) {
    this.subMchId = subMchId;
  }

  /**
   * 微信支付异步回掉地址，通知url必须为直接可访问的url，不能携带参数。
   */
  public String getNotifyUrl() {
    return this.notifyUrl;
  }

  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
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

  public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
  }

  public SSLContext getSslContext() {
    return this.sslContext;
  }

  public void setSslContext(SSLContext sslContext) {
    this.sslContext = sslContext;
  }

  /**
   * 微信支付是否使用仿真测试环境
   * 默认不使用
   */
  public boolean useSandbox() {
    return this.useSandboxEnv;
  }

  /**
   * 设置是否使用沙箱仿真测试环境
   */
  public void setUseSandboxEnv(boolean useSandboxEnv) {
    this.useSandboxEnv = useSandboxEnv;
  }

  public SSLContext initSSLContext() throws WxPayException {
    if (StringUtils.isBlank(mchId)) {
      throw new IllegalArgumentException("请确保商户号mchId已设置");
    }

    if (StringUtils.isBlank(this.keyPath)) {
      throw new IllegalArgumentException("请确保证书文件地址keyPath已配置");
    }

    InputStream inputStream;
    final String prefix = "classpath:";
    String fileHasProblemMsg = "证书文件【" + this.keyPath + "】有问题，请核实！";
    String fileNotFoundMsg = "证书文件【" + this.keyPath + "】不存在，请核实！";
    if (this.keyPath.startsWith(prefix)) {
      String path = StringUtils.removeFirst(this.keyPath, prefix);
      if (!path.startsWith("/")) {
        path = "/" + path;
      }
      inputStream = WxPayConfig.class.getResourceAsStream(path);
      if (inputStream == null) {
        throw new WxPayException(fileNotFoundMsg);
      }
    } else {
      try {
        File file = new File(this.keyPath);
        if (!file.exists()) {
          throw new WxPayException(fileNotFoundMsg);
        }

        inputStream = new FileInputStream(file);
      } catch (IOException e) {
        throw new WxPayException(fileHasProblemMsg, e);
      }
    }

    try {
      KeyStore keystore = KeyStore.getInstance("PKCS12");
      char[] partnerId2charArray = mchId.toCharArray();
      keystore.load(inputStream, partnerId2charArray);
      this.sslContext = SSLContexts.custom().loadKeyMaterial(keystore, partnerId2charArray).build();
      return this.sslContext;
    } catch (Exception e) {
      throw new WxPayException(fileHasProblemMsg, e);
    } finally {
      IOUtils.closeQuietly(inputStream);
    }
  }

  /**
   * http请求连接超时时间
   */
  public int getHttpConnectionTimeout() {
    return this.httpConnectionTimeout;
  }

  public void setHttpConnectionTimeout(int httpConnectionTimeout) {
    this.httpConnectionTimeout = httpConnectionTimeout;
  }

  /**
   * http请求数据读取等待时间
   */
  public int getHttpTimeout() {
    return this.httpTimeout;
  }

  public void setHttpTimeout(int httpTimeout) {
    this.httpTimeout = httpTimeout;
  }
}

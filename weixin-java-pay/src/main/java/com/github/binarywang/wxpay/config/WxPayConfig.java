package com.github.binarywang.wxpay.config;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.v3.WechatPayHttpClientBuilder;
import com.github.binarywang.wxpay.v3.auth.AutoUpdateCertificatesVerifier;
import com.github.binarywang.wxpay.v3.auth.PrivateKeySigner;
import com.github.binarywang.wxpay.v3.auth.WechatPay2Credentials;
import com.github.binarywang.wxpay.v3.auth.WechatPay2Validator;
import com.github.binarywang.wxpay.v3.util.PemUtils;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.URL;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

/**
 * 微信支付配置
 *
 * @author Binary Wang (https://github.com/binarywang)
 */
@Data
public class WxPayConfig {
  private static final String DEFAULT_PAY_BASE_URL = "https://api.mch.weixin.qq.com";

  /**
   * 微信支付接口请求地址域名部分.
   */
  private String payBaseUrl = DEFAULT_PAY_BASE_URL;

  /**
   * http请求连接超时时间.
   */
  private int httpConnectionTimeout = 5000;

  /**
   * http请求数据读取等待时间.
   */
  private int httpTimeout = 10000;

  /**
   * 公众号appid.
   */
  private String appId;
  /**
   * 服务商模式下的子商户公众账号ID.
   */
  private String subAppId;
  /**
   * 商户号.
   */
  private String mchId;
  /**
   * 商户密钥.
   */
  private String mchKey;
  /**
   * 企业支付密钥.
   */
  private String entPayKey;
  /**
   * 服务商模式下的子商户号.
   */
  private String subMchId;
  /**
   * 微信支付异步回掉地址，通知url必须为直接可访问的url，不能携带参数.
   */
  private String notifyUrl;
  /**
   * 交易类型.
   * <pre>
   * JSAPI--公众号支付
   * NATIVE--原生扫码支付
   * APP--app支付
   * </pre>
   */
  private String tradeType;
  /**
   * 签名方式.
   * 有两种HMAC_SHA256 和MD5
   *
   * @see com.github.binarywang.wxpay.constant.WxPayConstants.SignType
   */
  private String signType;
  private SSLContext sslContext;
  /**
   * p12证书文件的绝对路径或者以classpath:开头的类路径.
   */
  private String keyPath;

  /**
   * apiclient_key.pem证书文件的绝对路径或者以classpath:开头的类路径.
   */
  private String privateKeyPath;
  /**
   * apiclient_cert.pem证书文件的绝对路径或者以classpath:开头的类路径.
   */
  private String privateCertPath;

  /**
   * apiV3 秘钥值.
   */
  private String  apiv3Key;

  /**
   * apiV3 证书序列号值
   */
  private String certSerialNo;


  /**
   * 微信支付分serviceId
   */
  private String serviceId;

  /**
   * 微信支付分回调地址
   */
  private String payScoreNotifyUrl;

  private CloseableHttpClient apiv3HttpClient;


  /**
   * p12证书文件内容的字节数组.
   */
  private byte[] keyContent;
  /**
   * 微信支付是否使用仿真测试环境.
   * 默认不使用
   */
  private boolean useSandboxEnv = false;

  /**
   * 是否将接口请求日志信息保存到threadLocal中.
   * 默认不保存
   */
  private boolean ifSaveApiData = false;

  private String httpProxyHost;
  private Integer httpProxyPort;
  private String httpProxyUsername;
  private String httpProxyPassword;

  /**
   * 返回所设置的微信支付接口请求地址域名.
   * @return 微信支付接口请求地址域名
   */
  public String getPayBaseUrl() {
    if (StringUtils.isEmpty(this.payBaseUrl)) {
      return DEFAULT_PAY_BASE_URL;
    }

    return this.payBaseUrl;
  }

  /**
   * 初始化ssl.
   *
   * @return the ssl context
   * @throws WxPayException the wx pay exception
   */
  public SSLContext initSSLContext() throws WxPayException {
    if (StringUtils.isBlank(this.getMchId())) {
      throw new WxPayException("请确保商户号mchId已设置");
    }

    InputStream inputStream;
    if (this.keyContent != null) {
      inputStream = new ByteArrayInputStream(this.keyContent);
    } else {
      if (StringUtils.isBlank(this.getKeyPath())) {
        throw new WxPayException("请确保证书文件地址keyPath已配置");
      }

      final String prefix = "classpath:";
      String fileHasProblemMsg = "证书文件【" + this.getKeyPath() + "】有问题，请核实！";
      String fileNotFoundMsg = "证书文件【" + this.getKeyPath() + "】不存在，请核实！";
      if (this.getKeyPath().startsWith(prefix)) {
        String path = StringUtils.removeFirst(this.getKeyPath(), prefix);
        if (!path.startsWith("/")) {
          path = "/" + path;
        }
        inputStream = WxPayConfig.class.getResourceAsStream(path);
        if (inputStream == null) {
          throw new WxPayException(fileNotFoundMsg);
        }
      } else if (this.getKeyPath().startsWith("http://") || this.getKeyPath().startsWith("https://")) {
        try {
          inputStream = new URL(this.keyPath).openStream();
          if (inputStream == null) {
            throw new WxPayException(fileNotFoundMsg);
          }
        } catch (IOException e) {
          throw new WxPayException(fileNotFoundMsg, e);
        }
      } else {
        try {
          File file = new File(this.getKeyPath());
          if (!file.exists()) {
            throw new WxPayException(fileNotFoundMsg);
          }

          inputStream = new FileInputStream(file);
        } catch (IOException e) {
          throw new WxPayException(fileHasProblemMsg, e);
        }
      }
    }

    try {
      KeyStore keystore = KeyStore.getInstance("PKCS12");
      char[] partnerId2charArray = this.getMchId().toCharArray();
      keystore.load(inputStream, partnerId2charArray);
      this.sslContext = SSLContexts.custom().loadKeyMaterial(keystore, partnerId2charArray).build();
      return this.sslContext;
    } catch (Exception e) {
      throw new WxPayException("证书文件有问题，请核实！", e);
    } finally {
      IOUtils.closeQuietly(inputStream);
    }
  }


  /**
   * @Author doger.wang
   * @Description 初始化api v3请求头 自动签名验签
   * 方法参照微信官方https://github.com/wechatpay-apiv3/wechatpay-apache-httpclient
   * @Date  2020/5/14 10:10
   * @Param []
   * @return org.apache.http.impl.client.CloseableHttpClient
   **/
  public CloseableHttpClient initApiV3HttpClient()throws WxPayException {
    String privateKeyPath = this.getPrivateKeyPath();
    String privateCertPath = this.getPrivateCertPath();
    String certSerialNo = this.getCertSerialNo();
    String apiv3Key = this.getApiv3Key();
    if (StringUtils.isBlank(privateKeyPath)) {
      throw new WxPayException("请确保privateKeyPath已设置");
    }
    if (StringUtils.isBlank(privateCertPath)) {
      throw new WxPayException("请确保privateCertPath已设置");
    }
    if (StringUtils.isBlank(certSerialNo)) {
      throw new WxPayException("请确保certSerialNo证书序列号已设置");
    }
    if (StringUtils.isBlank(apiv3Key)) {
      throw new WxPayException("请确保apiv3Key值已设置");
    }


    InputStream keyinputStream=null;
    InputStream certinputStream=null;
    final String prefix = "classpath:";
    if (privateKeyPath.startsWith(prefix)) {
      String keypath = StringUtils.removeFirst(privateKeyPath, prefix);
      if (!keypath.startsWith("/")) {
        keypath = "/" + keypath;
      }
      keyinputStream = WxPayConfig.class.getResourceAsStream(keypath);
      if (keyinputStream == null) {
        throw new WxPayException("证书文件【" + this.getPrivateKeyPath() + "】不存在，请核实！");
      }
    }

      if (privateCertPath.startsWith(prefix)) {
        String certpath = StringUtils.removeFirst(privateCertPath, prefix);
        if (!certpath.startsWith("/")) {
          certpath = "/" + certpath;
        }
        certinputStream = WxPayConfig.class.getResourceAsStream(certpath);
        if (certinputStream == null) {
          throw new WxPayException("证书文件【" + this.getPrivateCertPath() + "】不存在，请核实！");
        }
      }
        CloseableHttpClient httpClient = null;
        try {
          WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create();
          PrivateKey merchantPrivateKey = PemUtils.loadPrivateKey(keyinputStream);
          X509Certificate x509Certificate = PemUtils.loadCertificate(certinputStream);
          ArrayList<X509Certificate> certificates = new ArrayList<>();
          certificates.add(x509Certificate);
          builder.withMerchant(mchId, certSerialNo, merchantPrivateKey);
          builder.withWechatpay(certificates);
          AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
            new WechatPay2Credentials(mchId, new PrivateKeySigner(certSerialNo, merchantPrivateKey)),
            apiv3Key.getBytes("utf-8"));
          builder.withValidator(new WechatPay2Validator(verifier));
          httpClient = builder.build();
          this.apiv3HttpClient =httpClient;
        } catch (Exception e) {
          throw new WxPayException("v3请求构造异常", e);
        }
        return httpClient;


    }
  }

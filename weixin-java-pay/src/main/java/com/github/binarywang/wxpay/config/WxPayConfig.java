package com.github.binarywang.wxpay.config;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.v3.WxPayV3HttpClientBuilder;
import com.github.binarywang.wxpay.v3.auth.*;
import com.github.binarywang.wxpay.v3.util.PemUtils;
import jodd.util.ResourcesUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Collections;

/**
 * 微信支付配置
 *
 * @author Binary Wang (https://github.com/binarywang)
 */
@Data
@EqualsAndHashCode(exclude = "verifier")
public class WxPayConfig {
  private static final String DEFAULT_PAY_BASE_URL = "https://api.mch.weixin.qq.com";
  private static final String PROBLEM_MSG = "证书文件【%s】有问题，请核实！";
  private static final String NOT_FOUND_MSG = "证书文件【%s】不存在，请核实！";

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
  private String apiV3Key;

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


  /**
   * 微信支付分授权回调地址
   */
  private String payScorePermissionNotifyUrl;


  private CloseableHttpClient apiV3HttpClient;
  /**
   * 私钥信息
   */
  private PrivateKey privateKey;

  /**
   * 证书自动更新时间差(分钟)，默认一分钟
   */
  private int certAutoUpdateTime = 60;

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
   * v3接口下证书检验对象，通过改对象可以获取到X509Certificate，进一步对敏感信息加密
   * 文档见 https://wechatpay-api.gitbook.io/wechatpay-api-v3/qian-ming-zhi-nan-1/min-gan-xin-xi-jia-mi
   */
  private Verifier verifier;

  /**
   * 返回所设置的微信支付接口请求地址域名.
   *
   * @return 微信支付接口请求地址域名
   */
  public String getPayBaseUrl() {
    if (StringUtils.isEmpty(this.payBaseUrl)) {
      return DEFAULT_PAY_BASE_URL;
    }

    return this.payBaseUrl;
  }

  @SneakyThrows
  public Verifier getVerifier() {
    if (verifier == null) {
      //当改对象为null时，初始化api v3的请求头
      initApiV3HttpClient();
    }
    return verifier;
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
      inputStream = this.loadConfigInputStream(this.getKeyPath());
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
   * 初始化api v3请求头 自动签名验签
   * 方法参照微信官方https://github.com/wechatpay-apiv3/wechatpay-apache-httpclient
   *
   * @return org.apache.http.impl.client.CloseableHttpClient
   * @author doger.wang
   **/
  public CloseableHttpClient initApiV3HttpClient() throws WxPayException {
    String privateKeyPath = this.getPrivateKeyPath();
    String privateCertPath = this.getPrivateCertPath();
    String serialNo = this.getCertSerialNo();
    String apiV3Key = this.getApiV3Key();
    if (StringUtils.isBlank(privateKeyPath)) {
      throw new WxPayException("请确保privateKeyPath已设置");
    }
    if (StringUtils.isBlank(privateCertPath)) {
      throw new WxPayException("请确保privateCertPath已设置");
    }
//    if (StringUtils.isBlank(certSerialNo)) {
//      throw new WxPayException("请确保certSerialNo证书序列号已设置");
//    }
    if (StringUtils.isBlank(apiV3Key)) {
      throw new WxPayException("请确保apiV3Key值已设置");
    }

    InputStream keyInputStream = this.loadConfigInputStream(privateKeyPath);
    InputStream certInputStream = this.loadConfigInputStream(privateCertPath);
    try {
      PrivateKey merchantPrivateKey = PemUtils.loadPrivateKey(keyInputStream);
      X509Certificate certificate = PemUtils.loadCertificate(certInputStream);
      if(StringUtils.isBlank(serialNo)){
        this.certSerialNo = certificate.getSerialNumber().toString(16).toUpperCase();
      }

      AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
        new WxPayCredentials(mchId, new PrivateKeySigner(certSerialNo, merchantPrivateKey)),
        apiV3Key.getBytes(StandardCharsets.UTF_8), this.getCertAutoUpdateTime());

      CloseableHttpClient httpClient = WxPayV3HttpClientBuilder.create()
        .withMerchant(mchId, certSerialNo, merchantPrivateKey)
        .withWechatpay(Collections.singletonList(certificate))
        .withValidator(new WxPayValidator(verifier))
        .build();
      this.apiV3HttpClient = httpClient;
      this.verifier=verifier;
      this.privateKey = merchantPrivateKey;

      return httpClient;
    } catch (Exception e) {
      throw new WxPayException("v3请求构造异常！", e);
    }
  }

  /**
   * 从配置路径 加载配置 信息（支持 classpath、本地路径、网络url）
   * @param configPath 配置路径
   * @return
   * @throws WxPayException
   */
  private InputStream loadConfigInputStream(String configPath) throws WxPayException {
    InputStream inputStream;
    final String prefix = "classpath:";
    String fileHasProblemMsg = String.format(PROBLEM_MSG, configPath);
    String fileNotFoundMsg = String.format(NOT_FOUND_MSG, configPath);
    if (configPath.startsWith(prefix)) {
      String path = RegExUtils.removeFirst(configPath, prefix);
      if (!path.startsWith("/")) {
        path = "/" + path;
      }
      try {
        inputStream = ResourcesUtil.getResourceAsStream(path);
        if (inputStream == null) {
          throw new WxPayException(fileNotFoundMsg);
        }
      } catch (Exception e) {
        throw new WxPayException(fileNotFoundMsg, e);
      }
    } else if (configPath.startsWith("http://") || configPath.startsWith("https://")) {
      try {
        inputStream = new URL(configPath).openStream();
        if (inputStream == null) {
          throw new WxPayException(fileNotFoundMsg);
        }
      } catch (IOException e) {
        throw new WxPayException(fileNotFoundMsg, e);
      }
    } else {
      try {
        File file = new File(configPath);
        if (!file.exists()) {
          throw new WxPayException(fileNotFoundMsg);
        }

        inputStream = new FileInputStream(file);
      } catch (IOException e) {
        throw new WxPayException(fileHasProblemMsg, e);
      }
    }
    return inputStream;
  }
}

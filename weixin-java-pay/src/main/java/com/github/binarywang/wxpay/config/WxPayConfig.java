package com.github.binarywang.wxpay.config;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.util.HttpProxyUtils;
import com.github.binarywang.wxpay.util.ResourcesUtils;
import com.github.binarywang.wxpay.v3.WxPayV3HttpClientBuilder;
import com.github.binarywang.wxpay.v3.auth.*;
import com.github.binarywang.wxpay.v3.util.PemUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import lombok.ToString;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Base64;

/**
 * 微信支付配置
 *
 * @author Binary Wang (<a href="https://github.com/binarywang">...</a>)
 */
@Data
@ToString(exclude = "verifier")
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
   * p12证书base64编码
   */
  private String keyString;
  /**
   * p12证书文件的绝对路径或者以classpath:开头的类路径.
   */
  private String keyPath;

  /**
   * apiclient_key.pem证书base64编码
   */
  private String privateKeyString;
  /**
   * apiclient_key.pem证书文件的绝对路径或者以classpath:开头的类路径.
   */
  private String privateKeyPath;

  /**
   * apiclient_cert.pem证书base64编码
   */
  private String privateCertString;
  /**
   * apiclient_cert.pem证书文件的绝对路径或者以classpath:开头的类路径.
   */
  private String privateCertPath;

  /**
   * apiclient_key.pem证书文件内容的字节数组.
   */
  private byte[] privateKeyContent;

  /**
   * apiclient_cert.pem证书文件内容的字节数组.
   */
  private byte[] privateCertContent;

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
   * <a href="https://wechatpay-api.gitbook.io/wechatpay-api-v3/qian-ming-zhi-nan-1/min-gan-xin-xi-jia-mi">文档</a>
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

    try (InputStream inputStream = this.loadConfigInputStream(this.keyString, this.getKeyPath(),
      this.keyContent, "p12证书");) {
      KeyStore keystore = KeyStore.getInstance("PKCS12");
      char[] partnerId2charArray = this.getMchId().toCharArray();
      keystore.load(inputStream, partnerId2charArray);
      this.sslContext = SSLContexts.custom().loadKeyMaterial(keystore, partnerId2charArray).build();
      return this.sslContext;
    } catch (Exception e) {
      throw new WxPayException("证书文件有问题，请核实！", e);
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
    if (StringUtils.isBlank(this.getApiV3Key())) {
      throw new WxPayException("请确保apiV3Key值已设置");
    }

    InputStream keyInputStream = this.loadConfigInputStream(this.getPrivateKeyString(), this.getPrivateKeyPath(),
      this.privateKeyContent, "privateKeyPath");
    try {
      PrivateKey merchantPrivateKey = PemUtils.loadPrivateKey(keyInputStream);
      if (StringUtils.isBlank(this.getCertSerialNo())) {
        InputStream certInputStream = this.loadConfigInputStream(this.getPrivateCertString(), this.getPrivateCertPath(),
          this.privateCertContent, "privateCertPath");
        X509Certificate certificate = PemUtils.loadCertificate(certInputStream);
        this.certSerialNo = certificate.getSerialNumber().toString(16).toUpperCase();
      }
      //构造Http Proxy正向代理
      WxPayHttpProxy wxPayHttpProxy = getWxPayHttpProxy();

      AutoUpdateCertificatesVerifier certificatesVerifier = new AutoUpdateCertificatesVerifier(
        new WxPayCredentials(mchId, new PrivateKeySigner(certSerialNo, merchantPrivateKey)),
        this.getApiV3Key().getBytes(StandardCharsets.UTF_8), this.getCertAutoUpdateTime(), wxPayHttpProxy);

      WxPayV3HttpClientBuilder wxPayV3HttpClientBuilder = WxPayV3HttpClientBuilder.create()
        .withMerchant(mchId, certSerialNo, merchantPrivateKey)
        .withValidator(new WxPayValidator(certificatesVerifier));
      //初始化V3接口正向代理设置
      HttpProxyUtils.initHttpProxy(wxPayV3HttpClientBuilder, wxPayHttpProxy);

      CloseableHttpClient httpClient = wxPayV3HttpClientBuilder.build();

      this.apiV3HttpClient = httpClient;
      this.verifier = certificatesVerifier;
      this.privateKey = merchantPrivateKey;

      return httpClient;
    } catch (WxPayException e) {
      throw e;
    } catch (Exception e) {
      throw new WxPayException("v3请求构造异常！", e);
    }
  }

  /**
   * 初始化一个WxPayHttpProxy对象
   *
   * @return 返回封装的WxPayHttpProxy对象。如未指定代理主机和端口，则默认返回null
   */
  private WxPayHttpProxy getWxPayHttpProxy() {
    if (StringUtils.isNotBlank(this.getHttpProxyHost()) && this.getHttpProxyPort() > 0) {
      return new WxPayHttpProxy(getHttpProxyHost(), getHttpProxyPort(), getHttpProxyUsername(), getHttpProxyPassword());
    }
    return null;
  }

  private InputStream loadConfigInputStream(String configString, String configPath, byte[] configContent,
                                            String fileName) throws WxPayException {
    InputStream inputStream;
    if (configContent != null) {
      inputStream = new ByteArrayInputStream(configContent);
    } else if (StringUtils.isNotEmpty(configString)) {
      configContent = Base64.getDecoder().decode(configString);
      inputStream = new ByteArrayInputStream(configContent);
    } else {
      if (StringUtils.isBlank(configPath)) {
        throw new WxPayException("请确保证书文件地址【" + fileName + "】或者内容已配置");
      }
      inputStream = this.loadConfigInputStream(configPath);
    }
    return inputStream;
  }


  /**
   * 从配置路径 加载配置 信息（支持 classpath、本地路径、网络url）
   *
   * @param configPath 配置路径
   * @return .
   * @throws WxPayException .
   */
  private InputStream loadConfigInputStream(String configPath) throws WxPayException {
    String fileHasProblemMsg = String.format(PROBLEM_MSG, configPath);
    String fileNotFoundMsg = String.format(NOT_FOUND_MSG, configPath);

    final String prefix = "classpath:";
    InputStream inputStream;
    if (configPath.startsWith(prefix)) {
      String path = RegExUtils.removeFirst(configPath, prefix);
      if (!path.startsWith("/")) {
        path = "/" + path;
      }

      try {
        inputStream = ResourcesUtils.getResourceAsStream(path);
        if (inputStream == null) {
          throw new WxPayException(fileNotFoundMsg);
        }

        return inputStream;
      } catch (Exception e) {
        throw new WxPayException(fileNotFoundMsg, e);
      }
    }

    if (configPath.startsWith("http://") || configPath.startsWith("https://")) {
      try {
        inputStream = new URL(configPath).openStream();
        if (inputStream == null) {
          throw new WxPayException(fileNotFoundMsg);
        }
        return inputStream;
      } catch (IOException e) {
        throw new WxPayException(fileNotFoundMsg, e);
      }
    } else {
      try {
        File file = new File(configPath);
        if (!file.exists()) {
          throw new WxPayException(fileNotFoundMsg);
        }

        return Files.newInputStream(file.toPath());
      } catch (IOException e) {
        throw new WxPayException(fileHasProblemMsg, e);
      }
    }

  }
}

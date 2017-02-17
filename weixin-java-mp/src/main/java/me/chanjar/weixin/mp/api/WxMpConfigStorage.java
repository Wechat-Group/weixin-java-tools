package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.ApacheHttpClientBuilder;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.util.concurrent.locks.Lock;

/**
 * 微信客户端配置存储
 *
 * @author chanjarster
 */
public interface WxMpConfigStorage {

  String getAccessToken();

  Lock getAccessTokenLock();

  boolean isAccessTokenExpired();

  /**
   * 强制将access token过期掉
   */
  void expireAccessToken();

  /**
   * 应该是线程安全的
   *
   * @param accessToken 要更新的WxAccessToken对象
   */
  void updateAccessToken(WxAccessToken accessToken);

  /**
   * 应该是线程安全的
   *
   * @param accessToken      新的accessToken值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateAccessToken(String accessToken, int expiresInSeconds);

  String getJsapiTicket();

  Lock getJsapiTicketLock();

  boolean isJsapiTicketExpired();

  /**
   * 强制将jsapi ticket过期掉
   */
  void expireJsapiTicket();

  /**
   * 应该是线程安全的
   *
   * @param jsapiTicket      新的jsapi ticket值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateJsapiTicket(String jsapiTicket, int expiresInSeconds);

  String getCardApiTicket();

  Lock getCardApiTicketLock();

  boolean isCardApiTicketExpired();

  /**
   * 强制将卡券api ticket过期掉
   */
  void expireCardApiTicket();

  /**
   * 应该是线程安全的
   *
   * @param cardApiTicket    新的cardApi ticket值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateCardApiTicket(String cardApiTicket, int expiresInSeconds);

  String getAppId();

  String getSecret();

  String getPartnerId();

  String getPartnerKey();

  /**
   * 微信支付异步回掉地址，通知url必须为直接可访问的url，不能携带参数。
   *
   * @since 2.5.0
   */
  String getNotifyURL();

  /**
   * 交易类型
   * <pre>
   * JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
   * </pre>
   *
   * @since 2.5.0
   */
  String getTradeType();

  String getToken();

  String getAesKey();

  long getExpiresTime();

  String getOauth2redirectUri();

  String getHttpProxyHost();

  int getHttpProxyPort();

  String getHttpProxyUsername();

  String getHttpProxyPassword();

  File getTmpDirFile();

  SSLContext getSslContext();

  void setSslContext(SSLContext sslContext);

  /**
   * 在此之前，必须将partnerId进行赋值
   *
   * @param filePath apiclient_cert.p12的文件的绝对路径
   */
  void setSslContextFilePath(String filePath) throws Exception;

  /**
   * http client builder
   *
   * @return ApacheHttpClientBuilder
   */
  ApacheHttpClientBuilder getApacheHttpClientBuilder();

  /**
   * 是否自动刷新token
   */
  boolean autoRefreshToken();

  /**
   * 微信支付是否使用仿真测试环境
   */
  boolean useSandboxForWxPay();
}

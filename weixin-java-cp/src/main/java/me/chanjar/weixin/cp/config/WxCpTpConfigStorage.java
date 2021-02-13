package me.chanjar.weixin.cp.config;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.cp.bean.WxCpProviderToken;

import java.io.File;
import java.util.concurrent.locks.Lock;

/**
 * 微信客户端（第三方应用）配置存储
 *
 * @author zhenjun cai
 */
public interface WxCpTpConfigStorage {

  /**
   * 设置企业微信服务器 baseUrl.
   * 默认值是 https://qyapi.weixin.qq.com , 如果使用默认值，则不需要调用 setBaseApiUrl
   *
   * @param baseUrl 企业微信服务器 Url
   */
  void setBaseApiUrl(String baseUrl);

  /**
   * 读取企业微信 API Url.
   * 支持私有化企业微信服务器.
   */
  String getApiUrl(String path);

  /**
   * 第三方应用的suite access token相关
   */
  String getSuiteAccessToken();
  /**
   * 获取suite_access_token和剩余过期时间
   * @return suite access token and the remaining expiration time
   */
  WxAccessToken getSuiteAccessTokenEntity();
  boolean isSuiteAccessTokenExpired();
  //强制将suite access token过期掉.
  void expireSuiteAccessToken();
  void updateSuiteAccessToken(WxAccessToken suiteAccessToken);
  void updateSuiteAccessToken(String suiteAccessToken, int expiresInSeconds);

  /**
   * 第三方应用的suite ticket相关
   */
  String getSuiteTicket();
  boolean isSuiteTicketExpired();
  //强制将suite ticket过期掉.
  void expireSuiteTicket();
  //应该是线程安全的
  void updateSuiteTicket(String suiteTicket, int expiresInSeconds);

  /**
   * 第三方应用的其他配置，来自于企微配置
   */
  String getSuiteId();
  String getSuiteSecret();
  // 第三方应用的token，用来检查应用的签名
  String getToken();
  //第三方应用的EncodingAESKey，用来检查签名
  String getAesKey();

  /**
   * 企微服务商企业ID & 企业secret
   */
  String getCorpId();
  String getCorpSecret();

  /**
   * 服务商secret
   */
  String getProviderSecret();

  /**
   * 授权企业的access token相关
   */
  String getAccessToken(String authCorpId);
  WxAccessToken getAccessTokenEntity(String authCorpId);
  boolean isAccessTokenExpired(String authCorpId);
  void updateAccessToken(String authCorpId, String accessToken, int expiredInSeconds);

  /**
   * 授权企业的js api ticket相关
   */
  String getAuthCorpJsApiTicket(String authCorpId);
  boolean isAuthCorpJsApiTicketExpired(String authCorpId);
  void expireAuthCorpJsApiTicket(String authCorpId);
  void updateAuthCorpJsApiTicket(String authCorpId, String jsApiTicket, int expiredInSeconds);

  /**
   * 授权企业的第三方应用js api ticket相关
   */
  String getAuthSuiteJsApiTicket(String authCorpId);
  boolean isAuthSuiteJsApiTicketExpired(String authCorpId);
  void expireAuthSuiteJsApiTicket(String authCorpId);
  void updateAuthSuiteJsApiTicket(String authCorpId, String jsApiTicket, int expiredInSeconds);;

  boolean isProviderTokenExpired();
  void updateProviderToken(String providerToken, int expiredInSeconds);

  String getProviderToken();
  WxCpProviderToken getProviderTokenEntity();
  // 强制过期
  void expireProviderToken();

  /**
   * 网络代理相关
   */
  String getHttpProxyHost();
  int getHttpProxyPort();
  String getHttpProxyUsername();
  String getHttpProxyPassword();
  ApacheHttpClientBuilder getApacheHttpClientBuilder();

  boolean autoRefreshToken();

  // 毫无相关性的代码
  @Deprecated
  File getTmpDirFile();

  Lock getProviderAccessTokenLock();
  Lock getSuiteAccessTokenLock();
  Lock getAccessTokenLock(String authCorpId);
  Lock getAuthCorpJsapiTicketLock(String authCorpId);
  Lock getSuiteJsapiTicketLock(String authCorpId);
}

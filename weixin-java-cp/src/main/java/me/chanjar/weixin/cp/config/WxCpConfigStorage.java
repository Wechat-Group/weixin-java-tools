package me.chanjar.weixin.cp.config;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;

import java.util.concurrent.locks.Lock;

/**
 * 微信客户端配置存储.
 *
 * @author Daniel Qian
 */
public interface WxCpConfigStorage {

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
   *
   * @param path the path
   * @return the api url
   */
  String getApiUrl(String path);

  /**
   * Gets access token.
   *
   * @return the access token
   */
  String getAccessToken();

  /**
   * Gets access token lock.
   *
   * @return the access token lock
   */
  Lock getAccessTokenLock();

  /**
   * Is access token expired boolean.
   *
   * @return the boolean
   */
  boolean isAccessTokenExpired();

  /**
   * 强制将access token过期掉.
   */
  void expireAccessToken();

  /**
   * Update access token.
   *
   * @param accessToken the access token
   */
  void updateAccessToken(WxAccessToken accessToken);

  /**
   * Update access token.
   *
   * @param accessToken the access token
   * @param expiresIn   the expires in
   */
  void updateAccessToken(String accessToken, int expiresIn);

  /**
   * Gets jsapi ticket.
   *
   * @return the jsapi ticket
   */
  String getJsapiTicket();

  /**
   * Gets jsapi ticket lock.
   *
   * @return the jsapi ticket lock
   */
  Lock getJsapiTicketLock();

  /**
   * Is jsapi ticket expired boolean.
   *
   * @return the boolean
   */
  boolean isJsapiTicketExpired();

  /**
   * 强制将jsapi ticket过期掉.
   */
  void expireJsapiTicket();

  /**
   * 应该是线程安全的.
   *
   * @param jsapiTicket      the jsapi ticket
   * @param expiresInSeconds the expires in seconds
   */
  void updateJsapiTicket(String jsapiTicket, int expiresInSeconds);

  /**
   * Gets agent jsapi ticket.
   *
   * @return the agent jsapi ticket
   */
  String getAgentJsapiTicket();

  /**
   * Gets agent jsapi ticket lock.
   *
   * @return the agent jsapi ticket lock
   */
  Lock getAgentJsapiTicketLock();

  /**
   * Is agent jsapi ticket expired boolean.
   *
   * @return the boolean
   */
  boolean isAgentJsapiTicketExpired();

  /**
   * 强制将jsapi ticket过期掉.
   */
  void expireAgentJsapiTicket();

  /**
   * 应该是线程安全的.
   *
   * @param jsapiTicket      the jsapi ticket
   * @param expiresInSeconds the expires in seconds
   */
  void updateAgentJsapiTicket(String jsapiTicket, int expiresInSeconds);

  /**
   * Gets corp id.
   *
   * @return the corp id
   */
  String getCorpId();

  /**
   * Gets corp secret.
   *
   * @return the corp secret
   */
  String getCorpSecret();

  /**
   * Gets agent id.
   *
   * @return 企业应用的id，整型。企业内部开发，可在应用的设置页面查看；第三方服务商，可通过接口 获取企业授权信息 获取该参数值
   */
  Integer getAgentId();

  /**
   * Gets token.
   *
   * @return the token
   */
  String getToken();

  /**
   * Gets aes key.
   *
   * @return the aes key
   */
  String getAesKey();

  /**
   * Gets expires time.
   *
   * @return the expires time
   */
  long getExpiresTime();

  /**
   * Gets oauth 2 redirect uri.
   *
   * @return the oauth 2 redirect uri
   */
  String getOauth2redirectUri();

  /**
   * Gets http proxy host.
   *
   * @return the http proxy host
   */
  String getHttpProxyHost();

  /**
   * Gets http proxy port.
   *
   * @return the http proxy port
   */
  int getHttpProxyPort();

  /**
   * Gets http proxy username.
   *
   * @return the http proxy username
   */
  String getHttpProxyUsername();

  /**
   * Gets http proxy password.
   *
   * @return the http proxy password
   */
  String getHttpProxyPassword();

  /**
   * http client builder.
   *
   * @return ApacheHttpClientBuilder apache http client builder
   */
  ApacheHttpClientBuilder getApacheHttpClientBuilder();

  /**
   * 是否自动刷新token
   *
   * @return . boolean
   */
  boolean autoRefreshToken();

  /**
   * 获取群机器人webhook的key
   *
   * @return key webhook key
   */
  String getWebhookKey();
}

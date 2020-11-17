package cn.binarywang.wx.miniapp.config;

import java.util.concurrent.locks.Lock;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;

/**
 * 小程序配置
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaConfig {

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

  /**
   * 卡券api_ticket.
   *
   * @return the card api ticket
   */
  String getCardApiTicket();

  /**
   * Gets card api ticket lock.
   *
   * @return the card api ticket lock
   */
  Lock getCardApiTicketLock();

  /**
   * Is card api ticket expired boolean.
   *
   * @return the boolean
   */
  boolean isCardApiTicketExpired();

  /**
   * 强制将卡券api ticket过期掉.
   */
  void expireCardApiTicket();

  /**
   * 应该是线程安全的.
   *
   * @param apiTicket        新的卡券api ticket值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateCardApiTicket(String apiTicket, int expiresInSeconds);

  /**
   * Gets appid.
   *
   * @return the appid
   */
  String getAppid();

  /**
   * Gets secret.
   *
   * @return the secret
   */
  String getSecret();

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
   * Gets original id.
   *
   * @return the original id
   */
  String getOriginalId();

  /**
   * Gets cloud env.
   *
   * @return the cloud env
   */
  String getCloudEnv();

  /**
   * Gets msg data format.
   *
   * @return the msg data format
   */
  String getMsgDataFormat();

  /**
   * Gets expires time.
   *
   * @return the expires time
   */
  long getExpiresTime();

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
   * http client builder
   *
   * @return ApacheHttpClientBuilder apache http client builder
   */
  ApacheHttpClientBuilder getApacheHttpClientBuilder();

  /**
   * 是否自动刷新token
   *
   * @return the boolean
   */
  boolean autoRefreshToken();

}

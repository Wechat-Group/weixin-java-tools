package me.chanjar.weixin.mp.api;

import java.io.File;
import java.util.concurrent.locks.Lock;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.mp.enums.TicketType;

/**
 * 微信客户端配置存储.
 *
 * @author chanjarster
 */
public interface WxMpConfigStorage {

  String getAccessToken();

  Lock getAccessTokenLock();

  boolean isAccessTokenExpired();

  /**
   * 强制将access token过期掉.
   */
  void expireAccessToken();

  /**
   * 应该是线程安全的.
   *
   * @param accessToken 要更新的WxAccessToken对象
   */
  void updateAccessToken(WxAccessToken accessToken);

  /**
   * 应该是线程安全的.
   *
   * @param accessToken      新的accessToken值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateAccessToken(String accessToken, int expiresInSeconds);

  String getTicket(TicketType type);

  Lock getTicketLock(TicketType type);

  boolean isTicketExpired(TicketType type);

  /**
   * 强制将ticket过期掉.
   */
  void expireTicket(TicketType type);

  /**
   * 更新ticket.
   * 应该是线程安全的
   *
   * @param type             ticket类型
   * @param ticket           新的ticket值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateTicket(TicketType type, String ticket, int expiresInSeconds);

  String getAppId();

  String getSecret();

  String getToken();

  String getAesKey();

  String getTemplateId();

  long getExpiresTime();

  String getOauth2redirectUri();

  String getHttpProxyHost();

  int getHttpProxyPort();

  String getHttpProxyUsername();

  String getHttpProxyPassword();

  File getTmpDirFile();

  /**
   * http client builder.
   *
   * @return ApacheHttpClientBuilder
   */
  ApacheHttpClientBuilder getApacheHttpClientBuilder();

  /**
   * 是否自动刷新token.
   */
  boolean autoRefreshToken();

}

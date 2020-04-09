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

  /**
   * 卡券api_ticket.
   */
  String getCardApiTicket();

  Lock getCardApiTicketLock();

  boolean isCardApiTicketExpired();

  /**
   * 强制将卡券api ticket过期掉.
   */
  void expireCardApiTicket();

  /**
   * 应该是线程安全的.
   *
   * @param apiTicket      新的卡券api ticket值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateCardApiTicket(String apiTicket, int expiresInSeconds);

  String getAppid();

  String getSecret();

  String getToken();

  String getAesKey();

  String getOriginalId();

  String getCloudEnv();

  String getMsgDataFormat();

  long getExpiresTime();

  String getHttpProxyHost();

  int getHttpProxyPort();

  String getHttpProxyUsername();

  String getHttpProxyPassword();

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

}

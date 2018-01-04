package me.chanjar.weixin.open.api;

import cn.binarywang.wx.miniapp.config.WxMaConfig;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.open.bean.WxOpenAuthorizerAccessToken;
import me.chanjar.weixin.open.bean.WxOpenComponentAccessToken;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public interface WxOpenConfigStorage {

  String getComponentAppId();

  void setComponentAppId(String componentAppId);

  String getComponentAppSecret();

  void setComponentAppSecret(String componentAppSecret);

  String getComponentToken();

  void setComponentToken(String componentToken);

  String getComponentAesKey();

  void setComponentAesKey(String componentAesKey);

  String getComponentVerifyTicket();

  void setComponentVerifyTicket(String componentVerifyTicket);

  String getComponentAccessToken();

  boolean isComponentAccessTokenExpired();

  void updateComponentAccessTokent(WxOpenComponentAccessToken componentAccessToken);

  WxMpConfigStorage getWxMpConfigStorage(String appId);
  WxMaConfig getWxMaConfig(String appId);

  /**
   * 应该是线程安全的
   *
   * @param componentAccessToken 新的accessToken值
   * @param expiresInSeconds     过期时间，以秒为单位
   */
  void updateComponentAccessTokent(String componentAccessToken, int expiresInSeconds);

  /**
   * 是否自动刷新token
   */
  boolean autoRefreshToken();


  String getAuthorizerRefreshToken(String appId);

  void setAuthorizerRefreshToken(String appId, String authorizerRefreshToken);

  String getAuthorizerAccessToken(String appId);


  boolean isAuthorizerAccessTokenExpired(String appId);

  /**
   * 强制将access token过期掉
   */
  void expireAuthorizerAccessToken(String appId);

  /**
   * 应该是线程安全的
   *
   * @param authorizerAccessToken 要更新的WxAccessToken对象
   */
  void updateAuthorizerAccessToken(String appId, WxOpenAuthorizerAccessToken authorizerAccessToken);

  /**
   * 应该是线程安全的
   *
   * @param authorizerAccessToken 新的accessToken值
   * @param expiresInSeconds      过期时间，以秒为单位
   */
  void updateAuthorizerAccessToken(String appId, String authorizerAccessToken, int expiresInSeconds);

  String getJsapiTicket(String appId);

  boolean isJsapiTicketExpired(String appId);

  /**
   * 强制将jsapi ticket过期掉
   */
  void expireJsapiTicket(String appId);

  /**
   * 应该是线程安全的
   *
   * @param jsapiTicket      新的jsapi ticket值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateJsapiTicket(String appId, String jsapiTicket, int expiresInSeconds);

  String getCardApiTicket(String appId);


  boolean isCardApiTicketExpired(String appId);

  /**
   * 强制将卡券api ticket过期掉
   */
  void expireCardApiTicket(String appId);

  /**
   * 应该是线程安全的
   *
   * @param cardApiTicket    新的cardApi ticket值
   * @param expiresInSeconds 过期时间，以秒为单位
   */
  void updateCardApiTicket(String appId, String cardApiTicket, int expiresInSeconds);

}

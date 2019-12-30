package me.chanjar.weixin.cp.config;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;

import java.io.File;

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
   */
  String getApiUrl(String path);

  String getAccessToken();

  boolean isAccessTokenExpired();

  /**
   * 强制将access token过期掉.
   */
  void expireAccessToken();

  void updateAccessToken(WxAccessToken accessToken);

  void updateAccessToken(String accessToken, int expiresIn);

  String getJsapiTicket();

  boolean isJsapiTicketExpired();

  /**
   * 强制将jsapi ticket过期掉.
   */
  void expireJsapiTicket();

  /**
   * 应该是线程安全的.
   */
  void updateJsapiTicket(String jsapiTicket, int expiresInSeconds);

  String getAgentJsapiTicket();

  boolean isAgentJsapiTicketExpired();

  /**
   * 强制将jsapi ticket过期掉.
   */
  void expireAgentJsapiTicket();

  /**
   * 应该是线程安全的.
   */
  void updateAgentJsapiTicket(String jsapiTicket, int expiresInSeconds);

  String getCorpId();

  String getCorpSecret();

  Integer getAgentId();

  String getToken();

  String getAesKey();

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
}

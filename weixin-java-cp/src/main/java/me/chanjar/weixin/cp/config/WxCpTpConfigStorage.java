package me.chanjar.weixin.cp.config;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;

import java.io.File;

/**
 * 微信客户端（第三方应用）配置存储
 *
 * @author zhenjun cai
 */
public interface WxCpTpConfigStorage {

  /**
   * 设置企业微信服务器 baseUrl.
   *
   * 默认值是 https://qyapi.weixin.qq.com , 如果使用默认值，则不需要调用 setBaseApiUrl
   *
   * @param baseUrl 企业微信服务器 Url
   */
  void setBaseApiUrl(String baseUrl);

  /**
   * 读取企业微信 API Url.
   *
   * 支持私有化企业微信服务器.
   */
  String getApiUrl(String path);

  String getSuiteAccessToken();

  boolean isSuiteAccessTokenExpired();

  /**
   * 强制将suite access token过期掉
   */
  void expireSuiteAccessToken();

  void updateSuiteAccessToken(WxAccessToken suiteAccessToken);

  void updateSuiteAccessToken(String suiteAccessToken, int expiresIn);

  String getSuiteTicket();

  boolean isSuiteTicketExpired();

  /**
   * 强制将suite ticket过期掉
   */
  void expireSuiteTicket();

  /**
   * 应该是线程安全的
   */
  void updateSuiteTicket(String suiteTicket, int expiresInSeconds);
  
  String getCorpId();

  String getCorpSecret();

  String getSuiteId();

  String getSuiteSecret();

  String getToken();

  String getAesKey();

  long getExpiresTime();

  String getHttpProxyHost();

  int getHttpProxyPort();

  String getHttpProxyUsername();

  String getHttpProxyPassword();

  File getTmpDirFile();

  /**
   * http client builder
   *
   * @return ApacheHttpClientBuilder
   */
  ApacheHttpClientBuilder getApacheHttpClientBuilder();
}

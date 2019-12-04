package me.chanjar.weixin.cp.robot.config;

import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;

/**
 * 微信客户端配置存储.
 *
 * @author Daniel Qian
 */
public interface WxCpRobotConfigStorage {

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

  String getRobotKey();

  String getHttpProxyHost();

  int getHttpProxyPort();

  String getHttpProxyUsername();

  String getHttpProxyPassword();

  /**
   * http client builder.
   *
   * @return ApacheHttpClientBuilder
   */
  ApacheHttpClientBuilder getApacheHttpClientBuilder();

}

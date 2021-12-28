package com.github.binarywang.wxpay.config;


import java.io.Serializable;

/**
 * 微信支付 HTTP Proxy 正向代理配置
 *
 * @author Long Yu
 * @date 2021-12-28 15:49:03
 */
public class WxPayHttpProxy implements Serializable {

  /**
   * 代理主机
   */
  private String httpProxyHost;
  /**
   * 代理端口
   */
  private Integer httpProxyPort;
  /**
   * 代理用户名称
   */
  private String httpProxyUsername;
  /**
   * 代理密码
   */
  private String httpProxyPassword;

  public WxPayHttpProxy() {
  }

  public WxPayHttpProxy(String httpProxyHost, Integer httpProxyPort, String httpProxyUsername, String httpProxyPassword) {
    this.httpProxyHost = httpProxyHost;
    this.httpProxyPort = httpProxyPort;
    this.httpProxyUsername = httpProxyUsername;
    this.httpProxyPassword = httpProxyPassword;
  }

  public String getHttpProxyHost() {
    return httpProxyHost;
  }

  public void setHttpProxyHost(String httpProxyHost) {
    this.httpProxyHost = httpProxyHost;
  }

  public Integer getHttpProxyPort() {
    return httpProxyPort;
  }

  public void setHttpProxyPort(Integer httpProxyPort) {
    this.httpProxyPort = httpProxyPort;
  }

  public String getHttpProxyUsername() {
    return httpProxyUsername;
  }

  public void setHttpProxyUsername(String httpProxyUsername) {
    this.httpProxyUsername = httpProxyUsername;
  }

  public String getHttpProxyPassword() {
    return httpProxyPassword;
  }

  public void setHttpProxyPassword(String httpProxyPassword) {
    this.httpProxyPassword = httpProxyPassword;
  }



}

package com.binarywang.spring.starter.wxjava.cp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.binarywang.spring.starter.wxjava.cp.enums.HttpClientType;
import com.binarywang.spring.starter.wxjava.cp.enums.StorageType;

import java.io.Serializable;

/**
 * 微信接入相关配置属性.
 *
 * @author xdtand
 */
@Data
@ConfigurationProperties("wx.cp")
public class WxCpProperties {

  /**
   * 设置企业微信corpid.
   */
  private String corpId;

  /**
   * 设置企业微信的secret.
   */
  private String corpSecret;

  /**
   * 设置企业微信的token.
   */
  private String token;

  /**
   * 设置企业微信的EncodingAESKey.
   */
  private String aesKey;

  /**
   * 自定义host配置
   */
  private String host;

  /**
   * 存储策略
   */
  private ConfigStorage configStorage = new ConfigStorage();

  @Data
  public static class ConfigStorage implements Serializable {
    private static final long serialVersionUID = 4815731027000065434L;

    /**
     * 存储类型.
     */
    private StorageType type = StorageType.Memory;

    /**
     * 指定key前缀.
     */
    private String keyPrefix = "wx";

    /**
     * redis连接配置.
     */
    private RedisProperties redis = new RedisProperties();

    /**
     * http客户端类型.
     */
    private HttpClientType httpClientType = HttpClientType.HttpClient;

    /**
     * http代理主机.
     */
    private String httpProxyHost;

    /**
     * http代理端口.
     */
    private Integer httpProxyPort;

    /**
     * http代理用户名.
     */
    private String httpProxyUsername;

    /**
     * http代理密码.
     */
    private String httpProxyPassword;

  }

}

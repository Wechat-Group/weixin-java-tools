package com.binarywang.spring.starter.wxjava.miniapp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * 属性配置类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-08-10
 */
@Data
@ConfigurationProperties(prefix = "wx.miniapp")
public class WxMaProperties {
  /**
   * 设置微信小程序的appid.
   */
  private String appid;

  /**
   * 设置微信小程序的Secret.
   */
  private String secret;

  /**
   * 设置微信小程序消息服务器配置的token.
   */
  private String token;

  /**
   * 设置微信小程序消息服务器配置的EncodingAESKey.
   */
  private String aesKey;

  /**
   * 消息格式，XML或者JSON.
   */
  private String msgDataFormat;

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
    private StorageType type = StorageType.memory;

    /**
     * 指定key前缀.
     */
    private String keyPrefix = "wa";

    /**
     * redis连接配置.
     */
    private RedisProperties redis;

    /**
     * http客户端类型.
     */
    private HttpClientType httpClientType = HttpClientType.httpclient;

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

  public enum StorageType {
    /**
     * 内存.
     */
    memory,
    /**
     * redis(JedisClient).
     */
    jedis,
    /**
     * redis(RedisTemplate).
     */
    redistemplate
  }

  public enum HttpClientType {
    /**
     * HttpClient.
     */
    httpclient
  }

  @Data
  public static class RedisProperties implements Serializable {
    private static final long serialVersionUID = -5924815351660074401L;

    /**
     * 主机地址.
     */
    private String host;

    /**
     * 端口号.
     */
    private int port = 6379;

    /**
     * 密码.
     */
    private String password;

    /**
     * 超时.
     */
    private int timeout = 2000;

    /**
     * 数据库.
     */
    private int database = 0;

    private Integer maxActive;
    private Integer maxIdle;
    private Integer maxWaitMillis;
    private Integer minIdle;
  }
}

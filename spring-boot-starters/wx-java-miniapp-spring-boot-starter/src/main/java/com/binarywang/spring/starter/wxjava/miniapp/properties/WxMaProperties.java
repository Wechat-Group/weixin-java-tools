package com.binarywang.spring.starter.wxjava.miniapp.properties;

import com.binarywang.spring.starter.wxjava.miniapp.enums.HttpClientType;
import com.binarywang.spring.starter.wxjava.miniapp.enums.StorageType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
  private final ConfigStorage configStorage = new ConfigStorage();

  @Data
  public static class ConfigStorage {

    /**
     * 存储类型.
     */
    private StorageType type = StorageType.Memory;

    /**
     * 指定key前缀.
     */
    private String keyPrefix = "wa";

    /**
     * redis连接配置.
     */
    private final RedisProperties redis = new RedisProperties();

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

  @Data
  public static class RedisProperties {

    /**
     * 主机地址.不填则从spring容器内获取JedisPool
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

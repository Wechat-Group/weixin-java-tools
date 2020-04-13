package com.binarywang.spring.starter.wxjava.mp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

import static com.binarywang.spring.starter.wxjava.mp.properties.WxMpProperties.PREFIX;
import static com.binarywang.spring.starter.wxjava.mp.properties.WxMpProperties.StorageType.memory;


/**
 * 微信接入相关配置属性.
 *
 * @author someone
 */
@Data
@ConfigurationProperties(PREFIX)
public class WxMpProperties {
  public static final String PREFIX = "wx.mp";

  /**
   * 设置微信公众号的appid.
   */
  private String appId;

  /**
   * 设置微信公众号的app secret.
   */
  private String secret;

  /**
   * 设置微信公众号的token.
   */
  private String token;

  /**
   * 设置微信公众号的EncodingAESKey.
   */
  private String aesKey;

  /**
   * 存储策略
   */
  private ConfigStorage configStorage = new ConfigStorage();

  @Data
  public static class ConfigStorage implements Serializable {
    private static final long serialVersionUID = 4815731027000065434L;

    /**
     * 存储类型
     */
    private StorageType type = memory;

    /**
     * 指定key前缀
     */
    private String keyPrefix = "wx";

    /**
     * redis连接配置
     */
    private RedisProperties redis = new RedisProperties();
  }

  public enum StorageType {
    /**
     * 内存
     */
    memory,
    /**
     * jedis
     */
    @Deprecated
    redis,
    /**
     * redis(Jedis Client)
     */
    jedis,
    /**
     * redis(RedisTemplate)
     */
    redis_template,
    ;
  }


  @Data
  public static class RedisProperties implements Serializable {
    private static final long serialVersionUID = -5924815351660074401L;

    /**
     * 主机地址.
     */
    private String host = "127.0.0.1";

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

package com.binarywang.spring.starter.wxjava.open.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

import static com.binarywang.spring.starter.wxjava.open.properties.WxOpenProperties.PREFIX;
import static com.binarywang.spring.starter.wxjava.open.properties.WxOpenProperties.StorageType.memory;


/**
 * 微信接入相关配置属性.
 *
 * @author someone
 */
@Data
@ConfigurationProperties(PREFIX)
public class WxOpenProperties {
  public static final String PREFIX = "wx.open";

  /**
   * 设置微信开放平台的appid.
   */
  private String appId;

  /**
   * 设置微信开放平台的app secret.
   */
  private String secret;

  /**
   * 设置微信开放平台的token.
   */
  private String token;

  /**
   * 设置微信开放平台的EncodingAESKey.
   */
  private String aesKey;

  /**
   * 存储策略, memory, redis.
   */
  private ConfigStorage configStorage = new ConfigStorage();


  @Data
  public static class ConfigStorage implements Serializable {
    private static final long serialVersionUID = 4815731027000065434L;

    private StorageType type = memory;

    private RedisProperties redis = new RedisProperties();

  }

  public enum StorageType {
    /**
     * 内存.
     */
    memory,
    /**
     * redis.
     */
    redis
  }
}

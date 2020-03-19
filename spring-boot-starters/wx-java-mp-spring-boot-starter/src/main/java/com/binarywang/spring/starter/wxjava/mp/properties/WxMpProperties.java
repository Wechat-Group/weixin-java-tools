package com.binarywang.spring.starter.wxjava.mp.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

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
   * 存储策略, memory, redis.
   */
  private final ConfigStorage configStorage = new ConfigStorage();


  @Data
  public static class ConfigStorage implements Serializable {
    private static final long serialVersionUID = 4815731027000065434L;

    private StorageType type = memory;

    @NestedConfigurationProperty
    private final RedisProperties redis = new RedisProperties();
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

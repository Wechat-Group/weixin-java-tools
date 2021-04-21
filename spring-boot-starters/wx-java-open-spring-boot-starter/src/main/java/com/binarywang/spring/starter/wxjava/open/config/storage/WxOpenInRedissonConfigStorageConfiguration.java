package com.binarywang.spring.starter.wxjava.open.config.storage;

import com.binarywang.spring.starter.wxjava.open.properties.RedisProperties;
import com.binarywang.spring.starter.wxjava.open.properties.WxOpenProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.redis.RedissonWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInRedisConfigStorage;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yl
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxOpenProperties.PREFIX + ".config-storage", name = "type", havingValue = "redisson"
)
@ConditionalOnClass({Redisson.class, RedissonClient.class})
@RequiredArgsConstructor
public class WxOpenInRedissonConfigStorageConfiguration {
  private final WxOpenProperties properties;
  private final ApplicationContext applicationContext;

  @Bean
  @ConditionalOnMissingBean(WxOpenConfigStorage.class)
  public WxOpenConfigStorage wxOpenConfigStorage() {
    WxOpenInMemoryConfigStorage config = getWxOpenInRedissonConfigStorage();

    WxOpenProperties.ConfigStorage configStorageProperties = properties.getConfigStorage();
    config.setWxOpenInfo(properties.getAppId(), properties.getSecret(), properties.getToken(), properties.getAesKey());
    config.setHttpProxyHost(configStorageProperties.getHttpProxyHost());
    config.setHttpProxyUsername(configStorageProperties.getHttpProxyUsername());
    config.setHttpProxyPassword(configStorageProperties.getHttpProxyPassword());
    if (configStorageProperties.getHttpProxyPort() != null) {
      config.setHttpProxyPort(configStorageProperties.getHttpProxyPort());
    }
    config.setRetrySleepMillis(configStorageProperties.getRetrySleepMillis());
    config.setMaxRetryTimes(configStorageProperties.getMaxRetryTimes());
    return config;
  }

  private WxOpenInRedisConfigStorage getWxOpenInRedissonConfigStorage() {
    RedisProperties redisProperties = properties.getConfigStorage().getRedis();
    RedissonClient redissonClient;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      redissonClient = getRedissonClient();
    } else {
      redissonClient = applicationContext.getBean(RedissonClient.class);
    }
    WxRedisOps redisOps = new RedissonWxRedisOps(redissonClient);
    return new WxOpenInRedisConfigStorage(redisOps, properties.getConfigStorage().getKeyPrefix());
  }

  private RedissonClient getRedissonClient() {
    WxOpenProperties.ConfigStorage storage = properties.getConfigStorage();
    RedisProperties redis = storage.getRedis();

    Config config = new Config();
    config.useSingleServer()
      .setAddress("redis://" + redis.getHost() + ":" + redis.getPort())
      .setDatabase(redis.getDatabase())
      .setPassword(redis.getPassword());
    config.setTransportMode(TransportMode.NIO);
    return Redisson.create(config);
  }
}

package com.binarywang.spring.starter.wxjava.open.config.storage;

import com.binarywang.spring.starter.wxjava.open.properties.RedisProperties;
import com.binarywang.spring.starter.wxjava.open.properties.WxOpenProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.redis.JedisWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInRedisConfigStorage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author yl
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxOpenProperties.PREFIX + ".config-storage", name = "type", havingValue = "jedis"
)
@ConditionalOnClass({JedisPool.class, JedisPoolConfig.class})
@RequiredArgsConstructor
public class WxOpenInJedisConfigStorageConfiguration {
  private final WxOpenProperties properties;
  private final ApplicationContext applicationContext;

  @Bean
  @ConditionalOnMissingBean(WxOpenConfigStorage.class)
  public WxOpenConfigStorage wxOpenConfigStorage() {
    WxOpenInMemoryConfigStorage config = getWxOpenInRedisConfigStorage();

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

  private WxOpenInRedisConfigStorage getWxOpenInRedisConfigStorage() {
    RedisProperties redisProperties = properties.getConfigStorage().getRedis();
    JedisPool jedisPool;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      jedisPool = getJedisPool();
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    WxRedisOps redisOps = new JedisWxRedisOps(jedisPool);
    return new WxOpenInRedisConfigStorage(redisOps, properties.getConfigStorage().getKeyPrefix());
  }

  private JedisPool getJedisPool() {
    WxOpenProperties.ConfigStorage storage = properties.getConfigStorage();
    RedisProperties redis = storage.getRedis();

    JedisPoolConfig config = new JedisPoolConfig();
    if (redis.getMaxActive() != null) {
      config.setMaxTotal(redis.getMaxActive());
    }
    if (redis.getMaxIdle() != null) {
      config.setMaxIdle(redis.getMaxIdle());
    }
    if (redis.getMaxWaitMillis() != null) {
      config.setMaxWaitMillis(redis.getMaxWaitMillis());
    }
    if (redis.getMinIdle() != null) {
      config.setMinIdle(redis.getMinIdle());
    }
    config.setTestOnBorrow(true);
    config.setTestWhileIdle(true);

    return new JedisPool(config, redis.getHost(), redis.getPort(),
      redis.getTimeout(), redis.getPassword(), redis.getDatabase());
  }
}

package com.binarywang.spring.starter.wxjava.open.config;

import com.binarywang.spring.starter.wxjava.open.properties.RedisProperties;
import com.binarywang.spring.starter.wxjava.open.properties.WxOpenProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.redis.JedisWxRedisOps;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
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
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 微信公众号存储策略自动配置.
 *
 * @author someone
 */
@Configuration
@RequiredArgsConstructor
public class WxOpenStorageAutoConfiguration {
  private final WxOpenProperties properties;
  private final ApplicationContext applicationContext;

  @Bean
  @ConditionalOnMissingBean(WxOpenConfigStorage.class)
  public WxOpenConfigStorage wxOpenConfigStorage() {
    WxOpenProperties.ConfigStorage storage = properties.getConfigStorage();
    WxOpenProperties.StorageType type = storage.getType();

    WxOpenInMemoryConfigStorage config;
    if (type == WxOpenProperties.StorageType.redis || type == WxOpenProperties.StorageType.jedis) {
      config = getWxOpenInRedisConfigStorage();
    } else if (type == WxOpenProperties.StorageType.redisson) {
      config = getWxOpenInRedissonConfigStorage();
    } else if (type == WxOpenProperties.StorageType.redistemplate) {
      config = getWxOpenInRedisTemplateConfigStorage();
    } else {
      config = getWxOpenInMemoryConfigStorage();
    }

    WxOpenProperties.ConfigStorage configStorageProperties = properties.getConfigStorage();
    config.setWxOpenInfo(properties.getAppId(), properties.getSecret(), properties.getToken(), properties.getAesKey());
    config.setHttpProxyHost(configStorageProperties.getHttpProxyHost());
    config.setHttpProxyUsername(configStorageProperties.getHttpProxyUsername());
    config.setHttpProxyPassword(configStorageProperties.getHttpProxyPassword());
    if (configStorageProperties.getHttpProxyPort() != null) {
      config.setHttpProxyPort(configStorageProperties.getHttpProxyPort());
    }
    return config;
  }

  private WxOpenInMemoryConfigStorage getWxOpenInMemoryConfigStorage() {
    WxOpenInMemoryConfigStorage config = new WxOpenInMemoryConfigStorage();
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
    WxOpenInRedisConfigStorage config = new WxOpenInRedisConfigStorage(redisOps, properties.getConfigStorage().getKeyPrefix());
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
    WxOpenInRedisConfigStorage config = new WxOpenInRedisConfigStorage(redisOps, properties.getConfigStorage().getKeyPrefix());
    return config;
  }

  private WxOpenInRedisConfigStorage getWxOpenInRedisTemplateConfigStorage() {
    StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    WxRedisOps redisOps = new RedisTemplateWxRedisOps(redisTemplate);
    WxOpenInRedisConfigStorage config = new WxOpenInRedisConfigStorage(redisOps, properties.getConfigStorage().getKeyPrefix());
    return config;
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

    JedisPool pool = new JedisPool(config, redis.getHost(), redis.getPort(),
      redis.getTimeout(), redis.getPassword(), redis.getDatabase());
    return pool;
  }

  private RedissonClient getRedissonClient() {
    WxOpenProperties.ConfigStorage storage = properties.getConfigStorage();
    RedisProperties redis = storage.getRedis();

    Config config = new Config();
    config.useSingleServer()
      .setAddress("redis://" + redis.getHost() + ":" + redis.getPort())
      .setPassword(redis.getPassword());
    config.setTransportMode(TransportMode.NIO);
    return Redisson.create(config);
  }
}

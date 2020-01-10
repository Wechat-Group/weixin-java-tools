package com.binarywang.spring.starter.wxjava.open.config;

import com.binarywang.spring.starter.wxjava.open.properties.RedisProperties;
import com.binarywang.spring.starter.wxjava.open.properties.WxOpenProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInRedisConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInRedissonConfigStorage;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

  @Autowired(required = false)
  private JedisPool jedisPool;

  @Autowired(required = false)
  private RedissonClient redissonClient;

  @Value("${wx.open.config-storage.redis.host:}")
  private String redisHost;

  @Bean
  @ConditionalOnMissingBean(WxOpenConfigStorage.class)
  public WxOpenConfigStorage wxOpenConfigStorage() {
    WxOpenProperties.ConfigStorage storage = properties.getConfigStorage();
    WxOpenProperties.StorageType type = storage.getType();

    if (type == WxOpenProperties.StorageType.redis) {
      return getWxOpenInRedisConfigStorage();
    }

    if (type == WxOpenProperties.StorageType.jedis){
      return getWxOpenInRedisConfigStorage();
    }

    if (type == WxOpenProperties.StorageType.redisson){
      return getWxOpenInRedissonConfigStorage();
    }
    return getWxOpenInMemoryConfigStorage();
  }

  private WxOpenInMemoryConfigStorage getWxOpenInMemoryConfigStorage() {
    WxOpenInMemoryConfigStorage config = new WxOpenInMemoryConfigStorage();
    config.setWxOpenInfo(properties.getAppId(),properties.getSecret(), properties.getToken(), properties.getAesKey());
    return config;
  }

  private WxOpenInRedisConfigStorage getWxOpenInRedisConfigStorage() {
    JedisPool poolToUse = jedisPool;
    if (jedisPool == null || StringUtils.isNotEmpty(redisHost)) {
      poolToUse = getJedisPool();
    }
    WxOpenInRedisConfigStorage config = new WxOpenInRedisConfigStorage(poolToUse);
    config.setWxOpenInfo(properties.getAppId(),properties.getSecret(), properties.getToken(), properties.getAesKey());
    return config;
  }

  private WxOpenInRedissonConfigStorage getWxOpenInRedissonConfigStorage(){
    RedissonClient redissonClientToUse = this.redissonClient;
    if(redissonClient == null){
      redissonClientToUse = getRedissonClient();
    }
    WxOpenInRedissonConfigStorage config = new WxOpenInRedissonConfigStorage(redissonClientToUse);
    config.setWxOpenInfo(properties.getAppId(),properties.getSecret(), properties.getToken(), properties.getAesKey());
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

  private RedissonClient getRedissonClient(){
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

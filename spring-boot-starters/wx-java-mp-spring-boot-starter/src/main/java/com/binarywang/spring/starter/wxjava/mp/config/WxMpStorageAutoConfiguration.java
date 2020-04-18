package com.binarywang.spring.starter.wxjava.mp.config;

import com.binarywang.spring.starter.wxjava.mp.properties.WxMpProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.redis.JedisWxRedisOps;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.mp.config.impl.WxMpRedisConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
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
public class WxMpStorageAutoConfiguration {

  private final ApplicationContext applicationContext;

  private final WxMpProperties wxMpProperties;

  @Value("${wx.mp.config-storage.redis.host:")
  private String redisHost;

  @Value("${wx.mp.configStorage.redis.host:")
  private String redisHost2;

  @Bean
  @ConditionalOnMissingBean(WxMpConfigStorage.class)
  public WxMpConfigStorage wxMpConfigStorage() {
    WxMpProperties.StorageType type = wxMpProperties.getConfigStorage().getType();
    WxMpConfigStorage config;
    if (type == WxMpProperties.StorageType.redis || type == WxMpProperties.StorageType.jedis) {
      config = wxMpInJedisConfigStorage();
    } else if (type == WxMpProperties.StorageType.redistemplate) {
      config = wxMpInRedisTemplateConfigStorage();
    } else {
      config = wxMpInMemoryConfigStorage();
    }
    return config;
  }

  private WxMpConfigStorage wxMpInMemoryConfigStorage() {
    WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
    setWxMpInfo(config);
    return config;
  }

  private WxMpConfigStorage wxMpInJedisConfigStorage() {
    JedisPool jedisPool;
    if (StringUtils.isNotEmpty(redisHost) || StringUtils.isNotEmpty(redisHost2)) {
      jedisPool = getJedisPool();
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    WxRedisOps redisOps = new JedisWxRedisOps(jedisPool);
    WxMpRedisConfigImpl wxMpRedisConfig = new WxMpRedisConfigImpl(redisOps, wxMpProperties.getConfigStorage().getKeyPrefix());
    setWxMpInfo(wxMpRedisConfig);
    return wxMpRedisConfig;
  }

  private WxMpConfigStorage wxMpInRedisTemplateConfigStorage() {
    StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    WxRedisOps redisOps = new RedisTemplateWxRedisOps(redisTemplate);
    WxMpRedisConfigImpl wxMpRedisConfig = new WxMpRedisConfigImpl(redisOps, wxMpProperties.getConfigStorage().getKeyPrefix());
    setWxMpInfo(wxMpRedisConfig);
    return wxMpRedisConfig;
  }

  private void setWxMpInfo(WxMpDefaultConfigImpl config) {
    WxMpProperties properties = wxMpProperties;
    WxMpProperties.ConfigStorage configStorageProperties = properties.getConfigStorage();
    config.setAppId(properties.getAppId());
    config.setSecret(properties.getSecret());
    config.setToken(properties.getToken());
    config.setAesKey(properties.getAesKey());

    config.setHttpProxyHost(configStorageProperties.getHttpProxyHost());
    config.setHttpProxyUsername(configStorageProperties.getHttpProxyUsername());
    config.setHttpProxyPassword(configStorageProperties.getHttpProxyPassword());
    if (configStorageProperties.getHttpProxyPort() != null) {
      config.setHttpProxyPort(configStorageProperties.getHttpProxyPort());
    }
  }

  private JedisPool getJedisPool() {
    WxMpProperties.ConfigStorage storage = wxMpProperties.getConfigStorage();
    WxMpProperties.RedisProperties redis = storage.getRedis();

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

    return new JedisPool(config, redis.getHost(), redis.getPort(), redis.getTimeout(), redis.getPassword(),
      redis.getDatabase());
  }
}

package com.binarywang.spring.starter.wxjava.mp.configuration.services;

import com.binarywang.spring.starter.wxjava.mp.properties.WxMpMultiProperties;
import com.binarywang.spring.starter.wxjava.mp.properties.WxMpMultiRedisProperties;
import com.binarywang.spring.starter.wxjava.mp.service.WxMpMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.redis.JedisWxRedisOps;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.mp.config.impl.WxMpRedisConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 自动装配基于 jedis 策略配置
 *
 * @author yl
 * created on 2024/1/23
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxMpMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "jedis"
)
@RequiredArgsConstructor
public class WxMpInJedisConfiguration extends AbstractWxMpConfiguration {
  private final WxMpMultiProperties wxMpMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxMpMultiServices wxMpMultiServices() {
    return this.wxMpMultiServices(wxMpMultiProperties);
  }

  @Override
  protected WxMpDefaultConfigImpl wxMpConfigStorage(WxMpMultiProperties wxMpMultiProperties) {
    return this.configRedis(wxMpMultiProperties);
  }

  private WxMpDefaultConfigImpl configRedis(WxMpMultiProperties wxMpMultiProperties) {
    WxMpMultiRedisProperties wxMpMultiRedisProperties = wxMpMultiProperties.getConfigStorage().getRedis();
    JedisPool jedisPool;
    if (wxMpMultiRedisProperties != null && StringUtils.isNotEmpty(wxMpMultiRedisProperties.getHost())) {
      jedisPool = getJedisPool(wxMpMultiProperties);
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    return new WxMpRedisConfigImpl(new JedisWxRedisOps(jedisPool), wxMpMultiProperties.getConfigStorage().getKeyPrefix());
  }

  private JedisPool getJedisPool(WxMpMultiProperties wxMpMultiProperties) {
    WxMpMultiProperties.ConfigStorage storage = wxMpMultiProperties.getConfigStorage();
    WxMpMultiRedisProperties redis = storage.getRedis();

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

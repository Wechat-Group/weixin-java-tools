package com.binarywang.spring.starter.wxjava.miniapp.configuration.services;

import cn.binarywang.wx.miniapp.config.impl.WxMaRedisConfigImpl;
import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaMultiProperties;
import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaMultiRedisProperties;
import com.binarywang.spring.starter.wxjava.miniapp.service.WxMaMultiServices;
import lombok.RequiredArgsConstructor;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
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
 * @author monch
 * created on 2024/9/6
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxMaMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "jedis"
)
@RequiredArgsConstructor
public class WxMaInJedisConfiguration extends AbstractWxMaConfiguration {
  private final WxMaMultiProperties wxMaMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxMaMultiServices wxMaMultiServices() {
    return this.wxMaMultiServices(wxMaMultiProperties);
  }

  @Override
  protected WxMaDefaultConfigImpl wxMaConfigStorage(WxMaMultiProperties wxMaMultiProperties) {
    return this.configRedis(wxMaMultiProperties);
  }

  private WxMaDefaultConfigImpl configRedis(WxMaMultiProperties wxMaMultiProperties) {
    WxMaMultiRedisProperties wxMaMultiRedisProperties = wxMaMultiProperties.getConfigStorage().getRedis();
    JedisPool jedisPool;
    if (wxMaMultiRedisProperties != null && StringUtils.isNotEmpty(wxMaMultiRedisProperties.getHost())) {
      jedisPool = getJedisPool(wxMaMultiProperties);
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    return new WxMaRedisConfigImpl(jedisPool);
  }

  private JedisPool getJedisPool(WxMaMultiProperties wxMaMultiProperties) {
    WxMaMultiProperties.ConfigStorage storage = wxMaMultiProperties.getConfigStorage();
    WxMaMultiRedisProperties redis = storage.getRedis();

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

package com.binarywang.spring.starter.wxjava.cp.autoconfigure.services;

import com.binarywang.spring.starter.wxjava.cp.properties.WxCpMultiProperties;
import com.binarywang.spring.starter.wxjava.cp.properties.WxCpMultiRedisProperties;
import com.binarywang.spring.starter.wxjava.cp.service.WxCpMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import me.chanjar.weixin.cp.config.impl.WxCpJedisConfigImpl;
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
 * created on 2023/10/16
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxCpMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "jedis"
)
@RequiredArgsConstructor
public class WxCpInJedisConfiguration extends AbstractWxCpConfiguration {
  private final WxCpMultiProperties wxCpMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxCpMultiServices wxCpMultiServices() {
    return this.configWxCpServices(wxCpMultiProperties);
  }

  @Override
  protected WxCpDefaultConfigImpl configWxCpDefaultConfigImpl(WxCpMultiProperties wxCpMultiProperties) {
    return this.configRedis(wxCpMultiProperties);
  }

  private WxCpDefaultConfigImpl configRedis(WxCpMultiProperties wxCpMultiProperties) {
    WxCpMultiRedisProperties wxCpMultiRedisProperties = wxCpMultiProperties.getConfigStorage().getRedis();
    JedisPool jedisPool;
    if (wxCpMultiRedisProperties != null && StringUtils.isNotEmpty(wxCpMultiRedisProperties.getHost())) {
      jedisPool = getJedisPool(wxCpMultiProperties);
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    return new WxCpJedisConfigImpl(jedisPool, wxCpMultiProperties.getConfigStorage().getKeyPrefix());
  }

  private JedisPool getJedisPool(WxCpMultiProperties wxCpMultiProperties) {
    WxCpMultiProperties.ConfigStorage storage = wxCpMultiProperties.getConfigStorage();
    WxCpMultiRedisProperties redis = storage.getRedis();

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

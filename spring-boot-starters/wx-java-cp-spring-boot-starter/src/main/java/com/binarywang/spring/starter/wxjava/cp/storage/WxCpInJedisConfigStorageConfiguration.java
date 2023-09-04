package com.binarywang.spring.starter.wxjava.cp.storage;

import com.binarywang.spring.starter.wxjava.cp.properties.WxCpProperties;
import com.binarywang.spring.starter.wxjava.cp.properties.WxCpRedisProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import me.chanjar.weixin.cp.config.impl.WxCpJedisConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
 * created on  2023/04/23
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxCpProperties.PREFIX + ".config-storage", name = "type", havingValue = "jedis"
)
@RequiredArgsConstructor
public class WxCpInJedisConfigStorageConfiguration extends AbstractWxCpConfigStorageConfiguration {
  private final WxCpProperties wxCpProperties;
  private final ApplicationContext applicationContext;

  @Bean
  @ConditionalOnMissingBean(WxCpConfigStorage.class)
  public WxCpConfigStorage wxOpenConfigStorage() {
    WxCpDefaultConfigImpl config = getConfigStorage();
    return this.config(config, wxCpProperties);
  }

  private WxCpJedisConfigImpl getConfigStorage() {
    WxCpRedisProperties wxCpRedisProperties = wxCpProperties.getConfigStorage().getRedis();
    JedisPool jedisPool;
    if (wxCpRedisProperties != null && StringUtils.isNotEmpty(wxCpRedisProperties.getHost())) {
      jedisPool = getJedisPool();
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    return new WxCpJedisConfigImpl(jedisPool, wxCpProperties.getConfigStorage().getKeyPrefix());
  }

  private JedisPool getJedisPool() {
    WxCpProperties.ConfigStorage storage = wxCpProperties.getConfigStorage();
    WxCpRedisProperties redis = storage.getRedis();

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

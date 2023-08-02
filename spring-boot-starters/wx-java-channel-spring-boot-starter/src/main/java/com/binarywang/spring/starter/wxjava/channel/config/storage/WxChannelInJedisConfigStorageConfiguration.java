package com.binarywang.spring.starter.wxjava.channel.config.storage;


import com.binarywang.spring.starter.wxjava.channel.properties.RedisProperties;
import com.binarywang.spring.starter.wxjava.channel.properties.WxChannelProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.channel.config.WxChannelConfig;
import me.chanjar.weixin.channel.config.impl.WxChannelRedisConfigImpl;
import me.chanjar.weixin.common.redis.JedisWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
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
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Configuration
@ConditionalOnProperty(prefix = WxChannelProperties.PREFIX + ".config-storage", name = "type", havingValue = "jedis")
@ConditionalOnClass({JedisPool.class, JedisPoolConfig.class})
@RequiredArgsConstructor
public class WxChannelInJedisConfigStorageConfiguration extends AbstractWxChannelConfigStorageConfiguration {
  private final WxChannelProperties properties;
  private final ApplicationContext applicationContext;

  @Bean
  @ConditionalOnMissingBean(WxChannelConfig.class)
  public WxChannelConfig wxChannelConfig() {
    WxChannelRedisConfigImpl config = getWxChannelRedisConfig();
    return this.config(config, properties);
  }

  private WxChannelRedisConfigImpl getWxChannelRedisConfig() {
    RedisProperties redisProperties = properties.getConfigStorage().getRedis();
    JedisPool jedisPool;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      jedisPool = getJedisPool();
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    WxRedisOps redisOps = new JedisWxRedisOps(jedisPool);
    return new WxChannelRedisConfigImpl(redisOps, properties.getConfigStorage().getKeyPrefix());
  }

  private JedisPool getJedisPool() {
    WxChannelProperties.ConfigStorage storage = properties.getConfigStorage();
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

    return new JedisPool(config, redis.getHost(), redis.getPort(), redis.getTimeout(), redis.getPassword(), redis.getDatabase());
  }
}

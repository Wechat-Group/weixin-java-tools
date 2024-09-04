package com.binarywang.solon.wxjava.open.config.storage;

import com.binarywang.solon.wxjava.open.properties.WxOpenProperties;
import com.binarywang.solon.wxjava.open.properties.WxOpenRedisProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInRedisConfigStorage;
import org.apache.commons.lang3.StringUtils;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.core.AppContext;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author yl
 */
@Configuration
@Condition(
  onProperty = "${"+WxOpenProperties.PREFIX + ".configStorage.type} = jedis",
  onClass = JedisPool.class
)
@RequiredArgsConstructor
public class WxOpenInJedisConfigStorageConfiguration extends AbstractWxOpenConfigStorageConfiguration {
  private final WxOpenProperties properties;
  private final AppContext applicationContext;

  @Bean
  @Condition(onMissingBean=WxOpenConfigStorage.class)
  public WxOpenConfigStorage wxOpenConfigStorage() {
    WxOpenInMemoryConfigStorage config = getWxOpenInRedisConfigStorage();
    return this.config(config, properties);
  }

  private WxOpenInRedisConfigStorage getWxOpenInRedisConfigStorage() {
    WxOpenRedisProperties wxOpenRedisProperties = properties.getConfigStorage().getRedis();
    JedisPool jedisPool;
    if (wxOpenRedisProperties != null && StringUtils.isNotEmpty(wxOpenRedisProperties.getHost())) {
      jedisPool = getJedisPool();
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    return new WxOpenInRedisConfigStorage(jedisPool, properties.getConfigStorage().getKeyPrefix());
  }

  private JedisPool getJedisPool() {
    WxOpenProperties.ConfigStorage storage = properties.getConfigStorage();
    WxOpenRedisProperties redis = storage.getRedis();

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

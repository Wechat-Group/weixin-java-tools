package com.binarywang.solon.wxjava.mp_multi.configuration.services;

import com.binarywang.solon.wxjava.mp_multi.properties.WxMpMultiProperties;
import com.binarywang.solon.wxjava.mp_multi.properties.WxMpMultiRedisProperties;
import com.binarywang.solon.wxjava.mp_multi.service.WxMpMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.redis.JedisWxRedisOps;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.mp.config.impl.WxMpRedisConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.core.AppContext;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 自动装配基于 jedis 策略配置
 *
 * @author yl
 * created on 2024/1/23
 */
@Configuration
@Condition(
  onProperty = "${"+WxMpMultiProperties.PREFIX + ".configStorage.type} = jedis",
  onClass = JedisPool.class
)
@RequiredArgsConstructor
public class WxMpInJedisConfiguration extends AbstractWxMpConfiguration {
  private final WxMpMultiProperties wxCpMultiProperties;
  private final AppContext applicationContext;

  @Bean
  public WxMpMultiServices wxMpMultiServices() {
    return this.wxMpMultiServices(wxCpMultiProperties);
  }

  @Override
  protected WxMpDefaultConfigImpl wxMpConfigStorage(WxMpMultiProperties wxCpMultiProperties) {
    return this.configRedis(wxCpMultiProperties);
  }

  private WxMpDefaultConfigImpl configRedis(WxMpMultiProperties wxCpMultiProperties) {
    WxMpMultiRedisProperties wxCpMultiRedisProperties = wxCpMultiProperties.getConfigStorage().getRedis();
    JedisPool jedisPool;
    if (wxCpMultiRedisProperties != null && StringUtils.isNotEmpty(wxCpMultiRedisProperties.getHost())) {
      jedisPool = getJedisPool(wxCpMultiProperties);
    } else {
      jedisPool = applicationContext.getBean(JedisPool.class);
    }
    return new WxMpRedisConfigImpl(new JedisWxRedisOps(jedisPool), wxCpMultiProperties.getConfigStorage().getKeyPrefix());
  }

  private JedisPool getJedisPool(WxMpMultiProperties wxCpMultiProperties) {
    WxMpMultiProperties.ConfigStorage storage = wxCpMultiProperties.getConfigStorage();
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

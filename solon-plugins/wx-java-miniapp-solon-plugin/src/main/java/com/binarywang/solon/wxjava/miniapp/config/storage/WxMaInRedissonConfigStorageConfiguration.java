package com.binarywang.solon.wxjava.miniapp.config.storage;

import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaRedissonConfigImpl;
import com.binarywang.solon.wxjava.miniapp.properties.RedisProperties;
import com.binarywang.solon.wxjava.miniapp.properties.WxMaProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.core.AppContext;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

/**
 * @author yl TaoYu
 */
@Configuration
@Condition(
  onProperty = "${"+WxMaProperties.PREFIX + ".configStorage.type} = redisson",
  onClass = Redisson.class
)
@RequiredArgsConstructor
public class WxMaInRedissonConfigStorageConfiguration extends AbstractWxMaConfigStorageConfiguration {
  private final WxMaProperties properties;
  private final AppContext applicationContext;

  @Bean
  @Condition(onMissingBean=WxMaConfig.class)
  public WxMaConfig wxMaConfig() {
    WxMaRedissonConfigImpl config = getWxMaInRedissonConfigStorage();
    return this.config(config, properties);
  }

  private WxMaRedissonConfigImpl getWxMaInRedissonConfigStorage() {
    RedisProperties redisProperties = properties.getConfigStorage().getRedis();
    RedissonClient redissonClient;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      redissonClient = getRedissonClient();
    } else {
      redissonClient = applicationContext.getBean(RedissonClient.class);
    }
    return new WxMaRedissonConfigImpl(redissonClient, properties.getConfigStorage().getKeyPrefix());
  }

  private RedissonClient getRedissonClient() {
    WxMaProperties.ConfigStorage storage = properties.getConfigStorage();
    RedisProperties redis = storage.getRedis();

    Config config = new Config();
    config.useSingleServer()
      .setAddress("redis://" + redis.getHost() + ":" + redis.getPort())
      .setDatabase(redis.getDatabase())
      .setPassword(redis.getPassword());
    config.setTransportMode(TransportMode.NIO);
    return Redisson.create(config);
  }
}

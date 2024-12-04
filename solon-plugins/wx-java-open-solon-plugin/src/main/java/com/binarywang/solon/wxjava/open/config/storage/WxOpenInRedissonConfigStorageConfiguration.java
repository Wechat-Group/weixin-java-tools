package com.binarywang.solon.wxjava.open.config.storage;

import com.binarywang.solon.wxjava.open.properties.WxOpenProperties;
import com.binarywang.solon.wxjava.open.properties.WxOpenRedisProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInRedissonConfigStorage;
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
 * @author yl
 */
@Configuration
@Condition(
  onProperty = "${"+WxOpenProperties.PREFIX + ".configStorage.type} = redisson",
  onClass = Redisson.class
)
@RequiredArgsConstructor
public class WxOpenInRedissonConfigStorageConfiguration extends AbstractWxOpenConfigStorageConfiguration {
  private final WxOpenProperties properties;
  private final AppContext applicationContext;

  @Bean
  @Condition(onMissingBean=WxOpenConfigStorage.class)
  public WxOpenConfigStorage wxOpenConfigStorage() {
    WxOpenInMemoryConfigStorage config = getWxOpenInRedissonConfigStorage();
    return this.config(config, properties);
  }

  private WxOpenInRedissonConfigStorage getWxOpenInRedissonConfigStorage() {
    WxOpenRedisProperties wxOpenRedisProperties = properties.getConfigStorage().getRedis();
    RedissonClient redissonClient;
    if (wxOpenRedisProperties != null && StringUtils.isNotEmpty(wxOpenRedisProperties.getHost())) {
      redissonClient = getRedissonClient();
    } else {
      redissonClient = applicationContext.getBean(RedissonClient.class);
    }
    return new WxOpenInRedissonConfigStorage(redissonClient, properties.getConfigStorage().getKeyPrefix());
  }

  private RedissonClient getRedissonClient() {
    WxOpenProperties.ConfigStorage storage = properties.getConfigStorage();
    WxOpenRedisProperties redis = storage.getRedis();

    Config config = new Config();
    config.useSingleServer()
      .setAddress("redis://" + redis.getHost() + ":" + redis.getPort())
      .setDatabase(redis.getDatabase())
      .setPassword(redis.getPassword());
    config.setTransportMode(TransportMode.NIO);
    return Redisson.create(config);
  }
}

package com.binarywang.solon.wxjava.channel.config.storage;


import com.binarywang.solon.wxjava.channel.properties.RedisProperties;
import com.binarywang.solon.wxjava.channel.properties.WxChannelProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.channel.config.WxChannelConfig;
import me.chanjar.weixin.channel.config.impl.WxChannelRedissonConfigImpl;
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
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Configuration
@Condition(
  onProperty = "${"+WxChannelProperties.PREFIX + ".configStorage.type} = redisson",
  onClass = Redisson.class
)
@RequiredArgsConstructor
public class WxChannelInRedissonConfigStorageConfiguration extends AbstractWxChannelConfigStorageConfiguration {
  private final WxChannelProperties properties;
  private final AppContext applicationContext;

  @Bean
  @Condition(onMissingBean=WxChannelConfig.class)
  public WxChannelConfig wxChannelConfig() {
    WxChannelRedissonConfigImpl config = getWxChannelRedissonConfig();
    return this.config(config, properties);
  }

  private WxChannelRedissonConfigImpl getWxChannelRedissonConfig() {
    RedisProperties redisProperties = properties.getConfigStorage().getRedis();
    RedissonClient redissonClient;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      redissonClient = getRedissonClient();
    } else {
      redissonClient = applicationContext.getBean(RedissonClient.class);
    }
    return new WxChannelRedissonConfigImpl(redissonClient, properties.getConfigStorage().getKeyPrefix());
  }

  private RedissonClient getRedissonClient() {
    WxChannelProperties.ConfigStorage storage = properties.getConfigStorage();
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

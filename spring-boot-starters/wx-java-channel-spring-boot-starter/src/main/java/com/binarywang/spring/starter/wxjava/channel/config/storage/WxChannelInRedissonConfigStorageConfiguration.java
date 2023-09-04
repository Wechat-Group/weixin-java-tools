package com.binarywang.spring.starter.wxjava.channel.config.storage;


import com.binarywang.spring.starter.wxjava.channel.properties.RedisProperties;
import com.binarywang.spring.starter.wxjava.channel.properties.WxChannelProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.channel.config.WxChannelConfig;
import me.chanjar.weixin.channel.config.impl.WxChannelRedissonConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Configuration
@ConditionalOnProperty(prefix = WxChannelProperties.PREFIX + ".config-storage", name = "type", havingValue = "redisson")
@ConditionalOnClass({Redisson.class, RedissonClient.class})
@RequiredArgsConstructor
public class WxChannelInRedissonConfigStorageConfiguration extends AbstractWxChannelConfigStorageConfiguration {
  private final WxChannelProperties properties;
  private final ApplicationContext applicationContext;

  @Bean
  @ConditionalOnMissingBean(WxChannelConfig.class)
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

package com.binarywang.spring.starter.wxjava.cp.storage;

import com.binarywang.spring.starter.wxjava.cp.properties.WxCpProperties;
import com.binarywang.spring.starter.wxjava.cp.properties.WxCpRedisProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import me.chanjar.weixin.cp.config.impl.WxCpRedissonConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配基于 redisson 策略配置
 *
 * @author yl
 * created on  2023/04/23
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxCpProperties.PREFIX + ".config-storage", name = "type", havingValue = "redisson"
)
@RequiredArgsConstructor
public class WxCpInRedissonConfigStorageConfiguration extends AbstractWxCpConfigStorageConfiguration {
  private final WxCpProperties wxCpProperties;
  private final ApplicationContext applicationContext;

  @Bean
  @ConditionalOnMissingBean(WxCpConfigStorage.class)
  public WxCpConfigStorage wxOpenConfigStorage() {
    WxCpDefaultConfigImpl config = getConfigStorage();
    return this.config(config, wxCpProperties);
  }

  private WxCpRedissonConfigImpl getConfigStorage() {
    WxCpRedisProperties redisProperties = wxCpProperties.getConfigStorage().getRedis();
    RedissonClient redissonClient;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      redissonClient = getRedissonClient();
    } else {
      redissonClient = applicationContext.getBean(RedissonClient.class);
    }
    return new WxCpRedissonConfigImpl(redissonClient, wxCpProperties.getConfigStorage().getKeyPrefix());
  }

  private RedissonClient getRedissonClient() {
    WxCpProperties.ConfigStorage storage = wxCpProperties.getConfigStorage();
    WxCpRedisProperties redis = storage.getRedis();

    Config config = new Config();
    config.useSingleServer()
      .setAddress("redis://" + redis.getHost() + ":" + redis.getPort())
      .setDatabase(redis.getDatabase())
      .setPassword(redis.getPassword());
    config.setTransportMode(TransportMode.NIO);
    return Redisson.create(config);
  }
}

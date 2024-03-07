package com.binarywang.spring.starter.wxjava.mp.configuration.services;

import com.binarywang.spring.starter.wxjava.mp.properties.WxMpMultiProperties;
import com.binarywang.spring.starter.wxjava.mp.properties.WxMpMultiRedisProperties;
import com.binarywang.spring.starter.wxjava.mp.service.WxMpMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.mp.config.impl.WxMpRedissonConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配基于 redisson 策略配置
 *
 * @author yl
 * created on 2024/1/23
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxMpMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "redisson"
)
@RequiredArgsConstructor
public class WxMpInRedissonConfiguration extends AbstractWxMpConfiguration {
  private final WxMpMultiProperties wxCpMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxMpMultiServices wxMpMultiServices() {
    return this.wxMpMultiServices(wxCpMultiProperties);
  }

  @Override
  protected WxMpDefaultConfigImpl wxMpConfigStorage(WxMpMultiProperties wxCpMultiProperties) {
    return this.configRedisson(wxCpMultiProperties);
  }

  private WxMpDefaultConfigImpl configRedisson(WxMpMultiProperties wxCpMultiProperties) {
    WxMpMultiRedisProperties redisProperties = wxCpMultiProperties.getConfigStorage().getRedis();
    RedissonClient redissonClient;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      redissonClient = getRedissonClient(wxCpMultiProperties);
    } else {
      redissonClient = applicationContext.getBean(RedissonClient.class);
    }
    return new WxMpRedissonConfigImpl(redissonClient, wxCpMultiProperties.getConfigStorage().getKeyPrefix());
  }

  private RedissonClient getRedissonClient(WxMpMultiProperties wxCpMultiProperties) {
    WxMpMultiProperties.ConfigStorage storage = wxCpMultiProperties.getConfigStorage();
    WxMpMultiRedisProperties redis = storage.getRedis();

    Config config = new Config();
    config.useSingleServer()
      .setAddress("redis://" + redis.getHost() + ":" + redis.getPort())
      .setDatabase(redis.getDatabase())
      .setPassword(redis.getPassword());
    config.setTransportMode(TransportMode.NIO);
    return Redisson.create(config);
  }
}

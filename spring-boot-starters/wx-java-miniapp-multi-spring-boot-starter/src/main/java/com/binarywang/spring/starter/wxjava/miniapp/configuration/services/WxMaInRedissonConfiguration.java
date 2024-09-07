package com.binarywang.spring.starter.wxjava.miniapp.configuration.services;

import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaRedissonConfigImpl;
import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaMultiProperties;
import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaMultiRedisProperties;
import com.binarywang.spring.starter.wxjava.miniapp.service.WxMaMultiServices;
import lombok.RequiredArgsConstructor;
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
 * @author monch
 * created on 2024/9/6
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxMaMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "redisson"
)
@RequiredArgsConstructor
public class WxMaInRedissonConfiguration extends AbstractWxMaConfiguration {
  private final WxMaMultiProperties wxMaMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxMaMultiServices wxMaMultiServices() {
    return this.wxMaMultiServices(wxMaMultiProperties);
  }

  @Override
  protected WxMaDefaultConfigImpl wxMaConfigStorage(WxMaMultiProperties wxMaMultiProperties) {
    return this.configRedisson(wxMaMultiProperties);
  }

  private WxMaDefaultConfigImpl configRedisson(WxMaMultiProperties wxMaMultiProperties) {
    WxMaMultiRedisProperties redisProperties = wxMaMultiProperties.getConfigStorage().getRedis();
    RedissonClient redissonClient;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      redissonClient = getRedissonClient(wxMaMultiProperties);
    } else {
      redissonClient = applicationContext.getBean(RedissonClient.class);
    }
    return new WxMaRedissonConfigImpl(redissonClient, wxMaMultiProperties.getConfigStorage().getKeyPrefix());
  }

  private RedissonClient getRedissonClient(WxMaMultiProperties wxMaMultiProperties) {
    WxMaMultiProperties.ConfigStorage storage = wxMaMultiProperties.getConfigStorage();
    WxMaMultiRedisProperties redis = storage.getRedis();

    Config config = new Config();
    config.useSingleServer()
      .setAddress("redis://" + redis.getHost() + ":" + redis.getPort())
      .setDatabase(redis.getDatabase())
      .setPassword(redis.getPassword());
    config.setTransportMode(TransportMode.NIO);
    return Redisson.create(config);
  }
}

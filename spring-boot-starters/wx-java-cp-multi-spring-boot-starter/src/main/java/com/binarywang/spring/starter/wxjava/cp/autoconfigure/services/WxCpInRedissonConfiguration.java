package com.binarywang.spring.starter.wxjava.cp.autoconfigure.services;

import com.binarywang.spring.starter.wxjava.cp.properties.WxCpMultiProperties;
import com.binarywang.spring.starter.wxjava.cp.properties.WxCpMultiRedisProperties;
import com.binarywang.spring.starter.wxjava.cp.service.WxCpMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import me.chanjar.weixin.cp.config.impl.WxCpRedissonConfigImpl;
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
 * created on 2023/10/16
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxCpMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "redisson"
)
@RequiredArgsConstructor
public class WxCpInRedissonConfiguration extends AbstractWxCpConfiguration {
  private final WxCpMultiProperties wxCpMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxCpMultiServices wxCpServices() {
    return this.configWxCpServices(wxCpMultiProperties);
  }

  @Override
  protected WxCpDefaultConfigImpl configWxCpDefaultConfigImpl(WxCpMultiProperties wxCpMultiProperties) {
    return this.configRedisson(wxCpMultiProperties);
  }

  private WxCpDefaultConfigImpl configRedisson(WxCpMultiProperties wxCpMultiProperties) {
    WxCpMultiRedisProperties redisProperties = wxCpMultiProperties.getConfigStorage().getRedis();
    RedissonClient redissonClient;
    if (redisProperties != null && StringUtils.isNotEmpty(redisProperties.getHost())) {
      redissonClient = getRedissonClient(wxCpMultiProperties);
    } else {
      redissonClient = applicationContext.getBean(RedissonClient.class);
    }
    return new WxCpRedissonConfigImpl(redissonClient, wxCpMultiProperties.getConfigStorage().getKeyPrefix());
  }

  private RedissonClient getRedissonClient(WxCpMultiProperties wxCpMultiProperties) {
    WxCpMultiProperties.ConfigStorage storage = wxCpMultiProperties.getConfigStorage();
    WxCpMultiRedisProperties redis = storage.getRedis();

    Config config = new Config();
    config.useSingleServer()
      .setAddress("redis://" + redis.getHost() + ":" + redis.getPort())
      .setDatabase(redis.getDatabase())
      .setPassword(redis.getPassword());
    config.setTransportMode(TransportMode.NIO);
    return Redisson.create(config);
  }
}

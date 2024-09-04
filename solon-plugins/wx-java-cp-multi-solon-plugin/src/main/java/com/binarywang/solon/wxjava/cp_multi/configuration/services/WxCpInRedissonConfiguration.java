package com.binarywang.solon.wxjava.cp_multi.configuration.services;

import com.binarywang.solon.wxjava.cp_multi.properties.WxCpMultiProperties;
import com.binarywang.solon.wxjava.cp_multi.properties.WxCpMultiRedisProperties;
import com.binarywang.solon.wxjava.cp_multi.service.WxCpMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import me.chanjar.weixin.cp.config.impl.WxCpRedissonConfigImpl;
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
 * 自动装配基于 redisson 策略配置
 *
 * @author yl
 * created on 2023/10/16
 */
@Configuration
@Condition(
  onProperty = "${"+WxCpMultiProperties.PREFIX + ".configStorage.type} = redisson",
  onClass = Redisson.class
)
@RequiredArgsConstructor
public class WxCpInRedissonConfiguration extends AbstractWxCpConfiguration {
  private final WxCpMultiProperties wxCpMultiProperties;
  private final AppContext applicationContext;

  @Bean
  public WxCpMultiServices wxCpMultiServices() {
    return this.wxCpMultiServices(wxCpMultiProperties);
  }

  @Override
  protected WxCpDefaultConfigImpl wxCpConfigStorage(WxCpMultiProperties wxCpMultiProperties) {
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

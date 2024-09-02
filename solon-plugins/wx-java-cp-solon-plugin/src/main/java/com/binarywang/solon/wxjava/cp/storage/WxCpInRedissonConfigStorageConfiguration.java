package com.binarywang.solon.wxjava.cp.storage;

import com.binarywang.solon.wxjava.cp.properties.WxCpProperties;
import com.binarywang.solon.wxjava.cp.properties.WxCpRedisProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
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
 * created on  2023/04/23
 */
@Configuration
@Condition(
  onProperty = "${"+WxCpProperties.PREFIX + ".configStorage.type} = redisson",
  onClass = Redisson.class
)
@RequiredArgsConstructor
public class WxCpInRedissonConfigStorageConfiguration extends AbstractWxCpConfigStorageConfiguration {
  private final WxCpProperties wxCpProperties;
  private final AppContext applicationContext;

  @Bean
  @Condition(onMissingBean=WxCpConfigStorage.class)
  public WxCpConfigStorage wxCpConfigStorage() {
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

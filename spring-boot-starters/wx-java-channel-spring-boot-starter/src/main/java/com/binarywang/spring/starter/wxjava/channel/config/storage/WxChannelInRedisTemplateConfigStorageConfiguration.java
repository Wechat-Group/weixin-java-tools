package com.binarywang.spring.starter.wxjava.channel.config.storage;

import com.binarywang.spring.starter.wxjava.channel.properties.WxChannelProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.channel.config.WxChannelConfig;
import me.chanjar.weixin.channel.config.impl.WxChannelRedisConfigImpl;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Configuration
@ConditionalOnProperty(prefix = WxChannelProperties.PREFIX + ".config-storage", name = "type", havingValue = "redistemplate")
@ConditionalOnClass(StringRedisTemplate.class)
@RequiredArgsConstructor
public class WxChannelInRedisTemplateConfigStorageConfiguration extends AbstractWxChannelConfigStorageConfiguration {
  private final WxChannelProperties properties;
  private final ApplicationContext applicationContext;

  @Bean
  @ConditionalOnMissingBean(WxChannelConfig.class)
  public WxChannelConfig wxChannelConfig() {
    WxChannelRedisConfigImpl config = getWxChannelInRedisTemplateConfig();
    return this.config(config, properties);
  }

  private WxChannelRedisConfigImpl getWxChannelInRedisTemplateConfig() {
    StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    WxRedisOps redisOps = new RedisTemplateWxRedisOps(redisTemplate);
    return new WxChannelRedisConfigImpl(redisOps, properties.getConfigStorage().getKeyPrefix());
  }
}

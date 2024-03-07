package com.binarywang.spring.starter.wxjava.mp.configuration.services;

import com.binarywang.spring.starter.wxjava.mp.properties.WxMpMultiProperties;
import com.binarywang.spring.starter.wxjava.mp.service.WxMpMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.mp.config.impl.WxMpRedisConfigImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 自动装配基于 redisTemplate 策略配置
 *
 * @author yl
 * created on 2024/1/23
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxMpMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "redis_template"
)
@RequiredArgsConstructor
public class WxMpInRedisTemplateConfiguration extends AbstractWxMpConfiguration {
  private final WxMpMultiProperties WxMpMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxMpMultiServices wxMpMultiServices() {
    return this.wxMpMultiServices(WxMpMultiProperties);
  }

  @Override
  protected WxMpDefaultConfigImpl wxMpConfigStorage(WxMpMultiProperties wxMpMultiProperties) {
    return this.configRedisTemplate(WxMpMultiProperties);
  }

  private WxMpDefaultConfigImpl configRedisTemplate(WxMpMultiProperties wxMpMultiProperties) {
    StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    return new WxMpRedisConfigImpl(new RedisTemplateWxRedisOps(redisTemplate),
      wxMpMultiProperties.getConfigStorage().getKeyPrefix());
  }
}

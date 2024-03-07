package com.binarywang.spring.starter.wxjava.cp.configuration.services;

import com.binarywang.spring.starter.wxjava.cp.properties.WxCpMultiProperties;
import com.binarywang.spring.starter.wxjava.cp.service.WxCpMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import me.chanjar.weixin.cp.config.impl.WxCpRedisTemplateConfigImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 自动装配基于 redisTemplate 策略配置
 *
 * @author yl
 * created on 2023/10/16
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxCpMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "redistemplate"
)
@RequiredArgsConstructor
public class WxCpInRedisTemplateConfiguration extends AbstractWxCpConfiguration {
  private final WxCpMultiProperties wxCpMultiProperties;
  private final ApplicationContext applicationContext;

  @Bean
  public WxCpMultiServices wxCpMultiServices() {
    return this.wxCpMultiServices(wxCpMultiProperties);
  }

  @Override
  protected WxCpDefaultConfigImpl wxCpConfigStorage(WxCpMultiProperties wxCpMultiProperties) {
    return this.configRedisTemplate(wxCpMultiProperties);
  }

  private WxCpDefaultConfigImpl configRedisTemplate(WxCpMultiProperties wxCpMultiProperties) {
    StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    return new WxCpRedisTemplateConfigImpl(redisTemplate, wxCpMultiProperties.getConfigStorage().getKeyPrefix());
  }
}

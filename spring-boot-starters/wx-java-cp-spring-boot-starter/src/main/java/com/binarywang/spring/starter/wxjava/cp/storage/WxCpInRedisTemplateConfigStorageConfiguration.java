package com.binarywang.spring.starter.wxjava.cp.storage;

import com.binarywang.spring.starter.wxjava.cp.properties.WxCpProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import me.chanjar.weixin.cp.config.impl.WxCpRedisTemplateConfigImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 自动装配基于 redisTemplate 策略配置
 *
 * @author yl
 * created on  2023/04/23
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxCpProperties.PREFIX + ".config-storage", name = "type", havingValue = "redistemplate"
)
@RequiredArgsConstructor
public class WxCpInRedisTemplateConfigStorageConfiguration extends AbstractWxCpConfigStorageConfiguration {
  private final WxCpProperties wxCpProperties;
  private final ApplicationContext applicationContext;

  @Bean
  @ConditionalOnMissingBean(WxCpConfigStorage.class)
  public WxCpConfigStorage wxCpConfigStorage() {
    WxCpDefaultConfigImpl config = getConfigStorage();
    return this.config(config, wxCpProperties);
  }

  private WxCpRedisTemplateConfigImpl getConfigStorage() {
    StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    return new WxCpRedisTemplateConfigImpl(redisTemplate, wxCpProperties.getConfigStorage().getKeyPrefix());
  }
}

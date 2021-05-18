package com.binarywang.spring.starter.wxjava.open.config.storage;

import com.binarywang.spring.starter.wxjava.open.properties.WxOpenProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInRedisTemplateConfigStorage;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author yl
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxOpenProperties.PREFIX + ".config-storage", name = "type", havingValue = "redistemplate"
)
@ConditionalOnClass(StringRedisTemplate.class)
@RequiredArgsConstructor
public class WxOpenInRedisTemplateConfigStorageConfiguration extends AbstractWxOpenConfigStorageConfiguration {
  private final WxOpenProperties properties;
  private final ApplicationContext applicationContext;

  @Bean
  @ConditionalOnMissingBean(WxOpenConfigStorage.class)
  public WxOpenConfigStorage wxOpenConfigStorage() {
    WxOpenInMemoryConfigStorage config = getWxOpenInRedisTemplateConfigStorage();
    return this.config(config, properties);
  }

  private WxOpenInRedisTemplateConfigStorage getWxOpenInRedisTemplateConfigStorage() {
    StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    return new WxOpenInRedisTemplateConfigStorage(redisTemplate, properties.getConfigStorage().getKeyPrefix());
  }
}

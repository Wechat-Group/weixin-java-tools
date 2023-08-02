package com.binarywang.spring.starter.wxjava.miniapp.config.storage;

import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaRedisBetterConfigImpl;
import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaProperties;
import lombok.RequiredArgsConstructor;
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
 * @author yl TaoYu
 */
@Configuration
@ConditionalOnProperty(prefix = WxMaProperties.PREFIX + ".config-storage", name = "type", havingValue = "redistemplate")
@ConditionalOnClass(StringRedisTemplate.class)
@RequiredArgsConstructor
public class WxMaInRedisTemplateConfigStorageConfiguration extends AbstractWxMaConfigStorageConfiguration {
  private final WxMaProperties properties;
  private final ApplicationContext applicationContext;

  @Bean
  @ConditionalOnMissingBean(WxMaConfig.class)
  public WxMaConfig wxMaConfig() {
    WxMaRedisBetterConfigImpl config = getWxMaInRedisTemplateConfigStorage();
    return this.config(config, properties);
  }

  private WxMaRedisBetterConfigImpl getWxMaInRedisTemplateConfigStorage() {
    StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
    WxRedisOps redisOps = new RedisTemplateWxRedisOps(redisTemplate);
    return new WxMaRedisBetterConfigImpl(redisOps, properties.getConfigStorage().getKeyPrefix());
  }
}

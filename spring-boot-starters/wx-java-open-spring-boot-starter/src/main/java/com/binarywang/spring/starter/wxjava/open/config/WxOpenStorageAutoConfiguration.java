package com.binarywang.spring.starter.wxjava.open.config;

import com.binarywang.spring.starter.wxjava.open.config.storage.WxOpenInMemoryConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.open.config.storage.WxOpenInRedisConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.open.config.storage.WxOpenInRedisTemplateConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.open.config.storage.WxOpenInRedissonConfigStorageConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 微信公众号存储策略自动配置.
 *
 * @author someone
 */
@Configuration
@Import({
  WxOpenInMemoryConfigStorageConfiguration.class,
  WxOpenInRedisTemplateConfigStorageConfiguration.class,
  WxOpenInRedisConfigStorageConfiguration.class,
  WxOpenInRedissonConfigStorageConfiguration.class
})
public class WxOpenStorageAutoConfiguration {
}

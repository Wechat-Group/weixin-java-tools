package com.binarywang.spring.starter.wxjava.miniapp.config;

import com.binarywang.spring.starter.wxjava.miniapp.config.storage.WxMaInJedisConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.miniapp.config.storage.WxMaInMemoryConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.miniapp.config.storage.WxMaInRedisTemplateConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.miniapp.config.storage.WxMaInRedissonConfigStorageConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 微信小程序存储策略自动配置.
 *
 * @author someone TaoYu
 */
@Configuration
@Import({
  WxMaInMemoryConfigStorageConfiguration.class,
  WxMaInJedisConfigStorageConfiguration.class,
  WxMaInRedisTemplateConfigStorageConfiguration.class,
  WxMaInRedissonConfigStorageConfiguration.class
})
public class WxMaStorageAutoConfiguration {
}

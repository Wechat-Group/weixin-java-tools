package com.binarywang.spring.starter.wxjava.channel.config;

import com.binarywang.spring.starter.wxjava.channel.config.storage.WxChannelInJedisConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.channel.config.storage.WxChannelInMemoryConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.channel.config.storage.WxChannelInRedisTemplateConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.channel.config.storage.WxChannelInRedissonConfigStorageConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 微信小程序存储策略自动配置
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Configuration
@Import({
  WxChannelInMemoryConfigStorageConfiguration.class,
  WxChannelInJedisConfigStorageConfiguration.class,
  WxChannelInRedisTemplateConfigStorageConfiguration.class,
  WxChannelInRedissonConfigStorageConfiguration.class
})
public class WxChannelStorageAutoConfiguration {
}

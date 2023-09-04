package com.binarywang.spring.starter.wxjava.channel.config;

import com.binarywang.spring.starter.wxjava.channel.properties.WxChannelProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 自动配置
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Configuration
@EnableConfigurationProperties(WxChannelProperties.class)
@Import({
  WxChannelStorageAutoConfiguration.class,
  WxChannelServiceAutoConfiguration.class
})
public class WxChannelAutoConfiguration {
}

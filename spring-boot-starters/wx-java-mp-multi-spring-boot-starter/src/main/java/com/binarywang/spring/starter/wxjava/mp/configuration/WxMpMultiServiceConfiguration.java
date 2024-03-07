package com.binarywang.spring.starter.wxjava.mp.configuration;

import com.binarywang.spring.starter.wxjava.mp.configuration.services.WxMpInJedisConfiguration;
import com.binarywang.spring.starter.wxjava.mp.configuration.services.WxMpInMemoryConfiguration;
import com.binarywang.spring.starter.wxjava.mp.configuration.services.WxMpInRedisTemplateConfiguration;
import com.binarywang.spring.starter.wxjava.mp.configuration.services.WxMpInRedissonConfiguration;
import com.binarywang.spring.starter.wxjava.mp.properties.WxMpMultiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 微信公众号相关服务自动注册
 *
 * @author yl
 * created on 2024/1/23
 */
@Configuration
@EnableConfigurationProperties(WxMpMultiProperties.class)
@Import({
  WxMpInJedisConfiguration.class,
  WxMpInMemoryConfiguration.class,
  WxMpInRedissonConfiguration.class,
  WxMpInRedisTemplateConfiguration.class
})
public class WxMpMultiServiceConfiguration {
}

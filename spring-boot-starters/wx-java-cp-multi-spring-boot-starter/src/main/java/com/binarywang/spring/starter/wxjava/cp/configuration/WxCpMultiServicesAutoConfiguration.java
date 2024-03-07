package com.binarywang.spring.starter.wxjava.cp.configuration;

import com.binarywang.spring.starter.wxjava.cp.configuration.services.WxCpInJedisConfiguration;
import com.binarywang.spring.starter.wxjava.cp.configuration.services.WxCpInMemoryConfiguration;
import com.binarywang.spring.starter.wxjava.cp.configuration.services.WxCpInRedisTemplateConfiguration;
import com.binarywang.spring.starter.wxjava.cp.configuration.services.WxCpInRedissonConfiguration;
import com.binarywang.spring.starter.wxjava.cp.properties.WxCpMultiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 企业微信平台相关服务自动注册
 *
 * @author yl
 * created on 2023/10/16
 */
@Configuration
@EnableConfigurationProperties(WxCpMultiProperties.class)
@Import({
  WxCpInJedisConfiguration.class,
  WxCpInMemoryConfiguration.class,
  WxCpInRedissonConfiguration.class,
  WxCpInRedisTemplateConfiguration.class
})
public class WxCpMultiServicesAutoConfiguration {
}

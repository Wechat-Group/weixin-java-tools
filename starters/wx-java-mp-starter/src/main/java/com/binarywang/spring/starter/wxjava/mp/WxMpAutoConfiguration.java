package com.binarywang.spring.starter.wxjava.mp;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(WxMpProperties.class)
@Import({WxMpStorageAutoConfiguration.class, WxMpServiceAutoConfiguration.class})
public class WxMpAutoConfiguration {
}

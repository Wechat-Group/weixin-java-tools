package com.binarywang.spring.starter.wxjava.qidian.config;

import com.binarywang.spring.starter.wxjava.qidian.properties.WxQidianProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * .
 *
 * @author someone
 */
@Configuration
@EnableConfigurationProperties(WxQidianProperties.class)
@Import({ WxQidianStorageAutoConfiguration.class, WxQidianServiceAutoConfiguration.class })
public class WxQidianAutoConfiguration {
}

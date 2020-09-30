package com.binarywang.spring.starter.wxjava.cp.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.binarywang.spring.starter.wxjava.cp.properties.WxCpProperties;

/**
 * configuration for corp
 * 
 * @author xdtand
 *
 */
@Configuration
@EnableConfigurationProperties(WxCpProperties.class)
@Import({WxCpStorageAutoConfiguration.class, WxCpServiceAutoConfiguration.class})
public class WxCpAutoConfiguration {
}

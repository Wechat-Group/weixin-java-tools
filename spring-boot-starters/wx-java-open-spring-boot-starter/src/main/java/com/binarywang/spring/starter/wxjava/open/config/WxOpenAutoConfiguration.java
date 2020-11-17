package com.binarywang.spring.starter.wxjava.open.config;

import com.binarywang.spring.starter.wxjava.open.properties.WxOpenProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * .
 *
 * @author someone
 */
@Configuration
@EnableConfigurationProperties(WxOpenProperties.class)
@Import({WxOpenStorageAutoConfiguration.class, WxOpenServiceAutoConfiguration.class})
public class WxOpenAutoConfiguration {
}

package com.binarywang.spring.starter.wxjava.cp.config;

import com.binarywang.spring.starter.wxjava.cp.properties.WxCpProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 企业微信自动注册
 *
 * @author yl
 * @date 2021/12/6
 */
@Configuration
@EnableConfigurationProperties(WxCpProperties.class)
@Import({
  WxCpStorageAutoConfiguration.class,
  WxCpServiceAutoConfiguration.class
})
public class WxCpAutoConfiguration {
}

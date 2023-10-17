package com.binarywang.spring.starter.wxjava.cp.autoconfigure;

import com.binarywang.spring.starter.wxjava.cp.properties.WxCpMultiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 企业微信自动注册
 *
 * @author yl
 * created on 2023/10/16
 */
@Configuration
@EnableConfigurationProperties(WxCpMultiProperties.class)
@Import({
  WxCpMultiServicesAutoConfiguration.class
})
public class WxCpMultiAutoConfiguration {
}

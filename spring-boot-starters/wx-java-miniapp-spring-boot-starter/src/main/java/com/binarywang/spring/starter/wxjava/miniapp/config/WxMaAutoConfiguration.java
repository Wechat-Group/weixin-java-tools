package com.binarywang.spring.starter.wxjava.miniapp.config;

import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 自动配置.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-08-10
 */
@Configuration
@EnableConfigurationProperties(WxMaProperties.class)
@Import({
  WxMaStorageAutoConfiguration.class,
  WxMaServiceAutoConfiguration.class
})
public class WxMaAutoConfiguration {
}

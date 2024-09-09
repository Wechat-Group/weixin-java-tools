package com.binarywang.spring.starter.wxjava.miniapp.autoconfigure;

import com.binarywang.spring.starter.wxjava.miniapp.configuration.WxMaMultiServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author monch
 * created on 2024/9/6
 */
@Configuration
@Import(WxMaMultiServiceConfiguration.class)
public class WxMaMultiAutoConfiguration {
}

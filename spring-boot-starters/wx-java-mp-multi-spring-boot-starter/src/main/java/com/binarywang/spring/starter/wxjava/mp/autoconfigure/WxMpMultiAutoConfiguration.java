package com.binarywang.spring.starter.wxjava.mp.autoconfigure;

import com.binarywang.spring.starter.wxjava.mp.configuration.WxMpMultiServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author yl
 * created on 2024/1/23
 */
@Configuration
@Import(WxMpMultiServiceConfiguration.class)
public class WxMpMultiAutoConfiguration {
}

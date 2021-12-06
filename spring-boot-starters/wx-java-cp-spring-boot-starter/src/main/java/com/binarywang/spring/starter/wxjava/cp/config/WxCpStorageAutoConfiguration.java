package com.binarywang.spring.starter.wxjava.cp.config;

import com.binarywang.spring.starter.wxjava.cp.storage.WxCpInMemoryConfigStorageConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 企业微信存储策略自动配置
 *
 * @author yl
 * @date 2021/12/6
 */
@Configuration
@Import({
  WxCpInMemoryConfigStorageConfiguration.class
})
public class WxCpStorageAutoConfiguration {
}

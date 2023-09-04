package com.binarywang.spring.starter.wxjava.cp.config;

import com.binarywang.spring.starter.wxjava.cp.storage.WxCpInJedisConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.cp.storage.WxCpInMemoryConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.cp.storage.WxCpInRedisTemplateConfigStorageConfiguration;
import com.binarywang.spring.starter.wxjava.cp.storage.WxCpInRedissonConfigStorageConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 企业微信存储策略自动配置
 *
 * @author yl
 * created on  2021/12/6
 */
@Configuration
@Import({
  WxCpInMemoryConfigStorageConfiguration.class,
  WxCpInJedisConfigStorageConfiguration.class,
  WxCpInRedissonConfigStorageConfiguration.class,
  WxCpInRedisTemplateConfigStorageConfiguration.class
})
public class WxCpStorageAutoConfiguration {
}

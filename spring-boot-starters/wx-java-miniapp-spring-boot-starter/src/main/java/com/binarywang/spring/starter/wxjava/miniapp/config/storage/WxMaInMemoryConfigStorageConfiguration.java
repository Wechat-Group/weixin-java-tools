package com.binarywang.spring.starter.wxjava.miniapp.config.storage;

import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yl TaoYu
 */
@Configuration
@ConditionalOnProperty(prefix = WxMaProperties.PREFIX + ".config-storage", name = "type",
  matchIfMissing = true, havingValue = "memory")
@RequiredArgsConstructor
public class WxMaInMemoryConfigStorageConfiguration extends AbstractWxMaConfigStorageConfiguration {
  private final WxMaProperties properties;

  @Bean
  @ConditionalOnMissingBean(WxMaConfig.class)
  public WxMaConfig wxMaConfig() {
    WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
    return this.config(config, properties);
  }
}

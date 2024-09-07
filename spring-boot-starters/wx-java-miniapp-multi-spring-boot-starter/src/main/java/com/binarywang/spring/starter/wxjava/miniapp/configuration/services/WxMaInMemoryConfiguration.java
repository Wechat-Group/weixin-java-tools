package com.binarywang.spring.starter.wxjava.miniapp.configuration.services;

import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.binarywang.spring.starter.wxjava.miniapp.properties.WxMaMultiProperties;
import com.binarywang.spring.starter.wxjava.miniapp.service.WxMaMultiServices;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配基于内存策略配置
 *
 * @author monch
 * created on 2024/9/6
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxMaMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "memory", matchIfMissing = true
)
@RequiredArgsConstructor
public class WxMaInMemoryConfiguration extends AbstractWxMaConfiguration {
  private final WxMaMultiProperties wxMaMultiProperties;

  @Bean
  public WxMaMultiServices wxMaMultiServices() {
    return this.wxMaMultiServices(wxMaMultiProperties);
  }

  @Override
  protected WxMaDefaultConfigImpl wxMaConfigStorage(WxMaMultiProperties wxMaMultiProperties) {
    return this.configInMemory();
  }

  private WxMaDefaultConfigImpl configInMemory() {
    return new WxMaDefaultConfigImpl();
    // return new WxMaDefaultConfigImpl();
  }
}

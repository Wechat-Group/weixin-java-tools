package com.binarywang.spring.starter.wxjava.mp.configuration.services;

import com.binarywang.spring.starter.wxjava.mp.properties.WxMpMultiProperties;
import com.binarywang.spring.starter.wxjava.mp.service.WxMpMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.mp.config.impl.WxMpMapConfigImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配基于内存策略配置
 *
 * @author yl
 * created on 2024/1/23
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxMpMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "memory", matchIfMissing = true
)
@RequiredArgsConstructor
public class WxMpInMemoryConfiguration extends AbstractWxMpConfiguration {
  private final WxMpMultiProperties wxCpMultiProperties;

  @Bean
  public WxMpMultiServices wxCpMultiServices() {
    return this.wxMpMultiServices(wxCpMultiProperties);
  }

  @Override
  protected WxMpDefaultConfigImpl wxMpConfigStorage(WxMpMultiProperties wxCpMultiProperties) {
    return this.configInMemory();
  }

  private WxMpDefaultConfigImpl configInMemory() {
    return new WxMpMapConfigImpl();
    // return new WxMpDefaultConfigImpl();
  }
}

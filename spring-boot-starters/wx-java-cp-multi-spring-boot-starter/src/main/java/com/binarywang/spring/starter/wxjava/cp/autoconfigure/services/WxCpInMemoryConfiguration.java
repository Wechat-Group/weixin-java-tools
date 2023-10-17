package com.binarywang.spring.starter.wxjava.cp.autoconfigure.services;

import com.binarywang.spring.starter.wxjava.cp.properties.WxCpMultiProperties;
import com.binarywang.spring.starter.wxjava.cp.service.WxCpMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配基于内存策略配置
 *
 * @author yl
 * created on 2023/10/16
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxCpMultiProperties.PREFIX + ".config-storage", name = "type", havingValue = "memory", matchIfMissing = true
)
@RequiredArgsConstructor
public class WxCpInMemoryConfiguration extends AbstractWxCpConfiguration {
  private final WxCpMultiProperties wxCpMultiProperties;

  @Bean
  public WxCpMultiServices wxCpServices() {
    return this.configWxCpServices(wxCpMultiProperties);
  }

  @Override
  protected WxCpDefaultConfigImpl configWxCpDefaultConfigImpl(WxCpMultiProperties wxCpMultiProperties) {
    return this.configInMemory();
  }

  private WxCpDefaultConfigImpl configInMemory() {
    return new WxCpDefaultConfigImpl();
  }
}

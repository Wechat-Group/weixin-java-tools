package com.binarywang.solon.wxjava.mp_multi.configuration.services;

import com.binarywang.solon.wxjava.mp_multi.properties.WxMpMultiProperties;
import com.binarywang.solon.wxjava.mp_multi.service.WxMpMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.mp.config.impl.WxMpMapConfigImpl;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;

/**
 * 自动装配基于内存策略配置
 *
 * @author yl
 * created on 2024/1/23
 */
@Configuration
@Condition(
  onProperty = "${"+WxMpMultiProperties.PREFIX + ".configStorage.type:memory} = memory"
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

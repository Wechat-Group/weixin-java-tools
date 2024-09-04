package com.binarywang.solon.wxjava.cp_multi.configuration.services;

import com.binarywang.solon.wxjava.cp_multi.properties.WxCpMultiProperties;
import com.binarywang.solon.wxjava.cp_multi.service.WxCpMultiServices;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;

/**
 * 自动装配基于内存策略配置
 *
 * @author yl
 * created on 2023/10/16
 */
@Configuration
@Condition(
  onProperty = "${"+WxCpMultiProperties.PREFIX + ".configStorage.type:memory} = memory"
)
@RequiredArgsConstructor
public class WxCpInMemoryConfiguration extends AbstractWxCpConfiguration {
  private final WxCpMultiProperties wxCpMultiProperties;

  @Bean
  public WxCpMultiServices wxCpMultiServices() {
    return this.wxCpMultiServices(wxCpMultiProperties);
  }

  @Override
  protected WxCpDefaultConfigImpl wxCpConfigStorage(WxCpMultiProperties wxCpMultiProperties) {
    return this.configInMemory();
  }

  private WxCpDefaultConfigImpl configInMemory() {
    return new WxCpDefaultConfigImpl();
  }
}

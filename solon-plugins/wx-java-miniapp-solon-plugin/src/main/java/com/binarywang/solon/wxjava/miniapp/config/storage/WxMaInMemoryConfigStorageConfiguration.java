package com.binarywang.solon.wxjava.miniapp.config.storage;

import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.binarywang.solon.wxjava.miniapp.properties.WxMaProperties;
import lombok.RequiredArgsConstructor;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;

/**
 * @author yl TaoYu
 */
@Configuration
@Condition(
  onProperty = "${"+WxMaProperties.PREFIX + ".configStorage.type:memory} = memory"
)
@RequiredArgsConstructor
public class WxMaInMemoryConfigStorageConfiguration extends AbstractWxMaConfigStorageConfiguration {
  private final WxMaProperties properties;

  @Bean
  @Condition(onMissingBean=WxMaConfig.class)
  public WxMaConfig wxMaConfig() {
    WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
    return this.config(config, properties);
  }
}

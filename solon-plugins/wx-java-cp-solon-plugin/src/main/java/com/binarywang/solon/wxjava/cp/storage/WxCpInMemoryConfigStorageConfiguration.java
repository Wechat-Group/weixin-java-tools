package com.binarywang.solon.wxjava.cp.storage;

import com.binarywang.solon.wxjava.cp.properties.WxCpProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;

/**
 * 自动装配基于内存策略配置
 *
 * @author yl
 * created on  2021/12/6
 */
@Configuration
@Condition(
  onProperty = "${"+WxCpProperties.PREFIX + ".configStorage.type:memory} = memory"
)
@RequiredArgsConstructor
public class WxCpInMemoryConfigStorageConfiguration extends AbstractWxCpConfigStorageConfiguration {
  private final WxCpProperties wxCpProperties;

  @Bean
  @Condition(onMissingBean=WxCpConfigStorage.class)
  public WxCpConfigStorage wxCpConfigStorage() {
    WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();
    return this.config(config, wxCpProperties);
  }
}

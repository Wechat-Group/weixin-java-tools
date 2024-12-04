package com.binarywang.solon.wxjava.open.config.storage;

import com.binarywang.solon.wxjava.open.properties.WxOpenProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;

/**
 * @author yl
 */
@Configuration
@Condition(
  onProperty = "${"+WxOpenProperties.PREFIX + ".configStorage.type:memory} = memory"
)
@RequiredArgsConstructor
public class WxOpenInMemoryConfigStorageConfiguration extends AbstractWxOpenConfigStorageConfiguration {
  private final WxOpenProperties properties;

  @Bean
  @Condition(onMissingBean=WxOpenConfigStorage.class)
  public WxOpenConfigStorage wxOpenConfigStorage() {
    WxOpenInMemoryConfigStorage config = new WxOpenInMemoryConfigStorage();
    return this.config(config, properties);
  }
}

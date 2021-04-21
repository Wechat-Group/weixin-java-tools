package com.binarywang.spring.starter.wxjava.open.config.storage;

import com.binarywang.spring.starter.wxjava.open.properties.WxOpenProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yl
 */
@Configuration
@ConditionalOnProperty(
  prefix = WxOpenProperties.PREFIX + ".config-storage", name = "type",
  matchIfMissing = true, havingValue = "memory"
)
@RequiredArgsConstructor
public class WxOpenInMemoryConfigStorageConfiguration extends AbstractWxOpenConfigStorageConfiguration {
  private final WxOpenProperties properties;

  @Bean
  @ConditionalOnMissingBean(WxOpenConfigStorage.class)
  public WxOpenConfigStorage wxOpenConfigStorage() {
    WxOpenInMemoryConfigStorage config = new WxOpenInMemoryConfigStorage();
    return this.config(config, properties);
  }
}

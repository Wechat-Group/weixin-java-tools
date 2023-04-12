package com.binarywang.spring.starter.wxjava.channel.config.storage;


import com.binarywang.spring.starter.wxjava.channel.properties.WxChannelProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.channel.config.WxChannelConfig;
import me.chanjar.weixin.channel.config.impl.WxChannelDefaultConfigImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Configuration
@ConditionalOnProperty(prefix = WxChannelProperties.PREFIX + ".config-storage", name = "type",
  matchIfMissing = true, havingValue = "memory")
@RequiredArgsConstructor
public class WxChannelInMemoryConfigStorageConfiguration extends AbstractWxChannelConfigStorageConfiguration {
  private final WxChannelProperties properties;

  @Bean
  @ConditionalOnMissingBean(WxChannelProperties.class)
  public WxChannelConfig wxChannelConfig() {
    WxChannelDefaultConfigImpl config = new WxChannelDefaultConfigImpl();
    return this.config(config, properties);
  }
}

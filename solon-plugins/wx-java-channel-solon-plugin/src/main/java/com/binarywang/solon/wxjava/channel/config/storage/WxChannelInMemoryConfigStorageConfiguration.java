package com.binarywang.solon.wxjava.channel.config.storage;


import com.binarywang.solon.wxjava.channel.properties.WxChannelProperties;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.channel.config.WxChannelConfig;
import me.chanjar.weixin.channel.config.impl.WxChannelDefaultConfigImpl;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Configuration
@Condition(
  onProperty = "${"+WxChannelProperties.PREFIX + ".configStorage.type:memory} = memory"
)
@RequiredArgsConstructor
public class WxChannelInMemoryConfigStorageConfiguration extends AbstractWxChannelConfigStorageConfiguration {
  private final WxChannelProperties properties;

  @Bean
  @Condition(onMissingBean = WxChannelProperties.class)
  public WxChannelConfig wxChannelConfig() {
    WxChannelDefaultConfigImpl config = new WxChannelDefaultConfigImpl();
    return this.config(config, properties);
  }
}

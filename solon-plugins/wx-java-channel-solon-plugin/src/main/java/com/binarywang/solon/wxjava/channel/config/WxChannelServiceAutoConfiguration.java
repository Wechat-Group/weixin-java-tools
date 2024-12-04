package com.binarywang.solon.wxjava.channel.config;


import com.binarywang.solon.wxjava.channel.properties.WxChannelProperties;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.api.impl.WxChannelServiceImpl;
import me.chanjar.weixin.channel.config.WxChannelConfig;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;

/**
 * 微信小程序平台相关服务自动注册
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Configuration
@AllArgsConstructor
public class WxChannelServiceAutoConfiguration {
  private final WxChannelProperties properties;

  /**
   * Channel Service
   *
   * @return Channel Service
   */
  @Bean
  @Condition(onMissingBean=WxChannelService.class, onBean = WxChannelConfig.class)
  public WxChannelService wxChannelService(WxChannelConfig wxChannelConfig) {
    WxChannelService wxChannelService = new WxChannelServiceImpl();
    wxChannelService.setConfig(wxChannelConfig);
    return wxChannelService;
  }
}

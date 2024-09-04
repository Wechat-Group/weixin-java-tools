package com.binarywang.solon.wxjava.open.config;

import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.impl.WxOpenMessageRouter;
import me.chanjar.weixin.open.api.impl.WxOpenServiceImpl;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;

/**
 * 微信开放平台相关服务自动注册.
 *
 * @author someone
 */
@Configuration
public class WxOpenServiceAutoConfiguration {

  @Bean
  @Condition(onMissingBean = WxOpenService.class, onBean = WxOpenConfigStorage.class)
  public WxOpenService wxOpenService(WxOpenConfigStorage wxOpenConfigStorage) {
    WxOpenService wxOpenService = new WxOpenServiceImpl();
    wxOpenService.setWxOpenConfigStorage(wxOpenConfigStorage);
    return wxOpenService;
  }

  @Bean
  public WxOpenMessageRouter wxOpenMessageRouter(WxOpenService wxOpenService) {
    return new WxOpenMessageRouter(wxOpenService);
  }

  @Bean
  public WxOpenComponentService wxOpenComponentService(WxOpenService wxOpenService) {
    return wxOpenService.getWxOpenComponentService();
  }
}

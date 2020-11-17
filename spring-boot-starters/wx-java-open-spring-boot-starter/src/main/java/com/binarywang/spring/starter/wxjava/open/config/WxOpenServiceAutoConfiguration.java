package com.binarywang.spring.starter.wxjava.open.config;

import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.impl.WxOpenMessageRouter;
import me.chanjar.weixin.open.api.impl.WxOpenServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信开放平台相关服务自动注册.
 *
 * @author someone
 */
@Configuration
public class WxOpenServiceAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public WxOpenService wxOpenService(WxOpenConfigStorage configStorage) {
    WxOpenService wxOpenService = new WxOpenServiceImpl();
    wxOpenService.setWxOpenConfigStorage(configStorage);
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

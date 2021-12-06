package com.binarywang.spring.starter.wxjava.cp.config;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 企业微信平台相关服务自动注册
 *
 * @author yl
 * @date 2021/12/6
 */
@Configuration
public class WxCpServiceAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnBean(WxCpConfigStorage.class)
  public WxCpService wxCpService(WxCpConfigStorage wxCpConfigStorage) {
    WxCpService wxCpService = new WxCpServiceImpl();
    wxCpService.setWxCpConfigStorage(wxCpConfigStorage);
    return wxCpService;
  }
}

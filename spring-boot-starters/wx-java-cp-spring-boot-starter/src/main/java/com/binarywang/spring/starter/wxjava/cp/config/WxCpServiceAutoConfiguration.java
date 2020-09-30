package com.binarywang.spring.starter.wxjava.cp.config;

import me.chanjar.weixin.cp.api.*;
import me.chanjar.weixin.cp.api.impl.WxCpServiceApacheHttpClientImpl;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.api.impl.WxCpServiceJoddHttpImpl;
import me.chanjar.weixin.cp.api.impl.WxCpServiceOkHttpImpl;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.binarywang.spring.starter.wxjava.cp.enums.HttpClientType;
import com.binarywang.spring.starter.wxjava.cp.properties.WxCpProperties;

/**
 * 企业微信相关服务自动注册.
 *
 * @author xdtand
 */
@Configuration
public class WxCpServiceAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public WxCpService WxCpService(WxCpConfigStorage configStorage, WxCpProperties wxCpProperties) {
    HttpClientType httpClientType = wxCpProperties.getConfigStorage().getHttpClientType();
    WxCpService WxCpService;
    if (httpClientType == HttpClientType.OkHttp) {
      WxCpService = newWxCpServiceOkHttpImpl();
    } else if (httpClientType == HttpClientType.JoddHttp) {
      WxCpService = newWxCpServiceJoddHttpImpl();
    } else if (httpClientType == HttpClientType.HttpClient) {
      WxCpService = newWxCpServiceHttpClientImpl();
    } else {
      WxCpService = newWxCpServiceImpl();
    }

    WxCpService.setWxCpConfigStorage(configStorage);
    return WxCpService;
  }

  private WxCpService newWxCpServiceImpl() {
    return new WxCpServiceImpl();
  }

  private WxCpService newWxCpServiceHttpClientImpl() {
    return new WxCpServiceApacheHttpClientImpl();
  }

  private WxCpService newWxCpServiceOkHttpImpl() {
    return new WxCpServiceOkHttpImpl();
  }

  private WxCpService newWxCpServiceJoddHttpImpl() {
    return new WxCpServiceJoddHttpImpl();
  }

}

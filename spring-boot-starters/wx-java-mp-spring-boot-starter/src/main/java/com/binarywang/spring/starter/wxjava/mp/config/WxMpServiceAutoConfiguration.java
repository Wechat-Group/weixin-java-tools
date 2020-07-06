package com.binarywang.spring.starter.wxjava.mp.config;

import com.binarywang.spring.starter.wxjava.mp.properties.WxMpProperties;
import me.chanjar.weixin.common.api.WxOcrService;
import me.chanjar.weixin.mp.api.*;
import me.chanjar.weixin.mp.api.impl.WxMpServiceHttpClientImpl;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.api.impl.WxMpServiceJoddHttpImpl;
import me.chanjar.weixin.mp.api.impl.WxMpServiceOkHttpImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信公众号相关服务自动注册.
 *
 * @author someone
 */
@Configuration
public class WxMpServiceAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public WxMpService wxMpService(WxMpConfigStorage configStorage, WxMpProperties wxMpProperties) {
    WxMpProperties.HttpClientType httpClientType = wxMpProperties.getConfigStorage().getHttpClientType();
    WxMpService wxMpService;
    if (httpClientType == WxMpProperties.HttpClientType.okhttp) {
      wxMpService = newWxMpServiceOkHttpImpl();
    } else if (httpClientType == WxMpProperties.HttpClientType.joddhttp) {
      wxMpService = newWxMpServiceJoddHttpImpl();
    } else if (httpClientType == WxMpProperties.HttpClientType.httpclient) {
      wxMpService = newWxMpServiceHttpClientImpl();
    } else {
      wxMpService = newWxMpServiceImpl();
    }

    wxMpService.setWxMpConfigStorage(configStorage);
    return wxMpService;
  }

  private WxMpService newWxMpServiceImpl() {
    return new WxMpServiceImpl();
  }

  private WxMpService newWxMpServiceHttpClientImpl() {
    return new WxMpServiceHttpClientImpl();
  }

  private WxMpService newWxMpServiceOkHttpImpl() {
    return new WxMpServiceOkHttpImpl();
  }

  private WxMpService newWxMpServiceJoddHttpImpl() {
    return new WxMpServiceJoddHttpImpl();
  }

  @Bean
  @Deprecated
  public WxMpKefuService wxMpKefuService(WxMpService wxMpService) {
    return wxMpService.getKefuService();
  }

  @Bean
  @Deprecated
  public WxMpMaterialService wxMpMaterialService(WxMpService wxMpService) {
    return wxMpService.getMaterialService();
  }

  @Bean
  @Deprecated
  public WxMpMenuService wxMpMenuService(WxMpService wxMpService) {
    return wxMpService.getMenuService();
  }

  @Bean
  @Deprecated
  public WxMpUserService wxMpUserService(WxMpService wxMpService) {
    return wxMpService.getUserService();
  }

  @Bean
  @Deprecated
  public WxMpUserTagService wxMpUserTagService(WxMpService wxMpService) {
    return wxMpService.getUserTagService();
  }

  @Bean
  @Deprecated
  public WxMpQrcodeService wxMpQrcodeService(WxMpService wxMpService) {
    return wxMpService.getQrcodeService();
  }

  @Bean
  @Deprecated
  public WxMpCardService wxMpCardService(WxMpService wxMpService) {
    return wxMpService.getCardService();
  }

  @Bean
  @Deprecated
  public WxMpDataCubeService wxMpDataCubeService(WxMpService wxMpService) {
    return wxMpService.getDataCubeService();
  }

  @Bean
  @Deprecated
  public WxMpUserBlacklistService wxMpUserBlacklistService(WxMpService wxMpService) {
    return wxMpService.getBlackListService();
  }

  @Bean
  @Deprecated
  public WxMpStoreService wxMpStoreService(WxMpService wxMpService) {
    return wxMpService.getStoreService();
  }

  @Bean
  @Deprecated
  public WxMpTemplateMsgService wxMpTemplateMsgService(WxMpService wxMpService) {
    return wxMpService.getTemplateMsgService();
  }

  @Bean
  @Deprecated
  public WxMpSubscribeMsgService wxMpSubscribeMsgService(WxMpService wxMpService) {
    return wxMpService.getSubscribeMsgService();
  }

  @Bean
  @Deprecated
  public WxMpDeviceService wxMpDeviceService(WxMpService wxMpService) {
    return wxMpService.getDeviceService();
  }

  @Bean
  @Deprecated
  public WxMpShakeService wxMpShakeService(WxMpService wxMpService) {
    return wxMpService.getShakeService();
  }

  @Bean
  @Deprecated
  public WxMpMemberCardService wxMpMemberCardService(WxMpService wxMpService) {
    return wxMpService.getMemberCardService();
  }

  @Bean
  @Deprecated
  public WxMpMassMessageService wxMpMassMessageService(WxMpService wxMpService) {
    return wxMpService.getMassMessageService();
  }

  @Bean
  @Deprecated
  public WxMpAiOpenService wxMpAiOpenService(WxMpService wxMpService) {
    return wxMpService.getAiOpenService();
  }

  @Bean
  @Deprecated
  public WxMpWifiService wxMpWifiService(WxMpService wxMpService) {
    return wxMpService.getWifiService();
  }

  @Bean
  @Deprecated
  public WxMpMarketingService wxMpMarketingService(WxMpService wxMpService) {
    return wxMpService.getMarketingService();
  }

  @Bean
  @Deprecated
  public WxMpCommentService wxMpCommentService(WxMpService wxMpService) {
    return wxMpService.getCommentService();
  }

  @Bean
  @Deprecated
  public WxOcrService wxMpOcrService(WxMpService wxMpService) {
    return wxMpService.getOcrService();
  }

}

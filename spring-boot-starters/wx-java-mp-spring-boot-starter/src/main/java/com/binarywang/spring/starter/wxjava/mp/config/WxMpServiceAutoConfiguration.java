package com.binarywang.spring.starter.wxjava.mp.config;

import me.chanjar.weixin.mp.api.*;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
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
  public WxMpService wxMpService(WxMpConfigStorage configStorage) {
    WxMpService wxMpService = new WxMpServiceImpl();
    wxMpService.setWxMpConfigStorage(configStorage);
    return wxMpService;
  }

  @Bean
  public WxMpKefuService wxMpKefuService(WxMpService wxMpService) {
    return wxMpService.getKefuService();
  }

  @Bean
  public WxMpMaterialService wxMpMaterialService(WxMpService wxMpService) {
    return wxMpService.getMaterialService();
  }

  @Bean
  public WxMpMenuService wxMpMenuService(WxMpService wxMpService) {
    return wxMpService.getMenuService();
  }

  @Bean
  public WxMpUserService wxMpUserService(WxMpService wxMpService) {
    return wxMpService.getUserService();
  }

  @Bean
  public WxMpUserTagService wxMpUserTagService(WxMpService wxMpService) {
    return wxMpService.getUserTagService();
  }

  @Bean
  public WxMpQrcodeService wxMpQrcodeService(WxMpService wxMpService) {
    return wxMpService.getQrcodeService();
  }

  @Bean
  public WxMpCardService wxMpCardService(WxMpService wxMpService) {
    return wxMpService.getCardService();
  }

  @Bean
  public WxMpDataCubeService wxMpDataCubeService(WxMpService wxMpService) {
    return wxMpService.getDataCubeService();
  }

  @Bean
  public WxMpUserBlacklistService wxMpUserBlacklistService(WxMpService wxMpService) {
    return wxMpService.getBlackListService();
  }

  @Bean
  public WxMpStoreService wxMpStoreService(WxMpService wxMpService) {
    return wxMpService.getStoreService();
  }

  @Bean
  public WxMpTemplateMsgService wxMpTemplateMsgService(WxMpService wxMpService) {
    return wxMpService.getTemplateMsgService();
  }

  @Bean
  public WxMpSubscribeMsgService wxMpSubscribeMsgService(WxMpService wxMpService) {
    return wxMpService.getSubscribeMsgService();
  }

  @Bean
  public WxMpDeviceService wxMpDeviceService(WxMpService wxMpService) {
    return wxMpService.getDeviceService();
  }

  @Bean
  public WxMpShakeService wxMpShakeService(WxMpService wxMpService) {
    return wxMpService.getShakeService();
  }

  @Bean
  public WxMpMemberCardService wxMpMemberCardService(WxMpService wxMpService) {
    return wxMpService.getMemberCardService();
  }

  @Bean
  public WxMpMassMessageService wxMpMassMessageService(WxMpService wxMpService) {
    return wxMpService.getMassMessageService();
  }

  @Bean
  public WxMpAiOpenService wxMpAiOpenService(WxMpService wxMpService) {
    return wxMpService.getAiOpenService();
  }

  @Bean
  public WxMpWifiService wxMpWifiService(WxMpService wxMpService) {
    return wxMpService.getWifiService();
  }

  @Bean
  public WxMpMarketingService wxMpMarketingService(WxMpService wxMpService) {
    return wxMpService.getMarketingService();
  }

  @Bean
  public WxMpCommentService wxMpCommentService(WxMpService wxMpService) {
    return wxMpService.getCommentService();
  }

  @Bean
  public WxMpOcrService wxMpOcrService(WxMpService wxMpService) {
    return wxMpService.getOcrService();
  }

}

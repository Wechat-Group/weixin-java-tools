package com.binarywang.spring.starter.wxjava.mp;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信公众号相关服务自动注册
 */
@Configuration
public class WxMpServiceAutoConfiguration {
    @Autowired
    private ApplicationContext ctx;

    @Bean
    @ConditionalOnMissingBean
    public WxMpService wxMpService(WxMpConfigStorage configStorage) {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(configStorage);
        registerWxMpSubService(wxMpService);
        return wxMpService;
    }

    @ConditionalOnBean(WxMpService.class)
    public Object registerWxMpSubService(WxMpService wxMpService) {
        ConfigurableListableBeanFactory factory = (ConfigurableListableBeanFactory) ctx.getAutowireCapableBeanFactory();
        factory.registerSingleton("wxMpKefuService", wxMpService.getKefuService());
        factory.registerSingleton("wxMpMaterialService", wxMpService.getMaterialService());
        factory.registerSingleton("wxMpMenuService", wxMpService.getMenuService());
        factory.registerSingleton("wxMpUserService", wxMpService.getUserService());
        factory.registerSingleton("wxMpUserTagService", wxMpService.getUserTagService());
        factory.registerSingleton("wxMpQrcodeService", wxMpService.getQrcodeService());
        factory.registerSingleton("wxMpCardService", wxMpService.getCardService());
        factory.registerSingleton("wxMpDataCubeService", wxMpService.getDataCubeService());
        factory.registerSingleton("wxMpUserBlacklistService", wxMpService.getBlackListService());
        factory.registerSingleton("wxMpStoreService", wxMpService.getStoreService());
        factory.registerSingleton("wxMpTemplateMsgService", wxMpService.getTemplateMsgService());
        factory.registerSingleton("wxMpSubscribeMsgService", wxMpService.getSubscribeMsgService());
        factory.registerSingleton("wxMpDeviceService", wxMpService.getDeviceService());
        factory.registerSingleton("wxMpShakeService", wxMpService.getShakeService());
        factory.registerSingleton("wxMpMemberCardService", wxMpService.getMemberCardService());
        factory.registerSingleton("wxMpMassMessageService", wxMpService.getMassMessageService());
        return Boolean.TRUE;
    }

}

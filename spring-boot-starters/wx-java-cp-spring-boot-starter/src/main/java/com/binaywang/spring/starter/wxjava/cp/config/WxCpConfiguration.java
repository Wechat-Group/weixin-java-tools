package com.binaywang.spring.starter.wxjava.cp.config;

import com.binaywang.spring.starter.wxjava.cp.handler.WxCpMessageMatchHandler;
import com.binaywang.spring.starter.wxjava.cp.properties.WxCpProperties;
import lombok.val;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.impl.WxCpRedisConfigImpl;
import me.chanjar.weixin.cp.message.WxCpMessageRouter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author caiqy
 */
@Configuration
@EnableConfigurationProperties(WxCpProperties.class)
public class WxCpConfiguration {

  private final List<WxCpMessageMatchHandler> wxCpMessageMatchHandlerList;

  private final WxCpProperties wxCpProperties;

  private final StringRedisTemplate stringRedisTemplate;

  private final WxCpServiceContainer wxCpServiceContainer = new WxCpServiceContainer();

  private final Map<Integer, WxCpMessageRouter> routers = new HashMap<>();


  public WxCpConfiguration(WxCpProperties wxCpProperties, List<WxCpMessageMatchHandler> wxCpMessageMatchHandlerList, StringRedisTemplate stringRedisTemplate) {
    this.wxCpMessageMatchHandlerList = wxCpMessageMatchHandlerList;
    this.wxCpProperties = wxCpProperties;
    this.stringRedisTemplate = stringRedisTemplate;

  }

  @Bean
  public WxCpServiceContainer getWxCpServiceContainer() {
    return wxCpServiceContainer;
  }

  @PostConstruct
  public void initService() {
    if (this.wxCpProperties.getAppConfigs() == null) {
      return;
    }
    Map<Integer, WxCpService> map = this.wxCpProperties.getAppConfigs().stream().map(a -> {
      val configStorage = new WxCpRedisConfigImpl(new RedisTemplateWxRedisOps(stringRedisTemplate), "wx::cp");
      configStorage.setCorpId(this.wxCpProperties.getCorpId());
      configStorage.setAgentId(a.getAgentId());
      configStorage.setCorpSecret(a.getSecret());
      configStorage.setToken(a.getToken());
      configStorage.setAesKey(a.getAesKey());
      val service = new WxCpServiceImpl();
      service.setWxCpConfigStorage(configStorage);
      routers.put(a.getAgentId(), this.newRouter(service));
      return service;
    }).collect(Collectors.toMap(service -> service.getWxCpConfigStorage().getAgentId(), a -> a));
    this.wxCpServiceContainer.setWxCpServiceMap(map);
  }

  private WxCpMessageRouter newRouter(WxCpService wxCpService) {
    final val newRouter = new WxCpMessageRouter(wxCpService);
    if (wxCpMessageMatchHandlerList != null && wxCpMessageMatchHandlerList.size() > 0) {
      for (WxCpMessageMatchHandler wxCpMessageMatchHandler : wxCpMessageMatchHandlerList) {
        if (wxCpMessageMatchHandler.ignoreMatch()) {
          newRouter.rule().handler(wxCpMessageMatchHandler).next();
        } else {
          newRouter.rule().async(false).msgType(wxCpMessageMatchHandler.getMsgType())
            .event(wxCpMessageMatchHandler.getEventType())
            .handler(wxCpMessageMatchHandler)
            .end();
        }
      }
    }
    return newRouter;
  }
}

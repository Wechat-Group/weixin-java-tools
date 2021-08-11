package com.binaywang.spring.starter.wxjava.cp.config;

import com.binaywang.spring.starter.wxjava.cp.handler.WxCpTpMessageMatchHandler;
import com.binaywang.spring.starter.wxjava.cp.properties.WxCpTpProperties;
import lombok.val;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import me.chanjar.weixin.common.redis.RedissonWxRedisOps;
import me.chanjar.weixin.cp.config.impl.WxCpTpRedisConfigImpl;
import me.chanjar.weixin.cp.tp.message.WxCpTpMessageRouter;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.tp.service.impl.WxCpTpServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties(WxCpTpProperties.class)
public class WxCpTpConfiguration {

  private final List<WxCpTpMessageMatchHandler> wxCpTpMessageMatchHandlerList;

  private final WxCpTpProperties wxCpTpProperties;

  private final StringRedisTemplate stringRedisTemplate;

  private Map<String, WxCpTpService> wxCpTpServiceMap = new HashMap<>();
  private final Map<String, WxCpTpMessageRouter> routers = new HashMap<>();


  public WxCpTpConfiguration(List<WxCpTpMessageMatchHandler> wxCpTpMessageMatchHandlerList, WxCpTpProperties wxCpTpProperties, StringRedisTemplate stringRedisTemplate) {
    this.wxCpTpMessageMatchHandlerList = wxCpTpMessageMatchHandlerList;
    this.wxCpTpProperties = wxCpTpProperties;
    this.stringRedisTemplate = stringRedisTemplate;
  }

  public WxCpTpService getService(String suiteId) {
    return wxCpTpServiceMap.get(suiteId);
  }

  @PostConstruct
  public void initService() {
    wxCpTpServiceMap = this.wxCpTpProperties.getSuites().stream().map(prop -> {
      val configStorage =   WxCpTpRedisConfigImpl
        .builder()
        .suiteId(prop.getSuiteId())
        .suiteSecret(prop.getSuiteSecret())
        .token(prop.getToken())
        .aesKey(prop.getAesKey())
        .corpId(wxCpTpProperties.getCorpId())
        .providerSecret(wxCpTpProperties.getProviderSecret())
        .wxRedisOps(new RedisTemplateWxRedisOps(stringRedisTemplate))
        .keyPrefix("wx::cp::tp")
        .build();
      val service = new WxCpTpServiceImpl();
      service.setWxCpTpConfigStorage(configStorage);
      routers.put(prop.getSuiteId(), this.newRouter(service));
      return service;
    }).collect(Collectors.toMap(service -> service.getWxCpTpConfigStorage().getSuiteId(), a -> a));
  }

  private WxCpTpMessageRouter newRouter(WxCpTpService wxCpTpService) {
    val newRouter = new WxCpTpMessageRouter(wxCpTpService);
    if (wxCpTpMessageMatchHandlerList != null && wxCpTpMessageMatchHandlerList.size() > 0) {
      for (WxCpTpMessageMatchHandler wxCpTpMessageMatchHandler : wxCpTpMessageMatchHandlerList) {
        if (wxCpTpMessageMatchHandler.ignoreMatch()) {
          newRouter.rule().handler(wxCpTpMessageMatchHandler).next();
        } else {
          newRouter.rule().async(false).msgType(wxCpTpMessageMatchHandler.getMsgType())
            .event(wxCpTpMessageMatchHandler.getEventType())
            .handler(wxCpTpMessageMatchHandler)
            .end();
        }
      }
    }
    return newRouter;
  }
}

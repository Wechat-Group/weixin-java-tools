package com.binaywang.spring.starter.wxjava.cp.config;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.message.WxCpMessageRouter;
import me.chanjar.weixin.cp.util.crypto.WxCpCryptUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caiqy
 */
public class WxCpServiceContainer {
  private Map<Integer, WxCpService> wxCpServiceMap = new HashMap<>();

  private final Map<Integer, WxCpCryptUtil> wxCpCryptUtilMap = new HashMap<>();

  private Map<Integer, WxCpMessageRouter> routers = new HashMap<>();

  public WxCpService getCpService(Integer agentId) {
    return wxCpServiceMap.get(agentId);
  }

  public void setWxCpServiceMap(Map<Integer, WxCpService> wxCpServiceMap) {
    this.wxCpServiceMap = wxCpServiceMap;
  }

  public void setRouters(Map<Integer, WxCpMessageRouter> routers) {
    this.routers = routers;
  }

  public WxCpCryptUtil getCryptUtil(Integer agentId) {
    return wxCpCryptUtilMap.computeIfAbsent(agentId, si -> new WxCpCryptUtil(getCpService(si).getWxCpConfigStorage()));
  }

  public WxCpMessageRouter getRouter(Integer agentId) {
    return routers.get(agentId);
  }

}

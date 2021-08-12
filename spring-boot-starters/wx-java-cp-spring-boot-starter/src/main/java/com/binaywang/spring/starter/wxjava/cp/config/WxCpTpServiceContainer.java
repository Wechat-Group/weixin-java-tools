package com.binaywang.spring.starter.wxjava.cp.config;

import me.chanjar.weixin.cp.tp.service.WxCpTpService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caiqy
 */
public class WxCpTpServiceContainer {
  private Map<String, WxCpTpService> wxCpTpServiceMap = new HashMap<>();

  public WxCpTpService getTpService(String suiteId) {
    return wxCpTpServiceMap.get(suiteId);
  }

  public void setWxCpTpServiceMap(Map<String, WxCpTpService> wxCpTpServiceMap) {
    this.wxCpTpServiceMap = wxCpTpServiceMap;
  }
}

package com.binaywang.spring.starter.wxjava.cp.config;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.util.crypto.WxCpCryptUtil;
import me.chanjar.weixin.cp.util.crypto.WxCpTpCryptUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caiqy
 */
public class WxCpServiceContainer {
  private Map<Integer, WxCpService> wxCpServiceMap = new HashMap<>();

  private Map<Integer, WxCpCryptUtil> wxCpCryptUtilMap = new HashMap<>();

  public WxCpService getCpService(Integer agentId) {
    return wxCpServiceMap.get(agentId);
  }

  public void setWxCpServiceMap(Map<Integer, WxCpService> wxCpServiceMap) {
    this.wxCpServiceMap = wxCpServiceMap;
  }

  public WxCpCryptUtil getCryptUtil(Integer agentId) {
    return wxCpCryptUtilMap.computeIfAbsent(agentId, si -> new WxCpCryptUtil(getCpService(si).getWxCpConfigStorage()));
  }
}

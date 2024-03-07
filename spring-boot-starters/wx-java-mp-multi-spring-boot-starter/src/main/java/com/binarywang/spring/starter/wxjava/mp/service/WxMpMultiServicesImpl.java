package com.binarywang.spring.starter.wxjava.mp.service;

import me.chanjar.weixin.mp.api.WxMpService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 企业微信 {@link WxMpMultiServices} 默认实现
 *
 * @author yl
 * created on 2024/1/23
 */
public class WxMpMultiServicesImpl implements WxMpMultiServices {
  private final Map<String, WxMpService> services = new ConcurrentHashMap<>();

  @Override
  public WxMpService getWxMpService(String tenantId) {
    return this.services.get(tenantId);
  }

  /**
   * 根据租户 Id，添加一个 WxMpService 到列表
   *
   * @param tenantId    租户 Id
   * @param wxMpService WxMpService 实例
   */
  public void addWxMpService(String tenantId, WxMpService wxMpService) {
    this.services.put(tenantId, wxMpService);
  }

  @Override
  public void removeWxMpService(String tenantId) {
    this.services.remove(tenantId);
  }
}

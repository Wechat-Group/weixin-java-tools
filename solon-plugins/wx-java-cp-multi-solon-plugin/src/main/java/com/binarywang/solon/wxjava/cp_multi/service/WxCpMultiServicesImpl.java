package com.binarywang.solon.wxjava.cp_multi.service;

import me.chanjar.weixin.cp.api.WxCpService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 企业微信 {@link WxCpMultiServices} 默认实现
 *
 * @author yl
 * created on 2023/10/16
 */
public class WxCpMultiServicesImpl implements WxCpMultiServices {
  private final Map<String, WxCpService> services = new ConcurrentHashMap<>();

  /**
   * 通过租户 Id 获取 WxCpService
   *
   * @param tenantId 租户 Id
   * @return WxCpService
   */
  @Override
  public WxCpService getWxCpService(String tenantId) {
    return this.services.get(tenantId);
  }

  /**
   * 根据租户 Id，添加一个 WxCpService 到列表
   *
   * @param tenantId    租户 Id
   * @param wxCpService WxCpService 实例
   */
  public void addWxCpService(String tenantId, WxCpService wxCpService) {
    this.services.put(tenantId, wxCpService);
  }

  @Override
  public void removeWxCpService(String tenantId) {
    this.services.remove(tenantId);
  }
}

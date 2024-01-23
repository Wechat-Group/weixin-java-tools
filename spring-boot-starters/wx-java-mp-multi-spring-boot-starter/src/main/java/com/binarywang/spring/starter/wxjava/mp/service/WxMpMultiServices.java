package com.binarywang.spring.starter.wxjava.mp.service;


import me.chanjar.weixin.mp.api.WxMpService;

/**
 * 企业微信 {@link WxMpService} 所有实例存放类.
 *
 * @author yl
 * created on 2024/1/23
 */
public interface WxMpMultiServices {
  /**
   * 通过租户 Id 获取 WxCpService
   *
   * @param tenantId 租户 Id
   * @return WxCpService
   */
  WxMpService getWxMpService(String tenantId);

  /**
   * 根据租户 Id，从列表中移除一个 WxCpService 实例
   *
   * @param tenantId 租户 Id
   */
  void removeWxMpService(String tenantId);
}

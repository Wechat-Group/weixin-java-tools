package com.binarywang.solon.wxjava.cp_multi.service;

import me.chanjar.weixin.cp.api.WxCpService;

/**
 * 企业微信 {@link WxCpService} 所有实例存放类.
 *
 * @author yl
 * created on 2023/10/16
 */
public interface WxCpMultiServices {
  /**
   * 通过租户 Id 获取 WxCpService
   *
   * @param tenantId 租户 Id
   * @return WxCpService
   */
  WxCpService getWxCpService(String tenantId);

  /**
   * 根据租户 Id，从列表中移除一个 WxCpService 实例
   *
   * @param tenantId 租户 Id
   */
  void removeWxCpService(String tenantId);
}

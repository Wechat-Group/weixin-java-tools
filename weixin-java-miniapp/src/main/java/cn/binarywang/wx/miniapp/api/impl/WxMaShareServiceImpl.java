package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaShareService;
import cn.binarywang.wx.miniapp.bean.WxMaShareInfo;
import cn.binarywang.wx.miniapp.util.crypt.WxMaCryptUtils;

/**
 * @author zhfish
 */
public class WxMaShareServiceImpl implements WxMaShareService {
  private WxMaService service;

  public WxMaShareServiceImpl(WxMaService service) {
    this.service = service;
  }

  @Override
  public WxMaShareInfo getShareInfo(String sessionKey, String encryptedData, String ivStr) {
    return WxMaShareInfo.fromJson(WxMaCryptUtils.decrypt(sessionKey, encryptedData, ivStr));

  }
}

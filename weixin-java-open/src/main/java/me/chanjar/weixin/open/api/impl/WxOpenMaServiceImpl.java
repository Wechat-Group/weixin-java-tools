package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.open.api.WxOpenComponentService;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
/* package */ class WxOpenMaServiceImpl extends WxMaServiceImpl {
  private WxOpenComponentService wxOpenComponentService;
  private WxMaConfig wxMaConfig;
  private String appId;

  public WxOpenMaServiceImpl(WxOpenComponentService wxOpenComponentService, String appId, WxMaConfig wxMaConfig) {
    this.wxOpenComponentService = wxOpenComponentService;
    this.appId = appId;
    this.wxMaConfig = wxMaConfig;
    initHttp();
  }

  @Override
  public WxMaConfig getWxMaConfig() {
    return wxMaConfig;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    return wxOpenComponentService.getAuthorizerAccessToken(appId, forceRefresh);
  }

}

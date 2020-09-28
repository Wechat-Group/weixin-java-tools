package me.chanjar.weixin.open.api.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.open.api.WxOpenComponentService;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenMpServiceImpl extends WxMpServiceImpl {
  private WxOpenComponentService wxOpenComponentService;
  private WxMpConfigStorage wxMpConfigStorage;
  private String appId;

  public WxOpenMpServiceImpl(WxOpenComponentService wxOpenComponentService, String appId, WxMpConfigStorage wxMpConfigStorage) {
    this.wxOpenComponentService = wxOpenComponentService;
    this.appId = appId;
    this.wxMpConfigStorage = wxMpConfigStorage;
    initHttp();
  }

  @Override
  public WxMpConfigStorage getWxMpConfigStorage() {
    return wxMpConfigStorage;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    return wxOpenComponentService.getAuthorizerAccessToken(appId, forceRefresh);
  }

}

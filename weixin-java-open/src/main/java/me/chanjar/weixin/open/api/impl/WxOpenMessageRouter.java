package me.chanjar.weixin.open.api.impl;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.open.api.WxOpenService;

import java.util.HashMap;
import java.util.Map;

public class WxOpenMessageRouter extends WxMpMessageRouter {
  private WxOpenService wxOpenService;

  public WxOpenMessageRouter(WxOpenService wxOpenService) {
    super(null);
    this.wxOpenService = wxOpenService;
  }

  public WxMpXmlOutMessage route(final WxMpXmlMessage wxMessage, String appId) {
    return route(wxMessage, new HashMap<>(), appId);
  }

  public WxMpXmlOutMessage route(final WxMpXmlMessage wxMessage, final Map<String, Object> context, String appId) {
    return route(wxMessage, context, wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId));
  }
}

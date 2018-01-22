package me.chanjar.weixin.mp.api.impl;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.URIUtil;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpSubscribeMsgService;
import me.chanjar.weixin.mp.bean.subscribe.WxMpSubscribeMessage;

/**
 * @author Mklaus
 * @date 2018-01-22 上午11:19
 */
public class WxMpSubscribeMsgServiceImpl implements WxMpSubscribeMsgService {
  private static final String SUBSCRIBE_MESSAGE_AUTHORIZE_URL =
    "https://mp.weixin.qq.com/mp/subscribemsg?action=get_confirm&appid=%s&scene=%d&template_id=%s&redirect_url=%s&reserved=%s#wechat_redirect";
  private static final String SEND_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/subscribe";


  private WxMpService wxMpService;

  public WxMpSubscribeMsgServiceImpl(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
  }

  @Override
  public String subscribeMsgAuthorizationUrl(String redirectURI, int scene, String reserved) {
    WxMpConfigStorage storage = this.wxMpService.getWxMpConfigStorage();
    return String.format(SUBSCRIBE_MESSAGE_AUTHORIZE_URL,
      storage.getAppId(), scene, storage.getTemplateId(), URIUtil.encodeURIComponent(redirectURI), reserved);
  }

  @Override
  public boolean sendSubscribeMessage(WxMpSubscribeMessage message) throws WxErrorException {
    if (message.getTemplateId() == null && message.getTemplateId().trim().length() == 0) {
      message.setTemplateId(this.wxMpService.getWxMpConfigStorage().getTemplateId());
    }

    String responseContent = this.wxMpService.post(SEND_MESSAGE_URL, message.toJson());
    return responseContent != null;
  }
}

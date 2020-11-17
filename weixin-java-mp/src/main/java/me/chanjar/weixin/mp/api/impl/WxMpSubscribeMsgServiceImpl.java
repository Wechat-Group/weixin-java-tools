package me.chanjar.weixin.mp.api.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.URIUtil;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpSubscribeMsgService;
import me.chanjar.weixin.mp.bean.subscribe.WxMpSubscribeMessage;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.SubscribeMsg.SEND_MESSAGE_URL;
import static me.chanjar.weixin.mp.enums.WxMpApiUrl.SubscribeMsg.SUBSCRIBE_MESSAGE_AUTHORIZE_URL;

/**
 * 一次性订阅消息接口.
 *
 * @author Mklaus
 * @date 2018-01-22 上午11:19
 */
@RequiredArgsConstructor
public class WxMpSubscribeMsgServiceImpl implements WxMpSubscribeMsgService {
  private final WxMpService wxMpService;

  @Override
  public String subscribeMsgAuthorizationUrl(String redirectURI, int scene, String reserved) {
    WxMpConfigStorage storage = this.wxMpService.getWxMpConfigStorage();
    return String.format(SUBSCRIBE_MESSAGE_AUTHORIZE_URL.getUrl(storage), storage.getAppId(), scene, storage.getTemplateId(),
      URIUtil.encodeURIComponent(redirectURI), reserved);
  }

  @Override
  public boolean sendSubscribeMessage(WxMpSubscribeMessage message) throws WxErrorException {
    if (message.getTemplateId() == null) {
      message.setTemplateId(this.wxMpService.getWxMpConfigStorage().getTemplateId());
    }

    String responseContent = this.wxMpService.post(SEND_MESSAGE_URL, message.toJson());
    return responseContent != null;
  }
}

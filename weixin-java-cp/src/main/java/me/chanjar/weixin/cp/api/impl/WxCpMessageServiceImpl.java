package me.chanjar.weixin.cp.api.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpMessageService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.cp.bean.WxCpMessageSendResult;

/**
 * 实现消息推送接口的封装
 *
 * @author gaigeshen
 */
public class WxCpMessageServiceImpl implements WxCpMessageService {

  private WxCpService internalService;

  private static final String URI = "https://qyapi.weixin.qq.com/cgi-bin/message/send";

  /**
   * 构造实现消息推送接口的封装
   *
   * @param internalService 企业微信服务
   */
  public WxCpMessageServiceImpl(WxCpService internalService) {
    this.internalService = internalService;
  }

  @Override
  public WxCpMessageSendResult sendApplicationMessage(WxCpMessage message) throws WxErrorException {
    String result = internalService.post(URI, message.toJson());
    return WxCpMessageSendResult.fromJson(result);
  }
}

package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.cp.bean.WxCpMessageSendResult;

/**
 * 消息推送服务
 *
 * @author gaigeshen
 */
public interface WxCpMessageService {

  /**
   * 发送应用消息
   *
   * @param message 消息
   * @return 发送结果
   * @throws WxErrorException 发生异常
   */
  WxCpMessageSendResult sendApplicationMessage(WxCpMessage message) throws WxErrorException;

}

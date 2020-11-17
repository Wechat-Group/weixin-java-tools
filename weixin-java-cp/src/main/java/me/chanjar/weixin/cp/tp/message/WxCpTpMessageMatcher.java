package me.chanjar.weixin.cp.tp.message;

import me.chanjar.weixin.cp.bean.message.WxCpTpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;

/**
 * 消息匹配器，用在消息路由的时候
 *
 * @author Daniel Qian
 */
public interface WxCpTpMessageMatcher {

  /**
   * 消息是否匹配某种模式
   *
   * @param message the message
   * @return the boolean
   */
  boolean match(WxCpTpXmlMessage message);

}

package com.binaywang.spring.starter.wxjava.cp.handler;

import me.chanjar.weixin.cp.message.WxCpMessageHandler;

/**
 * The interface Wx cp message match handler.
 *
 * @author caiqy
 */
public interface WxCpMessageMatchHandler extends WxCpMessageHandler {

  /**
   * 获取指定事件类型
   *
   * @return event type
   */
  String getEventType();

  /**
   * 获取指定消息类型
   *
   * @return msg type
   */
  String getMsgType();

  /**
   * 忽略匹配,只要是true,都会执行
   *
   * @return the boolean
   */
  boolean ignoreMatch();

}

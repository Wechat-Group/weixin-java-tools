package com.binaywang.spring.starter.wxjava.cp.handler;

import me.chanjar.weixin.cp.tp.message.WxCpTpMessageHandler;

/**
 * The interface Wx cp message match handler.
 *
 * @author caiqy
 */
public interface WxCpTpMessageMatchHandler extends WxCpTpMessageHandler {

  //---------------------------------------------------------------------
  // 每个函数之间是 与 关系
  // #ignoreMatch() 方法返回true时,所有函数都无效,这个handler都会被执行
  //---------------------------------------------------------------------

  /**
   * 通知类型
   *
   * @return
   */
  String getInfoType();

  /**
   * 获取info type子类
   *
   * @return
   */
  String getChangeType();

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

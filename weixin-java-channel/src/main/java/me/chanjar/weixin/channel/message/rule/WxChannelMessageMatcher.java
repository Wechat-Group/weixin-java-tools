package me.chanjar.weixin.channel.message.rule;

import me.chanjar.weixin.channel.message.WxChannelMessage;

/**
 * 消息匹配器，用在消息路由的时候
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxChannelMessageMatcher {

  /**
   * 消息是否匹配某种模式
   *
   * @param message 消息
   * @return 是否匹配
   */
  boolean match(WxChannelMessage message);

}

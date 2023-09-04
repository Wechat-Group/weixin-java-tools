package me.chanjar.weixin.channel.message.rule;

import me.chanjar.weixin.channel.message.WxChannelMessage;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@FunctionalInterface
public interface HandlerConsumer<T extends WxChannelMessage, U, V, W, X> {

  void accept(T t, U u, V v, W w, X x);
}

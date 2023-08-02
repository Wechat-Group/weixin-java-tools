package me.chanjar.weixin.channel.message.rule;

import java.util.Map;
import me.chanjar.weixin.channel.message.WxChannelMessage;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;

/**
 * 处理视频号推送消息的处理器
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxChannelMessageHandler<T extends WxChannelMessage> {

  /**
   * 处理消息
   *
   * @param message        消息
   * @param content        消息原始内容
   * @param appId          appId
   * @param context        上下文
   * @param sessionManager session管理器
   * @return 输出消息 格式可能是String/Xml/Json，视情况而定
   *
   * @throws WxErrorException 异常
   */
  Object handle(T message, String content, String appId, Map<String, Object> context, WxSessionManager sessionManager)
    throws WxErrorException;

}

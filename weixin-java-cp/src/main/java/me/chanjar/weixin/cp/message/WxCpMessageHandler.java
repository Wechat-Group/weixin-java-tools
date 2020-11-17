package me.chanjar.weixin.cp.message;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;

import java.util.Map;

/**
 * 处理微信推送消息的处理器接口
 *
 * @author Daniel Qian
 */
public interface WxCpMessageHandler {

  /**
   * Handle wx cp xml out message.
   *
   * @param wxMessage      the wx message
   * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
   * @param wxCpService    the wx cp service
   * @param sessionManager the session manager
   * @return xml格式的消息 ，如果在异步规则里处理的话，可以返回null
   * @throws WxErrorException the wx error exception
   */
  WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage,
                           Map<String, Object> context,
                           WxCpService wxCpService,
                           WxSessionManager sessionManager) throws WxErrorException;

}

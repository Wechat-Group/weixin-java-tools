package me.chanjar.weixin.cp.tp.message;

import lombok.Data;
import me.chanjar.weixin.common.api.WxErrorExceptionHandler;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.bean.message.WxCpTpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.message.WxCpMessageRouterRule;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Pattern;

/**
 * The type Wx cp message router rule.
 *
 * @author Daniel Qian
 */
@Data
public class WxCpTpMessageRouterRule {
  private final WxCpTpMessageRouter routerBuilder;

  private boolean async = true;

  private String fromUser;

  private String msgType;

  private String event;

  private String eventKey;

  private String eventKeyRegex;

  private String content;

  private String rContent;

  private WxCpTpMessageMatcher matcher;

  private boolean reEnter = false;

  private Integer agentId;

  private String infoType;

  private String changeType;

  private List<WxCpTpMessageHandler> handlers = new ArrayList<>();

  private List<WxCpTpMessageInterceptor> interceptors = new ArrayList<>();
  private String suiteId;
  private String authCode;
  private String suiteTicket;

  /**
   * Instantiates a new Wx cp message router rule.
   *
   * @param routerBuilder the router builder
   */
  protected WxCpTpMessageRouterRule(WxCpTpMessageRouter routerBuilder) {
    this.routerBuilder = routerBuilder;
  }

  /**
   * 设置是否异步执行，默认是true
   *
   * @param async the async
   * @return the wx cp message router rule
   */
  public WxCpTpMessageRouterRule async(boolean async) {
    this.async = async;
    return this;
  }

  /**
   * 如果msgType等于某值
   *
   * @param msgType the msg type
   * @return the wx cp tp message router rule
   */
  public WxCpTpMessageRouterRule msgType(String msgType) {
    this.msgType = msgType;
    return this;
  }

  /**
   * 如果event等于某值
   *
   * @param event the event
   * @return the wx cp tp message router rule
   */
  public WxCpTpMessageRouterRule event(String event) {
    this.event = event;
    return this;
  }

  /**
   * 匹配 Message infoType
   *
   * @param infoType info
   */
  public WxCpTpMessageRouterRule infoType(String infoType) {
    this.infoType = infoType;
    return this;
  }

  /**
   * 如果changeType等于这个type，符合rule的条件之一
   * @param changeType
   * @return
   */
  public WxCpTpMessageRouterRule changeType(String changeType) {
    this.changeType = changeType;
    return this;
  }


  /**
   * 如果消息匹配某个matcher，用在用户需要自定义更复杂的匹配规则的时候
   *
   * @param matcher the matcher
   * @return the wx cp message router rule
   */
  public WxCpTpMessageRouterRule matcher(WxCpTpMessageMatcher matcher) {
    this.matcher = matcher;
    return this;
  }

  /**
   * 设置微信消息拦截器
   *
   * @param interceptor the interceptor
   * @return the wx cp message router rule
   */
  public WxCpTpMessageRouterRule interceptor(WxCpTpMessageInterceptor interceptor) {
    return interceptor(interceptor, (WxCpTpMessageInterceptor[]) null);
  }

  /**
   * 设置微信消息拦截器
   *
   * @param interceptor       the interceptor
   * @param otherInterceptors the other interceptors
   * @return the wx cp message router rule
   */
  public WxCpTpMessageRouterRule interceptor(WxCpTpMessageInterceptor interceptor, WxCpTpMessageInterceptor... otherInterceptors) {
    this.interceptors.add(interceptor);
    if (otherInterceptors != null && otherInterceptors.length > 0) {
      Collections.addAll(this.interceptors, otherInterceptors);
    }
    return this;
  }

  /**
   * 设置微信消息处理器
   *
   * @param handler the handler
   * @return the wx cp message router rule
   */
  public WxCpTpMessageRouterRule handler(WxCpTpMessageHandler handler) {
    return handler(handler, (WxCpTpMessageHandler[]) null);
  }

  /**
   * 设置微信消息处理器
   *
   * @param handler       the handler
   * @param otherHandlers the other handlers
   * @return the wx cp message router rule
   */
  public WxCpTpMessageRouterRule handler(WxCpTpMessageHandler handler, WxCpTpMessageHandler... otherHandlers) {
    this.handlers.add(handler);
    if (otherHandlers != null && otherHandlers.length > 0) {
      Collections.addAll(this.handlers, otherHandlers);
    }
    return this;
  }

  /**
   * 规则结束，代表如果一个消息匹配该规则，那么它将不再会进入其他规则
   *
   * @return the wx cp message router
   */
  public WxCpTpMessageRouter end() {
    this.routerBuilder.getRules().add(this);
    return this.routerBuilder;
  }

  /**
   * 规则结束，但是消息还会进入其他规则
   *
   * @return the wx cp message router
   */
  public WxCpTpMessageRouter next() {
    this.reEnter = true;
    return end();
  }

  /**
   * Test boolean.
   *
   * @param wxMessage the wx message
   * @return the boolean
   */
  protected boolean test(WxCpTpXmlMessage wxMessage) {
    return
      (this.suiteId == null || this.suiteId.equals(wxMessage.getSuiteId()))
        &&
        (this.fromUser == null || this.fromUser.equals(wxMessage.getFromUserName()))
        &&
        (this.agentId == null || this.agentId.equals(wxMessage.getAgentID()))
        &&
        (this.msgType == null || this.msgType.equalsIgnoreCase(wxMessage.getMsgType()))
        &&
        (this.event == null || this.event.equalsIgnoreCase(wxMessage.getEvent()))
        &&
        (this.infoType == null || this.infoType.equals(wxMessage.getInfoType()))
        &&
        (this.suiteTicket == null || this.suiteTicket.equalsIgnoreCase(wxMessage.getSuiteTicket()))
        &&
        (this.eventKeyRegex == null || Pattern.matches(this.eventKeyRegex, StringUtils.trimToEmpty(wxMessage.getEventKey())))
        &&
        (this.content == null || this.content.equals(StringUtils.trimToNull(wxMessage.getContent())))
        &&
        (this.rContent == null || Pattern.matches(this.rContent, StringUtils.trimToEmpty(wxMessage.getContent())))
        &&
        (this.infoType == null || this.infoType.equals(wxMessage.getInfoType()))
        &&
        (this.changeType == null || this.changeType.equals(wxMessage.getChangeType()))
        &&
        (this.matcher == null || this.matcher.match(wxMessage))
        &&
        (this.authCode == null || this.authCode.equalsIgnoreCase(wxMessage.getAuthCode()));
  }

  /**
   * 处理微信推送过来的消息
   *
   * @param wxMessage        the wx message
   * @param context          the context
   * @param wxCpService      the wx cp service
   * @param sessionManager   the session manager
   * @param exceptionHandler the exception handler
   * @return true 代表继续执行别的router，false 代表停止执行别的router
   */
  protected WxCpXmlOutMessage service(WxCpTpXmlMessage wxMessage,
                                      Map<String, Object> context,
                                      WxCpTpService wxCpService,
                                      WxSessionManager sessionManager,
                                      WxErrorExceptionHandler exceptionHandler) {
    if (context == null) {
      context = new HashMap<>(2);
    }

    try {
      // 如果拦截器不通过
      for (WxCpTpMessageInterceptor interceptor : this.interceptors) {
        if (!interceptor.intercept(wxMessage, context, wxCpService, sessionManager)) {
          return null;
        }
      }

      // 交给handler处理
      WxCpXmlOutMessage res = null;
      for (WxCpTpMessageHandler handler : this.handlers) {
        // 返回最后handler的结果
        res = handler.handle(wxMessage, context, wxCpService, sessionManager);
      }
      return res;

    } catch (WxErrorException e) {
      exceptionHandler.handle(e);
    }

    return null;

  }


}

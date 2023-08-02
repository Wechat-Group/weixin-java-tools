package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.api.WxErrorExceptionHandler;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Pattern;

/**
 * The type Wx mp message router rule.
 */
public class WxMpMessageRouterRule {

  private final WxMpMessageRouter routerBuilder;

  private boolean async = true;

  private String fromUser;

  private String msgType;

  private String event;

  private String eventKey;

  private String eventKeyRegex;

  private String content;

  private String rContent;

  private WxMpMessageMatcher matcher;

  private boolean reEnter = false;

  private List<WxMpMessageHandler> handlers = new ArrayList<>();

  private List<WxMpMessageInterceptor> interceptors = new ArrayList<>();

    /**
     * Instantiates a new Wx mp message router rule.
     *
     * @param routerBuilder the router builder
     */
    public WxMpMessageRouterRule(WxMpMessageRouter routerBuilder) {
    this.routerBuilder = routerBuilder;
  }

    /**
     * 设置是否异步执行，默认是true
     *
     * @param async the async
     * @return the wx mp message router rule
     */
    public WxMpMessageRouterRule async(boolean async) {
    this.async = async;
    return this;
  }

    /**
     * 如果msgType等于某值
     *
     * @param msgType the msg type
     * @return the wx mp message router rule
     */
    public WxMpMessageRouterRule msgType(String msgType) {
    this.msgType = msgType;
    return this;
  }

    /**
     * 如果event等于某值
     *
     * @param event the event
     * @return the wx mp message router rule
     */
    public WxMpMessageRouterRule event(String event) {
    this.event = event;
    return this;
  }

    /**
     * 如果eventKey等于某值
     *
     * @param eventKey the event key
     * @return the wx mp message router rule
     */
    public WxMpMessageRouterRule eventKey(String eventKey) {
    this.eventKey = eventKey;
    return this;
  }

    /**
     * 如果eventKey匹配该正则表达式
     *
     * @param regex the regex
     * @return the wx mp message router rule
     */
    public WxMpMessageRouterRule eventKeyRegex(String regex) {
    this.eventKeyRegex = regex;
    return this;
  }

    /**
     * 如果content等于某值
     *
     * @param content the content
     * @return the wx mp message router rule
     */
    public WxMpMessageRouterRule content(String content) {
    this.content = content;
    return this;
  }

    /**
     * 如果content匹配该正则表达式
     *
     * @param regex the regex
     * @return the wx mp message router rule
     */
    public WxMpMessageRouterRule rContent(String regex) {
    this.rContent = regex;
    return this;
  }

    /**
     * 如果fromUser等于某值
     *
     * @param fromUser the from user
     * @return the wx mp message router rule
     */
    public WxMpMessageRouterRule fromUser(String fromUser) {
    this.fromUser = fromUser;
    return this;
  }

    /**
     * 如果消息匹配某个matcher，用在用户需要自定义更复杂的匹配规则的时候
     *
     * @param matcher the matcher
     * @return the wx mp message router rule
     */
    public WxMpMessageRouterRule matcher(WxMpMessageMatcher matcher) {
    this.matcher = matcher;
    return this;
  }

    /**
     * 设置微信消息拦截器
     *
     * @param interceptor the interceptor
     * @return the wx mp message router rule
     */
    public WxMpMessageRouterRule interceptor(WxMpMessageInterceptor interceptor) {
    return interceptor(interceptor, (WxMpMessageInterceptor[]) null);
  }

    /**
     * 设置微信消息拦截器
     *
     * @param interceptor       the interceptor
     * @param otherInterceptors the other interceptors
     * @return the wx mp message router rule
     */
    public WxMpMessageRouterRule interceptor(WxMpMessageInterceptor interceptor, WxMpMessageInterceptor... otherInterceptors) {
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
     * @return the wx mp message router rule
     */
    public WxMpMessageRouterRule handler(WxMpMessageHandler handler) {
    return handler(handler, (WxMpMessageHandler[]) null);
  }

    /**
     * 设置微信消息处理器
     *
     * @param handler       the handler
     * @param otherHandlers the other handlers
     * @return the wx mp message router rule
     */
    public WxMpMessageRouterRule handler(WxMpMessageHandler handler, WxMpMessageHandler... otherHandlers) {
    this.handlers.add(handler);
    if (otherHandlers != null && otherHandlers.length > 0) {
      for (WxMpMessageHandler i : otherHandlers) {
        this.handlers.add(i);
      }
    }
    return this;
  }

    /**
     * 规则结束，代表如果一个消息匹配该规则，那么它将不再会进入其他规则
     *
     * @return the wx mp message router
     */
    public WxMpMessageRouter end() {
    this.routerBuilder.getRules().add(this);
    return this.routerBuilder;
  }

    /**
     * 规则结束，但是消息还会进入其他规则
     *
     * @return the wx mp message router
     */
    public WxMpMessageRouter next() {
    this.reEnter = true;
    return end();
  }

    /**
     * 将微信自定义的事件修正为不区分大小写,
     * 比如框架定义的事件常量为click，但微信传递过来的却是CLICK
     *
     * @param wxMessage the wx message
     * @return the boolean
     */
    protected boolean test(WxMpXmlMessage wxMessage) {
    return
      (this.fromUser == null || this.fromUser.equals(wxMessage.getFromUser()))
        &&
        (this.msgType == null || this.msgType.equalsIgnoreCase(wxMessage.getMsgType()))
        &&
        (this.event == null || this.event.equalsIgnoreCase(wxMessage.getEvent()))
        &&
        (this.eventKey == null || this.eventKey.equalsIgnoreCase(wxMessage.getEventKey()))
        &&
        (this.eventKeyRegex == null || Pattern.matches(this.eventKeyRegex, StringUtils.trimToEmpty(wxMessage.getEventKey())))
        &&
        (this.content == null || this.content.equals(StringUtils.trimToNull(wxMessage.getContent())))
        &&
        (this.rContent == null || Pattern.matches(this.rContent, StringUtils.trimToEmpty(wxMessage.getContent())))
        &&
        (this.matcher == null || this.matcher.match(wxMessage))
      ;
  }

    /**
     * 处理微信推送过来的消息
     *
     * @param wxMessage        the wx message
     * @param context          the context
     * @param wxMpService      the wx mp service
     * @param sessionManager   the session manager
     * @param exceptionHandler the exception handler
     * @return true 代表继续执行别的router，false 代表停止执行别的router
     */
    protected WxMpXmlOutMessage service(WxMpXmlMessage wxMessage,
                                      Map<String, Object> context,
                                      WxMpService wxMpService,
                                      WxSessionManager sessionManager,
                                      WxErrorExceptionHandler exceptionHandler) {

    if (context == null) {
      context = new HashMap<>();
    }

    try {
      // 如果拦截器不通过
      for (WxMpMessageInterceptor interceptor : this.interceptors) {
        if (!interceptor.intercept(wxMessage, context, wxMpService, sessionManager)) {
          return null;
        }
      }

      // 交给handler处理
      WxMpXmlOutMessage res = null;
      for (WxMpMessageHandler handler : this.handlers) {
        // 返回最后handler的结果
        if (handler == null) {
          continue;
        }
        res = handler.handle(wxMessage, context, wxMpService, sessionManager);
      }
      return res;
    } catch (WxErrorException e) {
      exceptionHandler.handle(e);
    }
    return null;

  }

    /**
     * Gets router builder.
     *
     * @return the router builder
     */
    public WxMpMessageRouter getRouterBuilder() {
    return this.routerBuilder;
  }

    /**
     * Is async boolean.
     *
     * @return the boolean
     */
    public boolean isAsync() {
    return this.async;
  }

    /**
     * Sets async.
     *
     * @param async the async
     */
    public void setAsync(boolean async) {
    this.async = async;
  }

    /**
     * Gets from user.
     *
     * @return the from user
     */
    public String getFromUser() {
    return this.fromUser;
  }

    /**
     * Sets from user.
     *
     * @param fromUser the from user
     */
    public void setFromUser(String fromUser) {
    this.fromUser = fromUser;
  }

    /**
     * Gets msg type.
     *
     * @return the msg type
     */
    public String getMsgType() {
    return this.msgType;
  }

    /**
     * Sets msg type.
     *
     * @param msgType the msg type
     */
    public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

    /**
     * Gets event.
     *
     * @return the event
     */
    public String getEvent() {
    return this.event;
  }

    /**
     * Sets event.
     *
     * @param event the event
     */
    public void setEvent(String event) {
    this.event = event;
  }

    /**
     * Gets event key.
     *
     * @return the event key
     */
    public String getEventKey() {
    return this.eventKey;
  }

    /**
     * Sets event key.
     *
     * @param eventKey the event key
     */
    public void setEventKey(String eventKey) {
    this.eventKey = eventKey;
  }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
    return this.content;
  }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(String content) {
    this.content = content;
  }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getrContent() {
    return this.rContent;
  }

    /**
     * Sets content.
     *
     * @param rContent the r content
     */
    public void setrContent(String rContent) {
    this.rContent = rContent;
  }

    /**
     * Gets matcher.
     *
     * @return the matcher
     */
    public WxMpMessageMatcher getMatcher() {
    return this.matcher;
  }

    /**
     * Sets matcher.
     *
     * @param matcher the matcher
     */
    public void setMatcher(WxMpMessageMatcher matcher) {
    this.matcher = matcher;
  }

    /**
     * Is re enter boolean.
     *
     * @return the boolean
     */
    public boolean isReEnter() {
    return this.reEnter;
  }

    /**
     * Sets re enter.
     *
     * @param reEnter the re enter
     */
    public void setReEnter(boolean reEnter) {
    this.reEnter = reEnter;
  }

    /**
     * Gets handlers.
     *
     * @return the handlers
     */
    public List<WxMpMessageHandler> getHandlers() {
    return this.handlers;
  }

    /**
     * Sets handlers.
     *
     * @param handlers the handlers
     */
    public void setHandlers(List<WxMpMessageHandler> handlers) {
    this.handlers = handlers;
  }

    /**
     * Gets interceptors.
     *
     * @return the interceptors
     */
    public List<WxMpMessageInterceptor> getInterceptors() {
    return this.interceptors;
  }

    /**
     * Sets interceptors.
     *
     * @param interceptors the interceptors
     */
    public void setInterceptors(List<WxMpMessageInterceptor> interceptors) {
    this.interceptors = interceptors;
  }
}

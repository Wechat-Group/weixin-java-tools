package me.chanjar.weixin.cp.message;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxErrorExceptionHandler;
import me.chanjar.weixin.common.api.WxMessageDuplicateChecker;
import me.chanjar.weixin.common.api.WxMessageInMemoryDuplicateChecker;
import me.chanjar.weixin.common.session.InternalSession;
import me.chanjar.weixin.common.session.InternalSessionManager;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.LogExceptionHandler;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * <pre>
 * 微信消息路由器，通过代码化的配置，把来自微信的消息交给handler处理
 *
 * 说明：
 * 1. 配置路由规则时要按照从细到粗的原则，否则可能消息可能会被提前处理
 * 2. 默认情况下消息只会被处理一次，除非使用 {@link WxCpMessageRouterRule#next()}
 * 3. 规则的结束必须用{@link WxCpMessageRouterRule#end()}或者{@link WxCpMessageRouterRule#next()}，否则不会生效
 *
 * 使用方法：
 * WxCpMessageRouter router = new WxCpMessageRouter();
 * router
 *   .rule()
 *       .msgType("MSG_TYPE").event("EVENT").eventKey("EVENT_KEY").content("CONTENT")
 *       .interceptor(interceptor, ...).handler(handler, ...)
 *   .end()
 *   .rule()
 *       // 另外一个匹配规则
 *   .end()
 * ;
 *
 * // 将WxXmlMessage交给消息路由器
 * router.route(message);
 *
 * </pre>
 *
 * @author Daniel Qian
 */
@Slf4j
public class WxCpMessageRouter {
  private static final int DEFAULT_THREAD_POOL_SIZE = 100;
  private final List<WxCpMessageRouterRule> rules = new ArrayList<>();

  private final WxCpService wxCpService;

  private ExecutorService executorService;

  private WxMessageDuplicateChecker messageDuplicateChecker;

  private WxSessionManager sessionManager;

  private WxErrorExceptionHandler exceptionHandler;

  /**
   * 构造方法.
   */
  public WxCpMessageRouter(WxCpService wxCpService) {
    this.wxCpService = wxCpService;
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("WxCpMessageRouter-pool-%d").build();
    this.executorService = new ThreadPoolExecutor(DEFAULT_THREAD_POOL_SIZE, DEFAULT_THREAD_POOL_SIZE,
      0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
    this.messageDuplicateChecker = new WxMessageInMemoryDuplicateChecker(30);
    this.sessionManager = wxCpService.getSessionManager();
    this.exceptionHandler = new LogExceptionHandler();
  }

  /**
   * <pre>
   * 设置自定义的 {@link ExecutorService}
   * 如果不调用该方法，默认使用 Executors.newFixedThreadPool(100)
   * </pre>
   */
  public void setExecutorService(ExecutorService executorService) {
    this.executorService = executorService;
  }

  /**
   * <pre>
   * 设置自定义的 {@link me.chanjar.weixin.common.api.WxMessageDuplicateChecker}
   * 如果不调用该方法，默认使用 {@link me.chanjar.weixin.common.api.WxMessageInMemoryDuplicateChecker}
   * </pre>
   */
  public void setMessageDuplicateChecker(WxMessageDuplicateChecker messageDuplicateChecker) {
    this.messageDuplicateChecker = messageDuplicateChecker;
  }

  /**
   * <pre>
   * 设置自定义的{@link me.chanjar.weixin.common.session.WxSessionManager}
   * 如果不调用该方法，默认使用 {@link me.chanjar.weixin.common.session.StandardSessionManager}
   * </pre>
   */
  public void setSessionManager(WxSessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  /**
   * <pre>
   * 设置自定义的{@link me.chanjar.weixin.common.api.WxErrorExceptionHandler}
   * 如果不调用该方法，默认使用 {@link me.chanjar.weixin.common.util.LogExceptionHandler}
   * </pre>
   */
  public void setExceptionHandler(WxErrorExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
  }

  List<WxCpMessageRouterRule> getRules() {
    return this.rules;
  }

  /**
   * 开始一个新的Route规则.
   */
  public WxCpMessageRouterRule rule() {
    return new WxCpMessageRouterRule(this);
  }

  /**
   * 处理微信消息.
   */
  public WxCpXmlOutMessage route(final WxCpXmlMessage wxMessage, final Map<String, Object> context) {
    if (isMsgDuplicated(wxMessage)) {
      if (log.isDebugEnabled()) {
        log.info("\n\n检测到重复消息:\n\n{}", WxGsonBuilder.create().toJson(wxMessage));
      }
      return null;
    }

    final List<WxCpMessageRouterRule> matchRules = new ArrayList<>();
    // 收集匹配的规则
    for (final WxCpMessageRouterRule rule : this.rules) {
      if (rule.test(wxMessage)) {
        matchRules.add(rule);
        if (!rule.isReEnter()) {
          break;
        }
      }
    }

    if (matchRules.size() == 0) {
      return null;
    }

    WxCpXmlOutMessage res = null;
    final List<Future> futures = new ArrayList<>();
    for (final WxCpMessageRouterRule rule : matchRules) {
      // 返回最后一个非异步的rule的执行结果
      if (rule.isAsync()) {
        futures.add(
          this.executorService.submit(() -> {
            rule.service(wxMessage, context, WxCpMessageRouter.this.wxCpService, WxCpMessageRouter.this.sessionManager, WxCpMessageRouter.this.exceptionHandler);
          })
        );
      } else {
        res = rule.service(wxMessage, context, this.wxCpService, this.sessionManager, this.exceptionHandler);
        // 在同步操作结束，session访问结束
        log.debug("End session access: async=false, sessionId={}", wxMessage.getFromUserName());
        sessionEndAccess(wxMessage);
      }
    }

    if (futures.size() > 0) {
      this.executorService.submit(() -> {
        for (Future future : futures) {
          try {
            future.get();
            log.debug("End session access: async=true, sessionId={}", wxMessage.getFromUserName());
            // 异步操作结束，session访问结束
            sessionEndAccess(wxMessage);
          } catch (InterruptedException e) {
            log.error("Error happened when wait task finish", e);
            Thread.currentThread().interrupt();
          } catch (ExecutionException e) {
            log.error("Error happened when wait task finish", e);
          }
        }
      });
    }
    return res;
  }

  /**
   * 处理微信消息.
   */
  public WxCpXmlOutMessage route(final WxCpXmlMessage wxMessage) {
    return this.route(wxMessage, new HashMap<>(2));
  }

  private boolean isMsgDuplicated(WxCpXmlMessage wxMessage) {
    return this.messageDuplicateChecker.isDuplicate(wxMessage.hashCode() + "");
  }

  /**
   * 对session的访问结束.
   */
  private void sessionEndAccess(WxCpXmlMessage wxMessage) {
    InternalSession session = ((InternalSessionManager) this.sessionManager).findSession(wxMessage.getFromUserName());
    if (session != null) {
      session.endAccess();
    }

  }
}

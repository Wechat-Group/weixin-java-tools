package me.chanjar.weixin.cp.tp.message;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxErrorExceptionHandler;
import me.chanjar.weixin.common.api.WxMessageDuplicateChecker;
import me.chanjar.weixin.common.api.WxMessageInMemoryDuplicateChecker;
import me.chanjar.weixin.common.session.InternalSession;
import me.chanjar.weixin.common.session.InternalSessionManager;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.LogExceptionHandler;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.cp.bean.message.WxCpTpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * <pre>
 * 微信消息路由器，通过代码化的配置，把来自微信的消息交给handler处理
 * 和WxCpMessageRouter的rule相比，多了infoType和changeType维度的匹配
 *
 * 说明：
 * 1. 配置路由规则时要按照从细到粗的原则，否则可能消息可能会被提前处理
 * 2. 默认情况下消息只会被处理一次，除非使用 {@link WxCpTpMessageRouterRule#next()}
 * 3. 规则的结束必须用{@link WxCpTpMessageRouterRule#end()}或者{@link WxCpTpMessageRouterRule#next()}，否则不会生效
 *
 * 使用方法：
 * WxCpTpMessageRouter router = new WxCpTpMessageRouter();
 * router
 *   .rule()
 *       .msgType("MSG_TYPE").event("EVENT").eventKey("EVENT_KEY").content("CONTENT")
 *       .interceptor(interceptor, ...).handler(handler, ...)
 *   .end()
 *   .rule()
 *       .infoType("INFO_TYPE").changeType("CHANGE_TYPE")
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
public class WxCpTpMessageRouter {
  private static final int DEFAULT_THREAD_POOL_SIZE = 100;
  private final List<WxCpTpMessageRouterRule> rules = new ArrayList<>();

  private final WxCpTpService wxCpTpService;

  private ExecutorService executorService;

  private WxMessageDuplicateChecker messageDuplicateChecker;

  private WxSessionManager sessionManager;

  private WxErrorExceptionHandler exceptionHandler;

  /**
   * 构造方法.
   */
  public WxCpTpMessageRouter(WxCpTpService wxCpTpService) {
    this.wxCpTpService = wxCpTpService;
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("WxCpTpMessageRouter-pool-%d").build();
    this.executorService = new ThreadPoolExecutor(DEFAULT_THREAD_POOL_SIZE, DEFAULT_THREAD_POOL_SIZE,
      0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);
    this.messageDuplicateChecker = new WxMessageInMemoryDuplicateChecker(30);
    this.sessionManager = wxCpTpService.getSessionManager();
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
   * 设置自定义的 {@link WxMessageDuplicateChecker}
   * 如果不调用该方法，默认使用 {@link WxMessageInMemoryDuplicateChecker}
   * </pre>
   */
  public void setMessageDuplicateChecker(WxMessageDuplicateChecker messageDuplicateChecker) {
    this.messageDuplicateChecker = messageDuplicateChecker;
  }

  /**
   * <pre>
   * 设置自定义的{@link WxSessionManager}
   * 如果不调用该方法，默认使用 {@link me.chanjar.weixin.common.session.StandardSessionManager}
   * </pre>
   */
  public void setSessionManager(WxSessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  /**
   * <pre>
   * 设置自定义的{@link WxErrorExceptionHandler}
   * 如果不调用该方法，默认使用 {@link LogExceptionHandler}
   * </pre>
   */
  public void setExceptionHandler(WxErrorExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
  }

  List<WxCpTpMessageRouterRule> getRules() {
    return this.rules;
  }

  /**
   * 开始一个新的Route规则.
   */
  public WxCpTpMessageRouterRule rule() {
    return new WxCpTpMessageRouterRule(this);
  }

  /**
   * 处理微信消息.
   */
  public WxCpXmlOutMessage route(final WxCpTpXmlMessage wxMessage, final Map<String, Object> context) {
    if (isMsgDuplicated(wxMessage)) {
      if (log.isDebugEnabled()) {
        log.info("\n\n检测到重复消息:\n\n{}", WxGsonBuilder.create().toJson(wxMessage));
      }
      return null;
    }

    final List<WxCpTpMessageRouterRule> matchRules = new ArrayList<>();
    // 收集匹配的规则
    for (final WxCpTpMessageRouterRule rule : this.rules) {
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
    for (final WxCpTpMessageRouterRule rule : matchRules) {
      // 返回最后一个非异步的rule的执行结果
      if (rule.isAsync()) {
        futures.add(
          this.executorService.submit(() -> {
            rule.service(wxMessage, context, WxCpTpMessageRouter.this.wxCpTpService, WxCpTpMessageRouter.this.sessionManager, WxCpTpMessageRouter.this.exceptionHandler);
          })
        );
      } else {
        res = rule.service(wxMessage, context, this.wxCpTpService, this.sessionManager, this.exceptionHandler);
        // 在同步操作结束，session访问结束
        log.debug("End session access: async=false, sessionId={}", wxMessage.getSuiteId());
        sessionEndAccess(wxMessage);
      }
    }

    if (futures.size() > 0) {
      this.executorService.submit(() -> {
        for (Future future : futures) {
          try {
            future.get();
            log.debug("End session access: async=true, sessionId={}", wxMessage.getSuiteId());
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
  public WxCpXmlOutMessage route(final WxCpTpXmlMessage wxMessage) {
    return this.route(wxMessage, new HashMap<>(2));
  }

  private boolean isMsgDuplicated(WxCpTpXmlMessage wxMessage) {
    // 这里简化之前的看不懂的不知道为什么要写出来的id生成
    return this.messageDuplicateChecker.isDuplicate(wxMessage.hashCode() + "");
  }

  /**
   * 对session的访问结束.
   */
  private void sessionEndAccess(WxCpTpXmlMessage wxMessage) {
    InternalSession session = ((InternalSessionManager) this.sessionManager).findSession(wxMessage.getSuiteId());
    if (session != null) {
      session.endAccess();
    }

  }
}

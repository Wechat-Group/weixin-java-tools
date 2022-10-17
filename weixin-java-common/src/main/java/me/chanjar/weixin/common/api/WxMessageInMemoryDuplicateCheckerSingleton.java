package me.chanjar.weixin.common.api;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangby
 * @version 1.0
 * <p>
 * 消息去重，记录消息ID首次出现时的时间戳，
 * 15S后定时任务触发时废除该记录消息ID
 * </p>
 * created on  2022/5/26 1:32
 */
@Slf4j
public class WxMessageInMemoryDuplicateCheckerSingleton implements WxMessageDuplicateChecker {

  /**
   * 一个消息ID在内存的过期时间：15秒.
   */
  private static final Long TIME_TO_LIVE = 15L;

  /**
   * 每隔多少周期检查消息ID是否过期：5秒.
   */
  private static final Long CLEAR_PERIOD = 5L;

  /**
   * 线程池
   */
  private static final ScheduledThreadPoolExecutor SCHEDULED_THREAD_POOL_EXECUTOR = new ScheduledThreadPoolExecutor(1,
    new ThreadFactoryBuilder().setNameFormat("wxMessage-memory-pool-%d").setDaemon(true).build(), new ThreadPoolExecutor.AbortPolicy());

  /**
   * 消息id->消息时间戳的map.
   */
  private static final ConcurrentHashMap<String, Long> MSG_ID_2_TIMESTAMP = new ConcurrentHashMap<>();

  static {
    SCHEDULED_THREAD_POOL_EXECUTOR.scheduleAtFixedRate(() -> {
      try {
        Long now = System.currentTimeMillis();
        MSG_ID_2_TIMESTAMP.entrySet().removeIf(entry -> now - entry.getValue() > TIME_TO_LIVE * 1000);
      } catch (Exception ex) {
        log.error("重复消息去重任务出现异常", ex);
      }
    }, 1, CLEAR_PERIOD, TimeUnit.SECONDS);
  }

  /**
   * 私有化构造方法，避免外部调用
   */
  private WxMessageInMemoryDuplicateCheckerSingleton() {
  }

  /**
   * 获取单例
   *
   * @return 单例对象
   */
  public static WxMessageInMemoryDuplicateCheckerSingleton getInstance() {
    return WxMessageInnerClass.CHECKER_SINGLETON;
  }

  /**
   * 内部类实现单例
   */
  private static class WxMessageInnerClass {
    static final WxMessageInMemoryDuplicateCheckerSingleton CHECKER_SINGLETON = new WxMessageInMemoryDuplicateCheckerSingleton();
  }

  /**
   * messageId是否重复
   *
   * @param messageId messageId
   * @return 是否
   */
  @Override
  public boolean isDuplicate(String messageId) {
    if (messageId == null) {
      return false;
    }
    Long timestamp = MSG_ID_2_TIMESTAMP.putIfAbsent(messageId, System.currentTimeMillis());
    return timestamp != null;
  }
}

package me.chanjar.weixin.common.api;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangby
 * @version 1.0
 * @description: 作用
 * @date 2022/5/26 1:32
 */
public class WxMessageInMemoryDuplicateCheckerSingleton implements WxMessageDuplicateChecker {

  /**
   * 一个消息ID在内存的过期时间：15秒.
   */
  private static final Long timeToLive = 15L;

  /**
   * 每隔多少周期检查消息ID是否过期：5秒.
   */
  private static final Long clearPeriod = 5L;

  /**
   * 线程池
   */
  private static final ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(1,
    new ThreadFactoryBuilder().setNameFormat("wxMessage-memory-pool-%d").build(), new ThreadPoolExecutor.AbortPolicy());

  /**
   * 消息id->消息时间戳的map.
   */
  private static final ConcurrentHashMap<String, Long> msgId2Timestamp = new ConcurrentHashMap<>();

  static {
    poolExecutor.scheduleAtFixedRate(() -> {
      Long now = System.currentTimeMillis();
      for (Map.Entry<String, Long> entry : msgId2Timestamp.entrySet()) {
        if (now - entry.getValue() > timeToLive * 1000) {
          msgId2Timestamp.entrySet().remove(entry);
        }
      }
    }, 1, clearPeriod, TimeUnit.SECONDS);
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
    return WxMessageInnerClass.singleton;
  }

  /**
   * 内部类实现单例
   */
  private static class WxMessageInnerClass {
     static final WxMessageInMemoryDuplicateCheckerSingleton singleton = new WxMessageInMemoryDuplicateCheckerSingleton();
  }

  @Override
  public boolean isDuplicate(String messageId) {
    if (messageId == null) {
      return false;
    }
    Long timestamp = msgId2Timestamp.putIfAbsent(messageId, System.currentTimeMillis());
    return timestamp != null;
  }
}

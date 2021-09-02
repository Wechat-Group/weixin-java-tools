package me.chanjar.weixin.common.api;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.checkerframework.checker.units.qual.Time;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <pre>
 * 默认消息重复检查器.
 * 使用 cache 重写原先的复杂的看不懂的不知道为什么要写出来的检查器
 * </pre>
 */
public class WxMessageInMemoryDuplicateChecker implements WxMessageDuplicateChecker {

  private final Cache<String, Integer> cache;

  /**
   * 构造重复消息检查器
   * @param expire 过期时间：单位 秒
   */
  public WxMessageInMemoryDuplicateChecker(long expire) {
    cache = CacheBuilder.newBuilder()
      .expireAfterWrite(expire, TimeUnit.SECONDS)
      .maximumSize(Long.MAX_VALUE)
      .build();
  }

  public WxMessageInMemoryDuplicateChecker(Cache<String, Integer> cache) {
    this.cache = cache;
  }

  @Override
  public boolean isDuplicate(String messageId) {
    Integer value = cache.getIfPresent(messageId);
    if (value == null) {
      cache.put(messageId, 1);
      return false;
    } else {
      return true;
    }
  }
}

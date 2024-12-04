package com.binarywang.solon.wxjava.miniapp.enums;

/**
 * storage类型.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2020-05-25
 */
public enum StorageType {
  /**
   * 内存.
   */
  Memory,
  /**
   * redis(JedisClient).
   */
  Jedis,
  /**
   * redis(Redisson).
   */
  Redisson,
  /**
   * redis(RedisTemplate).
   */
  RedisTemplate
}

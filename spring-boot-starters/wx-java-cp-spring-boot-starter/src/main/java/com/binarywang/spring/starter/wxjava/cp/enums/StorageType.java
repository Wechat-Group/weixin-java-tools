package com.binarywang.spring.starter.wxjava.cp.enums;

/**
 * storage 类型.
 *
 * @author xdtand
 * 
 * @date 2020-09-30
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
   * redis(RedisTemplate).
   */
  RedisTemplate
}

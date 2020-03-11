package com.binarywang.spring.starter.wxjava.mp.properties;

import lombok.Data;

import java.io.Serializable;

/**
 * Redis配置.
 *
 * @author someone
 */
@Data
public class RedisProperties implements Serializable {
  private static final long serialVersionUID = -5924815351660074401L;

  /**
   * 操作Redis的实现
   */
  private ImplType impl = ImplType.jedis;

  /**
   * 操作Redis的实现如果在spring容器里是否直接使用
   */
  private boolean reuseBean = true;

  /**
   * 主机地址.
   */
  private String host = "127.0.0.1";

  /**
   * 端口号.
   */
  private int port = 6379;

  /**
   * 密码.
   */
  private String password;

  /**
   * 超时.
   */
  private int timeout = 2000;

  /**
   * 数据库.
   */
  private int database = 0;

  private Integer maxActive;
  private Integer maxIdle;
  private Integer maxWaitMillis;
  private Integer minIdle;

  public enum ImplType {
    /**
     * jedis.
     */
    jedis,
    /**
     * redisson.
     */
//    redisson
  }
}

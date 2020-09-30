package com.binarywang.spring.starter.wxjava.cp.properties;

import lombok.Data;

import java.io.Serializable;

/**
 * redis 配置属性.
 *
 * @author xdtand
 * @date 2020-09-30
 */
@Data
public class RedisProperties implements Serializable {
  private static final long serialVersionUID = -5924815351660074401L;

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
  
  
  /**
   * cluster address
   */
  private String clusterAddress;
  
  /**
   * sentinel address
   */
  private String sentinelAddress;
  
  /**
   * sentinel name
   */
  private String sentinelName;
  
  /**
   * master address
   */
  private String masterAddress;
  
  /**
   * slavee address
   */
  private String slaveAddress;

  private Integer maxActive;
  private Integer maxIdle;
  private Integer maxWaitMillis;
  private Integer minIdle;
}

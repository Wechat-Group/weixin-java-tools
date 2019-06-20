package com.binarywang.spring.starter.wxjava.mp;

import lombok.Data;

import java.io.Serializable;

/**
 * Redis配置
 */
@Data
public class RedisProperties implements Serializable {

    /**
     * 主机地址
     */
    private String host = "127.0.0.1";

    /**
     * 端口号
     */
    private int port = 6379;

    /**
     * 密码
     */
    private String password;

    /**
     * 超时
     */
    private int timeout = 2000;

    /**
     * 数据库
     */
    private int database = 0;

    private Integer maxActive;
    private Integer maxIdle;
    private Integer maxWaitMillis;
    private Integer minIdle;
}
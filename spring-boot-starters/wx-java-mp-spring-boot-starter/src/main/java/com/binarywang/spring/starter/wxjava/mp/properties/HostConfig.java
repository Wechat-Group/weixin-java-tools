package com.binarywang.spring.starter.wxjava.mp.properties;

import lombok.Data;

import java.io.Serializable;

@Data
public class HostConfig implements Serializable {

  private static final long serialVersionUID = -4172767630740346001L;

  /**
   * 对应于：https://api.weixin.qq.com
   */
  private String apiHost;

  /**
   * 对应于：https://open.weixin.qq.com
   */
  private String openHost;

  /**
   * 对应于：https://mp.weixin.qq.com
   */
  private String mpHost;

}

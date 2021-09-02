package com.binarywang.spring.starter.wxjava.base.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author caiqy
 * @date 2021/9/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxCommonResult implements Serializable {
  private static final long serialVersionUID = -1L;

  /**
   * 微信错误码
   */
  private int code;

  /**
   * 微信错误信息
   */
  private String message;

  /**
   * 是否成功
   */
  private boolean success;

}

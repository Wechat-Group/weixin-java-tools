package com.github.binarywang.wxpay.bean.ecommerce.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 服务商账户类型
 *
 * @author f00lish
 * @date 2020/09/12
 */
@Getter
@AllArgsConstructor
public enum SpAccountTypeEnum {

  /**
   * 基本账户
   */
  BASIC("BASIC"),
  /**
   * 运营账户
   */
  OPERATION("OPERATION"),
  /**
   * 手续费账户
   */
  FEES("FEES");

  /**
   * 账户类型
   */
  private final String value;
}

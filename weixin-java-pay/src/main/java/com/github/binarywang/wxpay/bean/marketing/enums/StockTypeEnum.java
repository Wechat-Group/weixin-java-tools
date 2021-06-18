package com.github.binarywang.wxpay.bean.marketing.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 批次类型
 *
 * @author yujam
 */
@Getter
@AllArgsConstructor
public enum StockTypeEnum {

  /**
   * NORMAL：固定面额满减券批次
   */
  NORMAL("NORMAL"),

  /**
   * DISCOUNT：折扣券批次
   */
  DISCOUNT("DISCOUNT"),

  /**
   * EXCHANGE：换购券批次
   */
  EXCHANGE("EXCHANGE");

  /**
   * 批次类型
   */
  private final String value;
}

package com.github.binarywang.wxpay.bean.marketing.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 批次类型
 *
 * @author thinsstar
 */
@Getter
@AllArgsConstructor
public enum StockTypeEnum {

  /**
   * NORMAL：固定面额满减券批次
   */
  NORMAL("NORMAL"),
  ;

  /**
   * 批次类型
   */
  private final String value;
}

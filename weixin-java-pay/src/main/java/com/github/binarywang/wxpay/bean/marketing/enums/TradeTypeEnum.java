package com.github.binarywang.wxpay.bean.marketing.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付方式
 *
 * @author thinsstar
 */
@Getter
@AllArgsConstructor
public enum TradeTypeEnum {

  /**
   * MICROAPP：小程序支付
   */
  MICROAPP("MICROAPP"),
  /**
   * APPPAY：APP支付
   */
  APPPAY("APPPAY"),
  /**
   * PPAY：免密支付
   */
  PPAY("PPAY"),
  /**
   * CARD：刷卡支付
   */
  CARD("CARD"),
  /**
   * FACE：人脸支付
   */
  FACE("FACE"),
  /**
   * OTHER：其他支付
   */
  OTHER("OTHER"),
  ;

  /**
   * 支付方式
   */
  private final String value;
}

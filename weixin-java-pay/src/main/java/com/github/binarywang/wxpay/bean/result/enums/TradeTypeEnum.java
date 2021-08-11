package com.github.binarywang.wxpay.bean.result.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付方式
 */
@Getter
@AllArgsConstructor
public enum TradeTypeEnum {
  /**
   * APP
   */
  APP("/v3/pay/transactions/app", "/v3/combine-transactions/app"),
  /**
   * JSAPI 或 小程序
   */
  JSAPI("/v3/pay/transactions/jsapi", "/v3/combine-transactions/jsapi"),
  /**
   * NATIVE
   */
  NATIVE("/v3/pay/transactions/native", "/v3/combine-transactions/native"),
  /**
   * H5
   */
  H5("/v3/pay/transactions/h5", "/v3/combine-transactions/h5");

  /**
   * 单独下单url
   */
  private final String partnerUrl;

  /**
   * 合并下单url
   */
  private final String combineUrl;
}

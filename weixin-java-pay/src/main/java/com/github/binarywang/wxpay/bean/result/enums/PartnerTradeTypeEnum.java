package com.github.binarywang.wxpay.bean.result.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付方式
 *
 * @author thinsstar
 */
@Getter
@AllArgsConstructor
public enum PartnerTradeTypeEnum {
  /**
   * APP
   */
  APP("/v3/pay/partner/transactions/app", "/v3/combine-transactions/app", TradeTypeEnum.APP),
  /**
   * JSAPI 或 小程序
   */
  JSAPI("/v3/pay/partner/transactions/jsapi", "/v3/combine-transactions/jsapi", TradeTypeEnum.JSAPI),
  /**
   * NATIVE
   */
  NATIVE("/v3/pay/partner/transactions/native", "/v3/combine-transactions/native", TradeTypeEnum.NATIVE),
  /**
   * H5
   */
  H5("/v3/pay/partner/transactions/h5", "/v3/combine-transactions/h5", TradeTypeEnum.H5);

  /**
   * 单独下单url
   */
  private final String partnerUrl;

  /**
   * 合并下单url
   */
  private final String combineUrl;

  /**
   * 直连支付枚举
   */
  private final TradeTypeEnum directConnTrade;
}

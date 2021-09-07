package com.github.binarywang.wxpay.bean.ecommerce.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 账单类型
 *
 * @author f00lish
 * @date 2020/09/28
 */
@Getter
@AllArgsConstructor
public enum FundBillTypeEnum {

  /**
   * 资金账单
   */
  FUND_FLOW_BILL("%s/v3/bill/fundflowbill?%s"),
  /**
   * 二级商户资金账单
   */
  SUB_FUND_FLOW_BILL("%s/v3/ecommerce/bill/fundflowbill?%s");

  /**
   * url
   */
  private final String url;

}

package com.github.binarywang.wxpay.bean.profitsharing;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Wang GuangXin 2019/10/22 10:06
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class ProfitSharingResult extends BaseWxPayResult {
  /**
   * 微信订单号.
   */
  @XStreamAlias("transaction_id")
  private String transactionId;
  /**
   * 商户分账单号.
   */
  @XStreamAlias("out_order_no")
  private String outOrderNo;
  /**
   * 微信分账单号.
   */
  @XStreamAlias("order_id")
  private String orderId;
}

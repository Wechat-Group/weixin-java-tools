package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 微信支付-申请退款返回结果
 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
 * </pre>
 *
 * @author liukaitj
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxPayRefundResult extends WxPayBaseResult implements Serializable {
  private static final long serialVersionUID = 1L;

  @XStreamAlias("device_info")
  private String deviceInfo;

  @XStreamAlias("transaction_id")
  private String transactionId;

  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  @XStreamAlias("out_refund_no")
  private String outRefundNo;

  @XStreamAlias("refund_id")
  private String refundId;

  @XStreamAlias("refund_channel")
  private String refundChannel;

  @XStreamAlias("refund_fee")
  private String refundFee;

  @XStreamAlias("total_fee")
  private String totalFee;

  @XStreamAlias("fee_type")
  private String feeType;

  @XStreamAlias("cash_fee")
  private String cashFee;

  @XStreamAlias("cash_refund_fee")
  private String cashRefundFee;

  @XStreamAlias("coupon_refund_fee")
  private String couponRefundFee;

  @XStreamAlias("coupon_refund_count")
  private String couponRefundCount;

  @XStreamAlias("coupon_refund_id")
  private String couponRefundId;

}

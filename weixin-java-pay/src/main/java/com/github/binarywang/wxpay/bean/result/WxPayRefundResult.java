package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * <pre>
 * 微信支付-申请退款返回结果
 * https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
 * </pre>
 *
 * @author liukaitj
 */
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

  public String getDeviceInfo() {
    return this.deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  public String getTransactionId() {
    return this.transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getOutTradeNo() {
    return this.outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public String getOutRefundNo() {
    return this.outRefundNo;
  }

  public void setOutRefundNo(String outRefundNo) {
    this.outRefundNo = outRefundNo;
  }

  public String getRefundId() {
    return this.refundId;
  }

  public void setRefundId(String refundId) {
    this.refundId = refundId;
  }

  public String getRefundChannel() {
    return this.refundChannel;
  }

  public void setRefundChannel(String refundChannel) {
    this.refundChannel = refundChannel;
  }

  public String getRefundFee() {
    return this.refundFee;
  }

  public void setRefundFee(String refundFee) {
    this.refundFee = refundFee;
  }

  public String getTotalFee() {
    return this.totalFee;
  }

  public void setTotalFee(String totalFee) {
    this.totalFee = totalFee;
  }

  public String getFeeType() {
    return this.feeType;
  }

  public void setFeeType(String feeType) {
    this.feeType = feeType;
  }

  public String getCashFee() {
    return this.cashFee;
  }

  public void setCashFee(String cashFee) {
    this.cashFee = cashFee;
  }

  public String getCashRefundFee() {
    return this.cashRefundFee;
  }

  public void setCashRefundFee(String cashRefundFee) {
    this.cashRefundFee = cashRefundFee;
  }

  public String getCouponRefundFee() {
    return this.couponRefundFee;
  }

  public void setCouponRefundFee(String couponRefundFee) {
    this.couponRefundFee = couponRefundFee;
  }

  public String getCouponRefundCount() {
    return this.couponRefundCount;
  }

  public void setCouponRefundCount(String couponRefundCount) {
    this.couponRefundCount = couponRefundCount;
  }

  public String getCouponRefundId() {
    return this.couponRefundId;
  }

  public void setCouponRefundId(String couponRefundId) {
    this.couponRefundId = couponRefundId;
  }

}

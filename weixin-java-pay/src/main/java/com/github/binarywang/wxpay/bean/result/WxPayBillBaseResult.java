package com.github.binarywang.wxpay.bean.result;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 交易时间:2017-04-06 01:00:02 公众账号ID: 商户号: 子商户号:0 设备号:WEB 微信订单号: 商户订单号:2017040519091071873216 用户标识: 交易类型:NATIVE
 * 交易状态:REFUND 付款银行:CFT 货币种类:CNY 总金额:0.00 企业红包金额:0.00 微信退款单号: 商户退款单号:20170406010000933 退款金额:0.01 企业红包退款金额:0.00
 * 退款类型:ORIGINAL 退款状态:SUCCESS 商品名称: 商户数据包: 手续费:0.00000 费率 :0.60%
 *
 * @author BinaryWang
 */
@Data
@NoArgsConstructor
public class WxPayBillBaseResult implements Serializable {
  private static final long serialVersionUID = 2226245109137435453L;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

  /**
   * 交易时间.
   */
  private String tradeTime;
  /**
   * 公众账号ID.
   */
  private String appId;
  /**
   * 商户号.
   */
  private String mchId;
  /**
   * 子商户号.
   */
  private String subMchId;
  /**
   * 设备号.
   */
  private String deviceInfo;
  /**
   * 微信订单号.
   */
  private String transactionId;
  /**
   * 商户订单号.
   */
  private String outTradeNo;
  /**
   * 用户标识.
   */
  private String openId;
  /**
   * 交易类型.
   */
  private String tradeType;
  /**
   * 交易状态.
   */
  private String tradeState;
  /**
   * 付款银行.
   */
  private String bankType;
  /**
   * 货币种类.
   */
  private String feeType;
  /**
   * 总金额.
   */
  private String totalFee;
  /**
   * 企业红包金额.
   */
  private String couponFee;
  /**
   * 微信退款单号.
   */
  private String refundId;
  /**
   * 商户退款单号.
   */
  private String outRefundNo;
  /**
   * 退款金额.
   */
  private String settlementRefundFee;
  /**
   * 企业红包退款金额.
   */
  private String couponRefundFee;
  /**
   * 退款类型.
   */
  private String refundChannel;
  /**
   * 退款状态.
   */
  private String refundState;
  /**
   * 商品名称.
   */
  private String body;
  /**
   * 商户数据包.
   */
  private String attach;
  /**
   * 手续费.
   */
  private String poundage;
  /**
   * 费率.
   */
  private String poundageRate;

}

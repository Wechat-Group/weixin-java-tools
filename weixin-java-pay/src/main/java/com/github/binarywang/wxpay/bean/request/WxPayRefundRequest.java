package com.github.binarywang.wxpay.bean.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import me.chanjar.weixin.common.annotation.Required;

/**
 * <pre>
 * 微信支付-申请退款请求参数
 * 注释中各行每个字段描述对应如下：
 * <li>字段名
 * <li>变量名
 * <li>是否必填
 * <li>类型
 * <li>示例值
 * <li>描述
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 *         Created by Binary Wang on 2016-10-08.
 */
@XStreamAlias("xml")
public class WxPayRefundRequest extends WxPayBaseRequest {
  /**
   * <pre>
   * 设备号
   * device_info
   * 否
   * String(32)
   * 13467007045764
   * 终端设备号
   * </pre>
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  /**
   * <pre>
   * 微信订单号
   * transaction_id
   * 跟out_trade_no二选一
   * String(28)
   * 1217752501201400000000000000
   * 微信生成的订单号，在支付通知中有返回
   * </pre>
   */
  @XStreamAlias("transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * 跟transaction_id二选一
   * String(32)
   * 1217752501201400000000000000
   * 商户侧传给微信的订单号
   * </pre>
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 商户退款单号
   * out_refund_no
   * 是
   * String(32)
   * 1217752501201400000000000000
   * 商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
   * </pre>
   */
  @Required
  @XStreamAlias("out_refund_no")
  private String outRefundNo;

  /**
   * <pre>
   * 订单金额
   * total_fee
   * 是
   * Int
   * 100
   * 订单总金额，单位为分，只能为整数，详见支付金额
   * </pre>
   */
  @Required
  @XStreamAlias("total_fee")
  private Integer totalFee;

  /**
   * <pre>
   * 退款金额
   * refund_fee
   * 是
   * Int
   * 100
   * 退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
   * </pre>
   */
  @Required
  @XStreamAlias("refund_fee")
  private Integer refundFee;

  /**
   * <pre>
   * 货币种类
   * refund_fee_type
   * 否
   * String(8)
   * CNY
   * 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
   * </pre>
   */
  @XStreamAlias("refund_fee_type")
  private String refundFeeType;

  /**
   * <pre>
   * 操作员
   * op_user_id
   * 是
   * String(32)
   * 1900000109
   * 操作员帐号, 默认为商户号
   * </pre>
   */
  //@Required
  @XStreamAlias("op_user_id")
  private String opUserId;

  /**
   * <pre>
   * 退款资金来源
   * refund_account
   * 否
   * String(30)
   * REFUND_SOURCE_RECHARGE_FUNDS
   * 仅针对老资金流商户使用，
   * <li>REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款），
   * <li>REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
   * </pre>
   */
  @XStreamAlias("refund_account")
  private String refundAccount;

  public WxPayRefundRequest() {
  }

  private WxPayRefundRequest(Builder builder) {
    setDeviceInfo(builder.deviceInfo);
    setAppid(builder.appid);
    setTransactionId(builder.transactionId);
    setMchId(builder.mchId);
    setOutTradeNo(builder.outTradeNo);
    setSubAppId(builder.subAppId);
    setSubMchId(builder.subMchId);
    setOutRefundNo(builder.outRefundNo);
    setNonceStr(builder.nonceStr);
    setTotalFee(builder.totalFee);
    setSign(builder.sign);
    setRefundFee(builder.refundFee);
    setRefundFeeType(builder.refundFeeType);
    setOpUserId(builder.opUserId);
    setRefundAccount(builder.refundAccount);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

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

  public Integer getTotalFee() {
    return this.totalFee;
  }

  public void setTotalFee(Integer totalFee) {
    this.totalFee = totalFee;
  }

  public Integer getRefundFee() {
    return this.refundFee;
  }

  public void setRefundFee(Integer refundFee) {
    this.refundFee = refundFee;
  }

  public String getRefundFeeType() {
    return this.refundFeeType;
  }

  public void setRefundFeeType(String refundFeeType) {
    this.refundFeeType = refundFeeType;
  }

  public String getOpUserId() {
    return this.opUserId;
  }

  public void setOpUserId(String opUserId) {
    this.opUserId = opUserId;
  }

  public String getRefundAccount() {
    return this.refundAccount;
  }

  public void setRefundAccount(String refundAccount) {
    this.refundAccount = refundAccount;
  }

  public static final class Builder {
    private String deviceInfo;
    private String appid;
    private String transactionId;
    private String mchId;
    private String outTradeNo;
    private String subAppId;
    private String subMchId;
    private String outRefundNo;
    private String nonceStr;
    private Integer totalFee;
    private String sign;
    private Integer refundFee;
    private String refundFeeType;
    private String opUserId;
    private String refundAccount;

    private Builder() {
    }

    public Builder deviceInfo(String deviceInfo) {
      this.deviceInfo = deviceInfo;
      return this;
    }

    public Builder appid(String appid) {
      this.appid = appid;
      return this;
    }

    public Builder transactionId(String transactionId) {
      this.transactionId = transactionId;
      return this;
    }

    public Builder mchId(String mchId) {
      this.mchId = mchId;
      return this;
    }

    public Builder outTradeNo(String outTradeNo) {
      this.outTradeNo = outTradeNo;
      return this;
    }

    public Builder subAppId(String subAppId) {
      this.subAppId = subAppId;
      return this;
    }

    public Builder subMchId(String subMchId) {
      this.subMchId = subMchId;
      return this;
    }

    public Builder outRefundNo(String outRefundNo) {
      this.outRefundNo = outRefundNo;
      return this;
    }

    public Builder nonceStr(String nonceStr) {
      this.nonceStr = nonceStr;
      return this;
    }

    public Builder totalFee(Integer totalFee) {
      this.totalFee = totalFee;
      return this;
    }

    public Builder sign(String sign) {
      this.sign = sign;
      return this;
    }

    public Builder refundFee(Integer refundFee) {
      this.refundFee = refundFee;
      return this;
    }

    public Builder refundFeeType(String refundFeeType) {
      this.refundFeeType = refundFeeType;
      return this;
    }

    public Builder opUserId(String opUserId) {
      this.opUserId = opUserId;
      return this;
    }

    public Builder refundAccount(String refundAccount) {
      this.refundAccount = refundAccount;
      return this;
    }

    public WxPayRefundRequest build() {
      return new WxPayRefundRequest(this);
    }
  }
}

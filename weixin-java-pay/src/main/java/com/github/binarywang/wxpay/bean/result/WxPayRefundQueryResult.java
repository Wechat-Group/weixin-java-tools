package com.github.binarywang.wxpay.bean.result;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import io.restassured.path.xml.XmlPath;

import java.util.List;

/**
 * <pre>
 * Created by Binary Wang on 2016-11-24.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
@XStreamAlias("xml")
public class WxPayRefundQueryResult extends WxPayBaseResult {
  /**
   * <pre>
   * 设备号
   * device_info
   * 否
   * String(32)
   * 013467007045764
   * 终端设备号
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  /**
   * <pre>
   * 微信订单号
   * transaction_id
   * 是
   * String(32)
   * 1217752501201407033233368018
   * 微信订单号
   */
  @XStreamAlias("transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * 是
   * String(32)
   * 1217752501201407033233368018
   * 商户系统内部的订单号
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 订单金额
   * total_fee
   * 是
   * Int
   * 100
   * 订单总金额，单位为分，只能为整数，详见支付金额
   */
  @XStreamAlias("total_fee")
  private Integer totalFee;

  /**
   * <pre>
   * 应结订单金额
   * settlement_total_fee
   * 否
   * Int
   * 100
   * 应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
   */
  @XStreamAlias("settlement_total_fee")
  private Integer settlementTotalFee;

  /**
   * <pre>
   * 货币种类
   * fee_type
   * 否
   * String(8)
   * CNY
   * 订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
   */
  @XStreamAlias("fee_type")
  private String feeType;

  /**
   * <pre>
   * 现金支付金额
   * cash_fee
   * 是
   * Int
   * 100
   * 现金支付金额，单位为分，只能为整数，详见支付金额
   */
  @XStreamAlias("cash_fee")
  private Integer cashFee;

  /**
   * <pre>
   * 退款笔数
   * refund_count
   * 是
   * Int
   * 1
   * 退款记录数
   */
  @XStreamAlias("refund_count")
  private Integer refundCount;

  private List<RefundRecord> refundRecords;

  public String getDeviceInfo() {
    return deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }

  public Integer getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Integer totalFee) {
    this.totalFee = totalFee;
  }

  public Integer getSettlementTotalFee() {
    return settlementTotalFee;
  }

  public void setSettlementTotalFee(Integer settlementTotalFee) {
    this.settlementTotalFee = settlementTotalFee;
  }

  public String getFeeType() {
    return feeType;
  }

  public void setFeeType(String feeType) {
    this.feeType = feeType;
  }

  public Integer getCashFee() {
    return cashFee;
  }

  public void setCashFee(Integer cashFee) {
    this.cashFee = cashFee;
  }

  public Integer getRefundCount() {
    return refundCount;
  }

  public void setRefundCount(Integer refundCount) {
    this.refundCount = refundCount;
  }

  public List<RefundRecord> getRefundRecords() {
    return refundRecords;
  }

  public void setRefundRecords(List<RefundRecord> refundRecords) {
    this.refundRecords = refundRecords;
  }

  /**
   * 组装生成退款记录属性的内容
   */
  public void composeRefundRecords() {
    if (this.refundCount != null && this.refundCount > 0) {
      this.refundRecords = Lists.newArrayList();
      XmlPath xmlPath = new XmlPath(this.getXmlString());

      for (int i = 0; i < this.refundCount; i++) {
        RefundRecord refundRecord = new RefundRecord();
        this.refundRecords.add(refundRecord);

        refundRecord.setOutRefundNo(this.getXmlValue(xmlPath, "xml.out_refund_no_" + i, String.class));
        refundRecord.setRefundId(this.getXmlValue(xmlPath, "xml.refund_id_" + i, String.class));
        refundRecord.setRefundChannel(this.getXmlValue(xmlPath, "xml.refund_channel_" + i, String.class));
        refundRecord.setRefundFee(this.getXmlValue(xmlPath, "xml.refund_fee_" + i, Integer.class));
        refundRecord.setSettlementRefundFee(this.getXmlValue(xmlPath, "xml.settlement_refund_fee_" + i, Integer.class));
        refundRecord.setCouponType(this.getXmlValue(xmlPath, "xml.coupon_type_" + i, String.class));
        refundRecord.setCouponRefundFee(this.getXmlValue(xmlPath, "xml.coupon_refund_fee_" + i, Integer.class));
        refundRecord.setCouponRefundCount(this.getXmlValue(xmlPath, "xml.coupon_refund_count_" + i, Integer.class));
        refundRecord.setRefundStatus(this.getXmlValue(xmlPath, "xml.refund_status_" + i, String.class));
        refundRecord.setRefundRecvAccout(this.getXmlValue(xmlPath, "xml.refund_recv_accout_" + i, String.class));

        if (refundRecord.getCouponRefundCount() == null || refundRecord.getCouponRefundCount() == 0) {
          continue;
        }

        List<RefundRecord.RefundCoupon> coupons = Lists.newArrayList();
        for (int j = 0; j < refundRecord.getCouponRefundCount(); j++) {
          coupons.add(
            new RefundRecord.RefundCoupon(
              this.getXmlValue(xmlPath, "xml.coupon_refund_id_" + i + "_" + j, String.class),
              this.getXmlValue(xmlPath, "xml.coupon_refund_fee_" + i + "_" + j, Integer.class)
            )
          );
        }
      }

    }
  }

  public static class RefundRecord {
    /**
     * <pre>
     * 商户退款单号
     * out_refund_no_$n
     * 是
     * String(32)
     * 1217752501201407033233368018
     * 商户退款单号
     * </pre>
     */
    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    /**
     * <pre>
     * 微信退款单号
     * refund_id_$n
     * 是
     * String(28)
     * 1217752501201407033233368018
     * 微信退款单号
     * </pre>
     */
    @XStreamAlias("refund_id")
    private String refundId;

    /**
     * <pre>
     * 退款渠道
     * refund_channel_$n
     * 否
     * String(16)
     * ORIGINAL
     * ORIGINAL—原路退款 BALANCE—退回到余额
     * </pre>
     */
    @XStreamAlias("refund_channel")
    private String refundChannel;

    /**
     * <pre>
     * 申请退款金额
     * refund_fee_$n
     * 是
     * Int
     * 100
     * 退款总金额,单位为分,可以做部分退款
     * </pre>
     */
    @XStreamAlias("refund_fee")
    private Integer refundFee;

    /**
     * <pre>
     * 退款金额
     * settlement_refund_fee_$n
     * 否
     * Int
     * 100
     * 退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     * </pre>
     */
    @XStreamAlias("settlement_refund_fee")
    private Integer settlementRefundFee;

    /**
     * <pre>
     * 退款资金来源
     * refund_account
     * 否
     * String(30)
     * REFUND_SOURCE_RECHARGE_FUNDS
     * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款/基本账户, REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款
     * </pre>
     */
    @XStreamAlias("refund_account")
    private String refundAccount;

    /**
     * <pre>
     * 代金券类型
     * coupon_type_$n
     * 否
     * Int
     * CASH
     * CASH--充值代金券 , NO_CASH---非充值代金券。订单使用代金券时有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_$0
     * </pre>
     */
    @XStreamAlias("coupon_type")
    private String couponType;

    /**
     * <pre>
     * 代金券退款金额
     * coupon_refund_fee_$n
     * 否
     * Int
     * 100
     * 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
     * </pre>
     */
    @XStreamAlias("coupon_refund_fee")
    private Integer couponRefundFee;

    /**
     * <pre>
     * 退款代金券使用数量
     * coupon_refund_count_$n
     * 否
     * Int
     * 1
     * 退款代金券使用数量 ,$n为下标,从0开始编号
     * </pre>
     */
    @XStreamAlias("coupon_refund_count")
    private Integer couponRefundCount;

    private List<RefundCoupon> refundCoupons;

    /**
     * <pre>
     * 退款状态
     * refund_status_$n
     * 是
     * String(16)
     * SUCCESS
     * 退款状态：
     *  SUCCESS—退款成功，
     *  FAIL—退款失败，
     *  PROCESSING—退款处理中，
     *  CHANGE—转入代发，
     * 退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款。
     * </pre>
     */
    @XStreamAlias("refund_status")
    private String refundStatus;
    /**
     * <pre>
     * 退款入账账户
     * refund_recv_accout_$n
     * 是
     * String(64)
     * 招商银行信用卡0403
     * 取当前退款单的退款入账方，1）退回银行卡：{银行名称}{卡类型}{卡尾号}，2）退回支付用户零钱:支付用户零钱
     * </pre>
     */
    @XStreamAlias("refund_recv_accout")
    private String refundRecvAccout;

    public String getOutRefundNo() {
      return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
      this.outRefundNo = outRefundNo;
    }

    public String getRefundId() {
      return refundId;
    }

    public void setRefundId(String refundId) {
      this.refundId = refundId;
    }

    public String getRefundChannel() {
      return refundChannel;
    }

    public void setRefundChannel(String refundChannel) {
      this.refundChannel = refundChannel;
    }

    public Integer getRefundFee() {
      return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
      this.refundFee = refundFee;
    }

    public Integer getSettlementRefundFee() {
      return settlementRefundFee;
    }

    public void setSettlementRefundFee(Integer settlementRefundFee) {
      this.settlementRefundFee = settlementRefundFee;
    }

    public String getRefundAccount() {
      return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
      this.refundAccount = refundAccount;
    }

    public String getCouponType() {
      return couponType;
    }

    public void setCouponType(String couponType) {
      this.couponType = couponType;
    }

    public Integer getCouponRefundFee() {
      return couponRefundFee;
    }

    public void setCouponRefundFee(Integer couponRefundFee) {
      this.couponRefundFee = couponRefundFee;
    }

    public Integer getCouponRefundCount() {
      return couponRefundCount;
    }

    public void setCouponRefundCount(Integer couponRefundCount) {
      this.couponRefundCount = couponRefundCount;
    }

    public List<RefundCoupon> getRefundCoupons() {
      return refundCoupons;
    }

    public void setRefundCoupons(List<RefundCoupon> refundCoupons) {
      this.refundCoupons = refundCoupons;
    }

    public String getRefundStatus() {
      return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
      this.refundStatus = refundStatus;
    }

    public String getRefundRecvAccout() {
      return refundRecvAccout;
    }

    public void setRefundRecvAccout(String refundRecvAccout) {
      this.refundRecvAccout = refundRecvAccout;
    }

    public static class RefundCoupon {
      /**
       * <pre>
       * 退款代金券批次ID
       * coupon_refund_batch_id_$n_$m
       * 否
       * String(20)
       * 100
       * 退款代金券批次ID ,$n为下标，$m为下标，从0开始编号
       * </pre>
       *
       * @deprecated 貌似是被去掉了，但不知是何时！
       */
      @XStreamAlias("coupon_refund_batch_id")
      private String couponRefundBatchId;

      /**
       * <pre>
       * 退款代金券ID
       * coupon_refund_id_$n_$m
       * 否
       * String(20)
       * 10000
       * 退款代金券ID, $n为下标，$m为下标，从0开始编号
       * </pre>
       */
      @XStreamAlias("coupon_refund_id")
      private String couponRefundId;

      /**
       * <pre>
       * 单个退款代金券支付金额
       * coupon_refund_fee_$n_$m
       * 否
       * Int
       * 100
       * 单个退款代金券支付金额, $n为下标，$m为下标，从0开始编号
       * </pre>
       */
      @XStreamAlias("coupon_refund_fee")
      private Integer couponRefundFee;

      public RefundCoupon(String couponRefundId, Integer couponRefundFee) {
        this.couponRefundId = couponRefundId;
        this.couponRefundFee = couponRefundFee;
      }

      @Deprecated
      public RefundCoupon(String couponRefundBatchId, String couponRefundId, Integer couponRefundFee) {
        this.couponRefundBatchId = couponRefundBatchId;
        this.couponRefundId = couponRefundId;
        this.couponRefundFee = couponRefundFee;
      }
    }

  }

}


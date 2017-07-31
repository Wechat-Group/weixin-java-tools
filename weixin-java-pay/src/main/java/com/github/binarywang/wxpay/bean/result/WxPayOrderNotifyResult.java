package com.github.binarywang.wxpay.bean.result;

import com.github.binarywang.wxpay.bean.WxPayOrderNotifyCoupon;
import com.github.binarywang.wxpay.converter.WxPayOrderNotifyResultConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import me.chanjar.weixin.common.util.BeanUtils;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 支付结果通用通知 ，文档见：https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7
 *
 * @author aimilin6688
 * @since 2.5.0
 */
@XStreamAlias("xml")
public class WxPayOrderNotifyResult extends WxPayBaseResult implements Serializable {

  private static final long serialVersionUID = 5389718115223345496L;

  /**
   * <pre>
   * 设备号
   * device_info
   * 否
   * String(32)
   * 013467007045764
   * 微信支付分配的终端设备号，
   * </pre>
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  /**
   * <pre>
   * 用户标识
   * openid
   * 是
   * String(128)
   * wxd930ea5d5a258f4f
   * 用户在商户appid下的唯一标识
   * </pre>
   */
  @XStreamAlias("openid")
  private String openid;

  /**
   * <pre>
   * 是否关注公众账号
   * is_subscribe
   * 否
   * String(1)
   * Y
   * 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
   * </pre>
   */
  @XStreamAlias("is_subscribe")
  private String isSubscribe;


  /**
   * <pre>
   * 交易类型
   * trade_type
   * 是
   * String(16)
   * JSAPI	JSAPI、NATIVE、APP
   * </pre>
   */
  @XStreamAlias("trade_type")
  private String tradeType;


  /**
   * <pre>
   * 付款银行
   * bank_type
   * 是
   * String(16)
   * CMC
   * 银行类型，采用字符串类型的银行标识，银行类型见银行列表
   * </pre>
   */
  @XStreamAlias("bank_type")
  private String bankType;


  /**
   * <pre>
   * 订单金额
   * total_fee
   * 是
   * Int
   * 100
   * 订单总金额，单位为分
   * </pre>
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
   * </pre>
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
   * 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
   * </pre>
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
   * 现金支付金额订单现金支付金额，详见支付金额
   * </pre>
   */
  @XStreamAlias("cash_fee")
  private Integer cashFee;
  /**
   * <pre>
   * 现金支付货币类型
   * cash_fee_type
   * 否
   * String(16)
   * CNY
   * 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
   * </pre>
   */
  @XStreamAlias("cash_fee_type")
  private String cashFeeType;
  /**
   * <pre>
   * 总代金券金额
   * coupon_fee
   * 否
   * Int
   * 10
   * 代金券金额<=订单金额，订单金额-代金券金额=现金支付金额，详见支付金额
   * </pre>
   */
  @XStreamAlias("coupon_fee")
  private Integer couponFee;

  /**
   * <pre>
   * 代金券使用数量
   * coupon_count
   * 否
   * Int
   * 1
   * 代金券使用数量
   * </pre>
   */
  @XStreamAlias("coupon_count")
  private Integer couponCount;

  private List<WxPayOrderNotifyCoupon> couponList;

  /**
   * <pre>
   * 微信支付订单号
   * transaction_id
   * 是
   * String(32)
   * 1217752501201407033233368018
   * 微信支付订单号
   * </pre>
   */
  @XStreamAlias("transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * 是
   * String(32)
   * 1212321211201407033568112322
   * 商户系统的订单号，与请求一致。
   * </pre>
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;
  /**
   * <pre>
   * 商家数据包
   * attach
   * 否
   * String(128)
   * 123456
   * 商家数据包，原样返回
   * </pre>
   */
  @XStreamAlias("attach")
  private String attach;
  /**
   * <pre>
   * 支付完成时间
   * time_end
   * 是
   * String(14)
   * 20141030133525
   * 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
   * </pre>
   */
  @XStreamAlias("time_end")
  private String timeEnd;

  public static WxPayOrderNotifyResult fromXML(String xmlString) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.processAnnotations(WxPayOrderNotifyResult.class);
    xstream.registerConverter(new WxPayOrderNotifyResultConverter(xstream.getMapper(), xstream.getReflectionProvider()));
    WxPayOrderNotifyResult result = (WxPayOrderNotifyResult) xstream.fromXML(xmlString);
    result.setXmlString(xmlString);
    return result;
  }

  public Integer getCouponCount() {
    return couponCount;
  }

  public void setCouponCount(Integer couponCount) {
    this.couponCount = couponCount;
  }

  public List<WxPayOrderNotifyCoupon> getCouponList() {
    return couponList;
  }

  public void setCouponList(List<WxPayOrderNotifyCoupon> couponList) {
    this.couponList = couponList;
  }

  public String getDeviceInfo() {
    return deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public String getIsSubscribe() {
    return isSubscribe;
  }

  public void setIsSubscribe(String isSubscribe) {
    this.isSubscribe = isSubscribe;
  }

  public String getTradeType() {
    return tradeType;
  }

  public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
  }

  public String getBankType() {
    return bankType;
  }

  public void setBankType(String bankType) {
    this.bankType = bankType;
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

  public String getCashFeeType() {
    return cashFeeType;
  }

  public void setCashFeeType(String cashFeeType) {
    this.cashFeeType = cashFeeType;
  }

  public Integer getCouponFee() {
    return couponFee;
  }

  public void setCouponFee(Integer couponFee) {
    this.couponFee = couponFee;
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

  public String getAttach() {
    return attach;
  }

  public void setAttach(String attach) {
    this.attach = attach;
  }

  public String getTimeEnd() {
    return timeEnd;
  }

  public void setTimeEnd(String timeEnd) {
    this.timeEnd = timeEnd;
  }

  @Override
  public Map<String, String> toMap() {
    Map<String, String> resultMap = BeanUtils.xmlBean2Map(this);
    if (this.getCouponCount() != null && this.getCouponCount() > 0) {
      for (int i = 0; i < this.getCouponCount(); i++) {
        WxPayOrderNotifyCoupon coupon = couponList.get(i);
        resultMap.putAll(coupon.toMap(i));
      }
    }
    return resultMap;
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }
}

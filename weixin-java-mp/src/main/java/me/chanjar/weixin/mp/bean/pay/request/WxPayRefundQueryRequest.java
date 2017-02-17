package me.chanjar.weixin.mp.bean.pay.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 * Created by Binary Wang on 2016-11-24.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
@XStreamAlias("xml")
public class WxPayRefundQueryRequest extends WxPayBaseRequest {
  /**
   * <pre>
   * 设备号
   * device_info
   * 否
   * String(32)
   * 013467007045764
   * 商户自定义的终端设备号，如门店编号、设备的ID等
   * </pre>
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  /**
   * <pre>
   * 签名类型
   * sign_type
   * 否
   * String(32)
   * HMAC-SHA256
   * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
   * </pre>
   */
  @XStreamAlias("sign_type")
  private String signType;

  //************以下四选一************
  /**
   * <pre>
   * 微信订单号
   * transaction_id
   * String(32)
   * 1217752501201407033233368018
   * 微信订单号
   * </pre>
   */
  @XStreamAlias("transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * String(32)
   * 1217752501201407033233368018
   * 商户系统内部的订单号
   * </pre>
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 商户退款单号
   * out_refund_no
   * String(32)
   * 1217752501201407033233368018
   * 商户侧传给微信的退款单号
   * </pre>
   */
  @XStreamAlias("out_refund_no")
  private String outRefundNo;

  /**
   * <pre>
   * 微信退款单号
   * refund_id
   * String(28)
   * 1217752501201407033233368018
   * 微信生成的退款单号，在申请退款接口有返回
   * </pre>
   */
  @XStreamAlias("refund_id")
  private String refundId;

  public String getDeviceInfo() {
    return deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  public String getSignType() {
    return signType;
  }

  public void setSignType(String signType) {
    this.signType = signType;
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
}

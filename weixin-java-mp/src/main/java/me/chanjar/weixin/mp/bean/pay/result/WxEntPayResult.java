package me.chanjar.weixin.mp.bean.pay.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 企业付款返回结果
 * Created by Binary Wang on 2016/10/02.
 * @author binarywang (https://github.com/binarywang)
 */
@XStreamAlias("xml")
public class WxEntPayResult extends WxPayBaseResult {

  /**
   * 商户appid
   */
  @XStreamAlias("mch_appid")
  private String mchAppid;

  /**
   * 设备号
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  //############以下字段在return_code 和result_code都为SUCCESS的时候有返回##############
  /**
   * 商户订单号
   */
  @XStreamAlias("partner_trade_no")
  private String partnerTradeNo;

  /**
   * 微信订单号
   */
  @XStreamAlias("payment_no")
  private String paymentNo;

  /**
   * 微信支付成功时间
   */
  @XStreamAlias("payment_time")
  private String paymentTime;

  public String getMchAppid() {
    return this.mchAppid;
  }

  public void setMchAppid(String mchAppid) {
    this.mchAppid = mchAppid;
  }

  public String getDeviceInfo() {
    return this.deviceInfo;
  }

  public void setDeviceInfo(String deviceInfo) {
    this.deviceInfo = deviceInfo;
  }

  public String getPartnerTradeNo() {
    return this.partnerTradeNo;
  }

  public void setPartnerTradeNo(String partnerTradeNo) {
    this.partnerTradeNo = partnerTradeNo;
  }

  public String getPaymentNo() {
    return this.paymentNo;
  }

  public void setPaymentNo(String paymentNo) {
    this.paymentNo = paymentNo;
  }

  public String getPaymentTime() {
    return this.paymentTime;
  }

  public void setPaymentTime(String paymentTime) {
    this.paymentTime = paymentTime;
  }
}

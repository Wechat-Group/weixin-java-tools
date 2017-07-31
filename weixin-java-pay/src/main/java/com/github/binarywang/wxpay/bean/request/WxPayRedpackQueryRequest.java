package com.github.binarywang.wxpay.bean.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 *   注释中各行对应含义：
 *   字段名
 *   字段
 *   必填
 *   示例值
 *   类型
 *   说明
 * Created by Binary Wang on 2016-11-28.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
@XStreamAlias("xml")
public class WxPayRedpackQueryRequest extends WxPayBaseRequest {
  /**
   * 商户订单号
   * mch_billno
   * 是
   * 10000098201411111234567890
   * String(28)
   * 商户发放红包的商户订单号
   */
  @XStreamAlias("mch_billno")
  private String mchBillNo;

  /**
   * 订单类型
   * bill_type
   * 是
   * MCHT
   * String(32)
   * MCHT:通过商户订单号获取红包信息。
   */
  @XStreamAlias("bill_type")
  private String billType;

  public String getBillType() {
    return billType;
  }

  public void setBillType(String billType) {
    this.billType = billType;
  }

  public String getMchBillNo() {
    return mchBillNo;
  }

  public void setMchBillNo(String mchBillNo) {
    this.mchBillNo = mchBillNo;
  }

  @Override
  protected void checkConstraints() {

  }
}

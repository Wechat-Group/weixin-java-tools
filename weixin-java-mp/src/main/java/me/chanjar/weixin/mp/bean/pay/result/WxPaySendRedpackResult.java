package me.chanjar.weixin.mp.bean.pay.result;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 向微信用户个人发现金红包返回结果
 * https://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=13_5
 * @author kane
 *
 */
@XStreamAlias("xml")
public class WxPaySendRedpackResult extends WxPayBaseResult implements Serializable {
  private static final long serialVersionUID = -4837415036337132073L;

  @XStreamAlias("mch_billno")
  private String mchBillno;

  @XStreamAlias("wxappid")
  private String wxappid;

  @XStreamAlias("re_openid")
  private String reOpenid;

  @XStreamAlias("total_amount")
  private int totalAmount;

  @XStreamAlias("send_time")
  private String sendTime;

  @XStreamAlias("send_listid")
  private String sendListid;

  public String getMchBillno() {
    return this.mchBillno;
  }

  public void setMchBillno(String mchBillno) {
    this.mchBillno = mchBillno;
  }

  public String getWxappid() {
    return this.wxappid;
  }

  public void setWxappid(String wxappid) {
    this.wxappid = wxappid;
  }

  public String getReOpenid() {
    return this.reOpenid;
  }

  public void setReOpenid(String reOpenid) {
    this.reOpenid = reOpenid;
  }

  public int getTotalAmount() {
    return this.totalAmount;
  }

  public void setTotalAmount(int totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getSendTime() {
    return this.sendTime;
  }

  public void setSendTime(String sendTime) {
    this.sendTime = sendTime;
  }

  public String getSendListid() {
    return this.sendListid;
  }

  public void setSendListid(String sendListid) {
    this.sendListid = sendListid;
  }
}

package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * 向微信用户个人发现金红包返回结果
 * https://pay.weixin.qq.com/wiki/doc/api/cash_coupon.php?chapter=13_5
 *
 * @author kane
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxPaySendRedpackResult extends BaseWxPayResult implements Serializable {
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

  /**
   * 从XML结构中加载额外的熟悉
   *
   * @param d Document
   */
  @Override
  protected void loadXML(Document d) {
    mchBillno = readXMLString(d, "mch_billno");
    wxappid = readXMLString(d, "wxappid");
    reOpenid = readXMLString(d, "re_openid");
    totalAmount = readXMLInteger(d, "total_amount");
    sendTime = readXMLString(d, "send_time");
    sendListid = readXMLString(d, "send_listid");
  }

}

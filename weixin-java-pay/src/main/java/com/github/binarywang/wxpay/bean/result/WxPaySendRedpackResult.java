package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

}

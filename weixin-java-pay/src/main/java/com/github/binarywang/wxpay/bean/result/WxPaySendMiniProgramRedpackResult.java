package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * 发送小程序红包的返回结果
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxPaySendMiniProgramRedpackResult extends BaseWxPayResult implements Serializable {
  private static final long serialVersionUID = 5847928569755121611L;

  /**
   * 商户订单号.
   */
  @XStreamAlias("mch_billno")
  private String mchBillNo;

  /**
   * 公众账号appid.
   */
  @XStreamAlias("wxappid")
  private String wxAppid;

  /**
   * 用户openid.
   */
  @XStreamAlias("re_openid")
  private String reOpenid;

  /**
   * 付款金额.
   */
  @XStreamAlias("total_amount")
  private int totalAmount;

  /**
   * 返回jaspi的入参package的值.
   */
  @XStreamAlias("package")
  private String packageName;

  /**
   * 微信单号.
   */
  @XStreamAlias("send_listid")
  private String sendListId;

  @Override
  protected void loadXml(Document d) {
    mchBillNo = readXmlString(d, "mch_billno");
    wxAppid = readXmlString(d, "wxappid");
    reOpenid = readXmlString(d, "re_openid");
    totalAmount = readXmlInteger(d, "total_amount");
    packageName = readXmlString(d, "package");
    sendListId = readXmlString(d, "send_listid");
  }
}

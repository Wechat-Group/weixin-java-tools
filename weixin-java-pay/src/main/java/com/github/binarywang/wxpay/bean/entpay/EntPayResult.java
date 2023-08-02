package com.github.binarywang.wxpay.bean.entpay;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * <pre>
 * 企业付款返回结果
 * Created by Binary Wang on 2016/10/02.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class EntPayResult extends BaseWxPayResult  implements Serializable {
  private static final long serialVersionUID = 8523569987269603097L;

  /**
   * 商户号.
   */
  @XStreamAlias("mchid")
  private String mchId;

  /**
   * 商户appid.
   */
  @XStreamAlias("mch_appid")
  private String mchAppid;

  /**
   * 设备号.
   */
  @XStreamAlias("device_info")
  private String deviceInfo;

  //############以下字段在return_code 和result_code都为SUCCESS的时候有返回##############
  /**
   * 商户订单号.
   */
  @XStreamAlias("partner_trade_no")
  private String partnerTradeNo;

  /**
   * 微信订单号.
   */
  @XStreamAlias("payment_no")
  private String paymentNo;

  /**
   * 微信支付成功时间.
   */
  @XStreamAlias("payment_time")
  private String paymentTime;

  /**
   * 企业付款失败时返回的状态码.
   */
  @XStreamAlias("retcode")
  protected String retCode;

  /**
   * 企业付款失败时返回的信息.
   */
  @XStreamAlias("retmsg")
  protected String retMsg;

  @Override
  protected void loadXml(Document d) {
    mchId = readXmlString(d, "mchid");
    mchAppid = readXmlString(d, "mch_appid");
    deviceInfo = readXmlString(d, "device_info");
    partnerTradeNo = readXmlString(d, "partner_trade_no");
    paymentNo = readXmlString(d, "payment_no");
    paymentTime = readXmlString(d, "payment_time");
  }
}

package com.github.binarywang.wxpay.bean.entpay;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
public class EntPayResult extends BaseWxPayResult {
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

}

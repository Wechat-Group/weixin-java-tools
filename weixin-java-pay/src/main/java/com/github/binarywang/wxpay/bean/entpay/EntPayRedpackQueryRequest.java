package com.github.binarywang.wxpay.bean.entpay;

import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;

/**
 * 红包发送记录查询请求
 * @author wuyong
 * @date 2019-12-01 17:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class EntPayRedpackQueryRequest extends BaseWxPayRequest {


  /**
   * 商户订单号
   */
  @XStreamAlias("mch_billno")
  private String mchBillNo;


  @Override
  protected void checkConstraints() throws WxPayException {

  }
}

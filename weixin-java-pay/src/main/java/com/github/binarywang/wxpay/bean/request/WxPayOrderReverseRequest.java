package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * <pre>
 * 撤销订单请求类
 * Created by Binary Wang on 2017-3-23.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxPayOrderReverseRequest extends BaseWxPayRequest {

  /**
   * <pre>
   * 微信订单号
   * transaction_id
   * String(28)
   * 1217752501201400000000000000
   * 微信的订单号，优先使用
   * </pre>
   */
  @XStreamAlias("transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 商户订单号
   * out_trade_no
   * String(32)
   * 1217752501201400000000000000
   * 商户系统内部的订单号
   * transaction_id、out_trade_no二选一，如果同时存在优先级：transaction_id> out_trade_no
   * </pre>
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;

  @Override
  protected void checkConstraints() throws WxPayException {
    if (StringUtils.isBlank(transactionId) && StringUtils.isBlank(outTradeNo)) {
      throw new WxPayException("transaction_id 和 out_trade_no不能同时为空！");
    }
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("transaction_id", transactionId);
    map.put("out_trade_no", outTradeNo);
  }

}

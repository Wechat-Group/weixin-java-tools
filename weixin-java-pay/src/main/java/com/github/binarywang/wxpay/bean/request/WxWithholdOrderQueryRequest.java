package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author chenliang
 * @date 2021-08-02 5:25 下午
 *
 * <pre>代扣订单查询参数</pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxWithholdOrderQueryRequest extends BaseWxPayRequest {
  /**
   * <pre>
   * 字段名：微信订单号.
   * 变量名：transaction_id
   * 是否必填：二选一
   * 类型：String(32)
   * 示例值：1000005698
   * 微信生成的单号，支付通知中返回
   * </pre>
   */
  @XStreamAlias("transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 字段名：商户订单号.
   * 变量名：out_trade_no
   * 是否必填：二选一
   * 类型：String(32)
   * 示例值：1000005698
   * 商户系统内部订单号
   * </pre>
   */
  @XStreamAlias("out_trade_no")
  private String outTradeNo;


  @Override
  protected void checkConstraints() throws WxPayException {
    if (StringUtils.isNotBlank(transactionId) && StringUtils.isNotBlank(outTradeNo)) {
      throw new WxPayException("transactionId 和 outTradeNo 不能同时存在或同时为空，必须二选一");
    }
  }

  @Override
  protected void storeMap(Map<String, String> map) {

    map.put("transaction_id", transactionId);
    map.put("out_trade_no", outTradeNo);

  }
}

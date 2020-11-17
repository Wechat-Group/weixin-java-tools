package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * 查询汇率请求.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-05-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxPayQueryExchangeRateRequest extends BaseWxPayRequest {
  private static final long serialVersionUID = -8796516942563060554L;
  /**
   * 币种
   * fee_type
   * 是
   * String(10)
   * USD
   * 外币币种
   */
  @XStreamAlias("fee_type")
  private String feeType;

  /**
   * 日期
   * date
   * 是
   * String(14)
   * 20150807
   * 格式为yyyyMMdd，如2009年12月25日表示为20091225。时区为GMT+8 beijing
   */
  @XStreamAlias("date")
  private String date;

  @Override
  protected void checkConstraints() throws WxPayException {

  }

  @Override
  protected void storeMap(Map<String, String> map) {

  }

  @Override
  protected boolean needNonceStr() {
    return false;
  }
}

package com.github.binarywang.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * 汇率查询响应.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-05-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxPayQueryExchangeRateResult extends BaseWxPayResult implements Serializable {
  private static final long serialVersionUID = 2269734222658532364L;

  /**
   * 币种
   * fee_type
   * 是
   * String(10)
   * SUCCESS	外币币种，详细请见参数规定
   */
  @XStreamAlias("fee_type")
  private String feeType;

  /**
   * 汇率时间
   * rate_time
   * 是
   * String(14)
   * 20150807131545
   * 格式：yyyyMMddhhmmss
   */
  @XStreamAlias("rate_time")
  private String rateTime;

  /**
   * 现汇卖出价
   * rate
   * 是
   * String(15)
   * 系统错误
   * 外币标准单位乘以100折算为人民币的金额，保留4位小数(如：100美元按当时汇率折算返回的先汇卖出价是628.2100)
   */
  @XStreamAlias("rate")
  private String rate;

  @Override
  protected void loadXml(Document d) {

  }
}

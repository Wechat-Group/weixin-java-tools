package com.github.binarywang.wxpay.bean.profitsharing;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * @author : cofedream
 * @date : 2020-12-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class ProfitSharingMerchantRatioQueryResult extends BaseWxPayResult implements Serializable {
  private static final long serialVersionUID = 7556620112016338659L;

  /**
   * 服务商模式下的子商户号.<br/>
   * 2000<br/>
   * 子商户允许服务商分账的最大比例，单位万分比，比如2000表示20%
   */
  @XStreamAlias("max_ratio")
  private Integer maxRatio;

  @Override
  protected void loadXml(Document d) {
    maxRatio = readXmlInteger(d, "max_ratio");
  }

}

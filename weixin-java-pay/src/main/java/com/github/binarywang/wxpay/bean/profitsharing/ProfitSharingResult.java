package com.github.binarywang.wxpay.bean.profitsharing;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

/**
 * @author Wang GuangXin 2019/10/22 10:06
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class ProfitSharingResult extends BaseWxPayResult {
  /**
   * 微信订单号.
   */
  @XStreamAlias("transaction_id")
  private String transactionId;
  /**
   * 商户分账单号.
   */
  @XStreamAlias("out_order_no")
  private String outOrderNo;
  /**
   * 微信分账单号.
   */
  @XStreamAlias("order_id")
  private String orderId;

  @Override
  protected void loadXml(Document d) {
    transactionId = readXmlString(d, "transaction_id");
    outOrderNo = readXmlString(d, "out_order_no");
    orderId = readXmlString(d, "order_id");
  }
}

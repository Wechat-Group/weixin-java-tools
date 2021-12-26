package com.github.binarywang.wxpay.bean.profitsharing;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * @author Wang GuangXin 2019/10/22 10:06
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class ProfitSharingResult extends BaseWxPayResult implements Serializable {
  private static final long serialVersionUID = 7435709584788869456L;

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

  /**
   * 分账单状态.
   */
  @XStreamAlias("status")
  private String status;

  /**
   * 分账接收方列表.
   */
  @XStreamAlias("receivers")
  private String receivers;

  @Override
  protected void loadXml(Document d) {
    transactionId = readXmlString(d, "transaction_id");
    outOrderNo = readXmlString(d, "out_order_no");
    orderId = readXmlString(d, "order_id");
    status = readXmlString(d, "status");
    receivers = readXmlString(d, "receivers");
  }
}

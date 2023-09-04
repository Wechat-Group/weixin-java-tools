package com.github.binarywang.wxpay.bean.profitsharing.result;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * @author : cofedream
 * created on  : 2020-12-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class ProfitSharingOrderAmountQueryResult extends BaseWxPayResult implements Serializable {
  private static final long serialVersionUID = 7355605400662796198L;
  /**
   * 微信订单号.
   */
  @XStreamAlias("transaction_id")
  private String transactionId;
  /**
   * 订单剩余待分金额.
   */
  @XStreamAlias("unsplit_amount")
  private Integer unSplitAmount;

  @Override
  protected void loadXml(Document d) {
    transactionId = readXmlString(d, "transaction_id");
    unSplitAmount = readXmlInteger(d, "unsplit_amount");
  }

}

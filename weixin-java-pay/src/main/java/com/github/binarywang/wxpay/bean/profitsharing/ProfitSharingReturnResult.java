package com.github.binarywang.wxpay.bean.profitsharing;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

/**
 * @author Wang GuangXin 2019/10/23 14:41
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class ProfitSharingReturnResult extends BaseWxPayResult {
  private static final long serialVersionUID = 718554909816994568L;
  /**
   * 微信分账单号
   */
  @XStreamAlias("order_id")
  private String orderId;
  /**
   * 商户分账单号
   */
  @XStreamAlias("out_order_no")
  private String outOrderNo;
  /**
   * 商户回退单号
   */
  @XStreamAlias("out_return_no")
  private String outReturnNo;
  /**
   * 微信回退单号
   */
  @XStreamAlias("return_no")
  private String returnNo;
  /**
   * 回退方类型
   */
  @XStreamAlias("return_account_type")
  private String returnAccountType;
  /**
   * 回退方账号
   */
  @XStreamAlias("return_account")
  private String returnAccount;
  /**
   * 回退金额
   */
  @XStreamAlias("return_amount")
  private Integer returnAmount;
  /**
   * 回退描述
   */
  @XStreamAlias("description")
  private String description;
  /**
   * 回退结果
   */
  @XStreamAlias("result")
  private String result;
  /**
   * 失败原因
   */
  @XStreamAlias("fail_reason")
  private String failReason;
  /**
   * 完成时间
   */
  @XStreamAlias("finish_time")
  private String finishTime;

  @Override
  protected void loadXML(Document d) {
    orderId = readXMLString(d, "order_id");
    outOrderNo = readXMLString(d, "out_order_no");
    outReturnNo = readXMLString(d, "out_return_no");
    returnNo = readXMLString(d, "return_no");
    returnAccountType = readXMLString(d, "return_account_type");
    returnAccount = readXMLString(d, "return_account");
    returnAmount = readXMLInteger(d, "return_amount");
    description = readXMLString(d, "description");
    result = readXMLString(d, "result");
    failReason = readXMLString(d, "fail_reason");
    finishTime = readXMLString(d, "finish_time");
  }
}

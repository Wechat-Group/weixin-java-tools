package com.github.binarywang.wxpay.bean.profitsharing.result;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * @author Wang GuangXin 2019/10/23 14:41
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class ProfitSharingReturnResult extends BaseWxPayResult implements Serializable {
  private static final long serialVersionUID = 718554909816994568L;

  /**
   * 如果返回状态码为FAIL，则本字段存在，且为失败的错误码.
   */
  @XStreamAlias("error_code")
  private String errorCode;

  /**
   * 如果返回状态码为FAIL，则本字段存在，且为失败的错误信息.
   */
  @XStreamAlias("error_msg")
  private String errorMsg;

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
  protected void loadXml(Document d) {
    orderId = readXmlString(d, "order_id");
    outOrderNo = readXmlString(d, "out_order_no");
    outReturnNo = readXmlString(d, "out_return_no");
    returnNo = readXmlString(d, "return_no");
    returnAccountType = readXmlString(d, "return_account_type");
    returnAccount = readXmlString(d, "return_account");
    returnAmount = readXmlInteger(d, "return_amount");
    description = readXmlString(d, "description");
    result = readXmlString(d, "result");
    failReason = readXmlString(d, "fail_reason");
    finishTime = readXmlString(d, "finish_time");
  }

  @Override
  public String getErrCode() {
    return this.errorCode;
  }

  @Override
  public String getErrCodeDes() {
    return this.errorMsg;
  }
}

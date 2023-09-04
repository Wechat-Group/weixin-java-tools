package com.github.binarywang.wxpay.bean.profitsharing.request;

import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

import java.io.Serializable;

/**
 * @author lyt 2023/08/21 15:44
 * @version 1.0
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class ProfitSharingQueryV3Request implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * <pre>
   * 字段名：子商户号
   * 是否必填：是
   * 描述：微信支付分配的子商户号，即分账的出资商户号。
   * </pre>
   */
  @SerializedName("sub_mchid")
  private String subMchId;
  /**
   * <pre>
   * 字段名：微信支付订单号.
   * 变量名：transaction_id
   * 是否必填：是
   * String(32)
   * 示例值：4208450740201411110007820472
   * 描述：微信支付订单号
   * </pre>
   */
  @XStreamAlias("transaction_id")
  @Required
  private String transactionId;

  /**
   * <pre>
   * 字段名：商户分账单号.
   * 变量名：out_order_no
   * 是否必填：是
   * String(64)
   * 示例值：P20150806125346
   * 描述：查询分账结果，输入申请分账时的商户分账单号； 查询分账完结的执行结果，输入发起分账完结时的商户分账单号
   * </pre>
   */
  @XStreamAlias("out_order_no")
  @Required
  private String outOrderNo;

}

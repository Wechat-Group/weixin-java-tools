package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * 查询订单剩余待分金额API 结果响应
 *
 * @author mshyh
 * created on  2022/05/26
 */

@Data
@NoArgsConstructor
public class ProfitSharingOrdersUnSplitAmountResult {

  /**
   * <pre>
   * 字段名：微信支付订单号
   * 变量名：transaction_id
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：微信支付订单号。
   * 示例值：4208450740201411110007820472
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 字段名：订单剩余待分金额
   * 变量名：unsplit_amount
   * 是否必填：是
   * 类型：int
   * 描述：订单剩余待分金额，整数，单位为分。
   * 示例值：1000
   * </pre>
   */
  @SerializedName(value = "unsplit_amount")
  private Integer unsplitAmount;


}

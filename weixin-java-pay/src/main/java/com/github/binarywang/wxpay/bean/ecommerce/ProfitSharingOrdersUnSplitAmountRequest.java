package com.github.binarywang.wxpay.bean.ecommerce;


import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 查询订单剩余待分金额API 请求对象
 *
 * @author mshyh
 * created on  2022/05/26
 */


@Data
@NoArgsConstructor
public class ProfitSharingOrdersUnSplitAmountRequest {

  /**
   * <pre>
   * 字段名：微信订单号
   * 变量名：transaction_id
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：微信支付订单号
   * 示例值：4208450740201411110007820472
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;



}

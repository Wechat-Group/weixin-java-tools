package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: f00lish
 * @date: 2020/09/12
 */
@Data
@NoArgsConstructor
public class FundBalanceResult {
  /**
   * <pre>
   * 字段名：二级商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string(32)
   * 描述：
   *  电商平台二级商户号，由微信支付生成并下发。
   *  示例值：1900000109
   * </pre>
   */
  @SerializedName("sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：可用余额
   * 变量名：available_amount
   * 是否必填：是
   * 类型：int64
   * 描述：
   *  可用余额（单位：分），此余额可做提现操作。
   *  示例值：100
   * </pre>
   */
  @SerializedName("available_amount")
  private Integer availableAmount;

  /**
   * <pre>
   * 字段名：不可用余额
   * 变量名：pending_amount
   * 是否必填：否
   * 类型：int64
   * 描述：
   *  不可用余额（单位：分）。
   *  示例值：100
   * </pre>
   */
  @SerializedName("pending_amount")
  private Integer pendingAmount;


}

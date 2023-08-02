package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProfitSharingReceiverResult implements Serializable {

  /**
   * <pre>
   * 字段名：接收方类型
   * 变量名：type
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  分账接收方的类型，枚举值：
   *    MERCHANT_ID：商户
   *    PERSONAL_OPENID：个人
   * 示例值：MERCHANT_ID
   * </pre>
   */
  @SerializedName(value = "type")
  private String type;

  /**
   * <pre>
   * 字段名：接收方账号
   * 变量名：account
   * 是否必填：是
   * 类型：string（64）
   * 描述：
   *  分账接收方的账号
   *    类型是MERCHANT_ID时，是商户号
   *    类型是PERSONAL_OPENID时，是个人openid，openid获取方法
   * 示例值：190001001
   * </pre>
   */
  @SerializedName(value = "account")
  private String account;
}

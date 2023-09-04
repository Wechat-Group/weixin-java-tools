package com.github.binarywang.wxpay.bean.profitsharing.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信V3接口
 * 解冻剩余资金API请求实体
 *
 * @author pg
 * created on  2021-6-25
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class ProfitSharingUnfreezeV3Request implements Serializable {
  private static final long serialVersionUID = 6835471990040104843L;

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
   * 字段名：微信订单号
   * 是否必填：是
   * 描述：微信支付订单号
   * </pre>
   */
  @SerializedName("transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 字段名：商户分账单号
   * 是否必填：是
   * 描述：商户系统内部的分账单号，在商户系统内部唯一，同一分账单号多次请求等同一次。只能是数字、大小写字母_-|*@
   * </pre>
   */
  @SerializedName("out_order_no")
  private String outOrderNo;

  /**
   * <pre>
   * 字段名：分账描述
   * 是否必填：是
   * 描述： 分账的原因描述，分账账单中需要体现
   * </pre>
   */
  @SerializedName("description")
  private String description;
}

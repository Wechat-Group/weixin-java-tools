package com.github.binarywang.wxpay.bean.profitsharingV3;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信V3接口
 * 请求分账回退API请求实体
 *
 * @author pg
 * @date 2021-6-25
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class ProfitSharingReturnRequest implements Serializable {
  private static final long serialVersionUID = -2175582517588397426L;

  /**
   * <pre>
   * 字段名：微信分账单号
   * 是否必填：是
   * 描述：微信分账单号，微信系统返回的唯一标识。
   * </pre>
   */
  @SerializedName("order_id")
  private String orderId;

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
   * 字段名：商户回退单号
   * 是否必填：是
   * 描述：此回退单号是商户在自己后台生成的一个新的回退单号，在商户后台唯一
   * </pre>
   */
  @SerializedName("out_return_no")
  private String outReturnNo;

  /**
   * <pre>
   * 字段名：回退商户号
   * 是否必填：是
   * 描述：分账回退的出资商户，只能对原分账请求中成功分给商户接收方进行回退
   * </pre>
   */
  @SerializedName("return_mchid")
  private String returnMchid;

  /**
   * <pre>
   * 字段名：回退金额
   * 是否必填：是
   * 描述：需要从分账接收方回退的金额，单位为分，只能为整数，不能超过原始分账单分出给该接收方的金额
   * </pre>
   */
  private Long amount;

  /**
   * <pre>
   * 字段名：回退描述
   * 是否必填：是
   * 描述： 分账回退的原因描述
   * </pre>
   */
  private String description;
}

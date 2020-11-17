package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 二级商户进件 提交申请结果响应
 */
@Data
@NoArgsConstructor
public class ApplymentsResult implements Serializable {
  private static final long serialVersionUID = -4549193755252593150L;
  /**
   * <pre>
   * 字段名：微信支付申请单号
   * 变量名：applyment_id
   * 是否必填：是
   * 类型：uint64
   * 描述：
   *  微信支付分配的申请单号 。
   *  示例值：2000002124775691
   * </pre>
   */
  @SerializedName(value = "applyment_id")
  private String applymentId;

  /**
   * <pre>
   * 字段名：业务申请编号
   * 变量名：out_request_no
   * 是否必填：是
   * 类型：string(124)
   * 描述：
   *  服务商自定义的商户唯一编号。每个编号对应一个申请单，每个申请单审核通过后会生成一个微信支付商户号。
   *  示例值：APPLYMENT_00000000001
   * </pre>
   */
  @SerializedName(value = "out_request_no")
  private String outRequestNo;
}

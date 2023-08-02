package com.github.binarywang.wxpay.bean.customs;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xifengzhu
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class VerifyCertificateResult implements Serializable {
  private static final long serialVersionUID = -8578640869555299753L;
  /**
   * <pre>
   * 字段名：机构APPID
   * 变量名：appid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信分配的公众账号ID
   *  示例值：wxd678efh567hg6787
   * </pre>
   */
  @SerializedName(value = "appid")
  private String appid;

  /**
   * <pre>
   * 字段名：商户号
   * 变量名：mchid
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信支付分配的商户号
   *  示例值：1230000109
   * </pre>
   */
  @SerializedName(value = "mchid")
  private String mchid;

  /**
   * <pre>
   * 字段名：商户订单号
   * 变量名：out_trade_no
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-&#124;*@ ，且在同一个商户号下唯一
   *  示例值：20150806125346
   * </pre>
   */
  @SerializedName(value = "out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 字段名：微信支付返回的订单号
   * 变量名：transaction_id
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信分配的公众账号ID
   *  示例值：1000320306201511078440737890
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 字段名：身份核验结果
   * 变量名：certificate_check_result
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  订购人和支付人身份信息校验结果
   *  SAME：身份信息校验匹配
   *  DIFFERENT：身份信息校验不匹配
   *  示例值：SAME
   * </pre>
   */
  @SerializedName(value = "certificate_check_result")
  private String certificateCheckResult;
}

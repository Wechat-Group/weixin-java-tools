package com.github.binarywang.wxpay.bean.profitsharingV3;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信V3接口
 * 请求分账回退API返回实体
 *
 * @author pg
 * @date 2021-6-25
 */
@Data
public class ProfitSharingReturnResult implements Serializable {
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
   * 字段名：微信回退单号
   * 是否必填：是
   * 描述：微信分账回退单号，微信系统返回的唯一标识
   * </pre>
   */
  @SerializedName("return_id")
  private String returnId;

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

  /**
   * <pre>
   * 字段名：分账结果
   * 是否必填：是
   * 描述：
   * 如果请求返回为处理中，则商户可以通过调用回退结果查询接口获取请求的最终处理结果。
   * 如果查询到回退结果在处理中，请勿变更商户回退单号，使用相同的参数再次发起分账回退，否则会出现资金风险。
   * 在处理中状态的回退单如果5天没有成功，会因为超时被设置为已失败。
   * PROCESSING：处理中
   * SUCCESS：已成功
   * FAILED：已失败
   * </pre>
   */
  @SerializedName("result")
  private String result;

  /**
   * <pre>
   * 字段名：失败原因
   * 是否必填：是
   * 描述：失败原因。包含以下枚举值：
   * ACCOUNT_ABNORMAL : 分账接收方账户异常
   * TIME_OUT_CLOSED : 超时关单
   * </pre>
   */
  @SerializedName("fail_reason")
  private String failReason;

  /**
   * <pre>
   * 字段名：分账回退创建时间
   * 是否必填：是
   * 描述：遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，
   * YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，
   * HH:mm:ss.sss表示时分秒毫秒，
   * TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
   * 例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
   * </pre>
   */
  @SerializedName("create_time")
  private String createTime;
  /**
   * <pre>
   * 字段名：分账回退完成时间
   * 是否必填：是
   * 描述：遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，
   * YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，
   * HH:mm:ss.sss表示时分秒毫秒，
   * TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
   * 例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
   * </pre>
   */
  @SerializedName("finish_time")
  private String finishTime;
}

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
public class RedeclareResult implements Serializable {

  private static final long serialVersionUID = 8863516626598050095L;
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
   * 字段名：微信订单号
   * 变量名：transaction_id
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  微信支付返回的订单号
   *  示例值：1000320306201511078440737890
   * </pre>
   */
  @SerializedName(value = "transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 字段名：商户订单号
   * 变量名：out_trade_no
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一
   *  示例值：20150806125346
   * </pre>
   */
  @SerializedName(value = "out_trade_no")
  private String outTradeNo;

  /**
   * <pre>
   * 字段名：商户子订单号
   * 变量名：sub_order_no
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  商户子订单号，如有拆单则必传
   *  注意：仅适用于机构模式
   *  示例值：20150806125346
   * </pre>
   */
  @SerializedName(value = "sub_order_no")
  private String subOrderNo;

  /**
   * <pre>
   * 字段名：微信子订单号
   * 变量名：sub_order_id
   * 是否必填：否
   * 类型：string（32）
   * 描述：
   *  商户子订单号，如有拆单则必传
   *  注意：仅适用于机构模式
   *  示例值：20150806125346
   * </pre>
   */
  @SerializedName(value = "sub_order_id")
  private String subOrderId;

  /**
   * <pre>
   * 字段名：报关状态
   * 变量名：state
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  申报结果状态码
   *  PROCESSING：申报中
   *  UNDECLARED：未申报
   *  SUBMITTED：已修改未申报
   *  SUCCESS：申报成功
   *  FAIL：申报失败
   *  EXCEPT：海关接口异常
   *  示例值：PROCESSING
   * </pre>
   */
  @SerializedName(value = "state")
  private String state;

  /**
   * <pre>
   * 字段名：报关结果说明
   * 变量名：explanation
   * 是否必填：是
   * 类型：string（128）
   * 描述：
   *  申报结果说明，如果状态是失败或异常，显示失败原因
   *  示例值：支付单已存在并且为非退单状态
   * </pre>
   */
  @SerializedName(value = "explanation")
  private String explanation;

  /**
   * <pre>
   * 字段名：最后更新时间
   * 变量名：modify_time
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  最后更新时间，该时间取自微信服务器
   *  示例值：2015-09-01T10:00:00+08:00
   * </pre>
   */
  @SerializedName(value = "modify_time")
  private String modifyTime;
}

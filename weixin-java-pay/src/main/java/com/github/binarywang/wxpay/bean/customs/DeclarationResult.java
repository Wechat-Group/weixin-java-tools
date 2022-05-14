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
public class DeclarationResult implements Serializable {

  private static final long serialVersionUID = -5895139329545995308L;
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
   *  示例值：wxd678efh567hg6787
   * </pre>
   */
  @SerializedName(value = "20150806125346")
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
   * 字段名：商户子订单号
   * 变量名：sub_order_no
   * 是否必填：否
   * 类型：string（32）
   * 描述：
   *  微信子订单号，如有拆单则返回
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
   * 字段名：核验机构
   * 变量名：verify_department
   * 是否必填：是
   * 类型：string（16）
   * 描述：
   *  核验机构代码
   *  UNIONPAY：银联
   *  NETSUNION：网联
   *  OTHERS：其他
   *  示例值：UNIONPAY
   * </pre>
   */
  @SerializedName(value = "verify_department")
  private String verifyDepartment;

  /**
   * <pre>
   * 字段名：核验机构交易流水号
   * 变量名：Verify_department_trade_id
   * 是否必填：是
   * 类型：string(64)
   * 描述：
   *  交易流水号，来自验核机构，如银联记录的交易流水号，供商户报备海关
   *  示例值：2018112288340107038204310100000
   * </pre>
   */
  @SerializedName(value = "verify_department_trade_id")
  private String verifyDepartmentTradeId;
}

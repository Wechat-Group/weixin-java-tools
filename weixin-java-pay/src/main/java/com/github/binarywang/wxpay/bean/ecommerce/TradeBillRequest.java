package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * 交易账单请求
 * @author: f00lish
 * @date: 2020/09/28
 */
@Data
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TradeBillRequest {

  /**
   * <pre>
   * 字段名：账单日期
   * 变量名：bill_date
   * 是否必填：是
   * 类型：string（10）
   * 描述：
   *  格式YYYY-MM-DD
   *  仅支持三个月内的账单下载申请。
   *  示例值：2019-06-11
   * </pre>
   */
  @SerializedName(value = "bill_date")
  private String billDate;

  /**
   * <pre>
   * 字段名：二级商户号
   * 变量名：sub_mchid
   * 是否必填：否
   * 类型：string（12）
   * 描述：
   *  1、若商户是直连商户：无需填写该字段。
   *  2、若商户是服务商：
   *  ● 不填则默认返回服务商下的交易或退款数据。
   *  ● 如需下载某个子商户下的交易或退款数据，则该字段必填。
   *  特殊规则：最小字符长度为8
   *  注意：仅适用于电商平台 服务商
   *  示例值：1900000001
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;

  /**
   * <pre>
   * 字段名：账单类型
   * 变量名：bill_type
   * 是否必填：否
   * 类型：string（32）
   * 描述：
   *  不填则默认是ALL
   *  枚举值：
   *  ALL：返回当日所有订单信息（不含充值退款订单）
   *  SUCCESS：返回当日成功支付的订单（不含充值退款订单）
   *  REFUND：返回当日退款订单（不含充值退款订单）
   *  示例值：ALL
   * </pre>
   */
  @SerializedName(value = "bill_type")
  private String billType;

  /**
   * <pre>
   * 字段名：压缩类型
   * 变量名：tar_type
   * 是否必填：否
   * 类型：string（32）
   * 描述：
   *  不填则默认是数据流
   *  枚举值：
   *  GZIP：返回格式为.gzip的压缩包账单
   *  示例值：GZIP
   * </pre>
   */
  @SerializedName(value = "tar_type")
  private String tarType;

}

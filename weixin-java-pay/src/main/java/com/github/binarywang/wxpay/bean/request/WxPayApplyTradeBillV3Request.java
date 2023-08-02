package com.github.binarywang.wxpay.bean.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 微信支付-申请账单入参
 * </pre>
 *
 * @author thinsstar
 */
@Data
@NoArgsConstructor
public class WxPayApplyTradeBillV3Request implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * <pre>
   * 字段名：账单日期
   * 变量名：bill_date
   * 是否必填：是
   * 类型：string[1,10]
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
   * 字段名：账单类型
   * 变量名：bill_type
   * 是否必填：是
   * 类型：string[1,32]
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
   * 是否必填：是
   * 类型：string[1,32]
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

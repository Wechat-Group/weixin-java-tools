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
public class WxPayApplyFundFlowBillV3Request implements Serializable {
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
   * 字段名：资金账户类型
   * 变量名：account_type
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  不填则默认是BASIC
   *  枚举值：
   *  BASIC：基本账户
   *  OPERATION：运营账户
   *  FEES：手续费账户
   *  示例值：BASIC
   * </pre>
   */
  @SerializedName(value = "account_type")
  private String accountType;
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

package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;

/**
 * 资金账单请求
 *
 * @author: f00lish
 * @date: 2020/09/28
 */
@Data
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FundBillRequest implements Serializable {
  private static final long serialVersionUID = 686005394786326248L;

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
   * 字段名：资金账户类型
   * 变量名：account_type
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  枚举值：
   *  ALL：所有账户
   *  示例值：ALL
   * </pre>
   */
  @SerializedName(value = "account_type")
  private String accountType;

  /**
   * <pre>
   * 字段名：压缩类型
   * 变量名：tar_type
   * 是否必填：否
   * 类型：string（32）
   * 描述：
   *  不填则以不压缩的方式返回数据流
   *  枚举值：
   *  GZIP：返回格式为.gzip的压缩包账单
   *  示例值：GZIP
   * </pre>
   */
  @SerializedName(value = "tar_type")
  private String tarType;

  /**
   * <pre>
   * 字段名：加密算法
   * 变量名：algorithm
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  枚举值：
   *  AEAD_AES_256_GCM：AEAD_AES_256_GCM加密算法
   *  示例值：AEAD_AES_256_GCM
   * </pre>
   */
  @SerializedName(value = "algorithm")
  private String algorithm;
}

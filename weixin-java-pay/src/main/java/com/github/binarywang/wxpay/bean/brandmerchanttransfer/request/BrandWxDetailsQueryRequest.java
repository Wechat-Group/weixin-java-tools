package com.github.binarywang.wxpay.bean.brandmerchanttransfer.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 品牌红包微信支付明细单号查询明细单API参数
 *
 * @author moran
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BrandWxDetailsQueryRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：微信支付品牌红包批次单号
   * 变量名：batch_no
   * 是否必填：是
   * 类型：string[32, 64]
   * 描述：
   *  path微信支付品牌红包批次单号，微信商家品牌红包系统返回的唯一标识
   * 示例值：1030000071100999991182020050700019480001
   * </pre>
   */
  @SerializedName("batch_no")
  private String batchNo;

  /**
   * <pre>
   * 字段名：微信明细单号
   * 变量名：detail_no
   * 是否必填：是
   * 类型：string[32, 64]
   * 描述：
   *  path微信支付系统内部区分品牌红包批次单下不同品牌红包明细单的唯一标识
   * 示例值：1040000071100999991182020050700019500100
   * </pre>
   */
  @SerializedName("detail_no")
  private String detailNo;
}

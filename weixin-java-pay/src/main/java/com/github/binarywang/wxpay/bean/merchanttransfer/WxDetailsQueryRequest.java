package com.github.binarywang.wxpay.bean.merchanttransfer;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * The type Wx details query request.
 *
 * @author glz
 * created on  2022-6-11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxDetailsQueryRequest implements Serializable {
  private static final long serialVersionUID = 4869511970509348272L;

  /**
   * <pre>
   * 字段名：微信批次单号
   * 变量名：batch_id
   * 是否必填：是
   * 类型：string[1,64]
   * 描述：
   *  path微信批次单号，微信商家转账系统返回的唯一标识
   * 示例值：1030000071100999991182020050700019480001
   * </pre>
   */
  @SerializedName("batch_id")
  private String batchId;

  /**
   * <pre>
   * 字段名：微信明细单号
   * 变量名：detail_id
   * 是否必填：是
   * 类型：string[1,64]
   * 描述：
   *  path微信支付系统内部区分转账批次单下不同转账明细单的唯一标识
   * 示例值：1040000071100999991182020050700019500100
   * </pre>
   */
  @SerializedName("detail_id")
  private String detailId;
}

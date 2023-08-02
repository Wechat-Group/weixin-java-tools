package com.github.binarywang.wxpay.bean.merchanttransfer;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * The type Merchant details query request.
 *
 * @author glz
 * created on  2022-6-11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MerchantDetailsQueryRequest implements Serializable {
  private static final long serialVersionUID = 3167548999175561804L;
  /**
   * <pre>
   * 字段名：商家明细单号
   * 变量名：out_detail_no
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * path商户系统内部区分转账批次单下不同转账明细单的唯一标识，要求此参数只能由数字、大小写字母组成
   * 示例值：x23zy545Bd5436
   * </pre>
   */
  @SerializedName("out_detail_no")
  private String outDetailNo;

  /**
   * <pre>
   * 字段名：商家批次单号
   * 变量名：out_batch_no
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  path商户系统内部的商家批次单号，要求此参数只能由数字、大小写字母组成，在商户系统内部唯一
   * 示例值：plfk2020042013
   * </pre>
   */
  @SerializedName("out_batch_no")
  private String outBatchNo;
}

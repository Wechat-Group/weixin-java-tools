package com.github.binarywang.wxpay.bean.merchanttransfer;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * The type Detail electronic bill request.
 *
 * @author glz
 * created on  2022-6-11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DetailElectronicBillRequest implements Serializable {
  private static final long serialVersionUID = 716155129313310192L;
  /**
   * <pre>
   * 字段名：受理类型
   * 变量名：accept_type
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * query电子回单受理类型：
   * BATCH_TRANSFER：批量转账明细电子回单
   * TRANSFER_TO_POCKET：企业付款至零钱电子回单
   * TRANSFER_TO_BANK：企业付款至银行卡电子回单
   * 示例值：BATCH_TRANSFER
   * </pre>
   */
  @SerializedName("accept_type")
  private String acceptType;

  /**
   * <pre>
   * 字段名：商家转账批次单号
   * 变量名：out_batch_no
   * 是否必填：否
   * 类型：string[5,32]
   * 描述：
   * query需要电子回单的批量转账明细单所在的转账批次单号，该单号为商户申请转账时生成的商户单号。受理类型为BATCH_TRANSFER时该单号必填，否则该单号留空。
   * 示例值：GD2021011610162610BBdkkIwcu3
   * </pre>
   */
  @SerializedName("out_batch_no")
  private String outBatchNo;

  /**
   * <pre>
   * 字段名：商家转账明细单号
   * 变量名：out_detail_no
   * 是否必填：是
   * 类型：string[5,32]
   * 描述：
   * query该单号为商户申请转账时生成的商家转账明细单号。
   * 1.受理类型为BATCH_TRANSFER时填写商家批量转账明细单号。
   * 2. 受理类型为TRANSFER_TO_POCKET或TRANSFER_TO_BANK时填写商家转账单号。
   * 示例值：mx0911231610162610v4CNkO4HAf
   * </pre>
   */
  @SerializedName("out_detail_no")
  private String outDetailNo;
}

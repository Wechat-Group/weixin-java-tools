package com.github.binarywang.wxpay.bean.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商家转账结果
 *
 * @author zhongjun
 * created on  2022/6/17
 **/
@Data
@NoArgsConstructor
public class TransferBatchesResult implements Serializable {
  private static final long serialVersionUID = -2175582517588397426L;

  /**
   * 商家批次单号
   */
  @SerializedName("out_batch_no")
  private String outBatchNo;

  /**
   * 微信批次单号
   */
  @SerializedName("batch_id")
  private String batchId;

  /**
   * 批次创建时间
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 批次状态
   */
  @SerializedName("batch_status")
  private String batchStatus;
}

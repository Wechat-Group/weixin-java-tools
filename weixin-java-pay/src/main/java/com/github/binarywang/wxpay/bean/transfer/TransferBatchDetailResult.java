package com.github.binarywang.wxpay.bean.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 微信明细单号查询明细单API
 *
 * @author zhongjun
 */
@NoArgsConstructor
@Data
public class TransferBatchDetailResult implements Serializable {
  private static final long serialVersionUID = -2175582517588397426L;

  @SerializedName("mchid")
  private String mchid;

  @SerializedName("out_batch_no")
  private String outBatchNo;

  @SerializedName("batch_id")
  private String batchId;

  @SerializedName("appid")
  private String appid;

  @SerializedName("out_detail_no")
  private String outDetailNo;

  @SerializedName("detail_id")
  private String detailId;

  @SerializedName("detail_status")
  private String detailStatus;

  @SerializedName("transfer_amount")
  private Integer transferAmount;

  @SerializedName("transfer_remark")
  private String transferRemark;

  @SerializedName("fail_reason")
  private String failReason;

  @SerializedName("openid")
  private String openid;

  @SerializedName("user_name")
  private String userName;

  @SerializedName("initiate_time")
  private String initiateTime;

  @SerializedName("update_time")
  private String updateTime;
}

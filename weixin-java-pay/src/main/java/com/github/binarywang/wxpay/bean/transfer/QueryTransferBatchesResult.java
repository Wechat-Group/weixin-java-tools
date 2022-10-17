package com.github.binarywang.wxpay.bean.transfer;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 查询微信批次单号查询批次单API响应
 *
 * @author zhongjun
 * created on  2022/6/17
 **/
@Data
@NoArgsConstructor
public class QueryTransferBatchesResult implements Serializable {
  private static final long serialVersionUID = -2175582517588397426L;

  @SerializedName("offset")
  private Integer offset;

  @SerializedName("limit")
  private Integer limit;

  @SerializedName("transfer_batch")
  private TransferBatch transferBatch;

  @SerializedName("transfer_detail_list")
  private List<TransferDetail> transferDetailList;

  @NoArgsConstructor
  @Data
  public static class TransferBatch {
    @SerializedName("mchid")
    private String mchid;

    @SerializedName("out_batch_no")
    private String outBatchNo;

    @SerializedName("batch_id")
    private String batchId;

    @SerializedName("appid")
    private String appid;

    @SerializedName("batch_status")
    private String batchStatus;

    @SerializedName("batch_type")
    private String batchType;

    @SerializedName("batch_name")
    private String batchName;

    @SerializedName("batch_remark")
    private String batchRemark;

    @SerializedName("close_reason")
    private String closeReason;

    @SerializedName("total_amount")
    private Integer totalAmount;

    @SerializedName("total_num")
    private Integer totalNum;

    @SerializedName("create_time")
    private String createTime;

    @SerializedName("update_time")
    private String updateTime;

    @SerializedName("success_amount")
    private Integer successAmount;

    @SerializedName("success_num")
    private Integer successNum;

    @SerializedName("fail_amount")
    private Integer failAmount;

    @SerializedName("fail_num")
    private Integer failNum;
  }

  @NoArgsConstructor
  @Data
  public static class TransferDetail {

    @SerializedName("detail_id")
    private String detailId;

    @SerializedName("out_detail_no")
    private String outDetailNo;

    @SerializedName("detail_status")
    private String detailStatus;
  }
}

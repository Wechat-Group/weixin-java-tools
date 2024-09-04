package com.github.binarywang.wxpay.bean.transfer;

import com.github.binarywang.wxpay.bean.notify.OriginNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayBaseNotifyV3Result;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  <pre>
 *    商家转账到零钱接口将转账结果通知用户
 *    文档地址：https://pay.weixin.qq.com/docs/merchant/apis/batch-transfer-to-balance/transfer-batch-callback-notice.html
 *  </pre>
 */
@Data
public class TransferNotifyResult implements Serializable, WxPayBaseNotifyV3Result<TransferNotifyResult.DecryptNotifyResult> {
  /**
   * 源数据
   */
  private OriginNotifyResponse rawData;
  /**
   * 解密后的数据
   */
  private TransferNotifyResult.DecryptNotifyResult result;

  @Data
  @NoArgsConstructor
  public static class DecryptNotifyResult implements Serializable {
    /**
     * 商户号
     */
    @SerializedName(value = "mchid")
    private String mchid;
    /**
     * 商家批次单号
     */
    @SerializedName(value = "out_batch_no")
    private String outBatchNo;
    /**
     * 微信批次单号
     */
    @SerializedName(value = "batch_id")
    private String batchId;
    /**
     * 批次状态
     */
    @SerializedName(value = "batch_status")
    private String batchStatus;
    /**
     * 批次总笔数
     */
    @SerializedName(value = "total_num")
    private Integer totalNum;
    /**
     * 批次总金额
     */
    @SerializedName(value = "total_amount")
    private Integer totalAmount;
    /**
     * 转账成功金额
     */
    @SerializedName(value = "success_amount")
    private Integer successAmount;
    /**
     * 转账成功笔数
     */
    @SerializedName(value = "success_num")
    private Integer successNum;
    /**
     * 转账失败金额
     */
    @SerializedName(value = "fail_amount")
    private Integer failAmount;
    /**
     * 转账失败笔数
     */
    @SerializedName(value = "fail_num")
    private Integer failNum;
    /**
     * 批次更新时间
     */
    @SerializedName(value = "update_time")
    private String updateTime;
    /**
     * 批次关闭原因
     */
    @SerializedName(value = "close_reason")
    private String closeReason;
  }
}

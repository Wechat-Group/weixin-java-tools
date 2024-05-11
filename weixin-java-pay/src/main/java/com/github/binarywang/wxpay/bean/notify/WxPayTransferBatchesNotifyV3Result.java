package com.github.binarywang.wxpay.bean.notify;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商家转账批次回调通知
 * 文档见：https://pay.weixin.qq.com/docs/merchant/apis/batch-transfer-to-balance/transfer-batch-callback-notice.html
 *
 * @author Wang_Wong
 */
@Data
@NoArgsConstructor
public class WxPayTransferBatchesNotifyV3Result implements Serializable, WxPayBaseNotifyV3Result<WxPayTransferBatchesNotifyV3Result.DecryptNotifyResult> {
  private static final long serialVersionUID = -1L;
  /**
   * 源数据
   */
  private OriginNotifyResponse rawData;
  /**
   * 解密后的数据
   */
  private DecryptNotifyResult result;

  @Data
  @NoArgsConstructor
  public static class DecryptNotifyResult implements Serializable {
    private static final long serialVersionUID = -1L;
    /**
     * <pre>
     * 字段名：直连商户号
     * 变量名：mchid
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  直连商户的商户号，由微信支付生成并下发。
     *  示例值：1900000100
     * </pre>
     */
    @SerializedName(value = "mchid")
    private String mchid;

    /**
     * 【商家批次单号】
     * 商户系统内部的商家批次单号，在商户系统内部唯一
     */
    @SerializedName(value = "out_batch_no")
    private String outBatchNo;

    /**
     * 【微信批次单号】
     * 微信批次单号，微信商家转账系统返回的唯一标识
     */
    @SerializedName(value = "batch_id")
    private String batchId;

    /**
     * 【批次状态】
     * WAIT_PAY: 待付款确认。需要付款出资商户在商家助手小程序或服务商助手小程序进行付款确认
     * ACCEPTED:已受理。批次已受理成功，若发起批量转账的30分钟后，转账批次单仍处于该状态，可能原因是商户账户余额不足等。商户可查询账户资金流水，若该笔转账批次单的扣款已经发生，则表示批次已经进入转账中，请再次查单确认
     * PROCESSING:转账中。已开始处理批次内的转账明细单
     * FINISHED:已完成。批次内的所有转账明细单都已处理完成
     * CLOSED:已关闭。可查询具体的批次关闭原因确认
     */
    @SerializedName(value = "batch_status")
    private String batchStatus;

    /**
     * 【批次总笔数】
     * 转账总笔数。
     */
    @SerializedName(value = "total_num")
    private Integer totalNum;

    /**
     * 【批次总金额】
     * 转账总金额，单位为“分”。
     */
    @SerializedName(value = "total_amount")
    private Integer totalAmount;

    /**
     * 【转账成功金额】
     * 转账成功的金额，单位为“分”。当批次状态为“PROCESSING”（转账中）时，转账成功金额随时可能变化
     */
    @SerializedName(value = "success_amount")
    private Integer successAmount;

    /**
     * 【转账成功笔数】
     * 转账成功的笔数。当批次状态为“PROCESSING”（转账中）时，转账成功笔数随时可能变化
     */
    @SerializedName(value = "success_num")
    private Integer successNum;

    /**
     * 【转账失败金额】
     * 转账失败的金额，单位为“分”
     */
    @SerializedName(value = "fail_amount")
    private Integer failAmount;

    /**
     * 【转账失败笔数】
     * 转账失败的笔数
     */
    @SerializedName(value = "fail_num")
    private Integer failNum;

    /**
     * 【批次关闭原因】
     * 如果批次单状态为“CLOSED”（已关闭），则有关闭原因
     * 可选取值：
     * OVERDUE_CLOSE：系统超时关闭，可能原因账户余额不足或其他错误
     * TRANSFER_SCENE_INVALID：付款确认时，转账场景已不可用，系统做关单处理
     */
    @SerializedName(value = "close_reason")
    private String closeReason;

    /**
     * 【批次更新时间】
     * 遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示北京时间2015年05月20日13点29分35秒。
     */
    @SerializedName(value = "update_time")
    private String updateTime;

  }
}

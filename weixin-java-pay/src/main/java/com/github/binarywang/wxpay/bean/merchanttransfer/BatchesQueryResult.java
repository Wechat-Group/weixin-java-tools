package com.github.binarywang.wxpay.bean.merchanttransfer;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * The type Batches query result.
 *
 * @author glz
 * created on  2022-6-11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BatchesQueryResult implements Serializable {
  private static final long serialVersionUID = -4160610913430904527L;
  /**
   * <pre>
   * 字段名：最大资源条数
   * 变量名：limit
   * 是否必填：否
   * 类型：int
   * 描述：
   *  query该次请求可返回的最大资源（转账明细单）条数，最小20条，最大100条，不传则默认20条。不足20条按实际条数返回
   * 示例值：20
   * </pre>
   */
  @SerializedName("limit")
  private Integer limit;

  /**
   * <pre>
   * 字段名：请求资源起始位置
   * 变量名：transfer_batch
   * 是否必填：否
   * 类型：int
   * 描述：
   *  query该次请求资源（转账明细单）的起始位置，从0开始，转账明细单列表为空时不返回
   * 示例值：1
   * </pre>
   */
  @SerializedName("offset")
  private Integer offset;
  /**
   * <pre>
   * 字段名：转账批次单
   * 变量名：transfer_batch
   * 是否必填：是
   * 类型：object
   * 描述：
   *  转账批次单基本信息
   * </pre>
   */
  @SerializedName("transfer_batch")
  private TransferBatch transferBatch;

  /**
   * <pre>
   * 字段名：转账明细单列表
   * 变量名：transfer_detail_list
   * 是否必填：否
   * 类型：array
   * 描述：
   *  body发起批量转账的明细列表，最多三千笔
   * </pre>
   */
  @SerializedName("transfer_detail_list")
  private List<TransferDetail> transferDetailList;

  /**
   * The type Transfer batch.
   */
  @Data
  @Accessors(chain = true)
  public static class TransferBatch implements Serializable {
    private static final long serialVersionUID = -5889662087155073442L;

    /**
     * <pre>
     * 字段名：商户号
     * 变量名：mchid
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  微信支付分配的商户号
     * 示例值：1900001109
     * </pre>
     */
    @SerializedName("mchid")
    private String mchId;

    /**
     * <pre>
     * 字段名：商家批次单号
     * 变量名：out_batch_no
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  商户系统内部的商家批次单号，在商户系统内部唯一
     * 示例值：plfk2020042013
     * </pre>
     */
    @SerializedName("out_batch_no")
    private String outBatchNo;

    /**
     * <pre>
     * 字段名：微信批次单号
     * 变量名：batch_id
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  微信批次单号，微信商家转账系统返回的唯一标识
     * 示例值：1030000071100999991182020050700019480001
     * </pre>
     */
    @SerializedName("batch_id")
    private String batchId;

    /**
     * <pre>
     * 字段名：直连商户的appid
     * 变量名：appid
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  申请商户号的appid或商户号绑定的appid（企业号corpid即为此appid）
     * 示例值：wxf636efh567hg4356
     * </pre>
     */
    @SerializedName("appid")
    private String appid;

    /**
     * <pre>
     * 字段名：批次状态
     * 变量名：batch_status
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  枚举值：
     * WAIT_PAY：待付款，商户员工确认付款阶段
     * ACCEPTED:已受理。批次已受理成功，若发起批量转账的30分钟后，转账批次单仍处于该状态，可能原因是商户账户余额不足等。商户可查询账户资金流水，若该笔转账批次单的扣款已经发生，则表示批次已经进入转账中，请再次查单确认
     * PROCESSING:转账中。已开始处理批次内的转账明细单
     * FINISHED：已完成。批次内的所有转账明细单都已处理完成
     * CLOSED：已关闭。可查询具体的批次关闭原因确认
     * 示例值：ACCEPTED
     * </pre>
     */
    @SerializedName("batch_status")
    private String batchStatus;

    /**
     * <pre>
     * 字段名：批次类型
     * 变量名：batch_type
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  枚举值：
     * API：API方式发起
     * WEB：页面方式发起
     * 示例值：API
     * </pre>
     */
    @SerializedName("batch_type")
    private String batchType;

    /**
     * <pre>
     * 字段名：批次名称
     * 变量名：batch_name
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  该笔批量转账的名称
     * 示例值：2019年1月深圳分部报销单
     * </pre>
     */
    @SerializedName("batch_name")
    private String batchName;

    /**
     * <pre>
     * 字段名：批次备注
     * 变量名：batch_remark
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  转账说明，UTF8编码，最多允许32个字符
     * 示例值：2019年1月深圳分部报销单
     * </pre>
     */
    @SerializedName("batch_remark")
    private String batchRemark;

    /**
     * <pre>
     * 字段名：批次关闭原因
     * 变量名：close_reason
     * 是否必填：否
     * 类型：string[1,64]
     * 描述：
     *  如果批次单状态为“CLOSED”（已关闭），则有关闭原因
     * MERCHANT_REVOCATION：商户主动撤销
     * OVERDUE_CLOSE：系统超时关闭
     * 示例值：OVERDUE_CLOSE
     * </pre>
     */
    @SerializedName("close_reason")
    private String closeReason;

    /**
     * <pre>
     * 字段名：转账总金额
     * 变量名：total_amount
     * 是否必填：是
     * 类型：int
     * 描述：
     *  转账金额单位为分
     * 示例值：4000000
     * </pre>
     */
    @SerializedName("total_amount")
    private Integer totalAmount;

    /**
     * <pre>
     * 字段名：转账总笔数
     * 变量名：total_num
     * 是否必填：是
     * 类型：int
     * 描述：
     *  一个转账批次单最多发起三千笔转账
     * 示例值：200
     * </pre>
     */
    @SerializedName("total_num")
    private Integer totalNum;

    /**
     * <pre>
     * 字段名：批次创建时间
     * 变量名：create_time
     * 是否必填：否
     * 类型：string[1,32]
     * 描述：
     *  批次受理成功时返回，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss.sss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示北京时间2015年05月20日13点29分35秒
     * 示例值：2015-05-20T13:29:35.120+08:00
     * </pre>
     */
    @SerializedName("create_time")
    private String createTime;

    /**
     * <pre>
     * 字段名：批次更新时间
     * 变量名：update_time
     * 是否必填：否
     * 类型：string[1,32]
     * 描述：
     *  批次最近一次状态变更的时间，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss.sss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示北京时间2015年05月20日13点29分35秒
     * 示例值：2015-05-20T13:29:35.120+08:00
     * </pre>
     */
    @SerializedName("update_time")
    private String updateTime;

    /**
     * <pre>
     * 字段名：转账成功金额
     * 变量名：success_amount
     * 是否必填：否
     * 类型：int
     * 描述：
     *  转账成功的金额，单位为分。当批次状态为“PROCESSING”（转账中）时，转账成功金额随时可能变化
     * 示例值：3900000
     * </pre>
     */
    @SerializedName("success_amount")
    private Integer successAmount;

    /**
     * <pre>
     * 字段名：转账成功笔数
     * 变量名：success_num
     * 是否必填：否
     * 类型：int
     * 描述：
     *  转账成功的笔数。当批次状态为“PROCESSING”（转账中）时，转账成功笔数随时可能变化
     * 示例值：199
     * </pre>
     */
    @SerializedName("success_num")
    private Integer successNum;

    /**
     * <pre>
     * 字段名：转账失败金额
     * 变量名：fail_amount
     * 是否必填：否
     * 类型：int
     * 描述：
     *  转账失败的金额，单位为分
     * 示例值：100000
     * </pre>
     */
    @SerializedName("fail_amount")
    private Integer failAmount;

    /**
     * <pre>
     * 字段名：转账失败笔数
     * 变量名：fail_num
     * 是否必填：否
     * 类型：int
     * 描述：
     *  转账失败的笔数
     * 示例值：1
     * </pre>
     */
    @SerializedName("fail_num")
    private Integer failNum;
  }

  /**
   * The type Transfer detail.
   */
  @Data
  @Accessors(chain = true)
  public static class TransferDetail implements Serializable {
    private static final long serialVersionUID = 172904924437448719L;

    /**
     * <pre>
     * 字段名：微信明细单号
     * 变量名：detail_id
     * 是否必填：是
     * 类型：string[1,64]
     * 描述：
     *  微信支付系统内部区分转账批次单下不同转账明细单的唯一标识
     * 示例值：1040000071100999991182020050700019500100
     * </pre>
     */
    @SerializedName("detail_id")
    private String detailId;

    /**
     * <pre>
     * 字段名：商家明细单号
     * 变量名：out_detail_no
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  商户系统内部区分转账批次单下不同转账明细单的唯一标识
     * 示例值：x23zy545Bd5436
     * </pre>
     */
    @SerializedName("out_detail_no")
    private String outDetailNo;

    /**
     * <pre>
     * 字段名：明细状态
     * 变量名：detail_status
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  枚举值：
     * PROCESSING：转账中。正在处理中，转账结果尚未明确
     * SUCCESS：转账成功
     * FAIL：转账失败。需要确认失败原因后，再决定是否重新发起对该笔明细单的转账（并非整个转账批次单）
     * 示例值：SUCCESS
     * </pre>
     */
    @SerializedName("detail_status")
    private String detailStatus;
  }
}

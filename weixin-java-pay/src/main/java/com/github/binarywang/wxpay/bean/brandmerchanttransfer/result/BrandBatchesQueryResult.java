package com.github.binarywang.wxpay.bean.brandmerchanttransfer.result;

import com.github.binarywang.wxpay.v3.SpecEncrypt;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 查询批次单结果
 *
 * @author moran
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BrandBatchesQueryResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：品牌主商户号
   * 变量名：brand_mchid
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  微信服务商下特约商户的商户号，且已经认证品牌的品牌主商户号
   * 示例值：1900001109
   * </pre>
   */
  @SerializedName("brand_mchid")
  private String brandMchid;

  /**
   * <pre>
   * 字段名：微信支付品牌红包批次单号
   * 变量名：batch_no
   * 是否必填：是
   * 类型：string[32, 64]
   * 描述：
   *  品牌在微信支付进行品牌认证后的唯一标识品牌ID
   * 示例值：1030000071100999991182020050700019480001
   * </pre>
   */
  @SerializedName("batch_no")
  private String batchNo;

  /**
   * <pre>
   * 字段名：商家品牌红包批次单号
   * 变量名：out_batch_no
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  品牌商户系统内部的品牌红包批次单号，要求此参数只能由数字、大小写字母组成，在商户系统内部唯一
   * 示例值：plfk2020042013
   * </pre>
   */
  @SerializedName("out_batch_no")
  private String outBatchNo;

  /**
   * <pre>
   * 字段名：品牌ID
   * 变量名：brand_id
   * 是否必填：否
   * 类型：int
   * 描述：
   *  品牌在微信支付进行品牌认证后的唯一标识品牌ID
   * 示例值：1234
   * </pre>
   */
  @SerializedName("brand_id")
  private Integer brandId;

  /**
   * <pre>
   * 字段名：品牌红包模板ID
   * 变量名：template_id
   * 是否必填：否
   * 类型：string[1, 128]
   * 描述：
   *  品牌主配置的品牌红包模板ID
   * 示例值：12340000000001
   * </pre>
   */
  @SerializedName("template_id")
  private String templateId;

  /**
   * <pre>
   * 字段名：品牌AppID
   * 变量名：brand_appid
   * 是否必填：否
   * 类型：string[1, 32]
   * 描述：
   *  品牌商户在微信申请公众号/小程序或移动应用成功后分配的账号ID，该AppID需与品牌ID有绑定关系（B-A绑定关系）
   * 示例值：wxf636efh567hg4356
   * </pre>
   */
  @SerializedName("brand_appid")
  private String brandAppid;

  /**
   * <pre>
   * 字段名：品牌红包批次状态
   * 变量名：batch_state
   * 是否必填：是
   * 类型：string
   * 描述：
   *  当前品牌红包批次状态
   * WAIT_PAY - 待付款，商户员工确认付款阶段
   * ACCEPTED - 已受理，批次已受理成功，若发起品牌红包的30分钟后，品牌红包批次单仍处于该状态，可能原因是商户账户余额不足等。商户可查询账户资金流水，若该笔品牌红包批次单的扣款已经发生，则表示批次已经进入发送中，请再次查单确认
   * PROCESSING - 发送中，已开始处理批次内的品牌红包明细单
   * FINISHED - 已完成，批次内的所有品牌红包明细单都已处理完成
   * CLOSED - 已关闭，可查询具体的批次关闭原因确认
   * 示例值：ACCEPTED
   * </pre>
   */
  @SerializedName("batch_state")
  private String batchState;

  /**
   * <pre>
   * 字段名：品牌红包批次名称
   * 变量名：batch_name
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  该批次品牌红包的备注，用于品牌商户内部管理
   * 示例值：双十一营销发放品牌红包
   * </pre>
   */
  @SerializedName("batch_name")
  private String batchName;

  /**
   * <pre>
   * 字段名：品牌红包批次备注
   * 变量名：batch_remark
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  该批次品牌红包的备注，仅用于品牌商户内部管理
   * 示例值：双十一营销发放品牌红包
   * </pre>
   */
  @SerializedName("batch_remark")
  private String batchRemark;

  /**
   * <pre>
   * 字段名：品牌红包批次单关闭原因
   * 变量名：close_reason
   * 是否必填：否
   * 类型：string
   * 描述：
   *  品牌红包批次单状态为“CLOSED”（已关闭）时返回
   * MERCHANT_REVOCATION - 商户主动撤销
   * SYSTEM_OVERDUE_CLOSE - 系统超时关闭
   * 示例值：SYSTEM_OVERDUE_CLOSE
   * </pre>
   */
  @SerializedName("close_reason")
  private String closeReason;

  /**
   * <pre>
   * 字段名：总金额
   * 变量名：total_amount
   * 是否必填：是
   * 类型：int
   * 描述：
   *  品牌红包金额单位为“分”
   * 示例值：10000
   * </pre>
   */
  @SerializedName("total_amount")
  private Integer totalAmount;

  /**
   * <pre>
   * 字段名：总笔数
   * 变量名：total_num
   * 是否必填：是
   * 类型：int
   * 描述：
   *  一个品牌红包批次单最多发送10笔品牌红包明细。品牌红包总笔数必须与批次内所有品牌红包明细之和保持一致，否则无法发送品牌红包
   * 示例值：10
   * </pre>
   */
  @SerializedName("total_num")
  private Integer totalNum;

  /**
   * <pre>
   * 字段名：品牌红包批次创建时间
   * 变量名：create_time
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  品牌红包批次受理成功时返回，遵循rfc3339标准格式，
   *  格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
   *  例如：2015-05-20T13:29:35+08:00表示北京时间2015年05月20日13点29分35秒
   * 示例值：2015-05-20T13:29:35+08:00
   * </pre>
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * <pre>
   * 字段名：品牌红包批次更新时间
   * 变量名：update_time
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：
   *  品牌红包批次最近一次状态变更时间，遵循rfc3339标准格式，
   *  格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
   *  例如：2015-05-20T13:29:35+08:00表示北京时间2015年05月20日13点29分35秒
   * 示例值：2015-05-20T13:29:35+08:00
   * </pre>
   */
  @SerializedName("update_time")
  private String updateTime;

  /**
   * <pre>
   * 字段名：发放成功金额
   * 变量名：success_amount
   * 是否必填：是
   * 类型：int
   * 描述：
   *  品牌红包发放成功的金额，单位为“分”。当批次状态为“PROCESSING”（发送中）时，发放成功金额随时可能变化
   * 示例值：5000
   * </pre>
   */
  @SerializedName("success_amount")
  private Integer successAmount;

  /**
   * <pre>
   * 字段名：发放成功笔数
   * 变量名：success_num
   * 是否必填：是
   * 类型：int
   * 描述：
   *  品牌红包发放成功的笔数。当批次状态为“PROCESSING”（发放中）时，发放成功笔数随时可能变化
   * 示例值：10
   * </pre>
   */
  @SerializedName("success_num")
  private Integer successNum;

  /**
   * <pre>
   * 字段名：发放失败金额
   * 变量名：fail_amount
   * 是否必填：是
   * 类型：int
   * 描述：
   *  品牌红包发放失败的金额，单位为“分”
   * 示例值：5000
   * </pre>
   */
  @SerializedName("fail_amount")
  private Integer failAmount;

  /**
   * <pre>
   * 字段名：发放失败笔数
   * 变量名：fail_num
   * 是否必填：是
   * 类型：int
   * 描述：
   *  品牌红包发放失败的笔数
   * 示例值：10
   * </pre>
   */
  @SerializedName("fail_num")
  private Integer failNum;

  /**
   * <pre>
   * 字段名：品牌红包明细列表
   * 变量名：detail_list
   * 是否必填：否
   * 类型：array
   * 描述：
   *  当批次状态为“FINISHED”（已完成），且成功查询到品牌红包明细单时返回。包括微信支付品牌红包明细单号、明细状态信息
   * </pre>
   */
  @SpecEncrypt
  @SerializedName("detail_list")
  private List<BrandDetailResult> detailList;

  @Data
  @Accessors(chain = true)
  public static class BrandDetailResult implements Serializable {

    /**
     * <pre>
     * 字段名：微信支付品牌红包明细单号
     * 变量名：transfer_detail_no
     * 是否必填：是
     * 类型：string[32, 64]
     * 描述：
     *  微信支付系统内部区分品牌红包批次单下不同品牌红包明细单的唯一标识
     * 示例值：1220000071100999991182020050700019500100
     * </pre>
     */
    @SerializedName("transfer_detail_no")
    private String transferDetailNo;

    /**
     * <pre>
     * 字段名：商家品牌红包明细单号
     * 变量名：out_detail_no
     * 是否必填：是
     * 类型：string[1, 32]
     * 描述：
     *  品牌商户系统内部区分品牌红包批次单下不同品牌红包明细单的唯一标识，要求此参数只能由数字、大小写字母组成
     * 示例值：x23zy545Bd5436
     * </pre>
     */
    @SerializedName("out_detail_no")
    private String outDetailNo;

    /**
     * <pre>
     * 字段名：品牌红包明细单状态
     * 变量名：detail_state
     * 是否必填：是
     * 类型：string[1, 64]
     * 描述：
     *  品牌红包明细单的状态
     * DETAIL_PROCESSING - 发送中，正在处理中，品牌红包发送结果尚未明确
     * DETAIL_SUCCESS - 发送成功，发送成功
     * DETAIL_FAIL - 发送失败，需要确认失败原因后，再决定是否重新对该笔品牌红包明细单进行发送（并非整个品牌红包批次单）
     * 示例值：DETAIL_SUCCESS
     * </pre>
     */
    @SerializedName("detail_state")
    private String detailState;

  }
}

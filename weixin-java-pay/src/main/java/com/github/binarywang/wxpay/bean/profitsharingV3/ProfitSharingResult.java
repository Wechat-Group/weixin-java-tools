package com.github.binarywang.wxpay.bean.profitsharingV3;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 微信V3接口
 * 请求分账API返回的分账结果实体
 *
 * @author pg
 * @date 2021-6-24
 */
@Data
public class ProfitSharingResult implements Serializable {
  private static final long serialVersionUID = -6201692412535987502L;

  /**
   * <pre>
   * 字段名：微信订单号
   * 是否必填：是
   * 描述：微信支付订单号
   * </pre>
   */
  @SerializedName("transaction_id")
  private String transactionId;

  /**
   * <pre>
   * 字段名：商户分账单号
   * 是否必填：是
   * 描述：商户系统内部的分账单号，在商户系统内部唯一，同一分账单号多次请求等同一次。只能是数字、大小写字母_-|*@
   * </pre>
   */
  @SerializedName("out_order_no")
  private String outOrderNo;

  /**
   * <pre>
   * 字段名：微信分账单号，
   * 是否必填：是
   * 描述：微信系统返回的唯一标识.
   * </pre>
   */
  @SerializedName("order_id")
  private String orderId;

  /**
   * <pre>
   * 字段名：分账单状态
   * 是否必填：是
   * 描述：分账单状态（每个接收方的分账结果请查看receivers中的result字段）：
   * 1、PROCESSING：处理中
   * 2、FINISHED：分账完成.
   * </pre>
   */
  @SerializedName("state")
  private String state;

  /**
   * 分账接收方列表
   */
  @SerializedName("receivers")
  private List<Receiver> receivers;

  @Data
  public static class Receiver implements Serializable {
    private static final long serialVersionUID = 4240983048700956806L;

    /**
     * <pre>
     * 字段名：分账接收方类型
     * 是否必填：是
     * 描述：
     * 1、MERCHANT_ID：商户号
     * 2、PERSONAL_OPENID：个人openid（由父商户APPID转换得到）
     * </pre>
     */
    @SerializedName("type")
    private String type;

    /**
     * <pre>
     * 字段名：分账接收方帐号
     * 是否必填：是
     * 描述：
     * 1、分账接收方类型为MERCHANT_ID时，分账接收方账号为商户号
     * 2、分账接收方类型为PERSONAL_OPENID时，分账接收方账号为个人openid
     * </pre>
     */
    @SerializedName("account")
    private String account;

    /**
     * <pre>
     * 字段名：分账金额
     * 是否必填：是
     * 描述： 分账金额，单位为分，只能为整数，不能超过原订单支付金额及最大分账比例金额
     * </pre>
     */
    @SerializedName("amount")
    private Long amount;

    /**
     * <pre>
     * 字段名：分账描述
     * 是否必填：是
     * 描述： 分账的原因描述，分账账单中需要体现
     * </pre>
     */
    @SerializedName("description")
    private String description;

    /**
     * <pre>
     * 字段名：分账结果
     * 是否必填：是
     * 描述：
     * 1、PENDING：待分账
     * 2、SUCCESS：分账成功
     * 3、CLOSED：已关闭
     * </pre>
     */
    @SerializedName("result")
    private String result;

    /**
     * <pre>
     * 字段名：分账失败原因
     * 是否必填：是
     * 描述：包含以下枚举值：
     * 1、ACCOUNT_ABNORMAL : 分账接收账户异常
     * 2、NO_RELATION : 分账关系已解除
     * 3、RECEIVER_HIGH_RISK : 高风险接收方
     * 4、RECEIVER_REAL_NAME_NOT_VERIFIED : 接收方未实名
     * 5、NO_AUTH : 分账权限已解除
     * </pre>
     */
    @SerializedName("fail_reason")
    private String failReason;

    /**
     * <pre>
     * 字段名：分账创建时间
     * 是否必填：是
     * 描述：遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，
     * YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，
     * HH:mm:ss.sss表示时分秒毫秒，
     * TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
     * 例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
     * </pre>
     */
    @SerializedName("create_time")
    private String createTime;
    /**
     * <pre>
     * 字段名：分账完成时间
     * 是否必填：是
     * 描述：遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss.sss+TIMEZONE，
     * YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，
     * HH:mm:ss.sss表示时分秒毫秒，
     * TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。
     * 例如：2015-05-20T13:29:35.120+08:00表示，北京时间2015年5月20日 13点29分35秒。
     * </pre>
     */
    @SerializedName("finish_time")
    private String finishTime;

    /**
     * <pre>
     * 字段名：微信分账明细单号
     * 是否必填：是
     * 每笔分账业务执行的明细单号，可与资金账单对账使用，
     * 例如：36011111111111111111111
     * </pre>
     */
    @SerializedName("detail_id")
    private String detailId;
  }
}

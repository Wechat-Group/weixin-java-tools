package com.github.binarywang.wxpay.bean.profitsharing.v3;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * 微信V3接口
 * 分账动账通知解密后数据实体
 *
 * @author yuanbo
 * @since 2022-04-26-21:08 PM
 */
@Data
@NoArgsConstructor
public class ProfitSharingNotifyResult implements Serializable {
  private static final long serialVersionUID = -2875006651351414624L;

  /**
   * <pre>
   * 字段名：直连商户号
   * 是否必填：是
   * 描述：直连模式分账发起和出资商户
   * </pre>
   */
  @SerializedName("mchid")
  private String mchId;

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
   * 字段名：微信分账/回退单号
   * 是否必填：是
   * 描述：微信分账/回退单号
   * </pre>
   */
  @SerializedName("order_id")
  private String orderId;

  /**
   * <pre>
   * 字段名：商户分账/回退单号
   * 是否必填：是
   * 描述：分账方系统内部的分账/回退单号
   * </pre>
   */
  @SerializedName("out_order_no")
  private String outOrderNo;

  /**
   * <pre>
   * 字段名：分账接收方
   * 是否必填：是
   * 描述：分账接收方对象
   * </pre>
   */
  @SerializedName("receiver")
  private Receiver receiver;

  /**
   * <pre>
   * 字段名：成功时间
   * 是否必填：是
   * 描述：成功时间，Rfc3339标准
   * </pre>
   */
  @SerializedName("success_time")
  private String successTime;

  @Data
  @NoArgsConstructor
  public static class Receiver implements Serializable {

    private static final long serialVersionUID = -931070141604645363L;

    /**
     * <pre>
     * 字段名：分账接收方类型
     * 是否必填：是
     * 描述：MERCHANT_ID：商户号（mch_id或者sub_mch_id）
     * </pre>
     */
    @SerializedName("type")
    private String type;

    /**
     * <pre>
     * 字段名：分账接收方账号
     * 是否必填：是
     * 描述：申请本功能商户号
     * </pre>
     */
    @SerializedName("account")
    private String account;

    /**
     * <pre>
     * 字段名：分账动账金额
     * 是否必填：是
     * 描述：分账动账金额，单位为分，只能为整数
     * </pre>
     */
    @SerializedName("amount")
    private Integer amount;

    /**
     * <pre>
     * 字段名：分账/回退描述
     * 是否必填：是
     * 描述：分账/回退描述
     * </pre>
     */
    @SerializedName("description")
    private String description;
  }
}

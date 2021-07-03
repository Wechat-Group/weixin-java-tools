package com.github.binarywang.wxpay.bean.profitsharingV3;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 微信V3接口
 * 请求分账API请求实体
 *
 * @author pg
 * @date 2021-6-24
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class ProfitSharingRequest implements Serializable {
  private static final long serialVersionUID = 3644929701624280800L;

  /**
   * <pre>
   * 字段名：应用ID
   * 是否必填：是
   * 描述：微信分配的商户appid
   * </pre>
   */
  @SerializedName("appid")
  private String appid;

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
   * 字段名：分账接收方列表
   * 是否必填：是
   * 描述：分账接收方列表，可以设置出资商户作为分账接受方，最多可有50个分账接收方
   * </pre>
   */
  @SerializedName("receivers")
  private List<ProfitSharingReceiver> receivers;

  /**
   * <pre>
   * 字段名：是否解冻剩余未分资金
   * 是否必填：是
   * 描述：
   * 1、如果为true，该笔订单剩余未分账的金额会解冻回分账方商户；
   * 2、如果为false，该笔订单剩余未分账的金额不会解冻回分账方商户，可以对该笔订单再次进行分账。
   * </pre>
   */
  @SerializedName("unfreeze_unsplit")
  private boolean unfreezeUnsplit;
}

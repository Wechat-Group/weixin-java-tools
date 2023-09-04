package com.github.binarywang.wxpay.bean.profitsharing.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信V3接口
 * 查询剩余待分金额API返回实体
 *
 * @author pg
 * created on  2021-6-25
 */
@Data
public class ProfitSharingOrderAmountQueryV3Result implements Serializable {

  private static final long serialVersionUID = -7025255772409082288L;
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
   * 字段名：订单剩余待分金额
   * 是否必填：是
   * 描述：订单剩余待分金额，整数，单元为分
   * </pre>
   */
  @SerializedName("unsplit_amount")
  private String unsplitAmount;

}

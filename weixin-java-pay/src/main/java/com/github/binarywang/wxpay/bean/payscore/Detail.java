package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 明细.
 *
 * @author doger.wang
 * created on  2020-05-19
 */
@Data
@NoArgsConstructor
public class Detail implements Serializable {
  private static final long serialVersionUID = -3901373259400050385L;
  /**
   * seq : 1
   * amount : 900
   * paid_type : NEWTON
   * paid_time : 20091225091210
   * transaction_id : 15646546545165651651
   */
  @SerializedName("seq")
  private int seq;
  @SerializedName("amount")
  private int amount;
  @SerializedName("paid_type")
  private String paidType;
  @SerializedName("paid_time")
  private String paidTime;
  @SerializedName("transaction_id")
  private String transactionId;
  @SerializedName("promotion_detail")
  private List<PromotionDetail> promotionDetail;
}

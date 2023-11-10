package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author UltramanNoa
 * @className PayScorePlanDetail
 * @description 支付分计划明细列表
 * @createTime 2023/11/3 11:22
 **/
@Data
@NoArgsConstructor
public class PayScorePlanDetail implements Serializable {

  private static final long serialVersionUID = 999251141141181820L;
  /**
   * 计划明细原支付金额(单位分)
   */
  @SerializedName("original_price")
  private Integer originalPrice;

  /**
   * 计划明细优惠说明
   */
  @SerializedName("plan_discount_description")
  private String planDiscountDescription;

  /**
   * 计划明细实际支付金额(单位分)
   */
  @SerializedName("actual_price")
  private String actualPrice;

  /**
   * 计划明细名称
   */
  @SerializedName("plan_detail_name")
  private String planDetailName;

  /**
   * 计划明细序号(返回参数)
   */
  @SerializedName("plan_detail_no")
  private Integer planDetailNo;
}

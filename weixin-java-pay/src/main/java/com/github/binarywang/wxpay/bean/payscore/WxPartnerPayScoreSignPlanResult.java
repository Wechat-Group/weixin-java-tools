package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.util.List;

/**
 * @author UltramanNoa
 * @className WxPartnerPayScoreSignPlanResult
 * @description 支付分计划响应参数
 * @createTime 2023/11/3 10:11
 **/
@Data
@NoArgsConstructor
public class WxPartnerPayScoreSignPlanResult extends WxPayScoreResult {

  private static final long serialVersionUID = 4048529978029913621L;

  public static WxPartnerPayScoreSignPlanResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxPartnerPayScoreSignPlanResult.class);
  }

  /**
   * 子商户AppID
   */
  @SerializedName("sub_appid")
  private String subAppid;

  /**
   * 子商户商户号
   */
  @SerializedName("sub_mchid")
  private String subMchid;

  /**
   * 支付分计划ID
   */
  @SerializedName("plan_id")
  private String planId;

  /**
   * 商户侧计划号
   */
  @SerializedName("merchant_plan_no")
  private String merchantPlanNo;

  /**
   * 支付分计划名称
   */
  @SerializedName("plan_name")
  private String planName;

  /**
   * 支付分计划有效期(单位天)
   */
  @SerializedName("plan_duration")
  private String planDuration;

  /**
   * 支付分计划状态
   *
   * @see
   */
  @SerializedName("plan_state")
  private String planState;
  /**
   * 支付分计划原总金额(单位分)
   */
  @SerializedName("total_original_price")
  private String totalOriginalPrice;

  /**
   * 支付分计划扣费次数
   */
  @SerializedName("deduction_quantity")
  private String deductionQuantity;

  /**
   * 支付分计划实际扣费总金额(单位分)
   */
  @SerializedName("total_actual_price")
  private Integer totalActualPrice;

  /**
   * 支付分计划明细列表
   */
  @SerializedName("plan_detail_list")
  private List<PayScorePlanDetail> planDetailList;

  /**
   * 终止方商户号
   */
  @SerializedName("stop_mchid")
  private String stopMchid;

  /**
   * 终止时间
   */
  @SerializedName("stop_time")
  private String stopTime;
}

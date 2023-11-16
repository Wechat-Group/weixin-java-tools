package com.github.binarywang.wxpay.bean.payscore;

import com.github.binarywang.wxpay.bean.payscore.enums.SignPlanServiceOrderPlanDetailStateEnum;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author UltramanNoa
 * @className PartnerUserSignPlanDetail
 * @description 签约计划明细列表
 * @createTime 2023/11/3 17:19
 **/
@Data
@NoArgsConstructor
public class PartnerUserSignPlanDetail implements Serializable {

  private static final long serialVersionUID = 2089297485318293622L;
  /**
   * 计划明细序号
   */
  @SerializedName("plan_detail_no")
  private Integer planDetailNo;

  /**
   * 计划明细原支付金额（单位分）
   */
  @SerializedName("original_price")
  private Integer originalPrice;

  /**
   * 计划明细优惠说明
   */
  @SerializedName("plan_discount_description")
  private String planDiscountDescription;

  /**
   * 计划明细实际支付金额（单位分）
   */
  @SerializedName("actual_price")
  private Integer actualPrice;

  /**
   * 计划明细状态
   *
   * @see SignPlanServiceOrderPlanDetailStateEnum
   */
  @SerializedName("plan_detail_state")
  private String planDetailState;

  /**
   * 计划明细对应的支付分服务单号
   */
  @SerializedName("order_id")
  private String orderId;

  /**
   * 商户侧计划明细使用订单号
   */
  @SerializedName("merchant_plan_detail_no")
  private String merchantPlanDetailNo;

  /**
   * 计划详情名称
   */
  @SerializedName("plan_detail_name")
  private String planDetailName;

  /**
   * 计划明细对应订单实际支付金额（单位分）
   */
  @SerializedName("actual_pay_price")
  private Integer actualPayPrice;

  /**
   * 详情使用时间
   */
  @SerializedName("use_time")
  private String useTime;

  /**
   * 详情完成时间
   */
  @SerializedName("complete_time")
  private String completeTime;

  /**
   * 详情取消时间
   */
  @SerializedName("cancel_time")
  private String cancelTime;
}

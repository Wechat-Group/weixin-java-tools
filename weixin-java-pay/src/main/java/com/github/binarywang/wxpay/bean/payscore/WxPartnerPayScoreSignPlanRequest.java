package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.util.List;

/**
 * @author UltramanNoa
 * @className WxPartnerPayScoreSignPlanRequest
 * @description 支付分计划请求参数
 * @createTime 2023/11/3 09:54
 **/
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class WxPartnerPayScoreSignPlanRequest extends WxPayScoreRequest {
  private static final long serialVersionUID = 6269843192878112955L;

  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  /**
   * 子商户appid
   */
  @SerializedName("sub_appid")
  private String subAppid;

  /**
   * 子商户mchid
   */
  @SerializedName("sub_mchid")
  private String subMchid;

  /**
   * 子商户公众号下的用户标识
   */
  @SerializedName("sub_openid")
  private String subOpenid;
  /**
   * 支付分计划名称
   */
  @SerializedName("plan_name")
  private String planName;

  /**
   * 支付分计划有效期(单位天)
   */
  @SerializedName("plan_duration")
  private Integer planDuration;

  /**
   * 支付分计划扣费次数
   */
  @SerializedName("deduction_quantity")
  private Integer deductionQuantity;


  /**
   * 支付分计划原总金额(单位分)
   */
  @SerializedName("total_original_price")
  private Integer totalOriginalPrice;

  /**
   * 支付分计划实际扣费总金额(单位分)
   */
  @SerializedName("total_actual_price")
  private Integer totalActualPrice;

  /**
   * 支付分计划明细列表
   */
  @SerializedName("plan_detail_list")
  private List<PayScorePlanDetailRequest> planDetailList;

  /**
   * 商户侧计划号
   */
  @SerializedName("merchant_plan_no")
  private String merchantPlanNo;


  /**
   * 待创建服务订单对应的用户的签约计划
   */
  @SerializedName("sign_plan_id")
  private String signPlanId;

  /**
   * 待创建服务订单对应的用户的签约计划详情序号
   */
  @SerializedName("plan_detail_no")
  private Integer planDetailNo;

  /**
   * 商户侧订单号
   */
  @SerializedName("out_trade_no")
  private String outTradeNo;

  /**
   * 支付分计划ID
   */
  @SerializedName("plan_id")
  private String planId;

  /**
   * 商户签约计划单号
   */
  @SerializedName("merchant_sign_plan_no")
  private String merchantSignPlanNo;

  /**
   * 签约计划对应的计划详情列表的商户侧单号信息
   */
  @SerializedName("sign_plan_detail")
  private List<UserSignPlanDetailMerchatNo> signPlanDetail;

}

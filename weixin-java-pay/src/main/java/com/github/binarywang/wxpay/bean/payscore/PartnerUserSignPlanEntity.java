package com.github.binarywang.wxpay.bean.payscore;

import com.github.binarywang.wxpay.bean.payscore.enums.SignPlanServiceOrderStateEnum;
import com.github.binarywang.wxpay.bean.payscore.enums.UserSignPlanCancelSignTypeEnum;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author UltramanNoa
 * @className PartnerUserSignPlanEntity
 * @description 用户的签约计划
 * @createTime 2023/11/3 16:05
 **/
@Data
@NoArgsConstructor
public class PartnerUserSignPlanEntity implements Serializable {

  private static final long serialVersionUID = -662901613603698430L;

  public static PartnerUserSignPlanEntity fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, PartnerUserSignPlanEntity.class);
  }


  /**
   * 待创建服务订单对应的用户的签约计划
   */
  @SerializedName("sign_plan_id")
  private String signPlanId;

  @SerializedName("openid")
  private String openid;

  /**
   * <pre>
   * 字段名：二级商户用户标识
   * 变量名：sub_openid
   * 是否必填：否
   * 类型：string（128）
   * 描述：
   *  用户在二级商户appid下的唯一标识。
   *  示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
   * </pre>
   */
  @SerializedName(value = "sub_openid")
  private String subOpenid;


  @SerializedName("service_id")
  private String serviceId;

  @SerializedName("mchid")
  private String mchid;

  /**
   * 子商户商户号
   */
  @SerializedName("sub_mchid")
  private String subMchid;

  @SerializedName("appid")
  private String appid;

  /**
   * 子商户AppID
   */
  @SerializedName("sub_appid")
  private String subAppid;

  /**
   * 商户签约计划单号
   */
  @SerializedName("merchant_sign_plan_no")
  private String merchantSignPlanNo;

  /**
   * 商户回调地址
   */
  @SerializedName("merchant_callback_url")
  private String merchantCallbackUrl;

  /**
   * 支付分计划ID
   */
  @SerializedName("plan_id")
  private String planId;

  /**
   * 目前用户进行到的计划详情序号
   */
  @SerializedName("going_detail_no")
  private Integer goingDetailNo;

  /**
   * 计划签约状态
   *
   * @see SignPlanServiceOrderStateEnum
   */
  @SerializedName("sign_state")
  private String signState;

  /**
   * 签约计划取消时间
   */
  @SerializedName("cancel_sign_time")
  private String cancelSignTime;

  /**
   * 签约计划取消类型
   *
   * @see UserSignPlanCancelSignTypeEnum
   */
  @SerializedName("cancel_sign_type")
  private String cancelSignType;

  /**
   * 签约计划取消原因
   */
  @SerializedName("cancel_reason")
  private String cancelReason;

  /**
   * 签约计划的名称
   */
  @SerializedName("plan_name")
  private String planName;

  /**
   * 签约计划的过期时间
   */
  @SerializedName("plan_over_time")
  private String planOverTime;

  /**
   * 签约计划原总金额(单位分)
   */
  @SerializedName("total_origin_price")
  private Integer totalOriginPrice;

  /**
   * 签约计划扣费次数
   */
  @SerializedName("deduction_quantity")
  private Integer deductionQuantity;

  /**
   * 签约计划实际总金额（单位分）
   */
  @SerializedName("total_actual_price")
  private Integer totalActualPrice;

  @SerializedName("signed_detail_list")
  private List<PartnerUserSignPlanDetail> signedDetailList;

  /**
   * 签约时间
   */
  @SerializedName("sign_time")
  private String signTime;
}

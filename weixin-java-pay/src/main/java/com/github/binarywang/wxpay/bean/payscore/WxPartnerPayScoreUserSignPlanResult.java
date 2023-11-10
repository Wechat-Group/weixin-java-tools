package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * @author UltramanNoa
 * @className WxPartnerUserSignPlanResult
 * @description 微信支付分用户签约计划返回
 * @createTime 2023/11/3 16:38
 **/
@Data
@NoArgsConstructor
public class WxPartnerPayScoreUserSignPlanResult implements Serializable {

  private static final long serialVersionUID = 4148075707018175845L;

  public static WxPartnerPayScoreUserSignPlanResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxPartnerPayScoreUserSignPlanResult.class);
  }

  @SerializedName("sign_plan")
  private PartnerUserSignPlanEntity signPlan;

  @SerializedName("package")
  private String pack;
}

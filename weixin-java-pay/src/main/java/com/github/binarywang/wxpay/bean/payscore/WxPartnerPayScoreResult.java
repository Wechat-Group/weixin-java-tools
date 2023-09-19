package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

/**
 * @author hallkk
 * created on  2022/05/18
 */
@Data
@NoArgsConstructor
public class WxPartnerPayScoreResult extends WxPayScoreResult {
  private static final long serialVersionUID = 718267574622164410L;

  public static WxPartnerPayScoreResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxPartnerPayScoreResult.class);
  }

  @SerializedName("sub_appid")
  private String subAppid;

  @SerializedName("sub_mchid")
  private String subMchid;

  /**
   * 子商户公众号下的用户标识
   */
  @SerializedName("sub_openid")
  private String subOpenId;

  @SerializedName("out_apply_no")
  private String outApplyNo;

  @SerializedName("result_notify_url")
  private String resultNotifyUrl;

  @SerializedName("apply_state")
  private String applyState;

  @SerializedName("reject_reason")
  private String rejectReason;

}

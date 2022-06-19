package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

/**
 * @author hallkk
 * @date 2022/05/18
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxPartnerPayScoreRequest extends WxPayScoreRequest {
  private static final long serialVersionUID = 6269843192878112955L;

  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  @SerializedName("sub_appid")
  private String subAppid;

  @SerializedName("sub_mchid")
  private String subMchid;

  @SerializedName("out_apply_no")
  private String outApplyNo;

  @SerializedName("result_notify_url")
  private String resultNotifyUrl;

}

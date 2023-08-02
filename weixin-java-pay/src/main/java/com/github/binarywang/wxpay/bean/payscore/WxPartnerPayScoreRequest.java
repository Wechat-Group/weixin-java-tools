package com.github.binarywang.wxpay.bean.payscore;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class WxPartnerPayScoreRequest extends WxPayScoreRequest {
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
   * 子商户公众号下的用户表示sub_openid
   */
  @SerializedName("sub_openid")
  private String subOpenid;

  /**
   * [收付通子商户申请绑定支付分服务]的商户系统内部服务订单号
   */
  @SerializedName("out_apply_no")
  private String outApplyNo;

  /**
   * [收付通子商户申请绑定支付分服务]的绑定结果通知地址
   */
  @SerializedName("result_notify_url")
  private String resultNotifyUrl;

}

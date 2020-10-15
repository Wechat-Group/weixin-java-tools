package me.chanjar.weixin.open.bean.mp;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.bean.result.WxMpResult;

/**
 * 复用公众号资料快速注册小程序结果
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FastRegisterResult extends WxMpResult {

  /**
   * 小程序AppId
   */
  @SerializedName("appid")
  private String appId;

  /**
   * 授权码,然后请使用第三方平台的sdk获得授权, 参考: WxOpenService.getWxOpenComponentService().getQueryAuth( this.getAuthorizationCode() );
   */
  @SerializedName("authorization_code")
  private String authorizationCode;

  /**
   * 是否与公众号关联成功
   */
  @SerializedName("is_wx_verify_succ")
  private Boolean isWxVerifySucc;

  public static FastRegisterResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, FastRegisterResult.class);
  }
}

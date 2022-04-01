package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 预授权码返回
 *
 * @author yqx
 * @date 2020/3/19
 */
@Getter
@Setter
public class WxCpTpPreauthCode extends WxCpBaseResp {

  @SerializedName("pre_auth_code")
  String preAuthCode;

  @SerializedName("expires_in")
  Long expiresIn;

  public static WxCpTpPreauthCode fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpPreauthCode.class);
  }
}

package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * @author freedom
 * @since 2022/10/20 16:36
 */
@Data
public class WxTpCustomizedAuthUrl extends WxCpBaseResp {

  /**
   * 可用来生成二维码的授权url，需要开发者自行生成为二维码
   */
  @SerializedName("qrcode_url")
  private String qrCodeURL;

  /**
   * 有效期（秒）。10天过期。
   */
  @SerializedName("expires_in")
  private Integer expiresIn;

  /**
   * From json wx cp tp customized auth url.
   *
   * @param json the json
   * @return the wx cp tp customized auth url
   */
  public static WxTpCustomizedAuthUrl fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxTpCustomizedAuthUrl.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

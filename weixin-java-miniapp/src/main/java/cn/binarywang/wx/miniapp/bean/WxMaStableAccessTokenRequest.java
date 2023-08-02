package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 小程序码.
 *
 * @author Element
 * created on  2017/7/27
 */
@Data
public class WxMaStableAccessTokenRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @SerializedName("grant_type")
  private String grantType = "client_credential";

  @SerializedName("appid")
  private String appid;
  @SerializedName("secret")
  private String secret;

  @SerializedName("force_refresh")
  private boolean forceRefresh;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}

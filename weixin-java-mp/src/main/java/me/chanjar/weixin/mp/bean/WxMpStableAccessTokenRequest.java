package me.chanjar.weixin.mp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * @author SKYhuangjing
 * 微信公众号 获取稳定版接口调用凭据 请求参数
 */
@Data
public class WxMpStableAccessTokenRequest implements Serializable {

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
    return WxMpGsonBuilder.create().toJson(this);
  }
}

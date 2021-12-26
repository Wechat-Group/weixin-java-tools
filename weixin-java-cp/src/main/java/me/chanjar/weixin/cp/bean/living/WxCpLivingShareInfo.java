package me.chanjar.weixin.cp.bean.living;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 跳转小程序商城的直播观众信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpLivingShareInfo implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  private String livingid;

  @SerializedName("viewer_userid")
  private String viewerUserid;

  @SerializedName("viewer_external_userid")
  private String viewerExternalUserid;

  @SerializedName("invitor_userid")
  private String invitorUserid;

  @SerializedName("invitor_external_userid")
  private String invitorExternalUserid;

  public static WxCpLivingShareInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpLivingShareInfo.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

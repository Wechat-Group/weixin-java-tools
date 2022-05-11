package me.chanjar.weixin.cp.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 权限管理请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpSpaceSettingRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("userid")
  private String userId;

  @SerializedName("spaceid")
  private String spaceId;

  @SerializedName("enable_watermark")
  private Boolean enableWatermark;

  @SerializedName("add_member_only_admin")
  private Boolean addMemberOnlyAdmin;

  @SerializedName("enable_share_url")
  private Boolean enableShareUrl;

  @SerializedName("share_url_no_approve")
  private Boolean shareUrlNoApprove;

  @SerializedName("share_url_no_approve_default_auth")
  private Integer shareUrlNoApproveDefaultAuth;

  public static WxCpSpaceSettingRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpSpaceSettingRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

package me.chanjar.weixin.cp.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 移除成员/部门请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpSpaceAclDelRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("userid")
  private String userId;

  @SerializedName("spaceid")
  private String spaceId;

  @SerializedName("auth_info")
  private List<AuthInfo> authInfo;

  @Getter
  @Setter
  public static class AuthInfo implements Serializable {
    private static final long serialVersionUID = -4960239393895754598L;

    @SerializedName("type")
    private Integer type;

    @SerializedName("departmentid")
    private Integer departmentId;

    @SerializedName("userid")
    private String userId;

    public static AuthInfo fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, AuthInfo.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  public static WxCpSpaceAclDelRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpSpaceAclDelRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

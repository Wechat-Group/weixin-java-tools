package me.chanjar.weixin.cp.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 新建空间请求.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpSpaceCreateRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("userid")
  private String userId;

  @SerializedName("space_name")
  private String spaceName;

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

    @SerializedName("auth")
    private Integer auth;

    @SerializedName("userid")
    private String userId;

    public static AuthInfo fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, AuthInfo.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  public static WxCpSpaceCreateRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpSpaceCreateRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

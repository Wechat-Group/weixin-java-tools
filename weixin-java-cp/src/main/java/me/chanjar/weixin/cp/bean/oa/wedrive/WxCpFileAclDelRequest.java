package me.chanjar.weixin.cp.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 删除指定人请求参数.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpFileAclDelRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("userid")
  private String userId;

  @SerializedName("fileid")
  private String fileId;

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

  public static WxCpFileAclDelRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFileAclDelRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

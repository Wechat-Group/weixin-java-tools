package me.chanjar.weixin.cp.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取空间信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpSpaceInfo extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("space_info")
  private SpaceInfo spaceInfo;

  @Getter
  @Setter
  public static class SpaceInfo implements Serializable {
    private static final long serialVersionUID = -4960239393895754598L;

    @SerializedName("spaceid")
    private String spaceId;

    @SerializedName("space_name")
    private String spaceName;

    @SerializedName("auth_list")
    private AuthList authList;

    public static SpaceInfo fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, SpaceInfo.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  @Getter
  @Setter
  public static class AuthList implements Serializable {
    private static final long serialVersionUID = -4960239393895754598L;

    @SerializedName("auth_info")
    private List<AuthInfo> authInfo;

    @SerializedName("quit_userid")
    private List<String> quitUserId;

    public static AuthList fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, AuthList.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

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

  public static WxCpSpaceInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpSpaceInfo.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

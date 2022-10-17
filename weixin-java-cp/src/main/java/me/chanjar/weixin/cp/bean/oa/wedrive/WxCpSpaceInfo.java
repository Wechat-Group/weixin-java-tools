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

  /**
   * The type Space info.
   */
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

    /**
     * From json space info.
     *
     * @param json the json
     * @return the space info
     */
    public static SpaceInfo fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, SpaceInfo.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  /**
   * The type Auth list.
   */
  @Getter
  @Setter
  public static class AuthList implements Serializable {
    private static final long serialVersionUID = -4960239393895754598L;

    @SerializedName("auth_info")
    private List<AuthInfo> authInfo;

    @SerializedName("quit_userid")
    private List<String> quitUserId;

    /**
     * From json auth list.
     *
     * @param json the json
     * @return the auth list
     */
    public static AuthList fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, AuthList.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  /**
   * The type Auth info.
   */
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

    /**
     * From json auth info.
     *
     * @param json the json
     * @return the auth info
     */
    public static AuthInfo fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, AuthInfo.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  /**
   * From json wx cp space info.
   *
   * @param json the json
   * @return the wx cp space info
   */
  public static WxCpSpaceInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpSpaceInfo.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

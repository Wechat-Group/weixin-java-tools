package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 登录信息
 *
 * @author Jamie.shi
 * @date 2020-08-03 17:18
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class WxTpLoginInfo extends WxCpBaseResp {
  private static final long serialVersionUID = -6994487991072386856L;

  @SerializedName("usertype")
  private Integer userType;
  @SerializedName("user_info")
  private UserInfo userInfo;
  @SerializedName("corp_info")
  private CorpInfoBean corpInfo;
  @SerializedName("auth_info")
  private AuthInfo authInfo;
  private List<Agent> agent;

  public static WxTpLoginInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxTpLoginInfo.class);
  }

  @Data
  public static class UserInfo implements Serializable {
    private static final long serialVersionUID = -4558358748587735192L;

    @SerializedName("userid")
    private String userId;
    @SerializedName("open_userid")
    private String openUserId;
    private String name;
    private String avatar;
  }

  @Data
  public static class CorpInfoBean implements Serializable {
    private static final long serialVersionUID = -3160146744148144984L;

    @SerializedName("corpid")
    private String corpId;
  }

  @Data
  public static class AuthInfo implements Serializable {
    private static final long serialVersionUID = -8697184659526210472L;

    private List<Department> department;

    @Data
    public static class Department implements Serializable {
      private static final long serialVersionUID = -4389328276936557541L;

      private int id;
      private boolean writable;
    }
  }

  @Data
  public static class Agent implements Serializable {
    private static final long serialVersionUID = 1461544500964159037L;
    @SerializedName("agentid")
    private int agentId;
    @SerializedName("auth_type")
    private int authType;
  }
}

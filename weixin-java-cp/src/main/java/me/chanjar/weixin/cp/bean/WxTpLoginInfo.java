package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * @description: 登录信息
 * @author: Jamie.shi
 * @create: 2020-08-03 17:18
 **/
@Data
public class WxTpLoginInfo extends WxCpBaseResp {
  @SerializedName("usertype")
  private Integer userType;
  @SerializedName("user_info")
  private UserInfo userInfo;
  @SerializedName("corp_info")
  private CorpInfoBean corpInfo;
  @SerializedName("auth_info")
  private AuthInfo authInfo;
  private List<Agent> agent;

  @Data
  public static class UserInfo {
    @SerializedName("userid")
    private String userId;
    @SerializedName("open_userid")
    private String openUserId;
    private String name;
    private String avatar;
  }

  @Data
  public static class CorpInfoBean {
    @SerializedName("corpid")
    private String corpId;
  }

  @Data
  public static class AuthInfo {
    private List<Department> department;

    @Data
    public static class Department {

      private int id;
      private boolean writable;
    }
  }

  @Data
  public static class Agent {
    @SerializedName("agentid")
    private int agentId;
    @SerializedName("auth_type")
    private int authType;
  }

  public static WxTpLoginInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxTpLoginInfo.class);
  }
}

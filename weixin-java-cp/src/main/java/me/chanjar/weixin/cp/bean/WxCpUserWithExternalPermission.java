package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

public class WxCpUserWithExternalPermission {
  @SerializedName("errcode")
  @Expose
  private Long errcode;
  @SerializedName("errmsg")
  @Expose
  private String errmsg;

  @SerializedName("follow_user")
  @Expose
  private List<String> followUser = null;

  public Long getErrcode() {
    return errcode;
  }

  public void setErrcode(Long errcode) {
    this.errcode = errcode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }

  public List<String> getFollowUser() {
    return followUser;
  }

  public void setFollowUser(List<String> followUser) {
    this.followUser = followUser;
  }


  public static WxCpUserWithExternalPermission fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserWithExternalPermission.class);
  }
}

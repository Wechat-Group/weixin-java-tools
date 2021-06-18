package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 应用的管理员
 * @author huangxiaoming
 */
@Data
public class WxCpTpAdmin extends WxCpBaseResp {

  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("admin")
  private List<Admin> admin;

  @Getter
  @Setter
  public static class Admin extends WxCpBaseResp {
    private static final long serialVersionUID = -5028321625140879571L;

    @SerializedName("userid")
    private String userId;

    @SerializedName("auth_type")
    private Integer authType;

    public String toJson() {
      return WxGsonBuilder.create().toJson(this);
    }
  }

  public static WxCpTpAdmin fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpAdmin.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

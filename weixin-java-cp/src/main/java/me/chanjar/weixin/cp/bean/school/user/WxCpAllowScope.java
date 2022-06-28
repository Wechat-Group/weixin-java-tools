package me.chanjar.weixin.cp.bean.school.user;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取可使用的家长范围 返回结果.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpAllowScope extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("allow_scope")
  private AllowScope allowScope;

  @Setter
  @Getter
  public static class AllowScope implements Serializable {

    @SerializedName("students")
    private List<Student> students;

    @SerializedName("departments")
    private List<Integer> departments;

    public static AllowScope fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, AllowScope.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  @Setter
  @Getter
  public static class Student implements Serializable {

    @SerializedName("userid")
    private String userId;

  }

  public static WxCpAllowScope fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpAllowScope.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

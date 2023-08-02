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

  /**
   * The type Allow scope.
   */
  @Setter
  @Getter
  public static class AllowScope implements Serializable {

    @SerializedName("students")
    private List<Student> students;

    @SerializedName("departments")
    private Department departments;

    /**
     * From json allow scope.
     *
     * @param json the json
     * @return the allow scope
     */
    public static AllowScope fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, AllowScope.class);
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
   * The type Department.
   */
  @Setter
  @Getter
  public static class Department implements Serializable {

    @SerializedName("partyid")
    private List<Integer> partyId;

    /**
     * From json department.
     *
     * @param json the json
     * @return the department
     */
    public static Department fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Department.class);
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
   * The type Student.
   */
  @Setter
  @Getter
  public static class Student implements Serializable {

    @SerializedName("userid")
    private String userId;

  }

  /**
   * From json wx cp allow scope.
   *
   * @param json the json
   * @return the wx cp allow scope
   */
  public static WxCpAllowScope fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpAllowScope.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

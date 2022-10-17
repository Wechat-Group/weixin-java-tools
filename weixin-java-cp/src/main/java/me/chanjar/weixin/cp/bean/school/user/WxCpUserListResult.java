package me.chanjar.weixin.cp.bean.school.user;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取部门成员详情返回结果.
 *
 * @author Wang_Wong  created on  2022-07-13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpUserListResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("students")
  private List<Student> students;

  /**
   * The type Parent.
   */
  @Setter
  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Parent implements Serializable {

    @SerializedName("parent_userid")
    private String parentUserId;

    @SerializedName("relation")
    private String relation;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("external_userid")
    private String externalUserId;

    @SerializedName("is_subscribe")
    private Integer isSubscribe;

    /**
     * From json parent.
     *
     * @param json the json
     * @return the parent
     */
    public static Parent fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Parent.class);
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
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Student implements Serializable {

    @SerializedName("student_userid")
    private String studentUserId;

    @SerializedName("name")
    private String name;

    @SerializedName("department")
    private List<Integer> department;

    @SerializedName("parents")
    private List<Parent> parents;

    /**
     * From json student.
     *
     * @param json the json
     * @return the student
     */
    public static Student fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Student.class);
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
   * From json wx cp user list result.
   *
   * @param json the json
   * @return the wx cp user list result
   */
  public static WxCpUserListResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserListResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

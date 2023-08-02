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
 * 获取部门列表返回结果.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpDepartmentList extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("departments")
  private List<Department> departments;

  /**
   * The type Department.
   */
  @Setter
  @Getter
  public static class Department implements Serializable {
    @SerializedName("parentid")
    private Integer parentId;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private Integer id;

    @SerializedName("type")
    private Integer type;

    @SerializedName("register_year")
    private Integer registerYear;

    @SerializedName("standard_grade")
    private Integer standardGrade;

    @SerializedName("order")
    private Integer order;

    @SerializedName("is_graduated")
    private Integer isGraduated;

    @SerializedName("open_group_chat")
    private Integer openGroupChat;

    @SerializedName("group_chat_id")
    private String groupChatId;

    @SerializedName("department_admins")
    private List<DepartmentAdmin> departmentAdmins;

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
   * The type Department admin.
   */
  @Setter
  @Getter
  public static class DepartmentAdmin implements Serializable {

    @SerializedName("userid")
    private String userId;

    @SerializedName("type")
    private Integer type;

    @SerializedName("subject")
    private String subject;

    /**
     * From json department admin.
     *
     * @param json the json
     * @return the department admin
     */
    public static DepartmentAdmin fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, DepartmentAdmin.class);
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
   * From json wx cp department list.
   *
   * @param json the json
   * @return the wx cp department list
   */
  public static WxCpDepartmentList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpDepartmentList.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

package me.chanjar.weixin.cp.bean.school.user;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 更新部门请求.
 *
 * @author Wang_Wong  created on  2022-06-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpUpdateDepartmentRequest implements Serializable {
  private static final long serialVersionUID = -4960239394895754138L;

  @SerializedName("parentid")
  private Integer parentId;

  @SerializedName("name")
  private String name;

  @SerializedName("id")
  private Integer id;

  @SerializedName("new_id")
  private Integer newId;

  @SerializedName("type")
  private Integer type;

  @SerializedName("register_year")
  private Integer registerYear;

  @SerializedName("standard_grade")
  private Integer standardGrade;

  @SerializedName("order")
  private Integer order;

  @SerializedName("department_admins")
  private List<DepartmentAdmin> departmentAdmins;

  /**
   * The type Department admin.
   */
  @Setter
  @Getter
  public static class DepartmentAdmin implements Serializable {

    @SerializedName("userid")
    private String userId;

    @SerializedName("op")
    private Integer op;

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
   * From json wx cp update department request.
   *
   * @param json the json
   * @return the wx cp update department request
   */
  public static WxCpUpdateDepartmentRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUpdateDepartmentRequest.class);
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

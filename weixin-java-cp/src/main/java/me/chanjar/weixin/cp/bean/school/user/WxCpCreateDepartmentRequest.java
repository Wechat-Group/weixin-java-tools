package me.chanjar.weixin.cp.bean.school.user;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 创建部门请求.
 *
 * @author Wang_Wong
 * @date 2022-06-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpCreateDepartmentRequest implements Serializable {
  private static final long serialVersionUID = -4960239394895754138L;

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

  @SerializedName("department_admins")
  private List<DepartmentAdmin> departmentAdmins;

  @Setter
  @Getter
  public static class DepartmentAdmin implements Serializable {

    @SerializedName("userid")
    private String userId;

    @SerializedName("type")
    private Integer type;

    @SerializedName("subject")
    private String subject;

    public static DepartmentAdmin fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, DepartmentAdmin.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  public static WxCpCreateDepartmentRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpCreateDepartmentRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

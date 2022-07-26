package me.chanjar.weixin.cp.bean.school.user;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 批量创建学生请求.
 *
 * @author Wang_Wong
 * @date 2022-07-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpBatchCreateStudentRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("students")
  private List<Student> students;

  @Setter
  @Getter
  public static class Student implements Serializable {

    @SerializedName("student_userid")
    private String studentUserId;

    @SerializedName("name")
    private String name;

    @SerializedName("department")
    private List<Integer> department;

    public static Student fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Student.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  public static WxCpBatchCreateStudentRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpBatchCreateStudentRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

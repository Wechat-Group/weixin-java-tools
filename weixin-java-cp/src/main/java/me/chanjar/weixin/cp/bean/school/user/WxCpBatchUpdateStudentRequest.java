package me.chanjar.weixin.cp.bean.school.user;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 批量更新学生请求.
 *
 * @author Wang_Wong  created on  2022-07-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpBatchUpdateStudentRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("students")
  private List<Student> students;

  /**
   * The type Student.
   */
  @Setter
  @Getter
  public static class Student implements Serializable {

    @SerializedName("student_userid")
    private String studentUserId;

    @SerializedName("new_student_userid")
    private String newStudentUserId;

    @SerializedName("name")
    private String name;

    @SerializedName("department")
    private List<Integer> department;

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
   * From json wx cp batch update student request.
   *
   * @param json the json
   * @return the wx cp batch update student request
   */
  public static WxCpBatchUpdateStudentRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpBatchUpdateStudentRequest.class);
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

package me.chanjar.weixin.cp.bean.school;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取未观看直播统计
 *
 * @author Wang_Wong
 */
@Data
public class WxCpSchoolUnwatchStat extends WxCpBaseResp {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("ending")
  private Integer ending;

  @SerializedName("next_key")
  private String nextKey;

  @SerializedName("stat_info")
  private StatInfo statInfo;

  /**
   * The type Stat info.
   */
  @Getter
  @Setter
  public static class StatInfo implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("students")
    private List<Student> students;

  }

  /**
   * The type Student.
   */
  @Getter
  @Setter
  public static class Student implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("student_userid")
    private String studentUserId;

    @SerializedName("parent_userid")
    private String parentUserId;

    @SerializedName("partyids")
    private List<Integer> partyIds;

  }

  /**
   * From json wx cp school unwatch stat.
   *
   * @param json the json
   * @return the wx cp school unwatch stat
   */
  public static WxCpSchoolUnwatchStat fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpSchoolUnwatchStat.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

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
 * 获取观看直播统计.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpSchoolWatchStat extends WxCpBaseResp {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("ending")
  private Integer ending;

  @SerializedName("next_key")
  private String nextKey;

  @SerializedName("stat_infoes")
  private StatInfo statInfoes;

  /**
   * The type Stat info.
   */
  @Getter
  @Setter
  public static class StatInfo implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("students")
    private List<Student> students;

    @SerializedName("visitors")
    private List<Visitor> visitors;

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

    @SerializedName("watch_time")
    private Integer watchTime;

    @SerializedName("is_comment")
    private Integer isComment;

    @SerializedName("enter_time")
    private Long enterTime;

    @SerializedName("leave_time")
    private Long leaveTime;

    @SerializedName("partyids")
    private List<Integer> partyIds;

  }

  /**
   * The type Visitor.
   */
  @Getter
  @Setter
  public static class Visitor implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("watch_time")
    private Integer watchTime;

    @SerializedName("is_comment")
    private Integer isComment;

    @SerializedName("enter_time")
    private Long enterTime;

    @SerializedName("leave_time")
    private Long leaveTime;

  }

  /**
   * From json wx cp school watch stat.
   *
   * @param json the json
   * @return the wx cp school watch stat
   */
  public static WxCpSchoolWatchStat fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpSchoolWatchStat.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

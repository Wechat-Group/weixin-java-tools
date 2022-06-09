package me.chanjar.weixin.cp.bean.school.health;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取用户填写答案.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpGetReportAnswer extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("answers")
  private List<Answer> answers;

  @Getter
  @Setter
  public static class Answer implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("userid")
    private String userId;

    @SerializedName("id_type")
    private Integer idType;

    @SerializedName("report_time")
    private Long reportTime;

    @SerializedName("student_userid")
    private String studentUserId;

    @SerializedName("parent_userid")
    private String parentUserId;

    @SerializedName("report_values")
    private List<ReportValue> reportValues;

    public static Answer fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Answer.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  @Getter
  @Setter
  public static class ReportValue implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("question_id")
    private Integer questionId;

    @SerializedName("single_chose")
    private Integer singleChose;

    @SerializedName("multi_choice")
    private List<Integer> multiChoice;

    @SerializedName("text")
    private String text;

    @SerializedName("fileid")
    private List<String> fileId;

    public static ReportValue fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, ReportValue.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  public static WxCpGetReportAnswer fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGetReportAnswer.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

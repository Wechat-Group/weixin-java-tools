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
 * https://developer.work.weixin.qq.com/document/path/93679
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a>
 */
@Data
public class WxCpGetReportAnswer extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("answers")
  private List<Answer> answers;

  /**
   * The type Answer.
   */
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

    /**
     * From json answer.
     *
     * @param json the json
     * @return the answer
     */
    public static Answer fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Answer.class);
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
   * The type Report value.
   */
  @Getter
  @Setter
  public static class ReportValue implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("question_id")
    private Integer questionId;

    @SerializedName("single_choice")
    private Integer singleChoice;

    @SerializedName("multi_choice")
    private List<Integer> multiChoice;

    @SerializedName("text")
    private String text;

    @SerializedName("fileid")
    private List<String> fileId;

    /**
     * From json report value.
     *
     * @param json the json
     * @return the report value
     */
    public static ReportValue fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, ReportValue.class);
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
   * From json wx cp get report answer.
   *
   * @param json the json
   * @return the wx cp get report answer
   */
  public static WxCpGetReportAnswer fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGetReportAnswer.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

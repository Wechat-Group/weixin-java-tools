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
 * 获取健康上报任务详情.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpGetReportJobInfo extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("job_info")
  private JobInfo jobInfo;

  /**
   * The type Job info.
   */
  @Getter
  @Setter
  public static class JobInfo implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("title")
    private String title;

    @SerializedName("creator")
    private String creator;

    @SerializedName("type")
    private Integer type;

    @SerializedName("report_type")
    private Integer reportType;

    @SerializedName("skip_weekend")
    private Integer skipWeekend;

    @SerializedName("finish_cnt")
    private Integer finishCnt;

    @SerializedName("apply_range")
    private ApplyRange applyRange;

    @SerializedName("report_to")
    private ReportTo reportTo;

    @SerializedName("question_templates")
    private List<QuestionTemplate> questionTemplates;

    /**
     * From json job info.
     *
     * @param json the json
     * @return the job info
     */
    public static JobInfo fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, JobInfo.class);
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
   * The type Apply range.
   */
  @Getter
  @Setter
  public static class ApplyRange implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("userids")
    private List<String> userIds;

    @SerializedName("partyids")
    private List<Integer> partyIds;

    /**
     * From json apply range.
     *
     * @param json the json
     * @return the apply range
     */
    public static ApplyRange fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, ApplyRange.class);
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
   * The type Report to.
   */
  @Getter
  @Setter
  public static class ReportTo implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("userids")
    private List<String> userIds;

    /**
     * From json report to.
     *
     * @param json the json
     * @return the report to
     */
    public static ReportTo fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, ReportTo.class);
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
   * The type Question template.
   */
  @Getter
  @Setter
  public static class QuestionTemplate implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("question_id")
    private Integer questionId;

    @SerializedName("question_type")
    private Integer questionType;

    @SerializedName("is_required")
    private Integer isRequired;

    @SerializedName("title")
    private String title;

    @SerializedName("option_list")
    private List<OptionList> optionList;

    /**
     * From json question template.
     *
     * @param json the json
     * @return the question template
     */
    public static QuestionTemplate fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, QuestionTemplate.class);
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
   * The type Option list.
   */
  @Getter
  @Setter
  public static class OptionList implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("option_id")
    private Integer optionId;

    @SerializedName("option_text")
    private String optionText;

    /**
     * From json option list.
     *
     * @param json the json
     * @return the option list
     */
    public static OptionList fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, OptionList.class);
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
   * From json wx cp get report job info.
   *
   * @param json the json
   * @return the wx cp get report job info
   */
  public static WxCpGetReportJobInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGetReportJobInfo.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

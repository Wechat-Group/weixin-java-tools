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

    public static JobInfo fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, JobInfo.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  @Getter
  @Setter
  public static class ApplyRange implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("userids")
    private List<String> userIds;

    @SerializedName("partyids")
    private List<Integer> partyIds;

    public static ApplyRange fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, ApplyRange.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  @Getter
  @Setter
  public static class ReportTo implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("userids")
    private List<String> userIds;

    public static ReportTo fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, ReportTo.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

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

    public static QuestionTemplate fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, QuestionTemplate.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  @Getter
  @Setter
  public static class OptionList implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("option_id")
    private Integer optionId;

    @SerializedName("option_text")
    private String optionText;

    public static OptionList fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, OptionList.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  public static WxCpGetReportJobInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGetReportJobInfo.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

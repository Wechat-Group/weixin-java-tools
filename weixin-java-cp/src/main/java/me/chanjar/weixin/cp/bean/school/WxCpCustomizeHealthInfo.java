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
 * 获取健康信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpCustomizeHealthInfo extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("health_infos")
  private List<HealthInfo> healthInfos;

  @SerializedName("template_id")
  private String templateId;

  @SerializedName("next_key")
  private String nextKey;

  @SerializedName("ending")
  private Integer ending;

  /**
   * The type Health info.
   */
  @Getter
  @Setter
  public static class HealthInfo implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("userid")
    private String userId;

    @SerializedName("health_qrcode_status")
    private Integer healthQrCodeStatus;

    @SerializedName("self_submit")
    private Integer selfSubmit;

    @SerializedName("report_values")
    private List<ReportValue> reportValues;

    @SerializedName("question_templates")
    private List<QuestionTemplate> questionTemplates;

    /**
     * From json health info.
     *
     * @param json the json
     * @return the health info
     */
    public static HealthInfo fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, HealthInfo.class);
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

    @SerializedName("single_chose")
    private Integer singleChose;

    @SerializedName("text")
    private String text;

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

    @SerializedName("title")
    private String title;

    @SerializedName("is_must_fill")
    private Integer isMustFill;

    @SerializedName("is_not_display")
    private Integer isNotDisplay;

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
   * From json wx cp customize health info.
   *
   * @param json the json
   * @return the wx cp customize health info
   */
  public static WxCpCustomizeHealthInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpCustomizeHealthInfo.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

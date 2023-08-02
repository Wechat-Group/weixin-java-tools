package me.chanjar.weixin.cp.bean.oa;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 企业假期管理配置信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpCorpConfInfo extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = 7387181805254287157L;

  @SerializedName("lists")
  private List<CorpConf> lists;

  /**
   * The type Corp conf.
   */
  @Getter
  @Setter
  public static class CorpConf implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("time_attr")
    private Integer timeAttr;

    @SerializedName("duration_type")
    private Integer durationType;

    @SerializedName("quota_attr")
    private QuotaAttr quotaAttr;

    @SerializedName("perday_duration")
    private Integer perdayDuration;

  }

  /**
   * The type Quota attr.
   */
  @Getter
  @Setter
  public static class QuotaAttr implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("type")
    private Integer type;

    @SerializedName("autoreset_time")
    private Integer autoresetTime;

    @SerializedName("autoreset_duration")
    private Integer autoresetDuration;

  }

  /**
   * From json wx cp corp conf info.
   *
   * @param json the json
   * @return the wx cp corp conf info
   */
  public static WxCpCorpConfInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpCorpConfInfo.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

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
 * 获取直播详情.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpSchoolLivingInfo extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("living_info")
  private LivingInfo livingInfo;

  @Getter
  @Setter
  public static class LivingInfo implements Serializable {

    @SerializedName("theme")
    private String theme;

    @SerializedName("living_start")
    private Long livingStart;

    @SerializedName("living_duration")
    private Long livingDuration;

    @SerializedName("anchor_userid")
    private String anchorUserId;

    @SerializedName("living_range")
    private LivingRange livingRange;

    @SerializedName("viewer_num")
    private Integer viewerNum;

    @SerializedName("comment_num")
    private Integer commentNum;

    @SerializedName("open_replay")
    private Integer openReplay;

    @SerializedName("push_stream_url")
    private String pushStreamUrl;

    public static LivingInfo fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, LivingInfo.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  @Getter
  @Setter
  public static class LivingRange implements Serializable {

    @SerializedName("partyids")
    private List<Integer> partyIds;

    @SerializedName("group_names")
    private List<String> groupNames;

    public static LivingRange fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, LivingRange.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  public static WxCpSchoolLivingInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpSchoolLivingInfo.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

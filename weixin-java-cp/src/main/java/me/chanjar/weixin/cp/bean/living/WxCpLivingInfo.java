package me.chanjar.weixin.cp.bean.living;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 直播详情信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpLivingInfo implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("theme")
  private String theme;

  @SerializedName("living_start")
  private Long livingStart;

  @SerializedName("living_duration")
  private Long livingDurationme;

  @SerializedName("status")
  private Integer status;

  @SerializedName("reserve_living_duration")
  private Long reserveLivingDuration;

  @SerializedName("reserve_start")
  private Long reserveStart;

  @SerializedName("description")
  private String description;

  @SerializedName("anchor_userid")
  private String anchorUserid;

  @SerializedName("main_department")
  private Long mainDepartment;

  @SerializedName("viewer_num")
  private Integer viewerNum;

  @SerializedName("comment_num")
  private Integer commentNum;

  @SerializedName("mic_num")
  private Integer micNum;

  @SerializedName("open_replay")
  private Integer openReplay;

  @SerializedName("replay_status")
  private Integer replayStatus;

  @SerializedName("type")
  private Integer type;

  @SerializedName("push_stream_url")
  private String pushStreamUrl;

  @SerializedName("online_count")
  private Integer onlineCount;

  @SerializedName("subscribe_count")
  private Integer subscribeCount;

  public static WxCpLivingInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpLivingInfo.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

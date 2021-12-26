package me.chanjar.weixin.cp.bean.living;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 创建预约直播请求.
 *
 * @author Wang_Wong
 * @date 2021-12-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpLivingCreateRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("anchor_userid")
  private String anchorUserid;

  @SerializedName("theme")
  private String theme;

  @SerializedName("living_start")
  private Long livingStart;

  @SerializedName("living_duration")
  private Long livingDuration;

  @SerializedName("remind_time")
  private Long remindTime;

  @SerializedName("description")
  private String description;

  @SerializedName("type")
  private Integer type;

  @SerializedName("agentid")
  private Integer agentId;

  @SerializedName("activity_cover_mediaid")
  private String activityCoverMediaid;

  @SerializedName("activity_share_mediaid")
  private String activityShareMediaid;

  @SerializedName("activity_detail")
  private ActivityDetail activityDetail;

  public static class ActivityDetail implements Serializable {

    @SerializedName("image_list")
    private String[] imageList;

    @SerializedName("description")
    private String description;

    public static ActivityDetail fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, ActivityDetail.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  public static WxCpLivingCreateRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpLivingCreateRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

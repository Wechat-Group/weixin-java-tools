package me.chanjar.weixin.cp.bean.living;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 创建预约直播请求.
 *
 * @author Wang_Wong  created on  2021-12-23
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

  /**
   * The type Activity detail.
   */
  @Getter
  @Setter
  public static class ActivityDetail implements Serializable {

    @SerializedName("image_list")
    private List<String> imageList;

    @SerializedName("description")
    private String description;

    /**
     * From json activity detail.
     *
     * @param json the json
     * @return the activity detail
     */
    public static ActivityDetail fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, ActivityDetail.class);
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
   * From json wx cp living create request.
   *
   * @param json the json
   * @return the wx cp living create request
   */
  public static WxCpLivingCreateRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpLivingCreateRequest.class);
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

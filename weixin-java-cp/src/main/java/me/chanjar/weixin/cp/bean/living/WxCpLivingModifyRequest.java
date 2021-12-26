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
public class WxCpLivingModifyRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("livingid")
  private String livingId;

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

  public static WxCpLivingModifyRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpLivingModifyRequest.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

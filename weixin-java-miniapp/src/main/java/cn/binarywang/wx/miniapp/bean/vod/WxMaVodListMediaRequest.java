package cn.binarywang.wx.miniapp.bean.vod;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaVodListMediaRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;

  @SerializedName("drama_id")
  private Integer dramaId;
  @SerializedName("media_name")
  private String mediaName;

  @SerializedName("start_time")
  private Long startTime;
  @SerializedName("end_time")
  private Long endTime;

  @SerializedName("offset")
  private Integer offset;
  @SerializedName("limit")
  private Integer limit;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}

package cn.binarywang.wx.miniapp.bean.vod;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxMaVodMediaPlaybackInfo implements Serializable {
  private static final long serialVersionUID = -7663757440028175135L;

  private String name;
  private String description;
  @SerializedName("media_id")
  private Integer mediaId;
  @SerializedName("duration")
  private Long duration;

  @SerializedName("cover_url")
  private String coverUrl;
  @SerializedName("mp4_url")
  private String mp4Url;
  @SerializedName("hls_url")
  private String hlsUrl;

}

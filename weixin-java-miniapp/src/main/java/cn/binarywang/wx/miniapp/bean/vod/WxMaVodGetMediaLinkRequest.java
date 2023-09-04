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
public class WxMaVodGetMediaLinkRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;

  @SerializedName("media_id")
  private Integer mediaId;
  @SerializedName("t")
  private Long t;

  @SerializedName("expr")
  private Long expr;
  @SerializedName("rlimit")
  private Long rlimit;

  @SerializedName("us")
  private String us;
  @SerializedName("whref")
  private String whRef;
  @SerializedName("bkref")
  private String bkRef;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}

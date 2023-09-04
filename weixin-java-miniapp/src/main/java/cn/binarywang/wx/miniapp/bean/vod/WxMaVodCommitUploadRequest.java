package cn.binarywang.wx.miniapp.bean.vod;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaVodCommitUploadRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("upload_id")
  private String uploadId;
  @SerializedName("media_part_infos")
  private List<PartInfo> mediaPartInfos;
  @SerializedName("cover_part_infos")
  private List<PartInfo> coverPartInfos;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

  @Data
  public static class PartInfo {

    @SerializedName("part_number")
    private Integer partNumber;
    @SerializedName("etag")
    private String etag;

  }
}

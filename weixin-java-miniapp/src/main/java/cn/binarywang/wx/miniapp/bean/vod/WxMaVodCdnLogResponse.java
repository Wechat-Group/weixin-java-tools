package cn.binarywang.wx.miniapp.bean.vod;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxMaVodCdnLogResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = -7663757440028175135L;
  @SerializedName("total_count")
  private Integer totalCount;
  @SerializedName("domestic_cdn_logs")
  private List<CdnLogInfo> domesticCdnLogs;

  @Data
  public static class CdnLogInfo {
    @SerializedName("date")
    private Long date;
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;
    @SerializedName("start_time")
    private Long startTime;
    @SerializedName("end_time")
    private Long endTime;

  }
}

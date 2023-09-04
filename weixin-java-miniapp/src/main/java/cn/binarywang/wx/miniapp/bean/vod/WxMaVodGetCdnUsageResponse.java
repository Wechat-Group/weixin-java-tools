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
public class WxMaVodGetCdnUsageResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = -7663757440028175135L;
  @SerializedName("data_interval")
  private Integer dataInterval;
  @SerializedName("item_list")
  private List<DataItem> itemList;

  @Data
  public static class DataItem {

    @SerializedName("value")
    private Long value;
    @SerializedName("time")
    private Long time;

  }
}

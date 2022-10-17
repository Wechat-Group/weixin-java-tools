package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/6/18 2:57 下午
 */
@Data
public class WxMaShopSharerLiveSummaryListResponse extends WxMaShopBaseResponse implements Serializable {

  private static final long serialVersionUID = -2085366863029618067L;

  private List<LiveSummaryItem> lives;

  @SerializedName("total_num")
  private Integer totalNum;

  @Data
  public static class LiveSummaryItem {
    @SerializedName("live_export_id")
    private String liveExportId;
    @SerializedName("live_nickname")
    private String liveNickname;
    @SerializedName("live_start_time")
    private Long liveStartTime;
    @SerializedName("live_end_time")
    private Long liveEndTime;
    @SerializedName("live_status")
    private Long liveStatus;
    @SerializedName("gmv")
    private Long gmv;
    @SerializedName("order_cnt")
    private Long orderCnt;
    @SerializedName("user_cnt")
    private Long userCnt;
  }
}

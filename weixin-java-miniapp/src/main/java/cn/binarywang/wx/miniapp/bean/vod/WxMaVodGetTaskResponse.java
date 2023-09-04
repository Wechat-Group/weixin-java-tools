package cn.binarywang.wx.miniapp.bean.vod;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
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
public class WxMaVodGetTaskResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("task_info")
  private TaskInfo taskInfo;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

  @Data
  public static class TaskInfo {

    @SerializedName("task_type")
    private Integer taskType;
    @SerializedName("status")
    private Integer status;
    @SerializedName("errcode")
    private Integer errCode;
    @SerializedName("errmsg")
    private String errMsg;
    @SerializedName("create_time")
    private Long createTime;
    @SerializedName("finish_time")
    private Long finishTime;
    @SerializedName("media_id")
    private Integer mediaId;

  }
}

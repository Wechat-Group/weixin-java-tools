package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 企业发表内容到客户的朋友圈 获取客户朋友圈企业发表的列表
 *
 * @author leiin
 * @date 2021-10-29
 */
@Data
public class WxCpGetMomentTask extends WxCpBaseResp {
  @SerializedName("next_cursor")
  private String nextCursor;

  @SerializedName("task_list")
  private List<MomentTaskItem> taskList;

  @Getter
  @Setter
  public static class MomentTaskItem {
    @SerializedName("userid")
    private String userId;
    @SerializedName("publish_status")
    private String publishStatus;
  }

  public static WxCpGetMomentTask fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGetMomentTask.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}

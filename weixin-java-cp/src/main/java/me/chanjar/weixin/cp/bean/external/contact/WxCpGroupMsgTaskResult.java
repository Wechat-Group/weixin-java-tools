package me.chanjar.weixin.cp.bean.external.contact;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 获取群发成员发送任务列表
 * 参考文档：https://work.weixin.qq.com/api/doc/90000/90135/93338
 * </pre>
 *
 * @author <a href="https://github.com/wslongchen">Mr.Pan</a>
 */
@Getter
@Setter
public class WxCpGroupMsgTaskResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5166048319463473186L;

  @SerializedName("task_list")
  private List<ExternalContactGroupMsgTaskInfo> taskList;

  @SerializedName("next_cursor")
  private String nextCursor;

  @Getter
  @Setter
  public static class ExternalContactGroupMsgTaskInfo implements Serializable {
    private static final long serialVersionUID = 1500416806087532531L;

    @SerializedName("userid")
    private String userId;

    private Integer status;

    @SerializedName("send_time")
    private Long sendTime;

  }

  public static WxCpGroupMsgTaskResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGroupMsgTaskResult.class);
  }

}

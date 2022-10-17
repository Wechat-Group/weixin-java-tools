package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.external.moment.ExternalContactList;
import me.chanjar.weixin.cp.bean.external.moment.SenderList;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 企业发表内容到客户的朋友圈 获取任务创建结果
 *
 * @author leiin  created on  2021-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpGetMomentTaskResult extends WxCpBaseResp {
  private static final long serialVersionUID = 2515140928288915077L;

  private Integer status;
  private String type;
  private TaskResult result;

  /**
   * The type Task result.
   */
  @Getter
  @Setter
  public static class TaskResult extends WxCpBaseResp {
    private static final long serialVersionUID = 2162642873632126707L;

    @SerializedName("moment_id")
    private String momentId;
    @SerializedName("invalid_sender_list")
    private SenderList invalidSenderList;
    @SerializedName("invalid_external_contact_list")
    private ExternalContactList invalidExternalContactList;

    /**
     * From json task result.
     *
     * @param json the json
     * @return the task result
     */
    public static TaskResult fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, TaskResult.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }
  }

  /**
   * From json wx cp get moment task result.
   *
   * @param json the json
   * @return the wx cp get moment task result
   */
  public static WxCpGetMomentTaskResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGetMomentTaskResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}

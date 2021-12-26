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
 * @author leiin
 * @date 2021-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpGetMomentTaskResult extends WxCpBaseResp {
  private static final long serialVersionUID = 2515140928288915077L;
  private Integer status;
  private String type;
  private TaskResult result;

  @Getter
  @Setter
  public static class TaskResult extends WxCpBaseResp {
    @SerializedName("moment_id")
    private String momentId;
    @SerializedName("invalid_sender_list")
    private SenderList invalidSenderList;
    @SerializedName("invalid_external_contact_list")
    private ExternalContactList invalidExternalContactList;

    public static TaskResult fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, TaskResult.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }
  }

  public static WxCpGetMomentTaskResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGetMomentTaskResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}

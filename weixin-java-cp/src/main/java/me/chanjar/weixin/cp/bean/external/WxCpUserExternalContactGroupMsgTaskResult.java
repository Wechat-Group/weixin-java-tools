package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author caiqy
 */
@Data
public class WxCpUserExternalContactGroupMsgTaskResult implements Serializable {

  private static final long serialVersionUID = 7560561423015869886L;

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  @SerializedName("next_cursor")
  private String nextCursor;

  @SerializedName("task_list")
  private List<Task> taskList;

  public static WxCpUserExternalContactGroupMsgTaskResult fromJson(String result) {
    return WxCpGsonBuilder.create().fromJson(result, WxCpUserExternalContactGroupMsgTaskResult.class);
  }

  @Data
  public static class Task implements Serializable {

    private static final long serialVersionUID = 1892353713509084471L;

    /**
     * 企业服务人员的userid
     */
    @SerializedName("userid")
    private String userId;

    /**
     * 发送状态：0-未发送 2-已发送
     */
    @SerializedName("status")
    private int status;

    /**
     * 发送时间，未发送时不返回
     */
    @SerializedName("send_time")
    private String sendTime;
  }

}

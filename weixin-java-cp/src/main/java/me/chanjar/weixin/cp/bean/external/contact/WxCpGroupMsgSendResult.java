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
 * 获取企业群发成员执行结果
 * 参考文档：https://work.weixin.qq.com/api/doc/90000/90135/93338
 * </pre>
 *
 * @author <a href="https://github.com/wslongchen">Mr.Pan</a>
 */
@Getter
@Setter
public class WxCpGroupMsgSendResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5166048319463473186L;

  @SerializedName("send_list")
  private List<ExternalContactGroupMsgSendInfo> sendList;

  @SerializedName("next_cursor")
  private String nextCursor;

  @Getter
  @Setter
  public static class ExternalContactGroupMsgSendInfo implements Serializable {
    private static final long serialVersionUID = 1500416806087532531L;

    @SerializedName("external_userid")
    private String externalUserId;

    @SerializedName("chat_id")
    private String chatId;

    @SerializedName("userid")
    private String userId;

    private Integer status;

    @SerializedName("send_time")
    private Long sendTime;

  }

  public static WxCpGroupMsgSendResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGroupMsgSendResult.class);
  }

}

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
 * 参考文档：https://work.weixin.qq.com/api/doc/16251
 * </pre>
 *
 * @author <a href="https://github.com/timsims">Tim Sims</a>
 */
@Getter
@Setter
public class WxCpGroupMsgResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5166048319463473186L;

  @SerializedName("detail_list")
  private List<ExternalContactGroupMsgDetailInfo> detailList;

  @SerializedName("next_cursor")
  private String nextCursor;

  @Getter
  @Setter
  public static class ExternalContactGroupMsgDetailInfo implements Serializable {
    private static final long serialVersionUID = 1500416806087532531L;

    // 外部联系人userid，群发消息到企业的客户群不吐出该字段
    @SerializedName("external_userid")
    private String externalUserId;

    // 外部客户群id，群发消息到客户不吐出该字段
    @SerializedName("chat_id")
    private String chatId;

    // 企业服务人员的userid
    @SerializedName("userid")
    private String userId;

    // 发送状态 0-未发送 1-已发送 2-因客户不是好友导致发送失败 3-因客户已经收到其他群发消息导致发送失败
    private Integer status;

    // 发送时间，发送状态为1时返回
    @SerializedName("send_time")
    private Long sendTime;
  }

  public static WxCpGroupMsgResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGroupMsgResult.class);
  }
}

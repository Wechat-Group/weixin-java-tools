package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author caiqy
 */
@Data
public class WxCpUserExternalContactGroupMsgSendResult implements Serializable {

  private static final long serialVersionUID = 7560561423015869886L;

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  @SerializedName("next_cursor")
  private String nextCursor;

  @SerializedName("send_list")
  private List<SendItem> sendItemList;

  public static WxCpUserExternalContactGroupMsgSendResult fromJson(String result) {
    return WxCpGsonBuilder.create().fromJson(result, WxCpUserExternalContactGroupMsgSendResult.class);
  }

  @Data
  public static class SendItem implements Serializable {

    private static final long serialVersionUID = 1892353713509084471L;

    /**
     * 外部联系人userid，群发消息到企业的客户群不返回该字段
     */
    @SerializedName("external_userid")
    private String externalUserid;

    /**
     * 外部客户群id，群发消息到客户不返回该字段
     */
    @SerializedName("send_time")
    private long sendTime;

    /**
     * 企业服务人员的userid
     */
    @SerializedName("userid")
    private String userid;

    /**
     * 外部客户群id，群发消息到客户不返回该字段
     */
    @SerializedName("chat_id")
    private String chatId;

    /**
     * 发送状态：0-未发送 1-已发送 2-因客户不是好友导致发送失败 3-因客户已经收到其他群发消息导致发送失败
     */
    @SerializedName("status")
    private int status;

    public Date getSendDate() {
      if (sendTime > 0) {
        if (String.valueOf(sendTime).length() == 10) {
          return new Date(sendTime * 1000);
        }
        if (String.valueOf(sendTime).length() == 13) {
          return new Date(sendTime);
        }
      }
      return null;
    }

    public String getSendDetail() {
      switch (status) {
        case 0:
          return "未发送";
        case 1:
          return "已发送";
        case 2:
          return "客户不是好友导致发送失败";
        case 3:
          return "因客户已经收到其他群发消息导致发送失败";
        default:
          return "未知";
      }
    }
  }
}

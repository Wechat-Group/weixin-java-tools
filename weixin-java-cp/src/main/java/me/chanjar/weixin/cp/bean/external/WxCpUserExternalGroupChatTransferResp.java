package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * 分配离职成员的客户群结果
 * @author pg
 * @date 2021年6月21日
 */
@Getter
@Setter
public class WxCpUserExternalGroupChatTransferResp extends WxCpBaseResp {
  private static final long serialVersionUID = -943124579487821819L;
  /**
   * 没有成功继承的群列表
   */
  @SerializedName("failed_chat_list")
  private List<GroupChatFailedTransfer> failedChatList;

  public static WxCpUserExternalGroupChatTransferResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserExternalGroupChatTransferResp.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  @Getter
  @Setter
  public static class GroupChatFailedTransfer extends WxCpBaseResp  {
    private static final long serialVersionUID = -5836775099634587239L;
    /**
     * 没能成功继承的群ID
     */
    private String chatId;

    public static WxCpUserExternalGroupChatTransferResp.GroupChatFailedTransfer fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpUserExternalGroupChatTransferResp.GroupChatFailedTransfer.class);
    }

    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }
  }
}

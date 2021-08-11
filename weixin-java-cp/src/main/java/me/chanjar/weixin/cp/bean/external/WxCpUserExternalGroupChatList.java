package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author yqx
 * @date 2020/3/116
 */
@Getter
@Setter
public class WxCpUserExternalGroupChatList extends WxCpBaseResp {
  private static final long serialVersionUID = 1907272035492110236L;

  @SerializedName("group_chat_list")
  private List<ChatStatus> groupChatList;

  @SerializedName("next_cursor")
  private String nextCursor;

  @Getter
  @Setter
  public static class ChatStatus implements Serializable {

    /**
     * 客户群ID
     */
    @SerializedName("chat_id")
    private String chatId;

    /**
     * 客户群状态
     * 0 - 正常
     * 1 - 跟进人离职
     * 2 - 离职继承中
     * 3 - 离职继承完成
     */
    @SerializedName("status")
    private int status;

  }

  public static WxCpUserExternalGroupChatList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserExternalGroupChatList.class);
  }
}

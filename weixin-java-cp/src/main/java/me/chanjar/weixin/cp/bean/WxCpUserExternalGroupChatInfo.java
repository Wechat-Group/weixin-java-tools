package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * @author yqx
 * @date 2020/3/116
 */
@Getter
@Setter
public class WxCpUserExternalGroupChatInfo extends WxCpBaseResp{

  @SerializedName("group_chat")
  private GroupChat groupChat;

  @Getter
  @Setter
  public static class GroupChat {
    @SerializedName("chat_id")
    private String chatId;

    @SerializedName("name")
    private String name;

    @SerializedName("owner")
    private String owner;

    @SerializedName("create_time")
    private Long createTime;

    @SerializedName("notice")
    private String notice;

    @SerializedName("member_list")
    private List<GroupMember> memberList;

  }

  @Getter
  @Setter
  public static class GroupMember {
    @SerializedName("userid")
    private String userId;

    /**
     * 成员类型。
     * 1 - 企业成员
     * 2 - 外部联系人
     */
    @SerializedName("type")
    private int type;

    @SerializedName("join_time")
    private Long joinTime;

    /**
     * 入群方式。
     * 1 - 由成员邀请入群（直接邀请入群）
     * 2 - 由成员邀请入群（通过邀请链接入群）
     * 3 - 通过扫描群二维码入群
     */
    @SerializedName("join_scene")
    private int joinScene;

  }

  public static WxCpUserExternalGroupChatInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserExternalGroupChatInfo.class);
  }
}

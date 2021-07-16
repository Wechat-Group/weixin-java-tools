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
public class WxCpUserExternalGroupChatInfo extends WxCpBaseResp {

  @SerializedName("group_chat")
  private GroupChat groupChat;

  @Getter
  @Setter
  public static class GroupChat implements Serializable {
    private static final long serialVersionUID = -4301684507150486556L;

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

    @SerializedName("admin_list")
    private List<GroupAdmin> adminList;
  }

  @Getter
  @Setter
  public static class GroupMember implements Serializable {
    private static final long serialVersionUID = -4301684507150486556L;

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
    * 外部联系人在微信开放平台的唯一身份标识（微信unionid）
    * 通过此字段企业可将外部联系人与公众号/小程序用户关联起来
    * 仅当群成员类型是微信用户（包括企业成员未添加好友），且企业或第三方服务商绑定了微信开发者ID有此字段
    */
    @SerializedName("unionid")
    private String unionId;

    /**
     * 入群方式。
     * 1 - 由成员邀请入群（直接邀请入群）
     * 2 - 由成员邀请入群（通过邀请链接入群）
     * 3 - 通过扫描群二维码入群
     */
    @SerializedName("join_scene")
    private int joinScene;

    /**
     * 在群里的昵称
     */
    @SerializedName("group_nickname")
    private String groupNickname;

    /**
     * 名字。仅当 need_name = 1 时返回
     * 如果是微信用户，则返回其在微信中设置的名字
     * 如果是企业微信联系人，则返回其设置对外展示的别名或实名
     */
    @SerializedName("name")
    private String name;

    /**
     * 邀请者。目前仅当是由本企业内部成员邀请入群时会返回该值
     */
    @SerializedName("invitor")
    private Invitor invitor;
  }

  @Getter
  @Setter
  public static class Invitor{

    /**
     * 邀请者的userid
     */
    @SerializedName("userid")
    private String userId;
  }

  @Getter
  @Setter
  public static class GroupAdmin{

    /**
     * 群管理员userid
     */
    @SerializedName("userid")
    private String userId;
  }

  public static WxCpUserExternalGroupChatInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserExternalGroupChatInfo.class);
  }
}

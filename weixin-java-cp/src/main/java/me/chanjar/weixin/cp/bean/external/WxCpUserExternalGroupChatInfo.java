package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * The type Wx cp user external group chat info.
 *
 * @author yqx  created on  2020/3/116
 */
@Getter
@Setter
public class WxCpUserExternalGroupChatInfo extends WxCpBaseResp {

  /**
   * 客户群详情
   */
  @SerializedName("group_chat")
  private GroupChat groupChat;

  /**
   * 客户群详情
   */
  @Getter
  @Setter
  public static class GroupChat implements Serializable {
    private static final long serialVersionUID = -4301684507150486556L;

    /**
     * 客户群ID
     */
    @SerializedName("chat_id")
    private String chatId;
    /**
     * 群名
     */
    @SerializedName("name")
    private String name;
    /**
     * 群主ID
     */
    @SerializedName("owner")
    private String owner;

    /**
     * 群的创建时间
     */
    @SerializedName("create_time")
    private Long createTime;
    /**
     * 群公告
     */
    @SerializedName("notice")
    private String notice;
    /**
     * 群成员列表
     */
    @SerializedName("member_list")
    private List<GroupMember> memberList;
    /**
     * 群管理员列表
     */
    @SerializedName("admin_list")
    private List<GroupAdmin> adminList;
    /**
     * 当前群成员版本号。可以配合客户群变更事件减少主动调用本接口的次数
     */
    @SerializedName("member_version")
    private String memberVersion;
  }

  /**
   * 群成员
   */
  @Getter
  @Setter
  public static class GroupMember implements Serializable {
    private static final long serialVersionUID = -4301684507150486556L;

    /**
     * 群成员id
     */
    @SerializedName("userid")
    private String userId;

    /**
     * 成员类型。
     * 1 - 企业成员
     * 2 - 外部联系人
     */
    @SerializedName("type")
    private int type;

    /**
     * 外部联系人在微信开放平台的唯一身份标识（微信unionid）
     * 通过此字段企业可将外部联系人与公众号/小程序用户关联起来
     * 仅当群成员类型是微信用户（包括企业成员未添加好友），且企业绑定了微信开发者ID有此字段（查看绑定方法）。
     * 第三方不可获取，上游企业不可获取下游企业客户的unionid字段
     */
    @SerializedName("unionid")
    private String unionId;

    /**
     * 入群时间
     */
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

    /**
     * 邀请者。目前仅当是由本企业内部成员邀请入群时会返回该值
     */
    @SerializedName("invitor")
    private Invitor invitor;

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

  }

  /**
   * The type Invitor.
   */
  @Getter
  @Setter
  public static class Invitor {

    /**
     * 邀请者的userid
     */
    @SerializedName("userid")
    private String userId;
  }

  /**
   * 群管理员列表
   */
  @Getter
  @Setter
  public static class GroupAdmin {

    /**
     * 群管理员userid
     */
    @SerializedName("userid")
    private String userId;
  }

  /**
   * From json wx cp user external group chat info.
   *
   * @param json the json
   * @return the wx cp user external group chat info
   */
  public static WxCpUserExternalGroupChatInfo fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserExternalGroupChatInfo.class);
  }
}

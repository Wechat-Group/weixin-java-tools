package me.chanjar.weixin.cp.bean.school.user;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 获取外部联系人详情
 * https://developer.work.weixin.qq.com/document/path/91670
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a> created on : 2022/6/27 9:10
 */
@Data
public class WxCpExternalContact extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = 4311777322534499260L;

  @SerializedName("external_contact")
  private ExternalContact externalContact;

  @SerializedName("follow_user")
  private List<WxCpFollowUser> followedUsers;

  /**
   * The type Wx cp follow user.
   */
  @Getter
  @Setter
  public static class WxCpFollowUser implements Serializable {
    private static final long serialVersionUID = -4301684507150486556L;

    @SerializedName("userid")
    private String userId;

    private String remark;

    private String description;

    @SerializedName("createtime")
    private Long createTime;

    private String state;

    @SerializedName("remark_mobiles")
    private String[] remarkMobiles;

    @SerializedName("remark_corp_name")
    private String remarkCorpName;

    private Tag[] tags;

    /**
     * From json wx cp follow user.
     *
     * @param json the json
     * @return the wx cp follow user
     */
    public static WxCpFollowUser fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, WxCpFollowUser.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  /**
   * The type Tag.
   */
  @Getter
  @Setter
  public static class Tag implements Serializable {
    private static final long serialVersionUID = -7556237053703295482L;

    /**
     * 该成员添加此外部联系人所打标签的分组名称（标签功能需要企业微信升级到2.7.5及以上版本）
     */
    @SerializedName("group_name")
    private String groupName;

    /**
     * 该成员添加此外部联系人所打标签名称
     */
    @SerializedName("tag_name")
    private String tagName;

    /**
     * 该成员添加此外部联系人所打标签类型, 1-企业设置, 2-用户自定义
     */
    private int type;

    /**
     * From json tag.
     *
     * @param json the json
     * @return the tag
     */
    public static Tag fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, Tag.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  /**
   * The type External contact.
   */
  @Getter
  @Setter
  public static class ExternalContact implements Serializable {
    private static final long serialVersionUID = -1049085217436072418L;

    @SerializedName("external_userid")
    private String externalUserId;

    @SerializedName("position")
    private String position;

    @SerializedName("name")
    private String name;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("corp_name")
    private String corpName;

    @SerializedName("corp_full_name")
    private String corpFullName;

    @SerializedName("type")
    private Integer type;

    @SerializedName("gender")
    private Integer gender;

    @SerializedName("unionid")
    private String unionId;

    @SerializedName("is_subscribe")
    private Integer isSubscribe;

    @SerializedName("subscriber_info")
    private SubscriberInfo subscriberInfo;

    @SerializedName("external_profile")
    private ExternalProfile externalProfile;

    /**
     * From json external contact.
     *
     * @param json the json
     * @return the external contact
     */
    public static ExternalContact fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, ExternalContact.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  /**
   * The type Subscriber info.
   */
  @Getter
  @Setter
  public static class SubscriberInfo implements Serializable {
    private static final long serialVersionUID = -2899906589789022765L;

    @SerializedName("tag_id")
    private List<String> tagId;

    @SerializedName("remark_mobiles")
    private List<String> remarkMobiles;

    @SerializedName("remark")
    private String remark;

    /**
     * From json subscriber info.
     *
     * @param json the json
     * @return the subscriber info
     */
    public static SubscriberInfo fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, SubscriberInfo.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  /**
   * The type External profile.
   */
  @Getter
  @Setter
  public static class ExternalProfile implements Serializable {
    private static final long serialVersionUID = -2899906589789022765L;

    @SerializedName("external_attr")
    private List<ExternalAttribute> externalAttrs;

  }

  /**
   * The type External attribute.
   */
  @Getter
  @Setter
  public static class ExternalAttribute implements Serializable {
    private static final long serialVersionUID = -1262278808286421085L;

    private int type;

    private String name;

    private Text text;

    private Web web;

    @SerializedName("miniprogram")
    private MiniProgram miniProgram;

    /**
     * From json external attribute.
     *
     * @param json the json
     * @return the external attribute
     */
    public static ExternalAttribute fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, ExternalAttribute.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  /**
   * The type Text.
   */
  @Getter
  @Setter
  public static class Text implements Serializable {
    private static final long serialVersionUID = -8161579335600269094L;

    private String value;

  }

  /**
   * The type Web.
   */
  @Getter
  @Setter
  public static class Web implements Serializable {
    private static final long serialVersionUID = 3664557135411521862L;

    private String title;

    private String url;

  }

  /**
   * The type Mini program.
   */
  @Getter
  @Setter
  public static class MiniProgram implements Serializable {
    private static final long serialVersionUID = -5329210594501835796L;

    @SerializedName("pagepath")
    private String pagePath;

    private String appid;

    private String title;

  }

  /**
   * From json wx cp external contact.
   *
   * @param json the json
   * @return the wx cp external contact
   */
  public static WxCpExternalContact fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpExternalContact.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

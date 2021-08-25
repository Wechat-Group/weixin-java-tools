package me.chanjar.weixin.cp.bean.message.builder;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * The type Wx cp mini program notice message.
 *
 * @author caiqy
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxCpMiniProgramNoticeMessage extends WxCpMessage {

  private static final long serialVersionUID = -6243743821110199350L;

  @SerializedName("miniprogram_notice")
  private MiniProgramNoticeMessage miniProgramNoticeMessage;

  /**
   * Builder wx cp mini program notice message builder.
   *
   * @param toUser                 指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。
   *                               特殊情况：指定为”@all”，则向该企业应用的全部成员发送
   * @param toParty                指定接收消息的部门，部门ID列表，多个接收者用‘|’分隔，最多支持100个。
   *                               当touser为”@all”时忽略本参数
   * @param toTag                  指定接收消息的标签，标签ID列表，多个接收者用‘|’分隔，最多支持100个。
   *                               当touser为”@all”时忽略本参数
   * @param agentId                企业应用的id，整型。企业内部开发，可在应用的设置页面查看；第三方服务商，可通过接口 获取企业授权信息 获取该参数值
   * @param safe                   是否是保密消息，0表示可对外分享，1表示不能分享且内容显示水印，默认为0
   * @param enableIdTrans          是否开启id转译，0表示否，1表示是，默认0。仅第三方应用需要用到，企业自建应用可以忽略。
   * @param enableDuplicateCheck   是否开启重复消息检查，0表示否，1表示是，默认0
   * @param duplicateCheckInterval 是否重复消息检查的时间间隔，默认1800s，最大不超过4小时
   * @return the wx cp mini program notice message builder
   */
  public static WxCpMiniProgramNoticeMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  /**
   * Builder to user wx cp mini program notice message builder.
   *
   * @param toUser 指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。
   *               特殊情况：指定为”@all”，则向该企业应用的全部成员发送
   * @return the wx cp mini program notice message builder
   */
  public static WxCpMiniProgramNoticeMessageBuilder builderToUser(String toUser) {
    return _builder_().toUser(toUser);
  }

  /**
   * 发送给所有人
   *
   * @return the wx cp mini program notice message builder
   */
  public static WxCpMiniProgramNoticeMessageBuilder builderToAll() {
    return _builder_().toUser(ALL);
  }

  /**
   * 发送给部门
   *
   * @param toParty 指定接收消息的部门，部门ID列表，多个接收者用‘|’分隔，最多支持100个。
   *                当touser为”@all”时忽略本参数
   * @return the wx cp mini program notice message builder
   */
  public static WxCpMiniProgramNoticeMessageBuilder builderToParty(String toParty) {
    return _builder_().toParty(toParty);
  }

  /**
   * 发送给标签
   *
   * @param toTag 指定接收消息的标签，标签ID列表，多个接收者用‘|’分隔，最多支持100个。
   *              当touser为”@all”时忽略本参数
   * @return the wx cp mini program notice message builder
   */
  public static WxCpMiniProgramNoticeMessageBuilder builderToTag(String toTag) {
    return _builder_().toTag(toTag);
  }

  private static WxCpMiniProgramNoticeMessageBuilder _builder_() {
    return new WxCpMiniProgramNoticeMessageBuilder();
  }

  /**
   * Instantiates a new Wx cp mini program notice message.
   *
   * @param toUser                 指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。
   *                               特殊情况：指定为”@all”，则向该企业应用的全部成员发送
   * @param toParty                指定接收消息的部门，部门ID列表，多个接收者用‘|’分隔，最多支持100个。
   *                               当touser为”@all”时忽略本参数
   * @param toTag                  指定接收消息的标签，标签ID列表，多个接收者用‘|’分隔，最多支持100个。
   *                               当touser为”@all”时忽略本参数
   * @param agentId                企业应用的id，整型。企业内部开发，可在应用的设置页面查看；第三方服务商，可通过接口 获取企业授权信息 获取该参数值
   * @param safe                   是否是保密消息，0表示可对外分享，1表示不能分享且内容显示水印，默认为0
   * @param enableIdTrans          是否开启id转译，0表示否，1表示是，默认0。仅第三方应用需要用到，企业自建应用可以忽略。
   * @param enableDuplicateCheck   是否开启重复消息检查，0表示否，1表示是，默认0
   * @param duplicateCheckInterval 是否重复消息检查的时间间隔，默认1800s，最大不超过4小时
   * @param appId                  the app id
   * @param description            the description
   * @param emphasisFirstItem      the emphasis first item
   * @param page                   the page
   * @param title                  the title
   * @param contentItems           the content items
   */
  @Builder(builderMethodName = "_builder_")
  public WxCpMiniProgramNoticeMessage(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval, String appId, String description, boolean emphasisFirstItem, String page, String title, ContentItemItem... contentItems) {
    setMsgType("miniprogram_notice");
    init(toUser, toParty, toTag, agentId, safe, enableIdTrans, enableDuplicateCheck, duplicateCheckInterval);
    this.miniProgramNoticeMessage = new MiniProgramNoticeMessage(appId, description, emphasisFirstItem, page, title, contentItems);
  }

  /**
   * The type Mini program notice message.
   */
  @Data
  public static class MiniProgramNoticeMessage implements Serializable {

    private static final long serialVersionUID = -7038174221433580914L;

    @SerializedName("content_item")
    private List<ContentItemItem> contentItem;

    @SerializedName("appid")
    private String appId;

    @SerializedName("description")
    private String description;

    @SerializedName("emphasis_first_item")
    private boolean emphasisFirstItem;

    @SerializedName("page")
    private String page;

    @SerializedName("title")
    private String title;

    /**
     * Instantiates a new Mini program notice message.
     *
     * @param appId             the app id
     * @param description       the description
     * @param emphasisFirstItem the emphasis first item
     * @param page              the page
     * @param title             the title
     * @param contentItems      the content items
     */
    public MiniProgramNoticeMessage(String appId, String description, boolean emphasisFirstItem, String page, String title, ContentItemItem... contentItems) {

      this.appId = appId;
      this.description = description;
      this.emphasisFirstItem = emphasisFirstItem;
      this.page = page;
      this.title = title;
      if (contentItems != null) {
        this.contentItem = Arrays.asList(contentItems);
      }
    }
  }

  /**
   * The type Content item item.
   */
  @Data
  public static class ContentItemItem implements Serializable {

    private static final long serialVersionUID = 1367822169642792187L;

    @SerializedName("value")
    private String value;

    @SerializedName("key")
    private String key;

    /**
     * Instantiates a new Content item item.
     *
     * @param value the value
     * @param key   the key
     */
    public ContentItemItem(String value, String key) {
      this.value = value;
      this.key = key;
    }
  }

}

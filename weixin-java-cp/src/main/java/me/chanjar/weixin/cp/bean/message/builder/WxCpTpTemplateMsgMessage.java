package me.chanjar.weixin.cp.bean.message.builder;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * The type Wx cp tp template msg message.
 *
 * @author caiqy
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxCpTpTemplateMsgMessage extends WxCpMessage {

  private static final long serialVersionUID = -8123265658265283547L;

  /**
   * 此消息类型目前仅第三方应用支持，自建应用不支持。服务商需在管理端申请模版。接口传参的内容必须与申请的模版匹配；
   * 成员授权模式下，对于不在可见范围内的成员，第三方应用没有userid或open_userid，但可以通过传入合法且未过期的selected_ticket_list来推送模板消息，selected_ticket_list可通过返回ticket的选人接口获得。注意，管理员授权模式下，仅能给可见范围之内的成员推送消息，不在可见范围的成员将不能收到消息。
   * 对于应用可见范围内的成员，直接通过touser指定即可，无须传入selected_ticket_list；
   * https://work.weixin.qq.com/api/doc/90001/90144/94516
   */
  @SerializedName("selected_ticket_list")
  private List<String> selectedTicketList;

  @SerializedName("template_msg")
  private TemplateMsgMessage templateMsgMessage;

  /**
   * Builder wx cp tp template msg message builder.
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
   * @return the wx cp tp template msg message builder
   */
  public static WxCpTpTemplateMsgMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  /**
   * Builder to user wx cp tp template msg message builder.
   *
   * @param toUser 指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。
   *               特殊情况：指定为”@all”，则向该企业应用的全部成员发送
   * @return the wx cp tp template msg message builder
   */
  public static WxCpTpTemplateMsgMessageBuilder builderToUser(String toUser) {
    return _builder_().toUser(toUser);
  }

  /**
   * 发送给所有人
   *
   * @return the wx cp tp template msg message builder
   */
  public static WxCpTpTemplateMsgMessageBuilder builderToAll() {
    return _builder_().toUser(ALL);
  }

  /**
   * 发送给部门
   *
   * @param toParty 指定接收消息的部门，部门ID列表，多个接收者用‘|’分隔，最多支持100个。
   *                当touser为”@all”时忽略本参数
   * @return the wx cp tp template msg message builder
   */
  public static WxCpTpTemplateMsgMessageBuilder builderToParty(String toParty) {
    return _builder_().toParty(toParty);
  }

  /**
   * 发送给标签
   *
   * @param toTag 指定接收消息的标签，标签ID列表，多个接收者用‘|’分隔，最多支持100个。
   *              当touser为”@all”时忽略本参数
   * @return the wx cp tp template msg message builder
   */
  public static WxCpTpTemplateMsgMessageBuilder builderToTag(String toTag) {
    return _builder_().toTag(toTag);
  }

  private static WxCpTpTemplateMsgMessageBuilder _builder_() {
    return new WxCpTpTemplateMsgMessageBuilder();
  }

  /**
   * Instantiates a new Wx cp tp template msg message.
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
   * @param selectedTicketList     the selected ticket list
   * @param templateMsgMessage     the template msg message
   */
  @Builder(builderMethodName = "_builder_")
  public WxCpTpTemplateMsgMessage(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval, List<String> selectedTicketList, TemplateMsgMessage templateMsgMessage) {
    setMsgType("template_msg");
    init(toUser, toParty, toTag, agentId, safe, enableIdTrans, enableDuplicateCheck, duplicateCheckInterval);
    this.selectedTicketList = selectedTicketList;
    this.templateMsgMessage = templateMsgMessage;
  }

  /**
   * The type Template msg message.
   */
  @Data
  @NoArgsConstructor
  public static class TemplateMsgMessage implements Serializable {

    private static final long serialVersionUID = -1509122710020707743L;

    @SerializedName("url")
    private String url;

    @SerializedName("template_id")
    private String templateId;

    @SerializedName("content_item")
    private List<ContentItemItem> contentItem;

    /**
     * Instantiates a new Template msg message.
     *
     * @param url         the url
     * @param templateId  the template id
     * @param contentItem the content item
     */
    public TemplateMsgMessage(String url, String templateId, List<ContentItemItem> contentItem) {
      this.url = url;
      this.templateId = templateId;
      this.contentItem = contentItem;
    }

    /**
     * Instantiates a new Template msg message.
     *
     * @param url         the url
     * @param templateId  the template id
     * @param contentItem the content item
     */
    public TemplateMsgMessage(String url, String templateId, ContentItemItem... contentItem) {
      this.url = url;
      this.templateId = templateId;
      if (contentItem != null) {
        this.contentItem = Arrays.asList(contentItem);
      }
    }
  }

  /**
   * The type Content item item.
   */
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ContentItemItem implements Serializable {
    private static final long serialVersionUID = 4543832667368919817L;

    @SerializedName("key")
    private String key;

    @SerializedName("value")
    private String value;
  }
}

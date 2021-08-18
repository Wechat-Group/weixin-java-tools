package me.chanjar.weixin.cp.bean.message.builder;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;

import java.io.Serializable;
import java.util.List;

/**
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

  public static WxCpTpTemplateMsgMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  public static WxCpTpTemplateMsgMessageBuilder builderToUser(String toUser) {
    return _builder_().toUser(toUser);
  }

  public static WxCpTpTemplateMsgMessageBuilder builderToParty(String toParty) {
    return _builder_().toParty(toParty);
  }

  public static WxCpTpTemplateMsgMessageBuilder builderToTag(String toTag) {
    return _builder_().toTag(toTag);
  }

  private static WxCpTpTemplateMsgMessageBuilder _builder_() {
    return new WxCpTpTemplateMsgMessageBuilder();
  }

  @Builder(builderMethodName = "_builder_")
  public WxCpTpTemplateMsgMessage(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval, List<String> selectedTicketList, TemplateMsgMessage templateMsgMessage) {
    setTo(toUser, toParty, toTag);
    setMsgType("template_msg");
    setAgentId(agentId);
    setSafe(safe);
    setEnableIdTrans(enableIdTrans);
    setEnableDuplicateCheck(enableDuplicateCheck);
    setDuplicateCheckInterval(duplicateCheckInterval);
    this.selectedTicketList = selectedTicketList;
    this.templateMsgMessage = templateMsgMessage;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class TemplateMsgMessage implements Serializable {

    private static final long serialVersionUID = -1509122710020707743L;

    @SerializedName("content_item")
    private List<ContentItemItem> contentItem;

    @SerializedName("template_id")
    private String templateId;

    @SerializedName("url")
    private String url;

  }

  @Data
  public static class ContentItemItem implements Serializable {
    private static final long serialVersionUID = 4543832667368919817L;

    @SerializedName("key")
    private String key;

    @SerializedName("value")
    private String value;
  }
}

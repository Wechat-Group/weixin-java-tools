package me.chanjar.weixin.cp.bean.message.builder;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;

/**
 * @author caiqy
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxCpTemplateCardMessage extends WxCpMessage {

  private static final long serialVersionUID = -6243743821110199350L;

  @SerializedName("template_card")
  private AbstractTemplateCardMessage templateCardMessage;

  public static WxCpTemplateCardMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  public static WxCpTemplateCardMessageBuilder builderToUser(String toUser) {
    return _builder_().toUser(toUser);
  }

  public static WxCpTemplateCardMessageBuilder builderToParty(String toParty) {
    return _builder_().toParty(toParty);
  }

  public static WxCpTemplateCardMessageBuilder builderToTag(String toTag) {
    return _builder_().toTag(toTag);
  }

  private static WxCpTemplateCardMessageBuilder _builder_() {
    return new WxCpTemplateCardMessageBuilder();
  }

  @Builder(builderMethodName = "_builder_")
  public WxCpTemplateCardMessage(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval, AbstractTemplateCardMessage templateCardMessage) {
    setTo(toUser, toParty, toTag);
    setMsgType("template_card");
    setAgentId(agentId);
    setSafe(safe);
    setEnableIdTrans(enableIdTrans);
    setEnableDuplicateCheck(enableDuplicateCheck);
    setDuplicateCheckInterval(duplicateCheckInterval);
    this.templateCardMessage = templateCardMessage;
  }
}

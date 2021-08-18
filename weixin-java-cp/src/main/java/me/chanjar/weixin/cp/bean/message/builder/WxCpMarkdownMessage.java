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
public class WxCpMarkdownMessage extends WxCpMessage {

  private static final long serialVersionUID = -6243743821110199350L;

  @SerializedName("markdown")
  private MarkdownMessage markdownMessage;

  public static WxCpMarkdownMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  public static WxCpMarkdownMessageBuilder builderToUser(String toUser) {
    return _builder_().toUser(toUser);
  }

  public static WxCpMarkdownMessageBuilder builderToParty(String toParty) {
    return _builder_().toParty(toParty);
  }

  public static WxCpMarkdownMessageBuilder builderToTag(String toTag) {
    return _builder_().toTag(toTag);
  }

  private static WxCpMarkdownMessageBuilder _builder_() {
    return new WxCpMarkdownMessageBuilder();
  }

  @Builder(builderMethodName = "_builder_")
  public WxCpMarkdownMessage(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval, String content) {
    setTo(toUser, toParty, toTag);
    setMsgType("markdown");
    setAgentId(agentId);
    setSafe(safe);
    setEnableIdTrans(enableIdTrans);
    setEnableDuplicateCheck(enableDuplicateCheck);
    setDuplicateCheckInterval(duplicateCheckInterval);
    this.markdownMessage = new MarkdownMessage(content);
  }

  @Data
  public static class MarkdownMessage {

    @SerializedName("content")
    private String content;

    public MarkdownMessage(String content) {
      this.content = content;
    }
  }
}

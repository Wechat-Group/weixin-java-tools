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

  public static WxCpMarkdownMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  private static WxCpMarkdownMessageBuilder _builder_() {
    return new WxCpMarkdownMessageBuilder();
  }

  @Builder(builderMethodName = "_builder_")
  public WxCpMarkdownMessage(String toUser, String toParty, String toTag, String msgType, Integer agentId, String content) {
    setTo(toUser, toParty, toTag);
    setMsgType("markdown");
    setAgentId(agentId);
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

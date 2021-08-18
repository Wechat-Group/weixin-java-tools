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
public class WxCpTextMessage extends WxCpMessage {

  private static final long serialVersionUID = -6243743821110199350L;

  @SerializedName("content")
  private String content;

  public static WxCpTextMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  private static WxCpTextMessageBuilder _builder_() {
    return new WxCpTextMessageBuilder();
  }

  @Builder(builderMethodName = "_builder_")
  public WxCpTextMessage(String toUser, String toParty, String toTag, Integer agentId, String content) {
    setTo(toUser, toParty, toTag);
    setMsgType("content");
    setAgentId(agentId);
    this.content = content;
  }
}

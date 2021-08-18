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
public class WxCpFileMessage extends WxCpMessage {

  private static final long serialVersionUID = -6243743821110199350L;

  @SerializedName("file")
  private MediaMessage file;

  public static WxCpFileMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  private static WxCpFileMessageBuilder _builder() {
    return new WxCpFileMessageBuilder();
  }

  @Builder(builderMethodName = "_builder_")
  public WxCpFileMessage(String toUser, String toParty, String toTag, Integer agentId, String mediaId) {
    setTo(toUser, toParty, toTag);
    setMsgType("file");
    setAgentId(agentId);
    this.file = generateMediaMessage(mediaId);
  }
}

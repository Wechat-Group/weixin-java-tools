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
public class WxCpVideoMessage extends WxCpMessage {

  private static final long serialVersionUID = -6243743821110199350L;

  @SerializedName("video")
  private MediaMessage video;

  public static WxCpVideoMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  private static WxCpVideoMessageBuilder _builder_() {
    return new WxCpVideoMessageBuilder();
  }

  @Builder
  public WxCpVideoMessage(String toUser, String toParty, String toTag, String msgType, Integer agentId, String mediaId, String title, String description) {
    setTo(toUser, toParty, toTag);
    setMsgType("video");
    setAgentId(agentId);
    this.video = generateMediaMessage(mediaId, title, description);
  }
}

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
public class WxCpVoiceMessage extends WxCpMessage {

  private static final long serialVersionUID = -6243743821110199350L;

  @SerializedName("voice")
  private MediaMessage voice;

  public static WxCpVoiceMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  public static WxCpVoiceMessageBuilder builderToUser(String toUser) {
    return _builder_().toUser(toUser);
  }

  public static WxCpVoiceMessageBuilder builderToParty(String toParty) {
    return _builder_().toParty(toParty);
  }

  public static WxCpVoiceMessageBuilder builderToTag(String toTag) {
    return _builder_().toTag(toTag);
  }

  private static WxCpVoiceMessageBuilder _builder_() {
    return new WxCpVoiceMessageBuilder();
  }

  @Builder
  public WxCpVoiceMessage(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval, String mediaId) {
    setMsgType("voice");
    init(toUser, toParty, toTag, agentId, safe, enableIdTrans, enableDuplicateCheck, duplicateCheckInterval);
    this.voice = generateMediaMessage(mediaId);
  }
}

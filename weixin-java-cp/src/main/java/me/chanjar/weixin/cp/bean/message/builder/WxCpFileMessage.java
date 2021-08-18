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

  public static WxCpFileMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  public static WxCpFileMessageBuilder builderToUser(String toUser) {
    return _builder_().toUser(toUser);
  }

  public static WxCpFileMessageBuilder builderToParty(String toParty) {
    return _builder_().toParty(toParty);
  }

  public static WxCpFileMessageBuilder builderToTag(String toTag) {
    return _builder_().toTag(toTag);
  }

  private static WxCpFileMessageBuilder _builder_() {
    return new WxCpFileMessageBuilder();
  }

  @Builder(builderMethodName = "_builder_")
  public WxCpFileMessage(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval, String mediaId) {
    setTo(toUser, toParty, toTag);
    setMsgType("file");
    setAgentId(agentId);
    setSafe(safe);
    setEnableIdTrans(enableIdTrans);
    setEnableDuplicateCheck(enableDuplicateCheck);
    setDuplicateCheckInterval(duplicateCheckInterval);
    this.file = generateMediaMessage(mediaId);
  }
}

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
public class WxCpImageMessage extends WxCpMessage {

  private static final long serialVersionUID = -6243743821110199350L;

  @SerializedName("image")
  private MediaMessage image;

  public static WxCpImageMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  public static WxCpImageMessageBuilder builderToUser(String toUser) {
    return _builder_().toUser(toUser);
  }

  public static WxCpImageMessageBuilder builderToParty(String toParty) {
    return _builder_().toParty(toParty);
  }

  public static WxCpImageMessageBuilder builderToTag(String toTag) {
    return _builder_().toTag(toTag);
  }

  private static WxCpImageMessageBuilder _builder_() {
    return new WxCpImageMessageBuilder();
  }

  @Builder(builderMethodName = "_builder_")
  public WxCpImageMessage(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval, String mediaId) {
    setMsgType("image");
    init(toUser, toParty, toTag, agentId, safe, enableIdTrans, enableDuplicateCheck, duplicateCheckInterval);
    this.image = generateMediaMessage(mediaId);
  }
}

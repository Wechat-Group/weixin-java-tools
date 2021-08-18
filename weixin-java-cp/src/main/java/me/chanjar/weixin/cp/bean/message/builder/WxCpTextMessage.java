package me.chanjar.weixin.cp.bean.message.builder;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;

import java.io.Serializable;

/**
 * @author caiqy
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxCpTextMessage extends WxCpMessage {

  private static final long serialVersionUID = -6243743821110199350L;

  @SerializedName("text")
  private TextMessage textMessage;

  public static WxCpTextMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  public static WxCpTextMessageBuilder builderToUser(String toUser) {
    return _builder_().toUser(toUser);
  }

  public static WxCpTextMessageBuilder builderToParty(String toParty) {
    return _builder_().toParty(toParty);
  }

  public static WxCpTextMessageBuilder builderToTag(String toTag) {
    return _builder_().toTag(toTag);
  }

  private static WxCpTextMessageBuilder _builder_() {
    return new WxCpTextMessageBuilder();
  }

  @Builder(builderMethodName = "_builder_")
  public WxCpTextMessage(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval, String content) {
    setMsgType("text");
    init(toUser, toParty, toTag, agentId, safe, enableIdTrans, enableDuplicateCheck, duplicateCheckInterval);
    this.textMessage = new TextMessage(content);
  }

  @Data
  public static class TextMessage implements Serializable {

    private static final long serialVersionUID = -5240136299263024809L;

    @SerializedName("content")
    private String content;

    public TextMessage(String content) {
      this.content = content;
    }
  }

}

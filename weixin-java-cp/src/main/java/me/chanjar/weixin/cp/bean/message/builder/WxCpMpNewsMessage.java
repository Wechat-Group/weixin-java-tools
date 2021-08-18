package me.chanjar.weixin.cp.bean.message.builder;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author caiqy
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxCpMpNewsMessage extends WxCpMessage {

  private static final long serialVersionUID = -6243743821110199350L;

  @SerializedName("mpnews")
  private MpNewsMessageWrap mpNewsMessageWrap;

  public static WxCpMpNewsMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  public static WxCpMpNewsMessageBuilder builderToUser(String toUser) {
    return _builder_().toUser(toUser);
  }

  public static WxCpMpNewsMessageBuilder builderToParty(String toParty) {
    return _builder_().toParty(toParty);
  }

  public static WxCpMpNewsMessageBuilder builderToTag(String toTag) {
    return _builder_().toTag(toTag);
  }

  private static WxCpMpNewsMessageBuilder _builder_() {
    return new WxCpMpNewsMessageBuilder();
  }

  @Builder(builderMethodName = "_builder_")
  public WxCpMpNewsMessage(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval, MpNewsMessage... mpNewsMessages) {
    setTo(toUser, toParty, toTag);
    setMsgType("mpnews");
    setAgentId(agentId);
    setSafe(safe);
    setEnableIdTrans(enableIdTrans);
    setEnableDuplicateCheck(enableDuplicateCheck);
    setDuplicateCheckInterval(duplicateCheckInterval);
    this.mpNewsMessageWrap = new MpNewsMessageWrap(mpNewsMessages);
  }

  @Data
  public static class MpNewsMessageWrap implements Serializable {

    private static final long serialVersionUID = -211238111370772820L;

    @SerializedName("articles")
    private List<MpNewsMessage> mpNewsMessages;

    public MpNewsMessageWrap(MpNewsMessage... mpNewsMessages) {
      if (mpNewsMessages != null) {
        this.mpNewsMessages = Arrays.asList(mpNewsMessages);
      }
    }
  }

  @Data
  @Builder
  public static class MpNewsMessage implements Serializable {

    private static final long serialVersionUID = 6391986108989165301L;

    @SerializedName("title")
    private String title;

    @SerializedName("thumb_media_id")
    private String thumbMediaId;

    @SerializedName("author")
    private String author;

    @SerializedName("content_source_url")
    private String contentSourceUrl;

    @SerializedName("content")
    private String content;

    @SerializedName("digest")
    private String digest;
  }
}

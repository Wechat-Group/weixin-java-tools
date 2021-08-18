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

  public static WxCpMpNewsMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  private static WxCpMpNewsMessageBuilder _builder_() {
    return new WxCpMpNewsMessageBuilder();
  }

  @Builder(builderMethodName = "_builder_")
  public WxCpMpNewsMessage(String toUser, String toParty, String toTag, String msgType, Integer agentId, MpNewsMessage... mpNewsMessages) {
    setTo(toUser, toParty, toTag);
    setMsgType("mpnews");
    setAgentId(agentId);
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

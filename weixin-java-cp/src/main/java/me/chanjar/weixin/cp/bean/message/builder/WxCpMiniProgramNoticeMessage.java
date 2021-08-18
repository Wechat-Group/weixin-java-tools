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
public class WxCpMiniProgramNoticeMessage extends WxCpMessage {

  private static final long serialVersionUID = -6243743821110199350L;

  @SerializedName("miniprogram_notice")
  private MiniProgramNoticeMessage miniProgramNoticeMessage;

  public static WxCpMiniProgramNoticeMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  public static WxCpMiniProgramNoticeMessageBuilder builderToUser(String toUser) {
    return _builder_().toUser(toUser);
  }

  public static WxCpMiniProgramNoticeMessageBuilder builderToParty(String toParty) {
    return _builder_().toParty(toParty);
  }

  public static WxCpMiniProgramNoticeMessageBuilder builderToTag(String toTag) {
    return _builder_().toTag(toTag);
  }

  private static WxCpMiniProgramNoticeMessageBuilder _builder_() {
    return new WxCpMiniProgramNoticeMessageBuilder();
  }

  @Builder(builderMethodName = "_builder_")
  public WxCpMiniProgramNoticeMessage(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval, String appId, String description, boolean emphasisFirstItem, String page, String title, ContentItemItem... contentItems) {
    setMsgType("miniprogram_notice");
    init(toUser, toParty, toTag, agentId, safe, enableIdTrans, enableDuplicateCheck, duplicateCheckInterval);
    this.miniProgramNoticeMessage = new MiniProgramNoticeMessage(appId, description, emphasisFirstItem, page, title, contentItems);
  }

  @Data
  public static class MiniProgramNoticeMessage implements Serializable {

    private static final long serialVersionUID = -7038174221433580914L;

    @SerializedName("content_item")
    private List<ContentItemItem> contentItem;

    @SerializedName("appid")
    private String appId;

    @SerializedName("description")
    private String description;

    @SerializedName("emphasis_first_item")
    private boolean emphasisFirstItem;

    @SerializedName("page")
    private String page;

    @SerializedName("title")
    private String title;

    public MiniProgramNoticeMessage(String appId, String description, boolean emphasisFirstItem, String page, String title, ContentItemItem... contentItems) {

      this.appId = appId;
      this.description = description;
      this.emphasisFirstItem = emphasisFirstItem;
      this.page = page;
      this.title = title;
      if (contentItems != null) {
        this.contentItem = Arrays.asList(contentItems);
      }
    }
  }

  @Data
  public static class ContentItemItem implements Serializable {

    private static final long serialVersionUID = 1367822169642792187L;

    @SerializedName("value")
    private String value;

    @SerializedName("key")
    private String key;

    public ContentItemItem(String value, String key) {
      this.value = value;
      this.key = key;
    }
  }

}

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
public class WxCpNewsMessage extends WxCpMessage {

  private static final long serialVersionUID = -6243743821110199350L;

  @SerializedName("news")
  private NewsMessageWrap newsMessageWrap;

  public static WxCpNewsMessageBuilder builder(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval) {
    return _builder_().toUser(toUser).toParty(toParty).toTag(toTag).agentId(agentId);
  }

  public static WxCpNewsMessageBuilder builderToUser(String toUser) {
    return _builder_().toUser(toUser);
  }

  public static WxCpNewsMessageBuilder builderToParty(String toParty) {
    return _builder_().toParty(toParty);
  }

  public static WxCpNewsMessageBuilder builderToTag(String toTag) {
    return _builder_().toTag(toTag);
  }

  private static WxCpNewsMessageBuilder _builder_() {
    return new WxCpNewsMessageBuilder();
  }

  @Builder(builderMethodName = "_builder_")
  public WxCpNewsMessage(String toUser, String toParty, String toTag, Integer agentId, Integer safe, Integer enableIdTrans, Integer enableDuplicateCheck, Integer duplicateCheckInterval, NewsMessage... newsMessages) {
    setMsgType("news");
    init(toUser, toParty, toTag, agentId, safe, enableIdTrans, enableDuplicateCheck, duplicateCheckInterval);
    this.newsMessageWrap = new NewsMessageWrap(newsMessages);
  }

  @Data
  public static class NewsMessageWrap implements Serializable {

    private static final long serialVersionUID = -211238111370772820L;

    @SerializedName("articles")
    private List<NewsMessage> newsMessageList;

    public NewsMessageWrap(NewsMessage... newsMessageList) {
      if (newsMessageList != null) {
        this.newsMessageList = Arrays.asList(newsMessageList);
      }
    }
  }

  @Data
  public static class NewsMessage implements Serializable {

    private static final long serialVersionUID = -6288061856098371839L;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("url")
    private String url;

    @SerializedName("picurl")
    private String picUrl;

    @SerializedName("appid")
    private String appId;

    @SerializedName("pagepath")
    private String pagePath;

    public NewsMessage(String title, String description, String url, String picUrl, String appId, String pagePath) {
      this.title = title;
      this.description = description;
      this.url = url;
      this.picUrl = picUrl;
      this.appId = appId;
      this.pagePath = pagePath;
    }
  }
}

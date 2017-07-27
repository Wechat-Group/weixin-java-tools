package me.chanjar.weixin.mp.bean.result;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.common.util.json.WxBooleanTypeAdapter;
import me.chanjar.weixin.common.util.json.WxDateTypeAdapter;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.util.Date;
import java.util.List;

/**
 * <pre>
 * 公众号的自动回复规则
 * Created by Binary Wang on 2017-7-8.
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * </pre>
 */
public class WxMpCurrentAutoReplyInfo {
  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public static WxMpCurrentAutoReplyInfo fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpCurrentAutoReplyInfo.class);
  }

  @SerializedName("is_add_friend_reply_open")
  @JsonAdapter(WxBooleanTypeAdapter.class)
  private Boolean isAddFriendReplyOpen;

  @SerializedName("is_autoreply_open")
  @JsonAdapter(WxBooleanTypeAdapter.class)
  private Boolean isAutoReplyOpen;

  @SerializedName("add_friend_autoreply_info")
  private AutoReplyInfo addFriendAutoReplyInfo;

  @SerializedName("message_default_autoreply_info")
  private AutoReplyInfo messageDefaultAutoReplyInfo;

  @SerializedName("keyword_autoreply_info")
  private KeywordAutoReplyInfo keywordAutoReplyInfo;

  public Boolean getAddFriendReplyOpen() {
    return this.isAddFriendReplyOpen;
  }

  public void setAddFriendReplyOpen(Boolean addFriendReplyOpen) {
    isAddFriendReplyOpen = addFriendReplyOpen;
  }

  public Boolean getAutoReplyOpen() {
    return this.isAutoReplyOpen;
  }

  public void setAutoReplyOpen(Boolean autoReplyOpen) {
    isAutoReplyOpen = autoReplyOpen;
  }

  public AutoReplyInfo getAddFriendAutoReplyInfo() {
    return this.addFriendAutoReplyInfo;
  }

  public void setAddFriendAutoReplyInfo(AutoReplyInfo addFriendAutoReplyInfo) {
    this.addFriendAutoReplyInfo = addFriendAutoReplyInfo;
  }

  public AutoReplyInfo getMessageDefaultAutoReplyInfo() {
    return this.messageDefaultAutoReplyInfo;
  }

  public void setMessageDefaultAutoReplyInfo(AutoReplyInfo messageDefaultAutoReplyInfo) {
    this.messageDefaultAutoReplyInfo = messageDefaultAutoReplyInfo;
  }

  public KeywordAutoReplyInfo getKeywordAutoReplyInfo() {
    return this.keywordAutoReplyInfo;
  }

  public void setKeywordAutoReplyInfo(KeywordAutoReplyInfo keywordAutoReplyInfo) {
    this.keywordAutoReplyInfo = keywordAutoReplyInfo;
  }

  public static class AutoReplyRule {
    @Override
    public String toString() {
      return ToStringUtils.toSimpleString(this);
    }

    @SerializedName("rule_name")
    private String ruleName;

    @SerializedName("create_time")
    @JsonAdapter(WxDateTypeAdapter.class)
    private Date createTime;

    @SerializedName("reply_mode")
    private String replyMode;

    @SerializedName("keyword_list_info")
    private List<KeywordInfo> keywordListInfo;

    @SerializedName("reply_list_info")
    private List<ReplyInfo> replyListInfo;

    public String getRuleName() {
      return this.ruleName;
    }

    public void setRuleName(String ruleName) {
      this.ruleName = ruleName;
    }

    public Date getCreateTime() {
      return this.createTime;
    }

    public void setCreateTime(Date createTime) {
      this.createTime = createTime;
    }

    public String getReplyMode() {
      return this.replyMode;
    }

    public void setReplyMode(String replyMode) {
      this.replyMode = replyMode;
    }

    public List<KeywordInfo> getKeywordListInfo() {
      return this.keywordListInfo;
    }

    public void setKeywordListInfo(List<KeywordInfo> keywordListInfo) {
      this.keywordListInfo = keywordListInfo;
    }

    public List<ReplyInfo> getReplyListInfo() {
      return this.replyListInfo;
    }

    public void setReplyListInfo(List<ReplyInfo> replyListInfo) {
      this.replyListInfo = replyListInfo;
    }
  }

  public static class ReplyInfo {
    @Override
    public String toString() {
      return ToStringUtils.toSimpleString(this);
    }

    private String type;
    private String content;

    @SerializedName("news_info")
    private NewsInfo newsInfo;

    public String getType() {
      return this.type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getContent() {
      return this.content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public NewsInfo getNewsInfo() {
      return this.newsInfo;
    }

    public void setNewsInfo(NewsInfo newsInfo) {
      this.newsInfo = newsInfo;
    }
  }

  public static class NewsInfo {
    @Override
    public String toString() {
      return ToStringUtils.toSimpleString(this);
    }

    private List<NewsItem> list;

    public List<NewsItem> getList() {
      return this.list;
    }

    public void setList(List<NewsItem> list) {
      this.list = list;
    }
  }

  public static class NewsItem {
    @Override
    public String toString() {
      return ToStringUtils.toSimpleString(this);
    }

    @SerializedName("cover_url")
    private String coverUrl;
    private String author;
    @SerializedName("content_url")
    private String contentUrl;
    private String digest;
    @SerializedName("show_cover")
    @JsonAdapter(WxBooleanTypeAdapter.class)
    private Boolean showCover;
    @SerializedName("source_url")
    private String sourceUrl;
    private String title;

    public String getCoverUrl() {
      return this.coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
      this.coverUrl = coverUrl;
    }

    public String getAuthor() {
      return this.author;
    }

    public void setAuthor(String author) {
      this.author = author;
    }

    public String getContentUrl() {
      return this.contentUrl;
    }

    public void setContentUrl(String contentUrl) {
      this.contentUrl = contentUrl;
    }

    public String getDigest() {
      return this.digest;
    }

    public void setDigest(String digest) {
      this.digest = digest;
    }

    public Boolean getShowCover() {
      return this.showCover;
    }

    public void setShowCover(Boolean showCover) {
      this.showCover = showCover;
    }

    public String getSourceUrl() {
      return this.sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
      this.sourceUrl = sourceUrl;
    }

    public String getTitle() {
      return this.title;
    }

    public void setTitle(String title) {
      this.title = title;
    }
  }

  public static class KeywordInfo {
    @Override
    public String toString() {
      return ToStringUtils.toSimpleString(this);
    }

    private String type;
    @SerializedName("match_mode")
    private String matchMode;
    private String content;

    public String getType() {
      return this.type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getMatchMode() {
      return this.matchMode;
    }

    public void setMatchMode(String matchMode) {
      this.matchMode = matchMode;
    }

    public String getContent() {
      return this.content;
    }

    public void setContent(String content) {
      this.content = content;
    }
  }

  public static class KeywordAutoReplyInfo {
    @Override
    public String toString() {
      return ToStringUtils.toSimpleString(this);
    }

    private List<AutoReplyRule> list;

    public List<AutoReplyRule> getList() {
      return this.list;
    }

    public void setList(List<AutoReplyRule> list) {
      this.list = list;
    }
  }

  public static class AutoReplyInfo {
    @Override
    public String toString() {
      return ToStringUtils.toSimpleString(this);
    }

    private String type;
    private String content;

    public String getType() {
      return this.type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getContent() {
      return this.content;
    }

    public void setContent(String content) {
      this.content = content;
    }
  }

}

package me.chanjar.weixin.cp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Data;
import me.chanjar.weixin.common.api.WxConsts.KefuMsgType;
import me.chanjar.weixin.cp.bean.article.MpnewsArticle;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.bean.messagebuilder.FileBuilder;
import me.chanjar.weixin.cp.bean.messagebuilder.ImageBuilder;
import me.chanjar.weixin.cp.bean.messagebuilder.MarkdownMsgBuilder;
import me.chanjar.weixin.cp.bean.messagebuilder.MpnewsBuilder;
import me.chanjar.weixin.cp.bean.messagebuilder.NewsBuilder;
import me.chanjar.weixin.cp.bean.messagebuilder.TextBuilder;
import me.chanjar.weixin.cp.bean.messagebuilder.TextCardBuilder;
import me.chanjar.weixin.cp.bean.messagebuilder.VideoBuilder;
import me.chanjar.weixin.cp.bean.messagebuilder.VoiceBuilder;

/**
 * 消息.
 *
 * @author Daniel Qian
 */
@Data
public class WxCpMessage implements Serializable {
  private static final long serialVersionUID = -2082278303476631708L;

  private String toUser;
  private String toParty;
  private String toTag;
  private Integer agentId;
  private String msgType;
  private String content;
  private String mediaId;
  private String thumbMediaId;
  private String title;
  private String description;
  private String musicUrl;
  private String hqMusicUrl;
  private String safe;
  private String url;
  private String btnTxt;
  private List<NewArticle> articles = new ArrayList<>();
  private List<MpnewsArticle> mpnewsArticles = new ArrayList<>();

  /**
   * 获得文本消息builder.
   */
  public static TextBuilder TEXT() {
    return new TextBuilder();
  }

  /**
   * 获得文本卡片消息builder.
   */
  public static TextCardBuilder TEXTCARD() {
    return new TextCardBuilder();
  }

  /**
   * 获得图片消息builder.
   */
  public static ImageBuilder IMAGE() {
    return new ImageBuilder();
  }

  /**
   * 获得语音消息builder.
   */
  public static VoiceBuilder VOICE() {
    return new VoiceBuilder();
  }

  /**
   * 获得视频消息builder.
   */
  public static VideoBuilder VIDEO() {
    return new VideoBuilder();
  }

  /**
   * 获得图文消息builder.
   */
  public static NewsBuilder NEWS() {
    return new NewsBuilder();
  }

  /**
   * 获得mpnews图文消息builder.
   */
  public static MpnewsBuilder MPNEWS() {
    return new MpnewsBuilder();
  }

  /**
   * 获得markdown消息builder.
   */
  public static MarkdownMsgBuilder MARKDOWN() {
    return new MarkdownMsgBuilder();
  }

  /**
   * 获得文件消息builder.
   */
  public static FileBuilder FILE() {
    return new FileBuilder();
  }


  /**
   * <pre>
   * 请使用
   * {@link KefuMsgType#TEXT}
   * {@link KefuMsgType#IMAGE}
   * {@link KefuMsgType#VOICE}
   * {@link KefuMsgType#MUSIC}
   * {@link KefuMsgType#VIDEO}
   * {@link KefuMsgType#NEWS}
   * {@link KefuMsgType#MPNEWS}
   * {@link KefuMsgType#MARKDOWN}
   * </pre>
   *
   * @param msgType 消息类型
   */
  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public String toJson() {
    JsonObject messageJson = new JsonObject();
    if (this.getAgentId() != null) {
      messageJson.addProperty("agentid", this.getAgentId());
    }

    if (StringUtils.isNotBlank(this.getToUser())) {
      messageJson.addProperty("touser", this.getToUser());
    }

    messageJson.addProperty("msgtype", this.getMsgType());

    if (StringUtils.isNotBlank(this.getToParty())) {
      messageJson.addProperty("toparty", this.getToParty());
    }

    if (StringUtils.isNotBlank(this.getToTag())) {
      messageJson.addProperty("totag", this.getToTag());
    }

    this.handleMsgType(messageJson);

    if (StringUtils.isNotBlank(this.getSafe())) {
      messageJson.addProperty("safe", this.getSafe());
    }

    return messageJson.toString();
  }

  private void handleMsgType(JsonObject messageJson) {
    switch (this.getMsgType()) {
      case KefuMsgType.TEXT: {
        JsonObject text = new JsonObject();
        text.addProperty("content", this.getContent());
        messageJson.add("text", text);
        break;
      }
      case KefuMsgType.MARKDOWN: {
        JsonObject text = new JsonObject();
        text.addProperty("content", this.getContent());
        messageJson.add("markdown", text);
        break;
      }
      case KefuMsgType.TEXTCARD: {
        JsonObject text = new JsonObject();
        text.addProperty("title", this.getTitle());
        text.addProperty("description", this.getDescription());
        text.addProperty("url", this.getUrl());
        text.addProperty("btntxt", this.getBtnTxt());
        messageJson.add("textcard", text);
        break;
      }
      case KefuMsgType.IMAGE: {
        JsonObject image = new JsonObject();
        image.addProperty("media_id", this.getMediaId());
        messageJson.add("image", image);
        break;
      }
      case KefuMsgType.FILE: {
        JsonObject image = new JsonObject();
        image.addProperty("media_id", this.getMediaId());
        messageJson.add("file", image);
        break;
      }
      case KefuMsgType.VOICE: {
        JsonObject voice = new JsonObject();
        voice.addProperty("media_id", this.getMediaId());
        messageJson.add("voice", voice);
        break;
      }
      case KefuMsgType.VIDEO: {
        JsonObject video = new JsonObject();
        video.addProperty("media_id", this.getMediaId());
        video.addProperty("thumb_media_id", this.getThumbMediaId());
        video.addProperty("title", this.getTitle());
        video.addProperty("description", this.getDescription());
        messageJson.add("video", video);
        break;
      }
      case KefuMsgType.NEWS: {
        JsonObject newsJsonObject = new JsonObject();
        JsonArray articleJsonArray = new JsonArray();
        for (NewArticle article : this.getArticles()) {
          JsonObject articleJson = new JsonObject();
          articleJson.addProperty("title", article.getTitle());
          articleJson.addProperty("description", article.getDescription());
          articleJson.addProperty("url", article.getUrl());
          articleJson.addProperty("picurl", article.getPicUrl());
          articleJsonArray.add(articleJson);
        }
        newsJsonObject.add("articles", articleJsonArray);
        messageJson.add("news", newsJsonObject);
        break;
      }
      case KefuMsgType.MPNEWS: {
        JsonObject newsJsonObject = new JsonObject();
        if (this.getMediaId() != null) {
          newsJsonObject.addProperty("media_id", this.getMediaId());
        } else {
          JsonArray articleJsonArray = new JsonArray();
          for (MpnewsArticle article : this.getMpnewsArticles()) {
            JsonObject articleJson = new JsonObject();
            articleJson.addProperty("title", article.getTitle());
            articleJson.addProperty("thumb_media_id", article.getThumbMediaId());
            articleJson.addProperty("author", article.getAuthor());
            articleJson.addProperty("content_source_url", article.getContentSourceUrl());
            articleJson.addProperty("content", article.getContent());
            articleJson.addProperty("digest", article.getDigest());
            articleJson.addProperty("show_cover_pic", article.getShowCoverPic());
            articleJsonArray.add(articleJson);
          }

          newsJsonObject.add("articles", articleJsonArray);
        }
        messageJson.add("mpnews", newsJsonObject);
        break;
      }
      default: {
        // do nothing
      }
    }
  }

}

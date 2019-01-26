package me.chanjar.weixin.cp.bean;

import java.io.Serializable;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.WxCpConsts;
import me.chanjar.weixin.cp.bean.article.MpnewsArticle;
import me.chanjar.weixin.cp.bean.article.NewArticle;

/**
 * <pre>
 * 应用推送消息
 * Created by Binary Wang on 2019/1/26.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpAppChatMessage implements Serializable {
  private static final long serialVersionUID = -5469013416372240229L;

  private String msgType;
  private String content;
  private String chatId;
  private String mediaId;
  private String title;
  private String description;
  private Boolean safe;
  private String url;
  private String btnTxt;
  private List<NewArticle> articles;
  private List<MpnewsArticle> mpnewsArticles;

  /**
   * 构建文本消息.
   */
  public static WxCpAppChatMessage buildTextMsg(String chatId, String content, boolean safe) {
    final WxCpAppChatMessage message = new WxCpAppChatMessage();
    message.setMsgType(WxCpConsts.AppChatMsgType.TEXT);
    message.setContent(content);
    message.setChatId(chatId);
    message.setSafe(safe);
    return message;
  }

  /**
   * 生成json字符串.
   */
  public String toJson() {
    JsonObject messageJson = new JsonObject();
    messageJson.addProperty("msgtype", this.getMsgType());
    messageJson.addProperty("chatid", this.getChatId());

    if (WxConsts.KefuMsgType.TEXT.equals(this.getMsgType())) {
      JsonObject text = new JsonObject();
      text.addProperty("content", this.getContent());
      messageJson.add("text", text);
    }

    if (WxConsts.KefuMsgType.MARKDOWN.equals(this.getMsgType())) {
      JsonObject text = new JsonObject();
      text.addProperty("content", this.getContent());
      messageJson.add("markdown", text);
    }

    if (WxConsts.KefuMsgType.TEXTCARD.equals(this.getMsgType())) {
      JsonObject text = new JsonObject();
      text.addProperty("title", this.getTitle());
      text.addProperty("description", this.getDescription());
      text.addProperty("url", this.getUrl());
      text.addProperty("btntxt", this.getBtnTxt());
      messageJson.add("textcard", text);
    }

    if (WxConsts.KefuMsgType.IMAGE.equals(this.getMsgType())) {
      JsonObject image = new JsonObject();
      image.addProperty("media_id", this.getMediaId());
      messageJson.add("image", image);
    }

    if (WxConsts.KefuMsgType.FILE.equals(this.getMsgType())) {
      JsonObject image = new JsonObject();
      image.addProperty("media_id", this.getMediaId());
      messageJson.add("file", image);
    }

    if (WxConsts.KefuMsgType.VOICE.equals(this.getMsgType())) {
      JsonObject voice = new JsonObject();
      voice.addProperty("media_id", this.getMediaId());
      messageJson.add("voice", voice);
    }

    if (this.getSafe() != null && this.getSafe()) {
      messageJson.addProperty("safe", 1);
    }

    if (WxConsts.KefuMsgType.VIDEO.equals(this.getMsgType())) {
      JsonObject video = new JsonObject();
      video.addProperty("media_id", this.getMediaId());
      video.addProperty("title", this.getTitle());
      video.addProperty("description", this.getDescription());
      messageJson.add("video", video);
    }

    if (WxConsts.KefuMsgType.NEWS.equals(this.getMsgType())) {
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
    }

    if (WxConsts.KefuMsgType.MPNEWS.equals(this.getMsgType())) {
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
          articleJsonArray.add(articleJson);
        }

        newsJsonObject.add("articles", articleJsonArray);
      }
      messageJson.add("mpnews", newsJsonObject);
    }

    return messageJson.toString();
  }
}

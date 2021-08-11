package me.chanjar.weixin.cp.bean.message;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.article.MpnewsArticle;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import me.chanjar.weixin.cp.constant.WxCpConsts.AppChatMsgType;

import java.io.Serializable;
import java.util.List;

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

  /**
   * 消息类型
   */
  private String msgType;
  /**
   * 消息内容
   */
  private String content;
  /**
   * 群聊id
   */
  private String chatId;
  /**
   * 图片媒体文件id，可以调用上传临时素材接口获取
   */
  private String mediaId;
  /**
   * 视频消息的标题，不超过128个字节，超过会自动截断
   */
  private String title;
  /**
   * 视频消息的描述，不超过512个字节，超过会自动截断
   */
  private String description;
  /**
   * 表示是否是保密消息
   */
  private Boolean safe;
  /**
   * 点击后跳转的链接。
   */
  private String url;
  /**
   * 按钮文字。 默认为“详情”， 不超过4个文字，超过自动截断。
   */
  private String btnTxt;
  /**
   * 图文消息，一个图文消息支持1到8条图文
   */
  private List<NewArticle> articles;
  /**
   * Mpnews图文消息，一个图文消息支持1到8条图文
   */
  private List<MpnewsArticle> mpnewsArticles;

  /**
   * 构建文本消息.
   */
  public static WxCpAppChatMessage buildTextMsg(String chatId, String content, boolean safe) {
    final WxCpAppChatMessage message = new WxCpAppChatMessage();
    message.setMsgType(AppChatMsgType.TEXT.getName());
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

    if (this.getSafe() != null && this.getSafe()) {
      messageJson.addProperty("safe", 1);
    }

    this.handleMsgType(messageJson);

    return messageJson.toString();
  }

  private void handleMsgType(JsonObject messageJson) {
    AppChatMsgType type = AppChatMsgType.getType(this.getMsgType());
    if (type == null) {
      return;
    }
    switch (type) {
      case TEXT: {
        JsonObject text = new JsonObject();
        text.addProperty("content", this.getContent());
        messageJson.add("text", text);
        break;
      }
      case MARKDOWN: {
        JsonObject text = new JsonObject();
        text.addProperty("content", this.getContent());
        messageJson.add("markdown", text);
        break;
      }
      case TEXTCARD: {
        JsonObject text = new JsonObject();
        text.addProperty("title", this.getTitle());
        text.addProperty("description", this.getDescription());
        text.addProperty("url", this.getUrl());
        text.addProperty("btntxt", this.getBtnTxt());
        messageJson.add("textcard", text);
        break;
      }
      case IMAGE: {
        JsonObject image = new JsonObject();
        image.addProperty("media_id", this.getMediaId());
        messageJson.add("image", image);
        break;
      }
      case FILE: {
        JsonObject image = new JsonObject();
        image.addProperty("media_id", this.getMediaId());
        messageJson.add("file", image);
        break;
      }
      case VOICE: {
        JsonObject voice = new JsonObject();
        voice.addProperty("media_id", this.getMediaId());
        messageJson.add("voice", voice);
        break;
      }
      case VIDEO: {
        JsonObject video = new JsonObject();
        video.addProperty("media_id", this.getMediaId());
        video.addProperty("title", this.getTitle());
        video.addProperty("description", this.getDescription());
        messageJson.add("video", video);
        break;
      }
      case NEWS: {
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
      case MPNEWS: {
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
        break;
      }
      default: {
        //do nothing
      }
    }
  }
}

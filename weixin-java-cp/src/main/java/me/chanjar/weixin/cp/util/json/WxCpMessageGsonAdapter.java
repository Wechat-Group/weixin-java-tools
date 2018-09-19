/*
 * KINGSTAR MEDIA SOLUTIONS Co.,LTD. Copyright c 2005-2013. All rights reserved.
 *
 * This source code is the property of KINGSTAR MEDIA SOLUTIONS LTD. It is intended
 * only for the use of KINGSTAR MEDIA application development. Reengineering, reproduction
 * arose from modification of the original source, or other redistribution of this source
 * is not permitted without written permission of the KINGSTAR MEDIA SOLUTIONS LTD.
 */
package me.chanjar.weixin.cp.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.WxCpMessage;
import me.chanjar.weixin.cp.bean.article.MpnewsArticle;
import me.chanjar.weixin.cp.bean.article.NewArticle;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

/**
 * @author Daniel Qian
 */
public class WxCpMessageGsonAdapter implements JsonSerializer<WxCpMessage> {

  @Override
  public JsonElement serialize(WxCpMessage message, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject messageJson = new JsonObject();
    messageJson.addProperty("agentid", message.getAgentId());
    if (StringUtils.isNotBlank(message.getToUser())) {
      messageJson.addProperty("touser", message.getToUser());
    }
    messageJson.addProperty("msgtype", message.getMsgType());

    if (StringUtils.isNotBlank(message.getToParty())) {
      messageJson.addProperty("toparty", message.getToParty());
    }
    if (StringUtils.isNotBlank(message.getToTag())) {
      messageJson.addProperty("totag", message.getToTag());
    }
    if (WxConsts.KefuMsgType.TEXT.equals(message.getMsgType())) {
      JsonObject text = new JsonObject();
      text.addProperty("content", message.getContent());
      messageJson.add("text", text);
    }

    if (WxConsts.KefuMsgType.TEXTCARD.equals(message.getMsgType())) {
      JsonObject text = new JsonObject();
      text.addProperty("title", message.getTitle());
      text.addProperty("description", message.getDescription());
      text.addProperty("url", message.getUrl());
      text.addProperty("btntxt", message.getBtnTxt());
      messageJson.add("textcard", text);
    }

    if (WxConsts.KefuMsgType.IMAGE.equals(message.getMsgType())) {
      JsonObject image = new JsonObject();
      image.addProperty("media_id", message.getMediaId());
      messageJson.add("image", image);
    }

    if (WxConsts.KefuMsgType.FILE.equals(message.getMsgType())) {
      JsonObject image = new JsonObject();
      image.addProperty("media_id", message.getMediaId());
      messageJson.add("file", image);
    }

    if (WxConsts.KefuMsgType.VOICE.equals(message.getMsgType())) {
      JsonObject voice = new JsonObject();
      voice.addProperty("media_id", message.getMediaId());
      messageJson.add("voice", voice);
    }

    if (StringUtils.isNotBlank(message.getSafe())) {
      messageJson.addProperty("safe", message.getSafe());
    }

    if (WxConsts.KefuMsgType.VIDEO.equals(message.getMsgType())) {
      JsonObject video = new JsonObject();
      video.addProperty("media_id", message.getMediaId());
      video.addProperty("thumb_media_id", message.getThumbMediaId());
      video.addProperty("title", message.getTitle());
      video.addProperty("description", message.getDescription());
      messageJson.add("video", video);
    }

    if (WxConsts.KefuMsgType.NEWS.equals(message.getMsgType())) {
      JsonObject newsJsonObject = new JsonObject();
      JsonArray articleJsonArray = new JsonArray();
      for (NewArticle article : message.getArticles()) {
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

    if (WxConsts.KefuMsgType.MPNEWS.equals(message.getMsgType())) {
      JsonObject newsJsonObject = new JsonObject();
      if (message.getMediaId() != null) {
        newsJsonObject.addProperty("media_id", message.getMediaId());
      } else {
        JsonArray articleJsonArray = new JsonArray();
        for (MpnewsArticle article : message.getMpnewsArticles()) {
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
    }

    return messageJson;
  }

}

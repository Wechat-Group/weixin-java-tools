/*
 * KINGSTAR MEDIA SOLUTIONS Co.,LTD. Copyright c 2005-2013. All rights reserved.
 *
 * This source code is the property of KINGSTAR MEDIA SOLUTIONS LTD. It is intended
 * only for the use of KINGSTAR MEDIA application development. Reengineering, reproduction
 * arose from modification of the original source, or other redistribution of this source
 * is not permitted without written permission of the KINGSTAR MEDIA SOLUTIONS LTD.
 */
package me.chanjar.weixin.mp.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.mp.bean.WxMpMassNews;

import java.lang.reflect.Type;

public class WxMpMassNewsArticleGsonAdapter implements JsonSerializer<WxMpMassNews.WxMpMassNewsArticle>, JsonDeserializer<WxMpMassNews.WxMpMassNewsArticle> {

  @Override
  public JsonElement serialize(WxMpMassNews.WxMpMassNewsArticle article, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject articleJson = new JsonObject();

    articleJson.addProperty("thumb_media_id", article.getThumbMediaId());
    articleJson.addProperty("title", article.getTitle());
    articleJson.addProperty("content", article.getContent());
    if (null != article.getAuthor()) {
      articleJson.addProperty("author", article.getAuthor());
    }
    if (null != article.getContentSourceUrl()) {
      articleJson.addProperty("content_source_url", article.getContentSourceUrl());
    }
    if (null != article.getDigest()) {
      articleJson.addProperty("digest", article.getDigest());
    }
    articleJson.addProperty("show_cover_pic", article.isShowCoverPic() ? "1" : "0");
    return articleJson;
  }

  @Override
  public WxMpMassNews.WxMpMassNewsArticle deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    JsonObject articleInfo = jsonElement.getAsJsonObject();
    WxMpMassNews.WxMpMassNewsArticle article = new WxMpMassNews.WxMpMassNewsArticle();

    JsonElement title = articleInfo.get("title");
    if (title != null && !title.isJsonNull()) {
      article.setTitle(GsonHelper.getAsString(title));
    }
    JsonElement content = articleInfo.get("content");
    if (content != null && !content.isJsonNull()) {
      article.setContent(GsonHelper.getAsString(content));
    }
    JsonElement contentSourceUrl = articleInfo.get("content_source_url");
    if (contentSourceUrl != null && !contentSourceUrl.isJsonNull()) {
      article.setContentSourceUrl(GsonHelper.getAsString(contentSourceUrl));
    }
    JsonElement author = articleInfo.get("author");
    if (author != null && !author.isJsonNull()) {
      article.setAuthor(GsonHelper.getAsString(author));
    }
    JsonElement digest = articleInfo.get("digest");
    if (digest != null && !digest.isJsonNull()) {
      article.setDigest(GsonHelper.getAsString(digest));
    }
    JsonElement thumbMediaId = articleInfo.get("thumb_media_id");
    if (thumbMediaId != null && !thumbMediaId.isJsonNull()) {
      article.setThumbMediaId(GsonHelper.getAsString(thumbMediaId));
    }
    JsonElement showCoverPic = articleInfo.get("show_cover_pic");
    if (showCoverPic != null && !showCoverPic.isJsonNull()) {
      article.setShowCoverPic(GsonHelper.getAsBoolean(showCoverPic));
    }
    return article;
  }
}

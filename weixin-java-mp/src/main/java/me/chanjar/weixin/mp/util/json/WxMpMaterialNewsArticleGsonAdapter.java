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
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import org.apache.commons.lang3.BooleanUtils;

import java.lang.reflect.Type;

public class WxMpMaterialNewsArticleGsonAdapter implements JsonSerializer<WxMpMaterialNews.WxMpMaterialNewsArticle>, JsonDeserializer<WxMpMaterialNews.WxMpMaterialNewsArticle> {

  @Override
  public JsonElement serialize(WxMpMaterialNews.WxMpMaterialNewsArticle article, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject articleJson = new JsonObject();

    articleJson.addProperty("thumb_media_id", article.getThumbMediaId());
    articleJson.addProperty("thumb_url", article.getThumbUrl());
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
    if (null != article.getUrl()) {
      articleJson.addProperty("url", article.getUrl());
    }

    if (null != article.getNeedOpenComment()) {
      articleJson.addProperty("need_open_comment",
        BooleanUtils.toInteger(article.getNeedOpenComment(), 1, 0));
    }

    if (null != article.getOnlyFansCanComment()) {
      articleJson.addProperty("only_fans_can_comment",
        BooleanUtils.toInteger(article.getOnlyFansCanComment(), 1, 0));
    }
    return articleJson;
  }

  @Override
  public WxMpMaterialNews.WxMpMaterialNewsArticle deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    JsonObject articleInfo = jsonElement.getAsJsonObject();
    WxMpMaterialNews.WxMpMaterialNewsArticle article = new WxMpMaterialNews.WxMpMaterialNewsArticle();

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
    JsonElement thumbUrl = articleInfo.get("thumb_url");
    if (thumbUrl != null && !thumbUrl.isJsonNull()) {
      article.setThumbUrl(GsonHelper.getAsString(thumbUrl));
    }
    JsonElement showCoverPic = articleInfo.get("show_cover_pic");
    if (showCoverPic != null && !showCoverPic.isJsonNull()) {
      article.setShowCoverPic(GsonHelper.getAsBoolean(showCoverPic));
    }
    JsonElement url = articleInfo.get("url");
    if (url != null && !url.isJsonNull()) {
      article.setUrl(GsonHelper.getAsString(url));
    }

    JsonElement needOpenComment = articleInfo.get("need_open_comment");
    if (needOpenComment != null && !needOpenComment.isJsonNull()) {
      article.setNeedOpenComment(GsonHelper.getAsBoolean(needOpenComment));
    }

    JsonElement onlyFansCanComment = articleInfo.get("only_fans_can_comment");
    if (onlyFansCanComment != null && !onlyFansCanComment.isJsonNull()) {
      article.setOnlyFansCanComment(GsonHelper.getAsBoolean(onlyFansCanComment));
    }
    return article;
  }
}

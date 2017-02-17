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
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;

import java.lang.reflect.Type;

public class WxMpMaterialNewsGsonAdapter implements JsonSerializer<WxMpMaterialNews>, JsonDeserializer<WxMpMaterialNews> {

  @Override
  public JsonElement serialize(WxMpMaterialNews wxMpMaterialNews, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject newsJson = new JsonObject();

    JsonArray articleJsonArray = new JsonArray();
    for (WxMpMaterialNews.WxMpMaterialNewsArticle article : wxMpMaterialNews.getArticles()) {
      JsonObject articleJson = WxMpGsonBuilder.create().toJsonTree(article, WxMpMaterialNews.WxMpMaterialNewsArticle.class).getAsJsonObject();
      articleJsonArray.add(articleJson);
    }
    newsJson.add("articles", articleJsonArray);

    return newsJson;
  }

  @Override
  public WxMpMaterialNews deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxMpMaterialNews wxMpMaterialNews = new WxMpMaterialNews();
    JsonObject json = jsonElement.getAsJsonObject();
    if (json.get("news_item") != null && !json.get("news_item").isJsonNull()) {
      JsonArray articles = json.getAsJsonArray("news_item");
      for (JsonElement article1 : articles) {
        JsonObject articleInfo = article1.getAsJsonObject();
        WxMpMaterialNews.WxMpMaterialNewsArticle article = WxMpGsonBuilder.create().fromJson(articleInfo, WxMpMaterialNews.WxMpMaterialNewsArticle.class);
        wxMpMaterialNews.addArticle(article);
      }
    }
    return wxMpMaterialNews;
  }
}

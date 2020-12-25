package me.chanjar.weixin.qidian.util.json;

import com.google.gson.*;
import me.chanjar.weixin.qidian.bean.WxMpMassNews;
import me.chanjar.weixin.qidian.bean.material.WxMpNewsArticle;

import java.lang.reflect.Type;

public class WxMpMassNewsGsonAdapter implements JsonSerializer<WxMpMassNews>, JsonDeserializer<WxMpMassNews> {

  @Override
  public JsonElement serialize(WxMpMassNews message, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject newsJson = new JsonObject();

    JsonArray articleJsonArray = new JsonArray();
    for (WxMpNewsArticle article : message.getArticles()) {
      JsonObject articleJson = WxMpGsonBuilder.create().toJsonTree(article, WxMpNewsArticle.class).getAsJsonObject();
      articleJsonArray.add(articleJson);
    }
    newsJson.add("articles", articleJsonArray);

    return newsJson;
  }

  @Override
  public WxMpMassNews deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxMpMassNews wxMpMassNews = new WxMpMassNews();
    JsonObject json = jsonElement.getAsJsonObject();
    if (json.get("media_id") != null && !json.get("media_id").isJsonNull()) {
      JsonArray articles = json.getAsJsonArray("articles");
      for (JsonElement article1 : articles) {
        JsonObject articleInfo = article1.getAsJsonObject();
        WxMpNewsArticle article = WxMpGsonBuilder.create().fromJson(articleInfo, WxMpNewsArticle.class);
        wxMpMassNews.addArticle(article);
      }
    }
    return wxMpMassNews;
  }
}

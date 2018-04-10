package me.chanjar.weixin.mp.util.json;

import com.google.gson.*;
import me.chanjar.weixin.mp.bean.WxMpMassNews;

import java.lang.reflect.Type;

public class WxMpMassNewsGsonAdapter implements JsonSerializer<WxMpMassNews>, JsonDeserializer<WxMpMassNews> {

  @Override
  public JsonElement serialize(WxMpMassNews message, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject newsJson = new JsonObject();

    JsonArray articleJsonArray = new JsonArray();
    for (WxMpMassNews.WxMpMassNewsArticle article : message.getArticles()) {
      JsonObject articleJson = WxMpGsonBuilder.create().toJsonTree(article, WxMpMassNews.WxMpMassNewsArticle.class).getAsJsonObject();
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
        WxMpMassNews.WxMpMassNewsArticle article = WxMpGsonBuilder.create().fromJson(articleInfo, WxMpMassNews.WxMpMassNewsArticle.class);
        wxMpMassNews.addArticle(article);
      }
    }
    return wxMpMassNews;
  }
}

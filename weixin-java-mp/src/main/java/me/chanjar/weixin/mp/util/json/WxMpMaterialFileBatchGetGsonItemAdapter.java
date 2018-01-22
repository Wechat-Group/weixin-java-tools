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
import me.chanjar.weixin.mp.bean.material.WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem;

import java.lang.reflect.Type;
import java.util.Date;

public class WxMpMaterialFileBatchGetGsonItemAdapter implements JsonDeserializer<WxMaterialFileBatchGetNewsItem> {

  @Override
  public WxMaterialFileBatchGetNewsItem deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxMaterialFileBatchGetNewsItem newsItem = new WxMaterialFileBatchGetNewsItem();
    JsonObject json = jsonElement.getAsJsonObject();
    if (json.get("media_id") != null && !json.get("media_id").isJsonNull()) {
      newsItem.setMediaId(GsonHelper.getAsString(json.get("media_id")));
    }
    if (json.get("update_time") != null && !json.get("update_time").isJsonNull()) {
      newsItem.setUpdateTime(new Date(1000 * GsonHelper.getAsLong(json.get("update_time"))));
    }
    if (json.get("name") != null && !json.get("name").isJsonNull()) {
      newsItem.setName(GsonHelper.getAsString(json.get("name")));
    }
    if (json.get("url") != null && !json.get("url").isJsonNull()) {
      newsItem.setUrl(GsonHelper.getAsString(json.get("url")));
    }
    return newsItem;
  }
}

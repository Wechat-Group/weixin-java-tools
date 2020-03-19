/*
 * KINGSTAR MEDIA SOLUTIONS Co.,LTD. Copyright c 2005-2013. All rights reserved.
 *
 * This source code is the property of KINGSTAR MEDIA SOLUTIONS LTD. It is intended
 * only for the use of KINGSTAR MEDIA application development. Reengineering, reproduction
 * arose from modification of the original source, or other redistribution of this source
 * is not permitted without written permission of the KINGSTAR MEDIA SOLUTIONS LTD.
 */
package me.chanjar.weixin.cp.util.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.cp.bean.WxCpDepart;

/**
 * WxCpDepart的gson适配器.
 *
 * @author Daniel Qian
 */
public class WxCpDepartGsonAdapter implements JsonSerializer<WxCpDepart>, JsonDeserializer<WxCpDepart> {
  private static final String ID = "id";
  private static final String NAME = "name";
  private static final String EN_NAME = "name_en";
  private static final String PARENT_ID = "parentid";
  private static final String ORDER = "order";

  @Override
  public JsonElement serialize(WxCpDepart group, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject json = new JsonObject();
    if (group.getId() != null) {
      json.addProperty(ID, group.getId());
    }
    if (group.getName() != null) {
      json.addProperty(NAME, group.getName());
    }
    if (group.getEnName() != null) {
      json.addProperty(EN_NAME, group.getEnName());
    }
    if (group.getParentId() != null) {
      json.addProperty(PARENT_ID, group.getParentId());
    }
    if (group.getOrder() != null) {
      json.addProperty(ORDER, String.valueOf(group.getOrder()));
    }
    return json;
  }

  @Override
  public WxCpDepart deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException {
    WxCpDepart depart = new WxCpDepart();
    JsonObject departJson = json.getAsJsonObject();
    if (departJson.get(ID) != null && !departJson.get(ID).isJsonNull()) {
      depart.setId(GsonHelper.getAsLong(departJson.get(ID)));
    }
    if (departJson.get(NAME) != null && !departJson.get(NAME).isJsonNull()) {
      depart.setName(GsonHelper.getAsString(departJson.get(NAME)));
    }
    if (departJson.get(EN_NAME) != null && !departJson.get(EN_NAME).isJsonNull()) {
      depart.setEnName(GsonHelper.getAsString(departJson.get(EN_NAME)));
    }
    if (departJson.get(ORDER) != null && !departJson.get(ORDER).isJsonNull()) {
      depart.setOrder(GsonHelper.getAsLong(departJson.get(ORDER)));
    }
    if (departJson.get(PARENT_ID) != null && !departJson.get(PARENT_ID).isJsonNull()) {
      depart.setParentId(GsonHelper.getAsLong(departJson.get(PARENT_ID)));
    }
    return depart;
  }

}

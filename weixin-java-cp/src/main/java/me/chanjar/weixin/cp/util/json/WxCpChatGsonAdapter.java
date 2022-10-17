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
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.cp.bean.WxCpChat;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 群聊适配器
 *
 * @author gaigeshen
 */
public class WxCpChatGsonAdapter implements JsonSerializer<WxCpChat>, JsonDeserializer<WxCpChat> {

  @Override
  public JsonElement serialize(WxCpChat chat, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject json = new JsonObject();
    if (chat.getId() != null) {
      json.addProperty("chatid", chat.getId());
    }
    if (chat.getName() != null) {
      json.addProperty("name", chat.getName());
    }
    if (chat.getOwner() != null) {
      json.addProperty("owner", chat.getOwner());
    }
    if (chat.getUsers() != null) {
      JsonArray users = new JsonArray();
      for (String user : chat.getUsers()) {
        users.add(user);
      }
      json.add("userlist", users);
    }
    return json;
  }

  @Override
  public WxCpChat deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject chatJson = json.getAsJsonObject();

    WxCpChat chat = new WxCpChat();
    chat.setId(GsonHelper.getAsString(chatJson.get("chatid")));
    chat.setName(GsonHelper.getAsString(chatJson.get("name")));
    chat.setOwner(GsonHelper.getAsString(chatJson.get("owner")));

    JsonArray usersJson = chatJson.getAsJsonArray("userlist");
    if (usersJson != null) {
      List<String> users = new ArrayList<>(usersJson.size());
      chat.setUsers(users);
      for (JsonElement userJson : usersJson) {
        users.add(userJson.getAsString());
      }
    }

    return chat;
  }

}

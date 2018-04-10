package me.chanjar.weixin.mp.util.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import me.chanjar.weixin.mp.bean.subscribe.WxMpSubscribeMessage;

import java.lang.reflect.Type;

/**
 * @author Mklaus
 * @date 2018-01-22 下午12:31
 */
public class WxMpSubscribeMessageGsonAdapter implements JsonSerializer<WxMpSubscribeMessage> {

  @Override
  public JsonElement serialize(WxMpSubscribeMessage message, Type type, JsonSerializationContext jsonSerializationContext) {
    JsonObject messageJson = new JsonObject();
    messageJson.addProperty("touser", message.getToUser());
    messageJson.addProperty("template_id", message.getTemplateId());

    if (message.getUrl() != null) {
      messageJson.addProperty("url", message.getUrl());
    }

    if (message.getMiniProgram() != null) {
      JsonObject miniProgramJson = new JsonObject();
      miniProgramJson.addProperty("appid", message.getMiniProgram().getAppid());
      miniProgramJson.addProperty("pagepath", message.getMiniProgram().getPagePath());
      messageJson.add("miniprogram", miniProgramJson);
    }

    messageJson.addProperty("scene", message.getScene());
    messageJson.addProperty("title", message.getTitle());

    JsonObject data = new JsonObject();
    messageJson.add("data", data);

    JsonObject content = new JsonObject();
    data.add("content", content);

    if (message.getContentValue() != null) {
      content.addProperty("value", message.getContentValue());
    }

    if (message.getContentColor() != null) {
      content.addProperty("color", message.getContentColor());
    }

    return messageJson;

  }
}

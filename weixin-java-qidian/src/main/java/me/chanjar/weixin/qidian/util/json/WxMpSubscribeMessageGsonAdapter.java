package me.chanjar.weixin.qidian.util.json;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import me.chanjar.weixin.qidian.bean.subscribe.WxMpSubscribeMessage;

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

    final WxMpSubscribeMessage.MiniProgram miniProgram = message.getMiniProgram();
    if (miniProgram != null) {
      JsonObject miniProgramJson = new JsonObject();
      miniProgramJson.addProperty("appid", miniProgram.getAppid());
      if (miniProgram.isUsePath()) {
        miniProgramJson.addProperty("path", miniProgram.getPagePath());
      } else {
        miniProgramJson.addProperty("pagepath", miniProgram.getPagePath());
      }
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

package me.chanjar.weixin.mp.util.json;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * @author chanjarster
 */
public class WxMpTemplateMessageGsonAdapter implements JsonSerializer<WxMpTemplateMessage> {

  @Override
  public JsonElement serialize(WxMpTemplateMessage message, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject messageJson = new JsonObject();
    messageJson.addProperty("touser", message.getToUser());
    messageJson.addProperty("template_id", message.getTemplateId());
    if (message.getUrl() != null) {
      messageJson.addProperty("url", message.getUrl());
    }

    final WxMpTemplateMessage.MiniProgram miniProgram = message.getMiniProgram();
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

    JsonObject data = new JsonObject();
    messageJson.add("data", data);

    for (WxMpTemplateData datum : message.getData()) {
      JsonObject dataJson = new JsonObject();
      dataJson.addProperty("value", datum.getValue());
      if (datum.getColor() != null) {
        dataJson.addProperty("color", datum.getColor());
      }
      data.add(datum.getName(), dataJson);
    }

    return messageJson;
  }

}

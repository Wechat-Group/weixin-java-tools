package cn.binarywang.wx.miniapp.util.json;

import cn.binarywang.wx.miniapp.bean.WxMaSubscribeData;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class WxMaSubscribeMessageGsonAdapter implements JsonSerializer<WxMaSubscribeMessage> {

  @Override
  public JsonElement serialize(WxMaSubscribeMessage message, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject messageJson = new JsonObject();
    messageJson.addProperty("touser", message.getToUser());
    messageJson.addProperty("template_id", message.getTemplateId());
    if (message.getPage() != null) {
      messageJson.addProperty("page", message.getPage());
    }


    JsonObject data = new JsonObject();
    messageJson.add("data", data);

    if (message.getData() == null) {
      return messageJson;
    }

    for (WxMaSubscribeData datum : message.getData()) {
      JsonObject dataJson = new JsonObject();
      dataJson.addProperty("value", datum.getValue());
      data.add(datum.getName(), dataJson);
    }

    return messageJson;
  }

}

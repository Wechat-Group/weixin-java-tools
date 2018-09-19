package cn.binarywang.wx.miniapp.util.json;

import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaTemplateMessageGsonAdapter implements JsonSerializer<WxMaTemplateMessage> {

  @Override
  public JsonElement serialize(WxMaTemplateMessage message, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject messageJson = new JsonObject();
    messageJson.addProperty("touser", message.getToUser());
    messageJson.addProperty("template_id", message.getTemplateId());
    if (message.getPage() != null) {
      messageJson.addProperty("page", message.getPage());
    }

    if (message.getFormId() != null) {
      messageJson.addProperty("form_id", message.getFormId());
    }

    if (message.getPage() != null) {
      messageJson.addProperty("page", message.getPage());
    }

    if (message.getColor() != null) {
      messageJson.addProperty("color", message.getColor());
    }

    if (message.getEmphasisKeyword() != null) {
      messageJson.addProperty("emphasis_keyword", message.getEmphasisKeyword());
    }

    JsonObject data = new JsonObject();
    messageJson.add("data", data);

    if (message.getData() == null) {
      return messageJson;
    }

    for (WxMaTemplateMessage.Data datum : message.getData()) {
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

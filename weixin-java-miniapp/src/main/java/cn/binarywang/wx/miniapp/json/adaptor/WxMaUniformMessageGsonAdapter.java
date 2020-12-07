package cn.binarywang.wx.miniapp.json.adaptor;

import java.lang.reflect.Type;

import cn.binarywang.wx.miniapp.bean.WxMaTemplateData;
import cn.binarywang.wx.miniapp.bean.WxMaUniformMessage;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaUniformMessageGsonAdapter implements JsonSerializer<WxMaUniformMessage> {

  @Override
  public JsonElement serialize(WxMaUniformMessage message, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject messageJson = new JsonObject();
    messageJson.addProperty("touser", message.getToUser());
    if (message.isMpTemplateMsg()) {
      JsonObject msg = new JsonObject();
      if (message.getAppid() != null) {
        msg.addProperty("appid", message.getAppid());
      }

      msg.addProperty("template_id", message.getTemplateId());

      if (message.getUrl() != null) {
        msg.addProperty("url", message.getUrl());
      }

      final WxMaUniformMessage.MiniProgram miniProgram = message.getMiniProgram();
      if (miniProgram != null) {
        JsonObject miniProgramJson = new JsonObject();
        miniProgramJson.addProperty("appid", miniProgram.getAppid());
        if (miniProgram.isUsePath()) {
          miniProgramJson.addProperty("path", miniProgram.getPagePath());
        } else if (miniProgram.isUsePagePath()) {
          miniProgramJson.addProperty("pagePath", miniProgram.getPagePath());
        } else {
          miniProgramJson.addProperty("pagepath", miniProgram.getPagePath());
        }
        msg.add("miniprogram", miniProgramJson);
      }

      if (message.getData() != null) {
        JsonObject data = new JsonObject();
        for (WxMaTemplateData templateData : message.getData()) {
          JsonObject dataJson = new JsonObject();
          dataJson.addProperty("value", templateData.getValue());
          if (templateData.getColor() != null) {
            dataJson.addProperty("color", templateData.getColor());
          }
          data.add(templateData.getName(), dataJson);
        }
        msg.add("data", data);
      }


      messageJson.add("mp_template_msg", msg);
      return messageJson;
    }

    //小程序模版消息
    JsonObject msg = new JsonObject();
    msg.addProperty("template_id", message.getTemplateId());

    if (message.getPage() != null) {
      msg.addProperty("page", message.getPage());
    }

    if (message.getFormId() != null) {
      msg.addProperty("form_id", message.getFormId());
    }

    JsonObject data = new JsonObject();
    msg.add("data", data);

    if (message.getData() != null) {
      for (WxMaTemplateData templateData : message.getData()) {
        JsonObject dataJson = new JsonObject();
        dataJson.addProperty("value", templateData.getValue());
        data.add(templateData.getName(), dataJson);
      }
    }

    if (message.getEmphasisKeyword() != null) {
      msg.addProperty("emphasis_keyword", message.getEmphasisKeyword());
    }

    messageJson.add("weapp_template_msg", msg);

    return messageJson;
  }

}

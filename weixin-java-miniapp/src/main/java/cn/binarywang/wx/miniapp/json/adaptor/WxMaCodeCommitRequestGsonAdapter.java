package cn.binarywang.wx.miniapp.json.adaptor;

import cn.binarywang.wx.miniapp.bean.code.WxMaCodeCommitRequest;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-26 19:47
 */
public class WxMaCodeCommitRequestGsonAdapter implements JsonSerializer<WxMaCodeCommitRequest> {

  @Override
  public JsonElement serialize(WxMaCodeCommitRequest request, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject requestJson = new JsonObject();
    requestJson.addProperty("template_id", request.getTemplateId());
    requestJson.addProperty("user_version", request.getUserVersion());
    requestJson.addProperty("user_desc", request.getUserDesc());
    if (request.getExtConfig() != null) {
      requestJson.addProperty("ext_json", WxMaGsonBuilder.create().toJson(request.getExtConfig()));
    }
    return requestJson;
  }
}

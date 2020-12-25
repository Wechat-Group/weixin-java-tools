package me.chanjar.weixin.qidian.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.qidian.bean.result.WxMpSemanticQueryResult;

import java.lang.reflect.Type;

/**
 * @author Daniel Qian
 */
public class WxMpSemanticQueryResultAdapter implements JsonDeserializer<WxMpSemanticQueryResult> {

  @Override
  public WxMpSemanticQueryResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxMpSemanticQueryResult result = new WxMpSemanticQueryResult();
    JsonObject resultJsonObject = json.getAsJsonObject();

    if (GsonHelper.getString(resultJsonObject, "query") != null) {
      result.setQuery(GsonHelper.getString(resultJsonObject, "query"));
    }
    if (GsonHelper.getString(resultJsonObject, "type") != null) {
      result.setType(GsonHelper.getString(resultJsonObject, "type"));
    }
    if (resultJsonObject.get("semantic") != null) {
      result.setSemantic(resultJsonObject.get("semantic").toString());
    }
    if (resultJsonObject.get("result") != null) {
      result.setResult(resultJsonObject.get("result").toString());
    }
    if (GsonHelper.getString(resultJsonObject, "answer") != null) {
      result.setAnswer(GsonHelper.getString(resultJsonObject, "answer"));
    }
    if (GsonHelper.getString(resultJsonObject, "text") != null) {
      result.setText(GsonHelper.getString(resultJsonObject, "text"));
    }
    return result;
  }

}

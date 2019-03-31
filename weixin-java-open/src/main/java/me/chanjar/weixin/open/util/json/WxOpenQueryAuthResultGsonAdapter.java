package me.chanjar.weixin.open.util.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.open.bean.auth.WxOpenAuthorizationInfo;
import me.chanjar.weixin.open.bean.result.WxOpenQueryAuthResult;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenQueryAuthResultGsonAdapter implements JsonDeserializer<WxOpenQueryAuthResult> {
  @Override
  public WxOpenQueryAuthResult deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxOpenQueryAuthResult queryAuthResult = new WxOpenQueryAuthResult();
    JsonObject jsonObject = jsonElement.getAsJsonObject();

    WxOpenAuthorizationInfo authorizationInfo = WxOpenGsonBuilder.create().fromJson(jsonObject.get("authorization_info"),
      new TypeToken<WxOpenAuthorizationInfo>() {
      }.getType());

    queryAuthResult.setAuthorizationInfo(authorizationInfo);
    return queryAuthResult;
  }
}

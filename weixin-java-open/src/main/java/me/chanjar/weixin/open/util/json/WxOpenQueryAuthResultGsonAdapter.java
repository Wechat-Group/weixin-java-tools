package me.chanjar.weixin.open.util.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.open.bean.auth.WxOpenAuthorizationInfo;
import me.chanjar.weixin.open.bean.result.WxOpenQueryAuthResult;

import java.lang.reflect.Type;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenQueryAuthResultGsonAdapter implements JsonDeserializer<WxOpenQueryAuthResult> {
  @Override
  public WxOpenQueryAuthResult deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxOpenQueryAuthResult queryAuthResult = new WxOpenQueryAuthResult();
    JsonObject jsonObject = jsonElement.getAsJsonObject();

    WxOpenAuthorizationInfo authorizationInfo = WxOpenGsonBuilder.INSTANCE.create().fromJson(jsonObject.get("authorization_info"),
      new TypeToken<WxOpenAuthorizationInfo>() {
      }.getType());

    queryAuthResult.setAuthorizationInfo(authorizationInfo);
    return queryAuthResult;
  }
}

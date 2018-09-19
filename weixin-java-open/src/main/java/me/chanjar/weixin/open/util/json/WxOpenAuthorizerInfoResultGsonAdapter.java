package me.chanjar.weixin.open.util.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.open.bean.auth.WxOpenAuthorizationInfo;
import me.chanjar.weixin.open.bean.auth.WxOpenAuthorizerInfo;
import me.chanjar.weixin.open.bean.result.WxOpenAuthorizerInfoResult;

import java.lang.reflect.Type;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenAuthorizerInfoResultGsonAdapter implements JsonDeserializer<WxOpenAuthorizerInfoResult> {
  @Override
  public WxOpenAuthorizerInfoResult deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxOpenAuthorizerInfoResult authorizerInfoResult = new WxOpenAuthorizerInfoResult();
    JsonObject jsonObject = jsonElement.getAsJsonObject();

    WxOpenAuthorizationInfo authorizationInfo = WxOpenGsonBuilder.INSTANCE.create().fromJson(jsonObject.get("authorization_info"),
      new TypeToken<WxOpenAuthorizationInfo>() {
      }.getType());

    authorizerInfoResult.setAuthorizationInfo(authorizationInfo);
    WxOpenAuthorizerInfo authorizerInfo = WxOpenGsonBuilder.INSTANCE.create().fromJson(jsonObject.get("authorizer_info"),
      new TypeToken<WxOpenAuthorizerInfo>() {
      }.getType());

    authorizerInfoResult.setAuthorizerInfo(authorizerInfo);
    return authorizerInfoResult;
  }
}

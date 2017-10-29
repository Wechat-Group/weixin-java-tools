package me.chanjar.weixin.open.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.open.bean.resp.AuthorizerTokenResp;

import java.lang.reflect.Type;

/**
 * Created by JasonY on 17/10/24.
 */
public class AuthorizerTokenRespAdapter implements JsonDeserializer<AuthorizerTokenResp> {

  @Override
  public AuthorizerTokenResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject o = json.getAsJsonObject();

    if (o == null) {
      return null;
    }

    AuthorizerTokenResp authorizerTokenResp = new AuthorizerTokenResp();
    authorizerTokenResp.setAuthorizerAccessToken(GsonHelper.getString(o, "authorizer_access_token"));
    authorizerTokenResp.setAuthorizerRefreshToken(GsonHelper.getString(o, "authorizer_refresh_token"));
    authorizerTokenResp.setExpiresIn(GsonHelper.getInteger(o, "expires_in"));

    return authorizerTokenResp;
  }
}

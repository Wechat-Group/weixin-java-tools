package us.wili.jason.weixin.open.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import us.wili.jason.weixin.open.bean.resp.ComponentAccessTokenResp;

import java.lang.reflect.Type;

/**
 * Created by JasonY on 17/10/24.
 */
public class ComponentAccessTokenRespAdapter implements JsonDeserializer<ComponentAccessTokenResp> {

  @Override
  public ComponentAccessTokenResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject o = json.getAsJsonObject();

    ComponentAccessTokenResp componentAccessTokenResp = new ComponentAccessTokenResp();

    componentAccessTokenResp.setComponentAccessToken(GsonHelper.getString(o, "component_access_token"));
    componentAccessTokenResp.setExpiresIn(GsonHelper.getInteger(o, "expires_in"));

    return componentAccessTokenResp;
  }
}

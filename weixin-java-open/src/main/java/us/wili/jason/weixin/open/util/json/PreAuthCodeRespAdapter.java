package us.wili.jason.weixin.open.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import us.wili.jason.weixin.open.bean.resp.PreAuthCodeResp;

import java.lang.reflect.Type;

/**
 * Created by JasonY on 17/10/24.
 */
public class PreAuthCodeRespAdapter implements JsonDeserializer<PreAuthCodeResp> {
  @Override
  public PreAuthCodeResp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject o = json.getAsJsonObject();

    PreAuthCodeResp preAuthCode = new PreAuthCodeResp();
    preAuthCode.setPreAuthCode(GsonHelper.getString(o, "pre_auth_code"));
    preAuthCode.setExpiresIn(GsonHelper.getInteger(o, "expires_in"));

    return preAuthCode;
  }
}

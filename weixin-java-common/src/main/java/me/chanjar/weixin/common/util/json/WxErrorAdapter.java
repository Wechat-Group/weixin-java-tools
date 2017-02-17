/*
 * KINGSTAR MEDIA SOLUTIONS Co.,LTD. Copyright c 2005-2013. All rights reserved.
 *
 * This source code is the property of KINGSTAR MEDIA SOLUTIONS LTD. It is intended
 * only for the use of KINGSTAR MEDIA application development. Reengineering, reproduction
 * arose from modification of the original source, or other redistribution of this source
 * is not permitted without written permission of the KINGSTAR MEDIA SOLUTIONS LTD.
 */
package me.chanjar.weixin.common.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.bean.result.WxError;

import java.lang.reflect.Type;

/**
 * @author Daniel Qian
 */
public class WxErrorAdapter implements JsonDeserializer<WxError> {

  @Override
  public WxError deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxError wxError = new WxError();
    JsonObject wxErrorJsonObject = json.getAsJsonObject();

    if (wxErrorJsonObject.get("errcode") != null && !wxErrorJsonObject.get("errcode").isJsonNull()) {
      wxError.setErrorCode(GsonHelper.getAsPrimitiveInt(wxErrorJsonObject.get("errcode")));
    }
    if (wxErrorJsonObject.get("errmsg") != null && !wxErrorJsonObject.get("errmsg").isJsonNull()) {
      wxError.setErrorMsg(GsonHelper.getAsString(wxErrorJsonObject.get("errmsg")));
    }
    wxError.setJson(json.toString());
    return wxError;
  }

}

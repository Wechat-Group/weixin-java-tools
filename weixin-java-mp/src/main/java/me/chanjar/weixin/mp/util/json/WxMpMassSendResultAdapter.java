/*
 * KINGSTAR MEDIA SOLUTIONS Co.,LTD. Copyright c 2005-2013. All rights reserved.
 *
 * This source code is the property of KINGSTAR MEDIA SOLUTIONS LTD. It is intended
 * only for the use of KINGSTAR MEDIA application development. Reengineering, reproduction
 * arose from modification of the original source, or other redistribution of this source
 * is not permitted without written permission of the KINGSTAR MEDIA SOLUTIONS LTD.
 */
package me.chanjar.weixin.mp.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;

import java.lang.reflect.Type;

/**
 * @author Daniel Qian
 */
public class WxMpMassSendResultAdapter implements JsonDeserializer<WxMpMassSendResult> {

  @Override
  public WxMpMassSendResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxMpMassSendResult sendResult = new WxMpMassSendResult();
    JsonObject sendResultJsonObject = json.getAsJsonObject();

    if (sendResultJsonObject.get("errcode") != null && !sendResultJsonObject.get("errcode").isJsonNull()) {
      sendResult.setErrorCode(GsonHelper.getAsString(sendResultJsonObject.get("errcode")));
    }
    if (sendResultJsonObject.get("errmsg") != null && !sendResultJsonObject.get("errmsg").isJsonNull()) {
      sendResult.setErrorMsg(GsonHelper.getAsString(sendResultJsonObject.get("errmsg")));
    }
    if (sendResultJsonObject.get("msg_id") != null && !sendResultJsonObject.get("msg_id").isJsonNull()) {
      sendResult.setMsgId(GsonHelper.getAsString(sendResultJsonObject.get("msg_id")));
    }
    if (sendResultJsonObject.get("msg_data_id") != null && !sendResultJsonObject.get("msg_data_id").isJsonNull()) {
      sendResult.setMsgDataId(GsonHelper.getAsString(sendResultJsonObject.get("msg_data_id")));
    }
    return sendResult;
  }

}

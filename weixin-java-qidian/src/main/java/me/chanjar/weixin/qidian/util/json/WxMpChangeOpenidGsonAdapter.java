package me.chanjar.weixin.qidian.util.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.qidian.bean.result.WxMpChangeOpenid;

import java.lang.reflect.Type;

public class WxMpChangeOpenidGsonAdapter implements JsonDeserializer<WxMpChangeOpenid> {

  @Override
  public WxMpChangeOpenid deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject o = json.getAsJsonObject();
    WxMpChangeOpenid changeOpenid = new WxMpChangeOpenid();
    changeOpenid.setOriOpenid(GsonHelper.getString(o, "ori_openid"));
    changeOpenid.setNewOpenid(GsonHelper.getString(o, "new_openid"));
    changeOpenid.setErrMsg(GsonHelper.getString(o, "err_msg"));
    return changeOpenid;
  }

}

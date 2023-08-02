package me.chanjar.weixin.mp.util.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

public class WxMpUserGsonAdapter implements JsonDeserializer<WxMpUser> {

  @Override
  public WxMpUser deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject o = json.getAsJsonObject();
    WxMpUser user = new WxMpUser();
    Integer subscribe = GsonHelper.getInteger(o, "subscribe");
    if (subscribe != null) {
      user.setSubscribe(!new Integer(0).equals(subscribe));
    }
    user.setHeadImgUrl(GsonHelper.getString(o, "headimgurl"));
    user.setLanguage(GsonHelper.getString(o, "language"));
    user.setNickname(GsonHelper.getString(o, "nickname"));
    user.setOpenId(GsonHelper.getString(o, "openid"));
    user.setSubscribeTime(GsonHelper.getLong(o, "subscribe_time"));
    user.setUnionId(GsonHelper.getString(o, "unionid"));
    user.setRemark(GsonHelper.getString(o, "remark"));
    user.setGroupId(GsonHelper.getInteger(o, "groupid"));
    user.setTagIds(GsonHelper.getLongArray(o, "tagid_list"));
    user.setPrivileges(GsonHelper.getStringArray(o, "privilege"));
    user.setSubscribeScene(GsonHelper.getString(o, "subscribe_scene"));
    user.setQrScene(GsonHelper.getString(o, "qr_scene"));
    user.setQrSceneStr(GsonHelper.getString(o, "qr_scene_str"));

    return user;
  }

}

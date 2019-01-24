package me.chanjar.weixin.open.util.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.open.bean.fastma.WxAccountBasicInfo;

import java.lang.reflect.Type;

/**
 * @author Hipple
 * @description
 * @since 2019/1/23 15:02
 */
public class WxAccountBasicInfoGsonAdapter implements JsonDeserializer<WxAccountBasicInfo> {
  @Override
  public WxAccountBasicInfo deserialize (JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxAccountBasicInfo accountBasicInfo = new WxAccountBasicInfo();
    JsonObject jsonObject = jsonElement.getAsJsonObject();

    accountBasicInfo.setAppId (GsonHelper.getString(jsonObject, "appid"));
    accountBasicInfo.setAccountType (GsonHelper.getInteger (jsonObject, "account_type"));
    accountBasicInfo.setPrincipalType (GsonHelper.getInteger (jsonObject, "principal_type"));
    accountBasicInfo.setPrincipalName (GsonHelper.getString(jsonObject, "principal_name"));
    accountBasicInfo.setRealnameStatus (GsonHelper.getInteger (jsonObject, "realname_status"));

    WxAccountBasicInfo.WxVerifyInfo verifyInfo = WxOpenGsonBuilder.create().fromJson(jsonObject.get("wx_verify_info"),
      new TypeToken<WxAccountBasicInfo.WxVerifyInfo> () {
      }.getType());
    accountBasicInfo.setWxVerifyInfo (verifyInfo);

    WxAccountBasicInfo.SignatureInfo signatureInfo = WxOpenGsonBuilder.create().fromJson(jsonObject.get("signature_info"),
      new TypeToken<WxAccountBasicInfo.SignatureInfo> () {
      }.getType());
    accountBasicInfo.setSignatureInfo (signatureInfo);

    WxAccountBasicInfo.HeadImageInfo headImageInfo = WxOpenGsonBuilder.create().fromJson(jsonObject.get("head_image_info"),
      new TypeToken<WxAccountBasicInfo.HeadImageInfo> () {
      }.getType());
    accountBasicInfo.setHeadImageInfo (headImageInfo);

    return accountBasicInfo;
  }
}

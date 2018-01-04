package me.chanjar.weixin.open.util.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.open.bean.auth.WxOpenAuthorizerInfo;
import me.chanjar.weixin.open.bean.auth.WxOpenMiniProgramInfo;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenAuthorizerInfoGsonAdapter implements JsonDeserializer<WxOpenAuthorizerInfo> {
  @Override
  public WxOpenAuthorizerInfo deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxOpenAuthorizerInfo authorizationInfo = new WxOpenAuthorizerInfo();
    JsonObject jsonObject = jsonElement.getAsJsonObject();

    authorizationInfo.setNickName(GsonHelper.getString(jsonObject, "nick_name"));
    authorizationInfo.setHeadImg(GsonHelper.getString(jsonObject, "head_img"));
    authorizationInfo.setUserName(GsonHelper.getString(jsonObject, "user_name"));
    authorizationInfo.setPrincipalName(GsonHelper.getString(jsonObject, "principal_name"));
    authorizationInfo.setAlias(GsonHelper.getString(jsonObject, "alias"));
    authorizationInfo.setQrcodeUrl(GsonHelper.getString(jsonObject, "qrcode_url"));
    authorizationInfo.setSignature(GsonHelper.getString(jsonObject, "signature"));

    if (jsonObject.has("service_type_info")) {
      authorizationInfo.setServiceTypeInfo(GsonHelper.getInteger(jsonObject.getAsJsonObject("service_type_info"), "id"));
    }
    if (jsonObject.has("verify_type_info")) {
      authorizationInfo.setVerifyTypeInfo(GsonHelper.getInteger(jsonObject.getAsJsonObject("verify_type_info"), "id"));
    }
    Map<String, Integer> businessInfo = WxOpenGsonBuilder.create().fromJson(jsonObject.get("business_info"),
      new TypeToken<Map<String, Integer>>() {
      }.getType());
    authorizationInfo.setBusinessInfo(businessInfo);

    WxOpenAuthorizerInfo.MiniProgramInfo miniProgramInfo = WxOpenGsonBuilder.create().fromJson(jsonObject.get("MiniProgramInfo"),
      new TypeToken<WxOpenAuthorizerInfo.MiniProgramInfo>() {
      }.getType());
    authorizationInfo.setMiniProgramInfo(miniProgramInfo);
    return authorizationInfo;
  }
}

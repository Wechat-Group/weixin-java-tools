package me.chanjar.weixin.open.util.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.open.bean.auth.WxOpenAuthorizerInfo;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenAuthorizerInfoGsonAdapter implements JsonDeserializer<WxOpenAuthorizerInfo> {

  private static final String VERIFY_TYPE_INFO = "verify_type_info";
  private static final String SERVICE_TYPE_INFO = "service_type_info";
  private static final String MINI_PROGRAM_INFO = "MiniProgramInfo";
  private static final String BASIC_CONFIG = "basic_config";

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
    authorizationInfo.setAccountStatus(GsonHelper.getInteger(jsonObject, "account_status"));
    authorizationInfo.setSignature(GsonHelper.getString(jsonObject, "signature"));
    authorizationInfo.setRegisterType(GsonHelper.getInteger(jsonObject, "register_type"));

    if (jsonObject.has(SERVICE_TYPE_INFO)) {
      authorizationInfo.setServiceTypeInfo(GsonHelper.getInteger(jsonObject.getAsJsonObject(SERVICE_TYPE_INFO), "id"));
    }
    if (jsonObject.has(VERIFY_TYPE_INFO)) {
      authorizationInfo.setVerifyTypeInfo(GsonHelper.getInteger(jsonObject.getAsJsonObject(VERIFY_TYPE_INFO), "id"));
    }
    if(jsonObject.has(BASIC_CONFIG)){
      authorizationInfo.setBasicConfig(WxOpenGsonBuilder.create().fromJson(jsonObject.get(BASIC_CONFIG),
        new TypeToken<WxOpenAuthorizerInfo.BasicConfig>(){
        }.getType()));
    }
    Map<String, Integer> businessInfo = WxOpenGsonBuilder.create().fromJson(jsonObject.get("business_info"),
      new TypeToken<Map<String, Integer>>() {
      }.getType());
    authorizationInfo.setBusinessInfo(businessInfo);
    if (jsonObject.has(MINI_PROGRAM_INFO)) {
      WxOpenAuthorizerInfo.MiniProgramInfo miniProgramInfo = WxOpenGsonBuilder.create().fromJson(jsonObject.get(MINI_PROGRAM_INFO),
        new TypeToken<WxOpenAuthorizerInfo.MiniProgramInfo>() {
        }.getType());
      authorizationInfo.setMiniProgramInfo(miniProgramInfo);
    }
    return authorizationInfo;
  }
}

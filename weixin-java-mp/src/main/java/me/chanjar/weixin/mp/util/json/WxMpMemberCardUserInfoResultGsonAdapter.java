package me.chanjar.weixin.mp.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.mp.bean.membercard.MemberCardUserInfo;
import me.chanjar.weixin.mp.bean.membercard.NameValues;
import me.chanjar.weixin.mp.bean.membercard.WxMpMemberCardUserInfoResult;

import java.lang.reflect.Type;

/**
 * Json to WxMpMemberCardUserInfoResult 的转换适配器
 *
 * @author YuJian(mgcnrx11@gmail.com)
 * @version 2017/7/11
 */
public class WxMpMemberCardUserInfoResultGsonAdapter implements JsonDeserializer<WxMpMemberCardUserInfoResult> {

  @Override
  public WxMpMemberCardUserInfoResult deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxMpMemberCardUserInfoResult result = new WxMpMemberCardUserInfoResult();

    JsonObject jsonObject = jsonElement.getAsJsonObject();

    result.setOpenId(GsonHelper.getString(jsonObject, "openid"));
    result.setErrorCode(GsonHelper.getString(jsonObject, "errcode"));
    result.setErrorMsg(GsonHelper.getString(jsonObject, "errmsg"));
    result.setNickname(GsonHelper.getString(jsonObject, "nickname"));
    result.setMembershipNumber(GsonHelper.getString(jsonObject, "membership_number"));
    result.setBonus(GsonHelper.getInteger(jsonObject, "bonus"));
    result.setSex(GsonHelper.getString(jsonObject, "sex"));
    result.setUserCardStatus(GsonHelper.getString(jsonObject, "user_card_status"));
    result.setHasActive(GsonHelper.getBoolean(jsonObject, "has_active"));

    JsonObject userInfoJsonObject = jsonObject.getAsJsonObject("user_info");
    MemberCardUserInfo cardUserInfo = new MemberCardUserInfo();

    JsonArray commonFieldListObj = userInfoJsonObject.getAsJsonArray("common_field_list");
    NameValues[] commonFieldListValues = new NameValues[commonFieldListObj.size()];
    for (int i = 0; i < commonFieldListObj.size(); i++) {
      JsonObject commonField = commonFieldListObj.get(i).getAsJsonObject();
      NameValues commonNameValues = new NameValues();
      commonNameValues.setName(GsonHelper.getString(commonField, "name"));
      commonNameValues.setValue(GsonHelper.getString(commonField, "value"));
      commonFieldListValues[i] = commonNameValues;
    }
    cardUserInfo.setCommonFieldList(commonFieldListValues);

    JsonArray customFieldListObj = userInfoJsonObject.getAsJsonArray("custom_field_list");
    NameValues[] customFieldListValues = new NameValues[customFieldListObj.size()];
    for (int i = 0; i < customFieldListObj.size(); i++) {
      JsonObject customField = customFieldListObj.get(i).getAsJsonObject();
      NameValues customNameValues = new NameValues();
      customNameValues.setName(GsonHelper.getString(customField, "name"));
      customNameValues.setValue(GsonHelper.getString(customField, "value"));

      JsonArray valueListArray = customField.getAsJsonArray("value_list");
      String[] valueList = new String[valueListArray.size()];
      for (int j = 0; j < valueListArray.size(); j++) {
        valueList[j] = valueListArray.get(j).getAsString();
      }
      customNameValues.setValueList(valueList);
      customFieldListValues[i] = customNameValues;
    }
    cardUserInfo.setCustomFieldList(customFieldListValues);

    result.setUserInfo(cardUserInfo);

    return result;
  }
}

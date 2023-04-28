package me.chanjar.weixin.mp.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.mp.bean.card.membercard.MemberCardUserInfo;
import me.chanjar.weixin.mp.bean.card.membercard.NameValues;
import me.chanjar.weixin.mp.bean.card.membercard.WxMpMemberCardActivateTempInfoResult;

import java.lang.reflect.Type;

/**
 * Json to WxMpMemberCardActivateTempInfoResultGsonAdapter 的转换适配器
 *
 * @author thomas(351402401 @ qq.com)
 * @version 2019/4/26
 */
public class WxMpMemberCardActivateTempInfoResultGsonAdapter implements JsonDeserializer<WxMpMemberCardActivateTempInfoResult> {

  @Override
  public WxMpMemberCardActivateTempInfoResult deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxMpMemberCardActivateTempInfoResult result = new WxMpMemberCardActivateTempInfoResult();

    JsonObject jsonObject = jsonElement.getAsJsonObject();

    result.setErrorCode(GsonHelper.getString(jsonObject, WxConsts.ERR_CODE));
    result.setErrorMsg(GsonHelper.getString(jsonObject, "errmsg"));

    JsonObject userInfoJsonObject = jsonObject.getAsJsonObject("info");
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

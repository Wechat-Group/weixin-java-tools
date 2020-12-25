package me.chanjar.weixin.qidian.util.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.qidian.bean.card.WxMpCard;
import me.chanjar.weixin.qidian.bean.card.WxMpCardResult;

/**
 * Created by YuJian on 15/11/11.
 *
 * @author YuJian
 * @version 15/11/11
 */
public class WxMpCardResultGsonAdapter implements JsonDeserializer<WxMpCardResult> {
  @Override
  public WxMpCardResult deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxMpCardResult cardResult = new WxMpCardResult();

    JsonObject jsonObject = jsonElement.getAsJsonObject();

    cardResult.setOpenId(GsonHelper.getString(jsonObject, "openid"));
    cardResult.setErrorCode(GsonHelper.getString(jsonObject, "errcode"));
    cardResult.setErrorMsg(GsonHelper.getString(jsonObject, "errmsg"));
    cardResult.setCanConsume(GsonHelper.getBoolean(jsonObject, "can_consume"));
    cardResult.setUserCardStatus(GsonHelper.getString(jsonObject, "user_card_status"));
    cardResult.setOutStr(GsonHelper.getString(jsonObject, "outer_str"));
    cardResult.setBackgroundPicUrl(GsonHelper.getString(jsonObject, "background_pic_url"));
    cardResult.setUnionid(GsonHelper.getString(jsonObject, "unionid"));

    WxMpCard card = WxMpGsonBuilder.create().fromJson(jsonObject.get("card"),
      new TypeToken<WxMpCard>() {
      }.getType());

    cardResult.setCard(card);

    return cardResult;
  }
}

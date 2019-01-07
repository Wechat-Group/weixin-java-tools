/*
 * KINGSTAR MEDIA SOLUTIONS Co.,LTD. Copyright c 2005-2013. All rights reserved.
 *
 * This source code is the property of KINGSTAR MEDIA SOLUTIONS LTD. It is intended
 * only for the use of KINGSTAR MEDIA application development. Reengineering, reproduction
 * arose from modification of the original source, or other redistribution of this source
 * is not permitted without written permission of the KINGSTAR MEDIA SOLUTIONS LTD.
 */
package me.chanjar.weixin.cp.util.json;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.cp.bean.Gender;
import me.chanjar.weixin.cp.bean.WxCpUser;

/**
 * @author Daniel Qian
 */
public class WxCpUserGsonAdapter implements JsonDeserializer<WxCpUser>, JsonSerializer<WxCpUser> {
  private static final String EXTERNAL_PROFILE = "external_profile";
  private static final String EXTERNAL_ATTR = "external_attr";

  @Override
  public WxCpUser deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject o = json.getAsJsonObject();
    WxCpUser user = new WxCpUser();

    if (o.get("department") != null) {
      JsonArray departJsonArray = o.get("department").getAsJsonArray();
      JsonArray orderJsonArray = o.get("order").getAsJsonArray();
      Integer[] departIds = new Integer[departJsonArray.size()];
      Integer[] order = new Integer[orderJsonArray.size()];
      int i = 0;
      for (JsonElement jsonElement : departJsonArray) {
        departIds[i++] = jsonElement.getAsInt();
      }
      i = 0;
      for (JsonElement jsonElement : orderJsonArray) {
        order[i++] = jsonElement.getAsInt();
      }
      user.setDepartIds(departIds);
      user.setOrder(order);
    }

    user.setUserId(GsonHelper.getString(o, "userid"));
    user.setName(GsonHelper.getString(o, "name"));
    user.setPosition(GsonHelper.getString(o, "position"));
    user.setMobile(GsonHelper.getString(o, "mobile"));
    user.setGender(Gender.fromCode(GsonHelper.getString(o, "gender")));
    user.setEmail(GsonHelper.getString(o, "email"));
    user.setAvatar(GsonHelper.getString(o, "avatar"));
    user.setAvatarMediaId(GsonHelper.getString(o, "avatar_mediaid"));
    user.setStatus(GsonHelper.getInteger(o, "status"));
    user.setEnable(GsonHelper.getInteger(o, "enable"));
    user.setIsLeader(GsonHelper.getInteger(o, "isleader"));
    user.setHideMobile(GsonHelper.getInteger(o, "hide_mobile"));
    user.setEnglishName(GsonHelper.getString(o, "english_name"));
    user.setTelephone(GsonHelper.getString(o, "telephone"));
    user.setQrCode(GsonHelper.getString(o, "qr_code"));
    user.setToInvite(GsonHelper.getBoolean(o, "to_invite"));

    if (GsonHelper.isNotNull(o.get("extattr"))) {
      JsonArray attrJsonElements = o.get("extattr").getAsJsonObject().get("attrs").getAsJsonArray();
      for (JsonElement attrJsonElement : attrJsonElements) {
        WxCpUser.Attr attr = new WxCpUser.Attr(
          GsonHelper.getString(attrJsonElement.getAsJsonObject(), "name"),
          GsonHelper.getString(attrJsonElement.getAsJsonObject(), "value")
        );
        user.getExtAttrs().add(attr);
      }
    }

    if (GsonHelper.isNotNull(o.get(EXTERNAL_PROFILE))) {
      JsonArray attrJsonElements = o.get(EXTERNAL_PROFILE).getAsJsonObject().get(EXTERNAL_ATTR).getAsJsonArray();
      for (JsonElement element : attrJsonElements) {
        final Integer type = GsonHelper.getInteger(element.getAsJsonObject(), "type");
        final String name = GsonHelper.getString(element.getAsJsonObject(), "name");

        switch (type) {
          case 0: {
            user.getExternalAttrs()
              .add(WxCpUser.ExternalAttribute.builder()
                .type(type)
                .name(name)
                .value(GsonHelper.getString(element.getAsJsonObject().get("text").getAsJsonObject(), "value"))
                .build()
              );
            break;
          }
          case 1: {
            final JsonObject web = element.getAsJsonObject().get("web").getAsJsonObject();
            user.getExternalAttrs()
              .add(WxCpUser.ExternalAttribute.builder()
                .type(type)
                .name(name)
                .url(GsonHelper.getString(web, "url"))
                .title(GsonHelper.getString(web, "title"))
                .build()
              );
            break;
          }
          case 2: {
            final JsonObject miniprogram = element.getAsJsonObject().get("miniprogram").getAsJsonObject();
            user.getExternalAttrs()
              .add(WxCpUser.ExternalAttribute.builder()
                .type(type)
                .name(name)
                .appid(GsonHelper.getString(miniprogram, "appid"))
                .pagePath(GsonHelper.getString(miniprogram, "pagepath"))
                .title(GsonHelper.getString(miniprogram, "title"))
                .build()
              );
            break;
          }
          default://ignored
        }
      }
    }
    return user;
  }

  @Override
  public JsonElement serialize(WxCpUser user, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject o = new JsonObject();
    if (user.getUserId() != null) {
      o.addProperty("userid", user.getUserId());
    }
    if (user.getName() != null) {
      o.addProperty("name", user.getName());
    }
    if (user.getDepartIds() != null) {
      JsonArray jsonArray = new JsonArray();
      for (Integer departId : user.getDepartIds()) {
        jsonArray.add(new JsonPrimitive(departId));
      }
      o.add("department", jsonArray);
    }
    if (user.getPosition() != null) {
      o.addProperty("position", user.getPosition());
    }
    if (user.getMobile() != null) {
      o.addProperty("mobile", user.getMobile());
    }
    if (user.getGender() != null) {
      o.addProperty("gender", user.getGender().getCode());
    }
    if (user.getEmail() != null) {
      o.addProperty("email", user.getEmail());
    }
    if (user.getAvatar() != null) {
      o.addProperty("avatar", user.getAvatar());
    }
    if (user.getAvatarMediaId() != null) {
      o.addProperty("avatar_mediaid", user.getAvatarMediaId());
    }
    if (user.getStatus() != null) {
      o.addProperty("status", user.getStatus());
    }
    if (user.getEnable() != null) {
      o.addProperty("enable", user.getEnable());
    }
    if (user.getIsLeader() != null) {
      o.addProperty("isleader", user.getIsLeader());
    }
    if (user.getHideMobile() != null) {
      o.addProperty("hide_mobile", user.getHideMobile());
    }
    if (user.getEnglishName() != null) {
      o.addProperty("english_name", user.getEnglishName());
    }
    if (user.getTelephone() != null) {
      o.addProperty("telephone", user.getTelephone());
    }
    if (user.getQrCode() != null) {
      o.addProperty("qr_code", user.getQrCode());
    }
    if (user.getToInvite() != null) {
      o.addProperty("to_invite", user.getToInvite());
    }

    if (user.getExtAttrs().size() > 0) {
      JsonArray attrsJsonArray = new JsonArray();
      for (WxCpUser.Attr attr : user.getExtAttrs()) {
        JsonObject attrJson = new JsonObject();
        attrJson.addProperty("name", attr.getName());
        attrJson.addProperty("value", attr.getValue());
        attrsJsonArray.add(attrJson);
      }
      JsonObject attrsJson = new JsonObject();
      attrsJson.add("attrs", attrsJsonArray);
      o.add("extattr", attrsJson);
    }

    if (user.getExternalAttrs().size() > 0) {
      JsonArray attrsJsonArray = new JsonArray();
      for (WxCpUser.ExternalAttribute attr : user.getExternalAttrs()) {
        JsonObject attrJson = new JsonObject();
        attrJson.addProperty("type",attr.getType());
        attrJson.addProperty("name", attr.getName());
        switch (attr.getType()) {
          case 0: {
            JsonObject text = new JsonObject();
            text.addProperty("value", attr.getValue());
            attrJson.add("text", text);
            break;
          }
          case 1: {
            JsonObject web = new JsonObject();
            web.addProperty("url", attr.getUrl());
            web.addProperty("title", attr.getTitle());
            attrJson.add("web", web);
            break;
          }
          case 2: {
            JsonObject miniprogram = new JsonObject();
            miniprogram.addProperty("appid", attr.getAppid());
            miniprogram.addProperty("pagepath", attr.getPagePath());
            miniprogram.addProperty("title", attr.getTitle());
            attrJson.add("miniprogram", miniprogram);
            break;
          }
          default://忽略
        }
        attrsJsonArray.add(attrJson);
      }

      JsonObject attrsJson = new JsonObject();
      attrsJson.add(EXTERNAL_ATTR, attrsJsonArray);
      o.add(EXTERNAL_PROFILE, attrsJson);
    }

    return o;
  }

}

/*
 * KINGSTAR MEDIA SOLUTIONS Co.,LTD. Copyright c 2005-2013. All rights reserved.
 *
 * This source code is the property of KINGSTAR MEDIA SOLUTIONS LTD. It is intended
 * only for the use of KINGSTAR MEDIA application development. Reengineering, reproduction
 * arose from modification of the original source, or other redistribution of this source
 * is not permitted without written permission of the KINGSTAR MEDIA SOLUTIONS LTD.
 */

package me.chanjar.weixin.cp.util.json;

import com.google.gson.*;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.cp.bean.Gender;
import me.chanjar.weixin.cp.bean.WxCpUser;

import java.lang.reflect.Type;

/**
 * cp user gson adapter.
 *
 * @author Daniel Qian
 */
public class WxCpUserGsonAdapter implements JsonDeserializer<WxCpUser>, JsonSerializer<WxCpUser> {
  private static final String EXTERNAL_PROFILE = "external_profile";
  private static final String EXTERNAL_ATTR = "external_attr";
  private static final String EXTRA_ATTR = "extattr";
  private static final String EXTERNAL_POSITION = "external_position";
  private static final String DEPARTMENT = "department";
  private static final String EXTERNAL_CORP_NAME = "external_corp_name";

  @Override
  public WxCpUser deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject o = json.getAsJsonObject();
    WxCpUser user = new WxCpUser();

    if (o.get(DEPARTMENT) != null) {
      JsonArray departJsonArray = o.get(DEPARTMENT).getAsJsonArray();
      Long[] departIds = new Long[departJsonArray.size()];
      int i = 0;
      for (JsonElement jsonElement : departJsonArray) {
        departIds[i++] = jsonElement.getAsLong();
      }
      user.setDepartIds(departIds);
    }

    if (o.get("order") != null) {
      JsonArray departJsonArray = o.get("order").getAsJsonArray();
      Integer[] orders = new Integer[departJsonArray.size()];
      int i = 0;
      for (JsonElement jsonElement : departJsonArray) {
        orders[i++] = jsonElement.getAsInt();
      }
      user.setOrders(orders);
    }

    if (o.get("positions") != null) {
      JsonArray positionJsonArray = o.get("positions").getAsJsonArray();
      String[] positions = new String[positionJsonArray.size()];
      int i = 0;
      for (JsonElement jsonElement : positionJsonArray) {
        positions[i++] = jsonElement.getAsString();
      }
      user.setPositions(positions);
    }

    user.setUserId(GsonHelper.getString(o, "userid"));
    user.setName(GsonHelper.getString(o, "name"));
    user.setPosition(GsonHelper.getString(o, "position"));
    user.setMobile(GsonHelper.getString(o, "mobile"));
    user.setGender(Gender.fromCode(GsonHelper.getString(o, "gender")));
    user.setEmail(GsonHelper.getString(o, "email"));
    user.setAvatar(GsonHelper.getString(o, "avatar"));
    user.setThumbAvatar(GsonHelper.getString(o, "thumb_avatar"));
    user.setAddress(GsonHelper.getString(o, "address"));
    user.setAvatarMediaId(GsonHelper.getString(o, "avatar_mediaid"));
    user.setStatus(GsonHelper.getInteger(o, "status"));
    user.setEnable(GsonHelper.getInteger(o, "enable"));
    user.setAlias(GsonHelper.getString(o, "alias"));
    user.setIsLeader(GsonHelper.getInteger(o, "isleader"));
    user.setIsLeaderInDept(GsonHelper.getIntArray(o, "is_leader_in_dept"));
    user.setHideMobile(GsonHelper.getInteger(o, "hide_mobile"));
    user.setEnglishName(GsonHelper.getString(o, "english_name"));
    user.setTelephone(GsonHelper.getString(o, "telephone"));
    user.setQrCode(GsonHelper.getString(o, "qr_code"));
    user.setToInvite(GsonHelper.getBoolean(o, "to_invite"));
    user.setMainDepartment(GsonHelper.getString(o, "main_department"));

    if (GsonHelper.isNotNull(o.get(EXTRA_ATTR))) {
      this.buildExtraAttrs(o, user);
    }

    if (GsonHelper.isNotNull(o.get(EXTERNAL_PROFILE))) {
      user.setExternalCorpName(GsonHelper.getString(o.getAsJsonObject().get(EXTERNAL_PROFILE).getAsJsonObject(), EXTERNAL_CORP_NAME));
      this.buildExternalAttrs(o, user);
    }

    user.setExternalPosition(GsonHelper.getString(o, EXTERNAL_POSITION));

    return user;
  }

  private void buildExtraAttrs(JsonObject o, WxCpUser user) {
    JsonArray attrJsonElements = o.get(EXTRA_ATTR).getAsJsonObject().get("attrs").getAsJsonArray();
    for (JsonElement attrJsonElement : attrJsonElements) {
      final Integer type = GsonHelper.getInteger(attrJsonElement.getAsJsonObject(), "type");
      final WxCpUser.Attr attr = new WxCpUser.Attr().setType(type)
        .setName(GsonHelper.getString(attrJsonElement.getAsJsonObject(), "name"));
      user.getExtAttrs().add(attr);

      if (type == null) {
        attr.setTextValue(GsonHelper.getString(attrJsonElement.getAsJsonObject(), "value"));
        continue;
      }

      switch (type) {
        case 0: {
          attr.setTextValue(GsonHelper.getString(attrJsonElement.getAsJsonObject().get("text").getAsJsonObject(), "value"));
          break;
        }
        case 1: {
          final JsonObject web = attrJsonElement.getAsJsonObject().get("web").getAsJsonObject();
          attr.setWebTitle(GsonHelper.getString(web, "title"))
            .setWebUrl(GsonHelper.getString(web, "url"));
          break;
        }
        default://ignored
      }
    }
  }

  private void buildExternalAttrs(JsonObject o, WxCpUser user) {
    JsonArray attrJsonElements = o.get(EXTERNAL_PROFILE).getAsJsonObject().get(EXTERNAL_ATTR).getAsJsonArray();
    for (JsonElement element : attrJsonElements) {
      final Integer type = GsonHelper.getInteger(element.getAsJsonObject(), "type");
      final String name = GsonHelper.getString(element.getAsJsonObject(), "name");

      if (type == null) {
        continue;
      }

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
      for (Long departId : user.getDepartIds()) {
        jsonArray.add(new JsonPrimitive(departId));
      }
      o.add("department", jsonArray);
    }

    if (user.getOrders() != null) {
      JsonArray jsonArray = new JsonArray();
      for (Integer order : user.getOrders()) {
        jsonArray.add(new JsonPrimitive(order));
      }
      o.add("order", jsonArray);
    }

    if (user.getPosition() != null) {
      o.addProperty("position", user.getPosition());
    }

    if (user.getPositions() != null) {
      JsonArray jsonArray = new JsonArray();
      for (String position : user.getPositions()) {
        jsonArray.add(new JsonPrimitive(position));
      }
      o.add("positions", jsonArray);
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
    if (user.getThumbAvatar() != null) {
      o.addProperty("thumb_avatar", user.getThumbAvatar());
    }
    if (user.getAddress() != null) {
      o.addProperty("address", user.getAddress());
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
    if (user.getAlias() != null) {
      o.addProperty("alias", user.getAlias());
    }
    if (user.getIsLeader() != null) {
      o.addProperty("isleader", user.getIsLeader());
    }
    if (user.getIsLeaderInDept() != null && user.getIsLeaderInDept().length > 0) {
      JsonArray ary = new JsonArray();
      for (int item : user.getIsLeaderInDept()) {
        ary.add(item);
      }
      o.add("is_leader_in_dept", ary);
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
    if (user.getMainDepartment() != null) {
      o.addProperty("main_department", user.getMainDepartment());
    }

    if (user.getExtAttrs().size() > 0) {
      JsonArray attrsJsonArray = new JsonArray();
      for (WxCpUser.Attr attr : user.getExtAttrs()) {
        JsonObject attrJson = new JsonObject();

        attrsJsonArray.add(attrJson);

        if (attr.getType() == null) {
          attrJson.addProperty("name", attr.getName());
          attrJson.addProperty("value", attr.getTextValue());
          continue;
        }

        switch (attr.getType()) {
          case 0: {
            JsonObject text = new JsonObject();
            text.addProperty("value", attr.getTextValue());
            attrJson.add("text", text);
            break;
          }
          case 1: {
            JsonObject web = new JsonObject();
            web.addProperty("url", attr.getWebUrl());
            web.addProperty("title", attr.getWebTitle());
            attrJson.add("web", web);
            break;
          }
          default: //ignored
        }
      }
      JsonObject attrsJson = new JsonObject();
      attrsJson.add("attrs", attrsJsonArray);
      o.add(EXTRA_ATTR, attrsJson);
    }

    if (user.getExternalPosition() != null) {
      o.addProperty(EXTERNAL_POSITION, user.getExternalPosition());
    }

    JsonObject attrsJson = new JsonObject();
    o.add(EXTERNAL_PROFILE, attrsJson);

    if (user.getExternalCorpName() != null) {
      attrsJson.addProperty(EXTERNAL_CORP_NAME, user.getExternalCorpName());
    }

    if (user.getExternalAttrs().size() > 0) {
      JsonArray attrsJsonArray = new JsonArray();
      for (WxCpUser.ExternalAttribute attr : user.getExternalAttrs()) {
        JsonObject attrJson = new JsonObject();
        attrJson.addProperty("type", attr.getType());
        attrJson.addProperty("name", attr.getName());

        attrsJsonArray.add(attrJson);

        if (attr.getType() == null) {
          continue;
        }

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
      }

      attrsJson.add(EXTERNAL_ATTR, attrsJsonArray);
    }

    return o;
  }

}

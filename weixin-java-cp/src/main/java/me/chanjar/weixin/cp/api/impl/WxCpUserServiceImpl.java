package me.chanjar.weixin.cp.api.impl;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.WxCpUserService;
import me.chanjar.weixin.cp.bean.WxCpInviteResult;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.WxCpUserExternalContactInfo;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * <pre>
 *  Created by BinaryWang on 2017/6/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxCpUserServiceImpl implements WxCpUserService {
  private WxCpService mainService;

  public WxCpUserServiceImpl(WxCpService mainService) {
    this.mainService = mainService;
  }

  @Override
  public void authenticate(String userId) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/user/authsucc?userid=" + userId;
    this.mainService.get(url, null);
  }

  @Override
  public void create(WxCpUser user) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/user/create";
    this.mainService.post(url, user.toJson());
  }

  @Override
  public void update(WxCpUser user) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/user/update";
    this.mainService.post(url, user.toJson());
  }

  @Override
  public void delete(String... userIds) throws WxErrorException {
    if (userIds.length == 1) {
      String url = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?userid=" + userIds[0];
      this.mainService.get(url, null);
      return;
    }

    String url = "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete";
    JsonObject jsonObject = new JsonObject();
    JsonArray jsonArray = new JsonArray();
    for (String userid : userIds) {
      jsonArray.add(new JsonPrimitive(userid));
    }
    jsonObject.add("useridlist", jsonArray);
    this.mainService.post(url, jsonObject.toString());
  }

  @Override
  public WxCpUser getById(String userid) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?userid=" + userid;
    String responseContent = this.mainService.get(url, null);
    return WxCpUser.fromJson(responseContent);
  }

  @Override
  public List<WxCpUser> listByDepartment(Long departId, Boolean fetchChild, Integer status) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/user/list?department_id=" + departId;
    String params = "";
    if (fetchChild != null) {
      params += "&fetch_child=" + (fetchChild ? "1" : "0");
    }
    if (status != null) {
      params += "&status=" + status;
    } else {
      params += "&status=0";
    }

    String responseContent = this.mainService.get(url, params);
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);
    return WxCpGsonBuilder.create()
      .fromJson(tmpJsonElement.getAsJsonObject().get("userlist"),
        new TypeToken<List<WxCpUser>>() {
        }.getType()
      );
  }

  @Override
  public List<WxCpUser> listSimpleByDepartment(Long departId, Boolean fetchChild, Integer status) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?department_id=" + departId;
    String params = "";
    if (fetchChild != null) {
      params += "&fetch_child=" + (fetchChild ? "1" : "0");
    }
    if (status != null) {
      params += "&status=" + status;
    } else {
      params += "&status=0";
    }

    String responseContent = this.mainService.get(url, params);
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);
    return WxCpGsonBuilder.create()
      .fromJson(
        tmpJsonElement.getAsJsonObject().get("userlist"),
        new TypeToken<List<WxCpUser>>() {
        }.getType()
      );
  }

  @Override
  public WxCpInviteResult invite(List<String> userIds, List<String> partyIds, List<String> tagIds) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/batch/invite";
    JsonObject jsonObject = new JsonObject();
    if (userIds != null) {
      JsonArray jsonArray = new JsonArray();
      for (String userId : userIds) {
        jsonArray.add(new JsonPrimitive(userId));
      }
      jsonObject.add("user", jsonArray);
    }

    if (partyIds != null) {
      JsonArray jsonArray = new JsonArray();
      for (String userId : partyIds) {
        jsonArray.add(new JsonPrimitive(userId));
      }
      jsonObject.add("party", jsonArray);
    }

    if (tagIds != null) {
      JsonArray jsonArray = new JsonArray();
      for (String tagId : tagIds) {
        jsonArray.add(new JsonPrimitive(tagId));
      }
      jsonObject.add("tag", jsonArray);
    }

    return WxCpInviteResult.fromJson(this.mainService.post(url, jsonObject.toString()));
  }

  @Override
  public Map<String, String> userId2Openid(String userId, Integer agentId) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid";
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userid", userId);
    if (agentId != null) {
      jsonObject.addProperty("agentid", agentId);
    }

    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);
    Map<String, String> result = Maps.newHashMap();
    if (tmpJsonElement.getAsJsonObject().get("openid") != null) {
      result.put("openid", tmpJsonElement.getAsJsonObject().get("openid").getAsString());
    }

    if (tmpJsonElement.getAsJsonObject().get("appid") != null) {
      result.put("appid", tmpJsonElement.getAsJsonObject().get("appid").getAsString());
    }

    return result;
  }

  @Override
  public String openid2UserId(String openid) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_userid";
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("openid", openid);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);
    return tmpJsonElement.getAsJsonObject().get("userid").getAsString();
  }

  @Override
  public WxCpUserExternalContactInfo getExternalContact(String userId) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/crm/get_external_contact?external_userid=" + userId;
    String responseContent = this.mainService.get(url, null);
    return WxCpUserExternalContactInfo.fromJson(responseContent);
  }
}

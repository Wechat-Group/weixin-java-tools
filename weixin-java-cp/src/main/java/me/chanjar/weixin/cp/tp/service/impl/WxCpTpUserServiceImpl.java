package me.chanjar.weixin.cp.tp.service.impl;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.bean.WxCpInviteResult;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.bean.WxCpUserExternalContactInfo;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.tp.service.WxCpTpUserService;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.User.*;

/**
 * <pre>
 *  Created by jamie on 2020/7/22.
 * </pre>
 */
@RequiredArgsConstructor
public class WxCpTpUserServiceImpl implements WxCpTpUserService {
  private final WxCpTpService mainService;

  @Override
  public void authenticate(String corpId, String userId) throws WxErrorException {
    this.mainService.get(mainService.getCorpApiUrl(USER_AUTHENTICATE + userId, corpId), null);
  }

  @Override
  public void create(String corpId, WxCpUser user) throws WxErrorException {
    String url = mainService.getCorpApiUrl(USER_CREATE, corpId);
    this.mainService.post(url, user.toJson());
  }

  @Override
  public void update(String corpId, WxCpUser user) throws WxErrorException {
    String url = mainService.getCorpApiUrl(USER_UPDATE, corpId);
    this.mainService.post(url, user.toJson());
  }

  @Override
  public void delete(String corpId, String[] userIds) throws WxErrorException {
    if (userIds.length == 1) {
      String url = mainService.getCorpApiUrl(USER_DELETE + userIds[0], corpId);
      this.mainService.get(url, null);
      return;
    }

    JsonObject jsonObject = new JsonObject();
    JsonArray jsonArray = new JsonArray();
    for (String userId : userIds) {
      jsonArray.add(new JsonPrimitive(userId));
    }

    jsonObject.add("useridlist", jsonArray);
    this.mainService.post(mainService.getCorpApiUrl(USER_BATCH_DELETE, corpId),
      jsonObject.toString());
  }

  @Override
  public WxCpUser getById(String corpId, String userid) throws WxErrorException {
    String url = mainService.getCorpApiUrl(USER_GET + userid, corpId);
    String responseContent = this.mainService.get(url, null);
    return WxCpUser.fromJson(responseContent);
  }

  @Override
  public List<WxCpUser> listByDepartment(String corpId, Long departId, Boolean fetchChild, Integer status) throws WxErrorException {
    String params = "";
    if (fetchChild != null) {
      params += "&fetch_child=" + (fetchChild ? "1" : "0");
    }
    if (status != null) {
      params += "&status=" + status;
    } else {
      params += "&status=0";
    }

    String url = mainService.getCorpApiUrl(USER_LIST + departId, corpId);
    String responseContent = this.mainService.get(url, params);
    JsonObject tmpJsonElement = GsonParser.parse(responseContent);
    return WxCpGsonBuilder.create()
      .fromJson(tmpJsonElement.getAsJsonObject().get("userlist"),
        new TypeToken<List<WxCpUser>>() {
        }.getType()
      );
  }

  @Override
  public List<WxCpUser> listSimpleByDepartment(String corpId, Long departId, Boolean fetchChild, Integer status)
    throws WxErrorException {
    String params = "";
    if (fetchChild != null) {
      params += "&fetch_child=" + (fetchChild ? "1" : "0");
    }
    if (status != null) {
      params += "&status=" + status;
    } else {
      params += "&status=0";
    }

    String url = mainService.getCorpApiUrl(USER_SIMPLE_LIST + departId, corpId);
    String responseContent = this.mainService.get(url, params);
    JsonObject tmpJsonElement = GsonParser.parse(responseContent);
    return WxCpGsonBuilder.create()
      .fromJson(
        tmpJsonElement.getAsJsonObject().get("userlist"),
        new TypeToken<List<WxCpUser>>() {
        }.getType()
      );
  }

  @Override
  public WxCpInviteResult invite(String corpId, List<String> userIds, List<String> partyIds, List<String> tagIds)
    throws WxErrorException {
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

    String url = mainService.getCorpApiUrl(BATCH_INVITE, corpId);
    return WxCpInviteResult.fromJson(this.mainService.post(url, jsonObject.toString()));
  }

  @Override
  public Map<String, String> userId2Openid(String corpId, String userId, Integer agentId) throws WxErrorException {
    String url = mainService.getCorpApiUrl(USER_CONVERT_TO_OPENID, corpId);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userid", userId);
    if (agentId != null) {
      jsonObject.addProperty("agentid", agentId);
    }

    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonObject tmpJsonElement = GsonParser.parse(responseContent);
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
  public String openid2UserId(String corpId, String openid) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("openid", openid);
    String url = mainService.getCorpApiUrl(USER_CONVERT_TO_USERID, corpId);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonObject tmpJsonElement = GsonParser.parse(responseContent);
    return tmpJsonElement.getAsJsonObject().get("userid").getAsString();
  }

  @Override
  public String getUserId(String corpId, String mobile) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("mobile", mobile);
    String url = mainService.getCorpApiUrl(GET_USER_ID, corpId);
    String responseContent = this.mainService.post(url, jsonObject.toString());
    JsonObject tmpJsonElement = GsonParser.parse(responseContent);
    return tmpJsonElement.getAsJsonObject().get("userid").getAsString();
  }

  @Override
  public WxCpUserExternalContactInfo getExternalContact(String corpId, String userId) throws WxErrorException {
    String url = mainService.getCorpApiUrl(GET_EXTERNAL_CONTACT + userId, corpId);
    String responseContent = this.mainService.get(url, null);
    return WxCpUserExternalContactInfo.fromJson(responseContent);
  }
}

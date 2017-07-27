package me.chanjar.weixin.cp.api.impl;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.WxCpUserService;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

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
  public List<WxCpUser> listByDepartment(Integer departId, Boolean fetchChild, Integer status) throws WxErrorException {
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
    return WxCpGsonBuilder.INSTANCE.create()
      .fromJson(tmpJsonElement.getAsJsonObject().get("userlist"),
        new TypeToken<List<WxCpUser>>() {
        }.getType()
      );
  }

  @Override
  public List<WxCpUser> listSimpleByDepartment(Integer departId, Boolean fetchChild, Integer status) throws WxErrorException {
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
    return WxCpGsonBuilder.INSTANCE.create()
      .fromJson(
        tmpJsonElement.getAsJsonObject().get("userlist"),
        new TypeToken<List<WxCpUser>>() {
        }.getType()
      );
  }

}

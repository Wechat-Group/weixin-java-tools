package me.chanjar.weixin.cp.api.impl;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.WxCpTagService;
import me.chanjar.weixin.cp.bean.WxCpTag;
import me.chanjar.weixin.cp.bean.WxCpTagAddOrRemoveUsersResult;
import me.chanjar.weixin.cp.bean.WxCpTagGetResult;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * <pre>
 *  标签管理接口
 * Created by Binary Wang on 2017-6-25.
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * </pre>
 */
public class WxCpTagServiceImpl implements WxCpTagService {
  private WxCpService mainService;

  public WxCpTagServiceImpl(WxCpService mainService) {
    this.mainService = mainService;
  }

  @Override
  public String create(String tagName) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/create";
    JsonObject o = new JsonObject();
    o.addProperty("tagname", tagName);
    String responseContent = this.mainService.post(url, o.toString());
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);
    return tmpJsonElement.getAsJsonObject().get("tagid").getAsString();
  }

  @Override
  public void update(String tagId, String tagName) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/update";
    JsonObject o = new JsonObject();
    o.addProperty("tagid", tagId);
    o.addProperty("tagname", tagName);
    this.mainService.post(url, o.toString());
  }

  @Override
  public void delete(String tagId) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/delete?tagid=" + tagId;
    this.mainService.get(url, null);
  }

  @Override
  public List<WxCpTag> listAll() throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/list";
    String responseContent = this.mainService.get(url, null);
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);
    return WxCpGsonBuilder.create()
      .fromJson(
        tmpJsonElement.getAsJsonObject().get("taglist"),
        new TypeToken<List<WxCpTag>>() {
        }.getType()
      );
  }

  @Override
  public List<WxCpUser> listUsersByTagId(String tagId) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/get?tagid=" + tagId;
    String responseContent = this.mainService.get(url, null);
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);
    return WxCpGsonBuilder.create()
      .fromJson(
        tmpJsonElement.getAsJsonObject().get("userlist"),
        new TypeToken<List<WxCpUser>>() {
        }.getType()
      );
  }

  @Override
  public WxCpTagAddOrRemoveUsersResult addUsers2Tag(String tagId, List<String> userIds, List<String> partyIds) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/addtagusers";
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("tagid", tagId);
    this.addUserIdsAndPartyIdsToJson(userIds, partyIds, jsonObject);

    return WxCpTagAddOrRemoveUsersResult.fromJson(this.mainService.post(url, jsonObject.toString()));
  }

  @Override
  public WxCpTagAddOrRemoveUsersResult removeUsersFromTag(String tagId, List<String> userIds, List<String> partyIds) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/deltagusers";
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("tagid", tagId);
    this.addUserIdsAndPartyIdsToJson(userIds, partyIds, jsonObject);

    return WxCpTagAddOrRemoveUsersResult.fromJson(this.mainService.post(url, jsonObject.toString()));
  }

  private void addUserIdsAndPartyIdsToJson(List<String> userIds, List<String> partyIds, JsonObject jsonObject) {
    if (userIds != null) {
      JsonArray jsonArray = new JsonArray();
      for (String userId : userIds) {
        jsonArray.add(new JsonPrimitive(userId));
      }
      jsonObject.add("userlist", jsonArray);
    }

    if (partyIds != null) {
      JsonArray jsonArray = new JsonArray();
      for (String userId : partyIds) {
        jsonArray.add(new JsonPrimitive(userId));
      }
      jsonObject.add("partylist", jsonArray);
    }
  }

  @Override
  public WxCpTagGetResult get(String tagId) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/get";
    if (tagId != null) {
      url += "?tagId=" + tagId;
    } else {
      throw new IllegalArgumentException("缺少tagId参数");
    }

    String responseContent = this.mainService.get(url, null);

    return WxCpTagGetResult.fromJson(responseContent);
  }
}

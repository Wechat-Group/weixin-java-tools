package me.chanjar.weixin.cp.tp.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.bean.WxCpTpTag;
import me.chanjar.weixin.cp.bean.WxCpTpTagAddOrRemoveUsersResult;
import me.chanjar.weixin.cp.bean.WxCpTpTagGetResult;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.tp.service.WxCpTpTagService;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Tag.*;

/**
 * <pre>
 *   企业微信第三方开发-标签相关接口，部分照搬了WxCpTagServiceImpl
 * </pre>
 *
 * @author zhangq <zhangq002@gmail.com>
 * @since 2021-02-14 16:02
 */
@RequiredArgsConstructor
public class WxCpTpTagServiceImpl implements WxCpTpTagService {
  private final WxCpTpService mainService;

  @Override
  public String create(String corpId, String name, Integer id) throws WxErrorException {
    JsonObject o = new JsonObject();
    o.addProperty("tagname", name);

    if (id != null) {
      o.addProperty("tagid", id);
    }
    return this.create(corpId, o);
  }

  private String create(String corpId, JsonObject param) throws WxErrorException {
    String url = this.mainService.getCorpApiUrl(TAG_CREATE, corpId);
    String responseContent = this.mainService.post(url, param.toString());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    return jsonObject.get("tagid").getAsString();
  }

  @Override
  public void update(String corpId, String tagId, String tagName) throws WxErrorException {
    String url = this.mainService.getCorpApiUrl(TAG_UPDATE, corpId);
    JsonObject o = new JsonObject();
    o.addProperty("tagid", tagId);
    o.addProperty("tagname", tagName);
    this.mainService.post(url, o.toString());
  }

  @Override
  public void delete(String corpId, String tagId) throws WxErrorException {
    String url = String.format(this.mainService.getCorpApiUrl(TAG_DELETE, corpId), tagId);
    this.mainService.get(url, null);
  }

  @Override
  public List<WxCpTpTag> listAll(String corpId) throws WxErrorException {
    String url = this.mainService.getCorpApiUrl(TAG_LIST, corpId);
    String responseContent = this.mainService.get(url, null);
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return WxCpGsonBuilder.create().fromJson(tmpJson.get("taglist"), new TypeToken<List<WxCpTpTag>>() {
      // do nothing
    }.getType());
  }

  @Override
  public WxCpTpTagGetResult get(String corpId, String tagId) throws WxErrorException {
    if (tagId == null) {
      throw new IllegalArgumentException("缺少tagId参数");
    }

    String url = String.format(this.mainService.getCorpApiUrl(TAG_GET, corpId), tagId);
    String responseContent = this.mainService.get(url, null);
    return WxCpTpTagGetResult.deserialize(responseContent);
  }

  @Override
  public WxCpTpTagAddOrRemoveUsersResult addUsers2Tag(String corpId, String tagId, List<String> userIds, List<String> partyIds)
    throws WxErrorException {
    String url = this.mainService.getCorpApiUrl(TAG_ADD_TAG_USERS, corpId);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("tagid", tagId);
    this.addUserIdsAndPartyIdsToJson(userIds, partyIds, jsonObject);

    return WxCpTpTagAddOrRemoveUsersResult.deserialize(this.mainService.post(url, jsonObject.toString()));
  }

  @Override
  public WxCpTpTagAddOrRemoveUsersResult removeUsersFromTag(String corpId, String tagId, List<String> userIds, List<String> partyIds)
    throws WxErrorException {
    String url = this.mainService.getCorpApiUrl(TAG_DEL_TAG_USERS, corpId);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("tagid", tagId);
    this.addUserIdsAndPartyIdsToJson(userIds, partyIds, jsonObject);

    return WxCpTpTagAddOrRemoveUsersResult.deserialize(this.mainService.post(url, jsonObject.toString()));
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

}

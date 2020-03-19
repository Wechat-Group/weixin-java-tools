package me.chanjar.weixin.cp.api.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpExternalContactService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.*;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Date;
import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.ExternalContact.*;

public class WxCpExternalContactServiceImpl implements WxCpExternalContactService {
  private WxCpService mainService;

  public WxCpExternalContactServiceImpl(WxCpService mainService) {
    this.mainService = mainService;
  }

  @Override
  public WxCpUserExternalContactInfo getExternalContact(String userId) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_EXTERNAL_CONTACT + userId);
    String responseContent = this.mainService.get(url, null);
    return WxCpUserExternalContactInfo.fromJson(responseContent);
  }

  @Override
  public WxCpUserExternalContactInfo getContactDetail(String userId) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CONTACT_DETAIL + userId);
    String responseContent = this.mainService.get(url, null);
    return WxCpUserExternalContactInfo.fromJson(responseContent);
  }

  @Override
  public List<String> listExternalContacts(String userId) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(LIST_EXTERNAL_CONTACT + userId);
    String responseContent = this.mainService.get(url, null);
    return WxCpUserExternalContactList.fromJson(responseContent).getExternalUserId();
  }

  @Override
  public List<String> listFollowUser() throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_FOLLOW_USER_LIST);
    String responseContent = this.mainService.get(url, null);
    return WxCpUserWithExternalPermission.fromJson(responseContent).getFollowUser();
  }

  @Override
  public WxCpUserExternalUnassignList listUnassignedList(Integer pageIndex, Integer pageSize) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("page_id",pageIndex == null ? 0 : pageIndex);
    json.addProperty("page_size",pageSize == null ? 100 : pageSize);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(LIST_UNASSIGNED_CONTACT);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalUnassignList.fromJson(result);
  }

  @Override
  public WxCpBaseResp transferExternalContact(String externalUserid, String handOverUserid, String takeOverUserid) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("external_userid", externalUserid);
    json.addProperty("handover_userid", handOverUserid);
    json.addProperty("takeover_userid", takeOverUserid);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(TRANSFER_UNASSIGNED_CONTACT);
    final String result = this.mainService.post(url, json.toString());
    return WxCpBaseResp.fromJson(result);
  }

  @Override
  public WxCpUserExternalGroupChatList listGroupChat(Integer pageIndex, Integer pageSize, int status, String[] userIds, String[] partyIds) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("offset",pageIndex == null ? 0 : pageIndex);
    json.addProperty("limit",pageSize == null ? 100 : pageSize);
    json.addProperty("status_filter",status);
    if(ArrayUtils.isNotEmpty(userIds) || ArrayUtils.isNotEmpty(partyIds)){
      JsonObject ownerFilter = new JsonObject();
      if(ArrayUtils.isNotEmpty(userIds)){
        json.add("userid", new Gson().toJsonTree(userIds).getAsJsonArray());
      }
      if(ArrayUtils.isNotEmpty(partyIds)){
        json.add("partyid", new Gson().toJsonTree(partyIds).getAsJsonArray());
      }
      json.add("owner_filter",ownerFilter);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_CHAT_LIST);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalGroupChatList.fromJson(result);
  }

  @Override
  public WxCpUserExternalGroupChatInfo getGroupChat(String chatId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("chat_id", chatId);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_CHAT_INFO);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalGroupChatInfo.fromJson(result);
  }

  @Override
  public WxCpUserExternalUserBehaviorStatistic getUserBehaviorStatistic(Date startTime, Date endTime, String[] userIds, String[] partyIds) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("start_time", startTime.getTime()/1000);
    json.addProperty("end_time", endTime.getTime()/1000);
    if(ArrayUtils.isNotEmpty(userIds) || ArrayUtils.isNotEmpty(partyIds)){
      if(ArrayUtils.isNotEmpty(userIds)){
        json.add("userid", new Gson().toJsonTree(userIds).getAsJsonArray());
      }
      if(ArrayUtils.isNotEmpty(partyIds)){
        json.add("partyid", new Gson().toJsonTree(partyIds).getAsJsonArray());
      }
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(LIST_USER_BEHAVIOR_DATA);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalUserBehaviorStatistic.fromJson(result);
  }

  @Override
  public WxCpUserExternalGroupChatStatistic getGroupChatStatistic(Date startTime, Integer orderBy, Integer orderAsc, Integer pageIndex, Integer pageSize, String[] userIds, String[] partyIds) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("day_begin_time", startTime.getTime()/1000);
    json.addProperty("order_by", orderBy == null ? 1 : orderBy);
    json.addProperty("order_asc",orderAsc == null ? 0 : orderAsc);
    json.addProperty("offset",pageIndex == null ? 0 : pageIndex);
    json.addProperty("limit",pageSize == null ? 500 : pageSize);
    if(ArrayUtils.isNotEmpty(userIds) || ArrayUtils.isNotEmpty(partyIds)){
      JsonObject ownerFilter = new JsonObject();
      if(ArrayUtils.isNotEmpty(userIds)){
        json.add("userid_list", new Gson().toJsonTree(userIds).getAsJsonArray());
      }
      if(ArrayUtils.isNotEmpty(partyIds)){
        json.add("userid_list", new Gson().toJsonTree(partyIds).getAsJsonArray());
      }
      json.add("owner_filter",ownerFilter);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(LIST_GROUP_CHAT_DATA);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalGroupChatStatistic.fromJson(result);
  }
}

package me.chanjar.weixin.cp.tp.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxCpErrorMsgEnum;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.BeanUtils;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.external.*;
import me.chanjar.weixin.cp.bean.external.contact.WxCpExternalContactBatchInfo;
import me.chanjar.weixin.cp.bean.external.contact.WxCpExternalContactInfo;
import me.chanjar.weixin.cp.tp.service.WxCpTpExternalContactService;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.ExternalContact.*;

/**
 * @author 曹祖鹏 & yuanqixun
 */
@RequiredArgsConstructor
public class WxCpTpExternalContactServiceImpl implements WxCpTpExternalContactService {
  private final WxCpTpService mainService;

  @Override
  public WxCpContactWayResult addContactWay(String corpId, @NonNull WxCpContactWayInfo info) throws WxErrorException {

    if (info.getContactWay().getUsers() != null && info.getContactWay().getUsers().size() > 100) {
      throw new WxRuntimeException("「联系我」使用人数默认限制不超过100人(包括部门展开后的人数)");
    }

    final String url = mainService.getCorpApiUrl(ADD_CONTACT_WAY, corpId);
    String responseContent = this.mainService.post(url, info.getContactWay().toJson());

    return WxCpContactWayResult.fromJson(responseContent);
  }

  @Override
  public WxCpContactWayInfo getContactWay(String corpId, @NonNull String configId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("config_id", configId);

    final String url = mainService.getCorpApiUrl(GET_CONTACT_WAY, corpId);
    String responseContent = this.mainService.post(url, json.toString());
    return WxCpContactWayInfo.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp updateContactWay(String corpId, @NonNull WxCpContactWayInfo info) throws WxErrorException {
    if (StringUtils.isBlank(info.getContactWay().getConfigId())) {
      throw new WxRuntimeException("更新「联系我」方式需要指定configId");
    }
    if (info.getContactWay().getUsers() != null && info.getContactWay().getUsers().size() > 100) {
      throw new WxRuntimeException("「联系我」使用人数默认限制不超过100人(包括部门展开后的人数)");
    }

    final String url = mainService.getCorpApiUrl(UPDATE_CONTACT_WAY, corpId);
    String responseContent = this.mainService.post(url, info.getContactWay().toJson());

    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp deleteContactWay(String corpId, @NonNull String configId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("config_id", configId);

    final String url = mainService.getCorpApiUrl(DEL_CONTACT_WAY, corpId);
    String responseContent = this.mainService.post(url, json.toString());

    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp closeTempChat(String corpId, @NonNull String userId, @NonNull String externalUserId) throws WxErrorException {

    JsonObject json = new JsonObject();
    json.addProperty("userid", userId);
    json.addProperty("external_userid", externalUserId);


    final String url = mainService.getCorpApiUrl(CLOSE_TEMP_CHAT, corpId);
    String responseContent = this.mainService.post(url, json.toString());

    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpExternalContactInfo getExternalContact(String corpId, String externalUserId) throws WxErrorException {
    final String url = mainService.getCorpApiUrl(GET_EXTERNAL_CONTACT + externalUserId, corpId);
    String responseContent = this.mainService.get(url, null);
    return WxCpExternalContactInfo.fromJson(responseContent);
  }

  @Override
  public WxCpExternalContactInfo getContactDetail(String corpId, String externalUserId) throws WxErrorException {
    final String url = mainService.getCorpApiUrl(GET_CONTACT_DETAIL + externalUserId, corpId);
    String responseContent = this.mainService.get(url, null);
    return WxCpExternalContactInfo.fromJson(responseContent);
  }

  @Override
  public String convertToOpenid(String corpId, @NotNull String externalUserId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("external_userid", externalUserId);
    final String url = mainService.getCorpApiUrl(CONVERT_TO_OPENID, corpId);
    String responseContent = this.mainService.post(url, json.toString());
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return tmpJson.get("openid").getAsString();
  }

  @Override
  public WxCpExternalContactBatchInfo getContactDetailBatch(String corpId, String userId, String cursor, Integer limit)
    throws WxErrorException {
    final String url = mainService.getCorpApiUrl(GET_CONTACT_DETAIL_BATCH, corpId);
    JsonObject json = new JsonObject();
    json.addProperty("userid", userId);
    if (StringUtils.isNotBlank(cursor)) {
      json.addProperty("cursor", cursor);
    }
    if (limit != null) {
      json.addProperty("limit", limit);
    }
    String responseContent = this.mainService.post(url, json.toString());
    return WxCpExternalContactBatchInfo.fromJson(responseContent);
  }

  @Override
  public void updateRemark(String corpId, WxCpUpdateRemarkRequest request) throws WxErrorException {
    final String url = mainService.getCorpApiUrl(UPDATE_REMARK, corpId);
    this.mainService.post(url, request.toJson());
  }

  @Override
  public List<String> listExternalContacts(String corpId, String userId) throws WxErrorException {
    final String url = mainService.getCorpApiUrl(LIST_EXTERNAL_CONTACT + userId, corpId);
    try {
      String responseContent = this.mainService.get(url, null);
      return WxCpUserExternalContactList.fromJson(responseContent).getExternalUserId();
    } catch (WxErrorException e) {
      // not external contact,无客户则返回空列表
      if (e.getError().getErrorCode() == WxCpErrorMsgEnum.CODE_84061.getCode()) {
        return Collections.emptyList();
      }
      throw e;
    }
  }

  @Override
  public List<String> listFollowers(String corpId) throws WxErrorException {
    final String url = mainService.getCorpApiUrl(GET_FOLLOW_USER_LIST, corpId);
    String responseContent = this.mainService.get(url, null);
    return WxCpUserWithExternalPermission.fromJson(responseContent).getFollowers();
  }

  @Override
  public WxCpUserExternalUnassignList listUnassignedList(String corpId, Integer pageIndex, Integer pageSize) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("page_id", pageIndex == null ? 0 : pageIndex);
    json.addProperty("page_size", pageSize == null ? 100 : pageSize);
    final String url = mainService.getCorpApiUrl(LIST_UNASSIGNED_CONTACT, corpId);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalUnassignList.fromJson(result);
  }

  @Override
  public WxCpBaseResp transferExternalContact(String corpId, String externalUserid, String handOverUserid, String takeOverUserid) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("external_userid", externalUserid);
    json.addProperty("handover_userid", handOverUserid);
    json.addProperty("takeover_userid", takeOverUserid);
    final String url = mainService.getCorpApiUrl(TRANSFER_UNASSIGNED_CONTACT, corpId);
    final String result = this.mainService.post(url, json.toString());
    return WxCpBaseResp.fromJson(result);
  }

  @Override
  public WxCpUserTransferCustomerResp transferCustomer(String corpId, WxCpUserTransferCustomerReq req) throws WxErrorException {
    BeanUtils.checkRequiredFields(req);
    final String url = mainService.getCorpApiUrl(TRANSFER_CUSTOMER, corpId);
    final String result = this.mainService.post(url, req.toJson());
    return WxCpUserTransferCustomerResp.fromJson(result);
  }

  @Override
  public WxCpUserTransferResultResp transferResult(String corpId, @NotNull String handOverUserid, @NotNull String takeOverUserid, String cursor) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("cursor", cursor);
    json.addProperty("handover_userid", handOverUserid);
    json.addProperty("takeover_userid", takeOverUserid);
    final String url = mainService.getCorpApiUrl(TRANSFER_RESULT, corpId);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserTransferResultResp.fromJson(result);
  }

  @Override
  public WxCpUserTransferCustomerResp resignedTransferCustomer(String corpId, WxCpUserTransferCustomerReq req) throws WxErrorException {
    BeanUtils.checkRequiredFields(req);
    final String url = mainService.getCorpApiUrl(RESIGNED_TRANSFER_CUSTOMER, corpId);
    final String result = this.mainService.post(url, req.toJson());
    return WxCpUserTransferCustomerResp.fromJson(result);
  }

  @Override
  public WxCpUserTransferResultResp resignedTransferResult(String corpId, @NotNull String handOverUserid, @NotNull String takeOverUserid, String cursor) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("cursor", cursor);
    json.addProperty("handover_userid", handOverUserid);
    json.addProperty("takeover_userid", takeOverUserid);
    final String url = mainService.getCorpApiUrl(RESIGNED_TRANSFER_RESULT, corpId);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserTransferResultResp.fromJson(result);
  }

  @Override
  public WxCpUserExternalGroupChatList listGroupChat(String corpId, Integer pageIndex, Integer pageSize, int status, String[] userIds, String[] partyIds) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("offset", pageIndex == null ? 0 : pageIndex);
    json.addProperty("limit", pageSize == null ? 100 : pageSize);
    json.addProperty("status_filter", status);
    if (ArrayUtils.isNotEmpty(userIds) || ArrayUtils.isNotEmpty(partyIds)) {
      JsonObject ownerFilter = new JsonObject();
      if (ArrayUtils.isNotEmpty(userIds)) {
        ownerFilter.add("userid_list", new Gson().toJsonTree(userIds).getAsJsonArray());
      }
      if (ArrayUtils.isNotEmpty(partyIds)) {
        ownerFilter.add("partyid_list", new Gson().toJsonTree(partyIds).getAsJsonArray());
      }
      json.add("owner_filter", ownerFilter);
    }
    final String url = mainService.getCorpApiUrl(GROUP_CHAT_LIST, corpId);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalGroupChatList.fromJson(result);
  }

  @Override
  public WxCpUserExternalGroupChatList listGroupChat(String corpId, Integer limit, String cursor, int status, String[] userIds) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("cursor", cursor == null ? "" : cursor);
    json.addProperty("limit", limit == null ? 100 : limit);
    json.addProperty("status_filter", status);
    if (ArrayUtils.isNotEmpty(userIds)) {
      JsonObject ownerFilter = new JsonObject();
      if (ArrayUtils.isNotEmpty(userIds)) {
        ownerFilter.add("userid_list", new Gson().toJsonTree(userIds).getAsJsonArray());
      }
      json.add("owner_filter", ownerFilter);
    }
    final String url = mainService.getCorpApiUrl(GROUP_CHAT_LIST, corpId);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalGroupChatList.fromJson(result);
  }

  @Override
  public WxCpUserExternalGroupChatInfo getGroupChat(String corpId, String chatId, Integer needName) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("chat_id", chatId);
    json.addProperty("need_name", needName);
    final String url = mainService.getCorpApiUrl(GROUP_CHAT_INFO, corpId);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalGroupChatInfo.fromJson(result);
  }

  @Override
  public WxCpUserExternalGroupChatTransferResp transferGroupChat(String corpId, String[] chatIds, String newOwner) throws WxErrorException {
    JsonObject json = new JsonObject();
    if (ArrayUtils.isNotEmpty(chatIds)) {
      json.add("chat_id_list", new Gson().toJsonTree(chatIds).getAsJsonArray());
    }
    json.addProperty("new_owner", newOwner);
    final String url = mainService.getCorpApiUrl(GROUP_CHAT_TRANSFER, corpId);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalGroupChatTransferResp.fromJson(result);
  }

  @Override
  public WxCpUserExternalUserBehaviorStatistic getUserBehaviorStatistic(String corpId, Date startTime, Date endTime, String[] userIds, String[] partyIds) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("start_time", startTime.getTime() / 1000);
    json.addProperty("end_time", endTime.getTime() / 1000);
    if (ArrayUtils.isNotEmpty(userIds) || ArrayUtils.isNotEmpty(partyIds)) {
      if (ArrayUtils.isNotEmpty(userIds)) {
        json.add("userid", new Gson().toJsonTree(userIds).getAsJsonArray());
      }
      if (ArrayUtils.isNotEmpty(partyIds)) {
        json.add("partyid", new Gson().toJsonTree(partyIds).getAsJsonArray());
      }
    }
    final String url = mainService.getCorpApiUrl(LIST_USER_BEHAVIOR_DATA, corpId);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalUserBehaviorStatistic.fromJson(result);
  }

  @Override
  public WxCpUserExternalGroupChatStatistic getGroupChatStatistic(String corpId, Date startTime, Integer orderBy, Integer orderAsc, Integer pageIndex, Integer pageSize, String[] userIds, String[] partyIds) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("day_begin_time", startTime.getTime() / 1000);
    json.addProperty("order_by", orderBy == null ? 1 : orderBy);
    json.addProperty("order_asc", orderAsc == null ? 0 : orderAsc);
    json.addProperty("offset", pageIndex == null ? 0 : pageIndex);
    json.addProperty("limit", pageSize == null ? 500 : pageSize);
    if (ArrayUtils.isNotEmpty(userIds) || ArrayUtils.isNotEmpty(partyIds)) {
      JsonObject ownerFilter = new JsonObject();
      if (ArrayUtils.isNotEmpty(userIds)) {
        ownerFilter.add("userid_list", new Gson().toJsonTree(userIds).getAsJsonArray());
      }
      if (ArrayUtils.isNotEmpty(partyIds)) {
        ownerFilter.add("partyid_list", new Gson().toJsonTree(partyIds).getAsJsonArray());
      }
      json.add("owner_filter", ownerFilter);
    }
    final String url = mainService.getCorpApiUrl(LIST_GROUP_CHAT_DATA, corpId);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalGroupChatStatistic.fromJson(result);
  }

  @Override
  public WxCpMsgTemplateAddResult addMsgTemplate(String corpId, WxCpUserExternalMsgTemplate wxCpUserExternalMsgTemplate) throws WxErrorException {
    final String url = mainService.getCorpApiUrl(ADD_MSG_TEMPLATE, corpId);
    final String result = this.mainService.post(url, wxCpUserExternalMsgTemplate.toJson());
    return WxCpMsgTemplateAddResult.fromJson(result);
  }

  @Override
  public WxCpUserExternalContactGroupMsgListV2Result groupMsgListV2(String corpId, WxCpUserExternalContactGroupMsgListV2Request wxCpUserExternalContactGroupMsgListV2Request) throws WxErrorException {
    final String url = mainService.getCorpApiUrl(GROUP_MSG_LIST_V2, corpId);
    final String result = this.mainService.post(url, wxCpUserExternalContactGroupMsgListV2Request.toJson());
    return WxCpUserExternalContactGroupMsgListV2Result.fromJson(result);
  }

  @Override
  public WxCpUserExternalContactGroupMsgTaskResult groupMsgTask(String corpId, WxCpUserExternalContactGroupMsgTaskRequest wxCpUserExternalContactGroupMsgTaskRequest) throws WxErrorException {
    final String url = mainService.getCorpApiUrl(GROUP_MSG_TASK, corpId);
    final String result = this.mainService.post(url, wxCpUserExternalContactGroupMsgTaskRequest.toJson());
    return WxCpUserExternalContactGroupMsgTaskResult.fromJson(result);
  }

  @Override
  public WxCpUserExternalContactGroupMsgSendResult groupMsgSendResult(String corpId, WxCpUserExternalContactGroupMsgSendRequest wxCpUserExternalContactGroupMsgSendRequest) throws WxErrorException {
    final String url = mainService.getCorpApiUrl(GROUP_MSG_SEND_RESULT, corpId);
    final String result = this.mainService.post(url, wxCpUserExternalContactGroupMsgSendRequest.toJson());
    return WxCpUserExternalContactGroupMsgSendResult.fromJson(result);
  }

  @Override
  public void sendWelcomeMsg(String corpId, WxCpWelcomeMsg msg) throws WxErrorException {
    final String url = mainService.getCorpApiUrl(SEND_WELCOME_MSG, corpId);
    this.mainService.post(url, msg.toJson());
  }

  @Override
  public WxCpUserExternalTagGroupList getCorpTagList(String corpId, String[] tagId) throws WxErrorException {
    JsonObject json = new JsonObject();
    if (ArrayUtils.isNotEmpty(tagId)) {
      json.add("tag_id", new Gson().toJsonTree(tagId).getAsJsonArray());
    }
    final String url = mainService.getCorpApiUrl(GET_CORP_TAG_LIST, corpId);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalTagGroupList.fromJson(result);
  }

  @Override
  public WxCpUserExternalTagGroupList getCorpTagList(String corpId, String[] tagId, String[] groupId) throws WxErrorException {
    JsonObject json = new JsonObject();
    if (ArrayUtils.isNotEmpty(tagId)) {
      json.add("tag_id", new Gson().toJsonTree(tagId).getAsJsonArray());
    }
    if (ArrayUtils.isNotEmpty(groupId)) {
      json.add("group_id", new Gson().toJsonTree(groupId).getAsJsonArray());
    }
    final String url = mainService.getCorpApiUrl(GET_CORP_TAG_LIST, corpId);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalTagGroupList.fromJson(result);
  }

  @Override
  public WxCpUserExternalTagGroupInfo addCorpTag(String corpId, WxCpUserExternalTagGroupInfo tagGroup) throws WxErrorException {

    final String url = mainService.getCorpApiUrl(ADD_CORP_TAG, corpId);
    final String result = this.mainService.post(url, tagGroup.getTagGroup().toJson());
    return WxCpUserExternalTagGroupInfo.fromJson(result);
  }

  @Override
  public WxCpBaseResp editCorpTag(String corpId, String id, String name, Integer order) throws WxErrorException {

    JsonObject json = new JsonObject();
    json.addProperty("id", id);
    json.addProperty("name", name);
    json.addProperty("order", order);
    final String url = mainService.getCorpApiUrl(EDIT_CORP_TAG, corpId);
    final String result = this.mainService.post(url, json.toString());
    return WxCpBaseResp.fromJson(result);
  }

  @Override
  public WxCpBaseResp delCorpTag(String corpId, String[] tagId, String[] groupId) throws WxErrorException {
    JsonObject json = new JsonObject();
    if (ArrayUtils.isNotEmpty(tagId)) {
      json.add("tag_id", new Gson().toJsonTree(tagId).getAsJsonArray());
    }
    if (ArrayUtils.isNotEmpty(groupId)) {
      json.add("group_id", new Gson().toJsonTree(groupId).getAsJsonArray());
    }

    final String url = mainService.getCorpApiUrl(DEL_CORP_TAG, corpId);
    final String result = this.mainService.post(url, json.toString());
    return WxCpBaseResp.fromJson(result);
  }

  @Override
  public WxCpBaseResp markTag(String corpId, String userid, String externalUserid, String[] addTag, String[] removeTag) throws WxErrorException {


    JsonObject json = new JsonObject();
    json.addProperty("userid", userid);
    json.addProperty("external_userid", externalUserid);

    if (ArrayUtils.isNotEmpty(addTag)) {
      json.add("add_tag", new Gson().toJsonTree(addTag).getAsJsonArray());
    }
    if (ArrayUtils.isNotEmpty(removeTag)) {
      json.add("remove_tag", new Gson().toJsonTree(removeTag).getAsJsonArray());
    }

    final String url = mainService.getCorpApiUrl(MARK_TAG, corpId);
    final String result = this.mainService.post(url, json.toString());
    return WxCpBaseResp.fromJson(result);
  }
}

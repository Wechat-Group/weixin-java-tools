package me.chanjar.weixin.cp.api.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxCpErrorMsgEnum;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.util.BeanUtils;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.WxCpExternalContactService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.external.*;
import me.chanjar.weixin.cp.bean.external.contact.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.ExternalContact.*;

/**
 * @author 曹祖鹏 & yuanqixun & Mr.Pan & Wang_Wong
 */
@RequiredArgsConstructor
public class WxCpExternalContactServiceImpl implements WxCpExternalContactService {
  private final WxCpService mainService;

  @Override
  public WxCpContactWayResult addContactWay(@NonNull WxCpContactWayInfo info) throws WxErrorException {

    if (info.getContactWay().getUsers() != null && info.getContactWay().getUsers().size() > 100) {
      throw new WxRuntimeException("「联系我」使用人数默认限制不超过100人(包括部门展开后的人数)");
    }

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(ADD_CONTACT_WAY);
    String responseContent = this.mainService.post(url, info.getContactWay().toJson());

    return WxCpContactWayResult.fromJson(responseContent);
  }

  @Override
  public WxCpContactWayInfo getContactWay(@NonNull String configId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("config_id", configId);

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CONTACT_WAY);
    String responseContent = this.mainService.post(url, json.toString());
    return WxCpContactWayInfo.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp updateContactWay(@NonNull WxCpContactWayInfo info) throws WxErrorException {
    if (StringUtils.isBlank(info.getContactWay().getConfigId())) {
      throw new WxRuntimeException("更新「联系我」方式需要指定configId");
    }
    if (info.getContactWay().getUsers() != null && info.getContactWay().getUsers().size() > 100) {
      throw new WxRuntimeException("「联系我」使用人数默认限制不超过100人(包括部门展开后的人数)");
    }

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(UPDATE_CONTACT_WAY);
    String responseContent = this.mainService.post(url, info.getContactWay().toJson());

    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp deleteContactWay(@NonNull String configId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("config_id", configId);

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(DEL_CONTACT_WAY);
    String responseContent = this.mainService.post(url, json.toString());

    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp closeTempChat(@NonNull String userId, @NonNull String externalUserId) throws WxErrorException {

    JsonObject json = new JsonObject();
    json.addProperty("userid", userId);
    json.addProperty("external_userid", externalUserId);


    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(CLOSE_TEMP_CHAT);
    String responseContent = this.mainService.post(url, json.toString());

    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpExternalContactInfo getExternalContact(String userId) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_EXTERNAL_CONTACT + userId);
    String responseContent = this.mainService.get(url, null);
    return WxCpExternalContactInfo.fromJson(responseContent);
  }

  @Override
  public WxCpExternalContactInfo getContactDetail(String userId, String cursor) throws WxErrorException {
    String params = userId;
    if (StringUtils.isNotEmpty(cursor)) {
      params = params + "&cursor=" + cursor;
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CONTACT_DETAIL + params);
    String responseContent = this.mainService.get(url, null);
    return WxCpExternalContactInfo.fromJson(responseContent);
  }

  @Override
  public String convertToOpenid(@NotNull String externalUserId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("external_userid", externalUserId);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(CONVERT_TO_OPENID);
    String responseContent = this.mainService.post(url, json.toString());
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return tmpJson.get("openid").getAsString();
  }

  @Override
  public String unionidToExternalUserid(@NotNull String unionid, String openid) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("unionid", unionid);
    if (StringUtils.isNotEmpty(openid)) {
      json.addProperty("openid", openid);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(UNIONID_TO_EXTERNAL_USERID);
    String responseContent = this.mainService.post(url, json.toString());
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return tmpJson.get("external_userid").getAsString();
  }

  @Override
  public String toServiceExternalUserid(@NotNull String externalUserid) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("external_userid", externalUserid);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(TO_SERVICE_EXTERNAL_USERID);
    String responseContent = this.mainService.post(url, json.toString());
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return tmpJson.get("external_userid").getAsString();
  }

  @Override
  public WxCpExternalUserIdList unionidToExternalUserid3rd(@NotNull String unionid, @NotNull String openid, String corpid) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("unionid", unionid);
    json.addProperty("openid", openid);
    if (StringUtils.isNotEmpty(corpid)) {
      json.addProperty("corpid", corpid);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(UNIONID_TO_EXTERNAL_USERID_3RD);
    String responseContent = this.mainService.post(url, json.toString());
    return WxCpExternalUserIdList.fromJson(responseContent);
  }

  @Override
  public WxCpNewExternalUserIdList getNewExternalUserId(String[] externalUserIdList) throws WxErrorException {
    JsonObject json = new JsonObject();
    if (ArrayUtils.isNotEmpty(externalUserIdList)) {
      json.add("external_userid_list", new Gson().toJsonTree(externalUserIdList).getAsJsonArray());
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_NEW_EXTERNAL_USERID);
    String responseContent = this.mainService.post(url, json.toString());
    return WxCpNewExternalUserIdList.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp finishExternalUserIdMigration(@NotNull String corpid) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("corpid", corpid);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(FINISH_EXTERNAL_USERID_MIGRATION);
    String responseContent = this.mainService.post(url, json.toString());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public String opengidToChatid(@NotNull String opengid) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("opengid", opengid);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(OPENID_TO_CHATID);
    String responseContent = this.mainService.post(url, json.toString());
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return tmpJson.get("chat_id").getAsString();
  }

  @Override
  public WxCpExternalContactBatchInfo getContactDetailBatch(String[] userIdList,
                                                            String cursor,
                                                            Integer limit)
    throws WxErrorException {
    final String url =
      this.mainService
        .getWxCpConfigStorage()
        .getApiUrl(GET_CONTACT_DETAIL_BATCH);
    JsonObject json = new JsonObject();
    json.add("userid_list", new Gson().toJsonTree(userIdList).getAsJsonArray());
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
  public void updateRemark(WxCpUpdateRemarkRequest request) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(UPDATE_REMARK);
    this.mainService.post(url, request.toJson());
  }

  @Override
  public List<String> listExternalContacts(String userId) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(LIST_EXTERNAL_CONTACT + userId);
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
  public List<String> listFollowers() throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_FOLLOW_USER_LIST);
    String responseContent = this.mainService.get(url, null);
    return WxCpUserWithExternalPermission.fromJson(responseContent).getFollowers();
  }

  @Override
  public WxCpUserExternalUnassignList listUnassignedList(Integer pageIndex, String cursor, Integer pageSize) throws WxErrorException {
    JsonObject json = new JsonObject();
    if (pageIndex != null) {
      json.addProperty("page_id", pageIndex);
    }
    json.addProperty("cursor", StringUtils.isEmpty(cursor) ? "" : cursor);
    json.addProperty("page_size", pageSize == null ? 1000 : pageSize);
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
  public WxCpUserTransferCustomerResp transferCustomer(WxCpUserTransferCustomerReq req) throws WxErrorException {
    BeanUtils.checkRequiredFields(req);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(TRANSFER_CUSTOMER);
    final String result = this.mainService.post(url, req.toJson());
    return WxCpUserTransferCustomerResp.fromJson(result);
  }

  @Override
  public WxCpUserTransferResultResp transferResult(@NotNull String handOverUserid, @NotNull String takeOverUserid, String cursor) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("cursor", cursor);
    json.addProperty("handover_userid", handOverUserid);
    json.addProperty("takeover_userid", takeOverUserid);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(TRANSFER_RESULT);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserTransferResultResp.fromJson(result);
  }

  @Override
  public WxCpUserTransferCustomerResp resignedTransferCustomer(WxCpUserTransferCustomerReq req) throws WxErrorException {
    BeanUtils.checkRequiredFields(req);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(RESIGNED_TRANSFER_CUSTOMER);
    final String result = this.mainService.post(url, req.toJson());
    return WxCpUserTransferCustomerResp.fromJson(result);
  }

  @Override
  public WxCpUserTransferResultResp resignedTransferResult(@NotNull String handOverUserid, @NotNull String takeOverUserid, String cursor) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("cursor", cursor);
    json.addProperty("handover_userid", handOverUserid);
    json.addProperty("takeover_userid", takeOverUserid);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(RESIGNED_TRANSFER_RESULT);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserTransferResultResp.fromJson(result);
  }

  @Override
  public WxCpUserExternalGroupChatList listGroupChat(Integer pageIndex, Integer pageSize, int status, String[] userIds, String[] partyIds) throws WxErrorException {
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
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_CHAT_LIST);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalGroupChatList.fromJson(result);
  }

  @Override
  public WxCpUserExternalGroupChatList listGroupChat(Integer limit, String cursor, int status, String[] userIds) throws WxErrorException {
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
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_CHAT_LIST);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalGroupChatList.fromJson(result);
  }

  @Override
  public WxCpUserExternalGroupChatInfo getGroupChat(String chatId, Integer needName) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("chat_id", chatId);
    json.addProperty("need_name", needName);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_CHAT_INFO);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalGroupChatInfo.fromJson(result);
  }

  @Override
  public WxCpUserExternalGroupChatTransferResp transferGroupChat(String[] chatIds, String newOwner) throws WxErrorException {
    JsonObject json = new JsonObject();
    if (ArrayUtils.isNotEmpty(chatIds)) {
      json.add("chat_id_list", new Gson().toJsonTree(chatIds).getAsJsonArray());
    }
    json.addProperty("new_owner", newOwner);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_CHAT_TRANSFER);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalGroupChatTransferResp.fromJson(result);
  }

  @Override
  public WxCpUserExternalUserBehaviorStatistic getUserBehaviorStatistic(Date startTime, Date endTime, String[] userIds, String[] partyIds) throws WxErrorException {
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
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(LIST_USER_BEHAVIOR_DATA);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalUserBehaviorStatistic.fromJson(result);
  }

  @Override
  public WxCpUserExternalGroupChatStatistic getGroupChatStatistic(Date startTime, Integer orderBy, Integer orderAsc, Integer pageIndex, Integer pageSize, String[] userIds, String[] partyIds) throws WxErrorException {
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
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(LIST_GROUP_CHAT_DATA);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalGroupChatStatistic.fromJson(result);
  }

  @Override
  public WxCpMsgTemplateAddResult addMsgTemplate(WxCpMsgTemplate wxCpMsgTemplate) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(ADD_MSG_TEMPLATE);
    final String result = this.mainService.post(url, wxCpMsgTemplate.toJson());
    return WxCpMsgTemplateAddResult.fromJson(result);
  }

  @Override
  public void sendWelcomeMsg(WxCpWelcomeMsg msg) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(SEND_WELCOME_MSG);
    this.mainService.post(url, msg.toJson());
  }

  @Override
  public WxCpUserExternalTagGroupList getCorpTagList(String[] tagId) throws WxErrorException {
    JsonObject json = new JsonObject();
    if (ArrayUtils.isNotEmpty(tagId)) {
      json.add("tag_id", new Gson().toJsonTree(tagId).getAsJsonArray());
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CORP_TAG_LIST);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalTagGroupList.fromJson(result);
  }

  @Override
  public WxCpUserExternalTagGroupList getCorpTagList(String[] tagId, String[] groupId) throws WxErrorException {
    JsonObject json = new JsonObject();
    if (ArrayUtils.isNotEmpty(tagId)) {
      json.add("tag_id", new Gson().toJsonTree(tagId).getAsJsonArray());
    }
    if (ArrayUtils.isNotEmpty(groupId)) {
      json.add("group_id", new Gson().toJsonTree(groupId).getAsJsonArray());
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_CORP_TAG_LIST);
    final String result = this.mainService.post(url, json.toString());
    return WxCpUserExternalTagGroupList.fromJson(result);
  }

  @Override
  public WxCpUserExternalTagGroupInfo addCorpTag(WxCpUserExternalTagGroupInfo tagGroup) throws WxErrorException {

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(ADD_CORP_TAG);
    final String result = this.mainService.post(url, tagGroup.getTagGroup().toJson());
    return WxCpUserExternalTagGroupInfo.fromJson(result);
  }

  @Override
  public WxCpBaseResp editCorpTag(String id, String name, Integer order) throws WxErrorException {

    JsonObject json = new JsonObject();
    json.addProperty("id", id);
    json.addProperty("name", name);
    json.addProperty("order", order);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(EDIT_CORP_TAG);
    final String result = this.mainService.post(url, json.toString());
    return WxCpBaseResp.fromJson(result);
  }

  @Override
  public WxCpBaseResp delCorpTag(String[] tagId, String[] groupId) throws WxErrorException {
    JsonObject json = new JsonObject();
    if (ArrayUtils.isNotEmpty(tagId)) {
      json.add("tag_id", new Gson().toJsonTree(tagId).getAsJsonArray());
    }
    if (ArrayUtils.isNotEmpty(groupId)) {
      json.add("group_id", new Gson().toJsonTree(groupId).getAsJsonArray());
    }

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(DEL_CORP_TAG);
    final String result = this.mainService.post(url, json.toString());
    return WxCpBaseResp.fromJson(result);
  }

  @Override
  public WxCpBaseResp markTag(String userid, String externalUserid, String[] addTag, String[] removeTag) throws WxErrorException {


    JsonObject json = new JsonObject();
    json.addProperty("userid", userid);
    json.addProperty("external_userid", externalUserid);

    if (ArrayUtils.isNotEmpty(addTag)) {
      json.add("add_tag", new Gson().toJsonTree(addTag).getAsJsonArray());
    }
    if (ArrayUtils.isNotEmpty(removeTag)) {
      json.add("remove_tag", new Gson().toJsonTree(removeTag).getAsJsonArray());
    }

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(MARK_TAG);
    final String result = this.mainService.post(url, json.toString());
    return WxCpBaseResp.fromJson(result);
  }

  @Override
  public WxCpAddMomentResult addMomentTask(WxCpAddMomentTask task) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(ADD_MOMENT_TASK);
    final String result = this.mainService.post(url, task.toJson());
    return WxCpAddMomentResult.fromJson(result);
  }

  @Override
  public WxCpGetMomentTaskResult getMomentTaskResult(String jobId) throws WxErrorException {
    String params = "&jobid=" + jobId;
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_MOMENT_TASK_RESULT);
    final String result = this.mainService.get(url, params);
    return WxCpGetMomentTaskResult.fromJson(result);
  }

  @Override
  public WxCpGetMomentList getMomentList(Long startTime, Long endTime, String creator, Integer filterType,
                                         String cursor, Integer limit) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("start_time", startTime);
    json.addProperty("end_time", endTime);
    if (!StringUtils.isEmpty(creator)) {
      json.addProperty("creator", creator);
    }
    if (filterType != null) {
      json.addProperty("filter_type", filterType);
    }
    if (!StringUtils.isEmpty(cursor)) {
      json.addProperty("cursor", cursor);
    }
    if (limit != null) {
      json.addProperty("limit", limit);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_MOMENT_LIST);
    final String result = this.mainService.post(url, json.toString());
    return WxCpGetMomentList.fromJson(result);
  }

  @Override
  public WxCpGetMomentTask getMomentTask(String momentId, String cursor, Integer limit)
    throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("moment_id", momentId);
    if (!StringUtils.isEmpty(cursor)) {
      json.addProperty("cursor", cursor);
    }
    if (limit != null) {
      json.addProperty("limit", limit);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_MOMENT_TASK);
    final String result = this.mainService.post(url, json.toString());
    return WxCpGetMomentTask.fromJson(result);
  }

  @Override
  public WxCpGetMomentCustomerList getMomentCustomerList(String momentId, String userId,
                                                         String cursor, Integer limit) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("moment_id", momentId);
    json.addProperty("userid", userId);
    if (!StringUtils.isEmpty(cursor)) {
      json.addProperty("cursor", cursor);
    }
    if (limit != null) {
      json.addProperty("limit", limit);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_MOMENT_CUSTOMER_LIST);
    final String result = this.mainService.post(url, json.toString());
    return WxCpGetMomentCustomerList.fromJson(result);
  }

  @Override
  public WxCpGetMomentSendResult getMomentSendResult(String momentId, String userId,
                                                     String cursor, Integer limit) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("moment_id", momentId);
    json.addProperty("userid", userId);
    if (!StringUtils.isEmpty(cursor)) {
      json.addProperty("cursor", cursor);
    }
    if (limit != null) {
      json.addProperty("limit", limit);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_MOMENT_SEND_RESULT);
    final String result = this.mainService.post(url, json.toString());
    return WxCpGetMomentSendResult.fromJson(result);
  }

  @Override
  public WxCpGetMomentComments getMomentComments(String momentId, String userId)
    throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("moment_id", momentId);
    json.addProperty("userid", userId);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_MOMENT_COMMENTS);
    final String result = this.mainService.post(url, json.toString());
    return WxCpGetMomentComments.fromJson(result);
  }

  /**
   * <pre>
   * 企业和第三方应用可通过此接口获取企业与成员的群发记录。
   * https://work.weixin.qq.com/api/doc/90000/90135/93338
   * </pre>
   *
   * @param chatType   群发任务的类型，默认为single，表示发送给客户，group表示发送给客户群
   * @param startTime  群发任务记录开始时间
   * @param endTime    群发任务记录结束时间
   * @param creator    群发任务创建人企业账号id
   * @param filterType 创建人类型。0：企业发表 1：个人发表 2：所有，包括个人创建以及企业创建，默认情况下为所有类型
   * @param limit      返回的最大记录数，整型，最大值100，默认值50，超过最大值时取默认值
   * @param cursor     用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  @Override
  public WxCpGroupMsgListResult getGroupMsgListV2(String chatType, @NonNull Date startTime, @NonNull Date endTime, String creator, Integer filterType, Integer limit, String cursor) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("chat_type", chatType);
    json.addProperty("start_time", startTime.getTime() / 1000);
    json.addProperty("end_time", endTime.getTime() / 1000);
    json.addProperty("creator", creator);
    json.addProperty("filter_type", filterType);
    json.addProperty("limit", limit);
    json.addProperty("cursor", cursor);

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_GROUP_MSG_LIST_V2);
    final String result = this.mainService.post(url, json.toString());
    return WxCpGroupMsgListResult.fromJson(result);
  }

  /**
   * <pre>
   * 企业和第三方应用可通过此接口获取企业与成员的群发记录。
   * https://work.weixin.qq.com/api/doc/90000/90135/93338#获取企业群发成员执行结果
   * </pre>
   *
   * @param msgid  群发消息的id，通过获取群发记录列表接口返回
   * @param userid 发送成员userid，通过获取群发成员发送任务列表接口返回
   * @param limit  返回的最大记录数，整型，最大值1000，默认值500，超过最大值时取默认值
   * @param cursor 用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  @Override
  public WxCpGroupMsgSendResult getGroupMsgSendResult(String msgid, String userid, Integer limit, String cursor) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("msgid", msgid);
    json.addProperty("userid", userid);
    json.addProperty("limit", limit);
    json.addProperty("cursor", cursor);

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_GROUP_MSG_SEND_RESULT);
    final String result = this.mainService.post(url, json.toString());
    return WxCpGroupMsgSendResult.fromJson(result);
  }

  /**
   * <pre>
   * 企业跟第三方应用可通过该接口获取到创建企业群发的群发发送结果。
   * https://work.weixin.qq.com/api/doc/16251
   * </pre>
   *
   * @param msgid  群发消息的id，通过创建企业群发接口返回
   * @param limit  返回的最大记录数，整型，最大值10000，默认值10000
   * @param cursor 用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  @Override
  public WxCpGroupMsgResult getGroupMsgResult(String msgid, Integer limit, String cursor) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("msgid", msgid);
    json.addProperty("limit", limit);
    json.addProperty("cursor", cursor);

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_GROUP_MSG_RESULT);
    final String result = this.mainService.post(url, json.toString());
    return WxCpGroupMsgResult.fromJson(result);
  }

  /**
   * <pre>
   * 获取群发成员发送任务列表。
   * https://work.weixin.qq.com/api/doc/90000/90135/93338#获取群发成员发送任务列表
   * </pre>
   *
   * @param msgid  群发消息的id，通过获取群发记录列表接口返回
   * @param limit  返回的最大记录数，整型，最大值1000，默认值500，超过最大值时取默认值
   * @param cursor 用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  @Override
  public WxCpGroupMsgTaskResult getGroupMsgTask(String msgid, Integer limit, String cursor) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("msgid", msgid);
    json.addProperty("limit", limit);
    json.addProperty("cursor", cursor);

    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_GROUP_MSG_TASK);
    final String result = this.mainService.post(url, json.toString());
    return WxCpGroupMsgTaskResult.fromJson(result);
  }

  /**
   * <pre>
   * 添加入群欢迎语素材。
   * https://open.work.weixin.qq.com/api/doc/90000/90135/92366#添加入群欢迎语素材
   * </pre>
   *
   * @param template 素材内容
   * @return template_id      欢迎语素材id
   * @throws WxErrorException the wx error exception
   */
  @Override
  public String addGroupWelcomeTemplate(WxCpGroupWelcomeTemplateResult template) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_WELCOME_TEMPLATE_ADD);
    final String responseContent = this.mainService.post(url, template.toJson());
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return tmpJson.get("template_id").getAsString();
  }

  /**
   * <pre>
   * 编辑入群欢迎语素材。
   * https://open.work.weixin.qq.com/api/doc/90000/90135/92366#编辑入群欢迎语素材
   * </pre>
   *
   * @param template
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  @Override
  public WxCpBaseResp editGroupWelcomeTemplate(WxCpGroupWelcomeTemplateResult template) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_WELCOME_TEMPLATE_EDIT);
    final String result = this.mainService.post(url, template.toJson());
    return WxCpGroupWelcomeTemplateResult.fromJson(result);
  }

  /**
   * <pre>
   * 获取入群欢迎语素材。
   * https://open.work.weixin.qq.com/api/doc/90000/90135/92366#获取入群欢迎语素材
   * </pre>
   *
   * @param templateId 群欢迎语的素材id
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  @Override
  public WxCpGroupWelcomeTemplateResult getGroupWelcomeTemplate(@NotNull String templateId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("template_id", templateId);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_WELCOME_TEMPLATE_GET);
    final String result = this.mainService.post(url, json.toString());
    return WxCpGroupWelcomeTemplateResult.fromJson(result);
  }

  /**
   * <pre>
   * 删除入群欢迎语素材。
   * 企业可通过此API删除入群欢迎语素材，且仅能删除调用方自己创建的入群欢迎语素材。
   * https://open.work.weixin.qq.com/api/doc/90000/90135/92366#删除入群欢迎语素材
   * </pre>
   *
   * @param templateId 群欢迎语的素材id
   * @param agentId
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  @Override
  public WxCpBaseResp delGroupWelcomeTemplate(@NotNull String templateId, String agentId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("template_id", templateId);
    if (!StringUtils.isEmpty(agentId)) {
      json.addProperty("agentid", agentId);
    }
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GROUP_WELCOME_TEMPLATE_DEL);
    final String result = this.mainService.post(url, json.toString());
    return WxCpBaseResp.fromJson(result);
  }

  /**
   * <pre>
   * 获取商品图册
   * https://work.weixin.qq.com/api/doc/90000/90135/95096#获取商品图册列表
   * </pre>
   *
   * @param limit  返回的最大记录数，整型，最大值100，默认值50，超过最大值时取默认值
   * @param cursor 用于分页查询的游标，字符串类型，由上一次调用返回，首次调用可不填
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  @Override
  public WxCpProductAlbumListResult getProductAlbumList(Integer limit, String cursor) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("limit", limit);
    json.addProperty("cursor", cursor);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_PRODUCT_ALBUM_LIST);
    final String result = this.mainService.post(url, json.toString());
    return WxCpProductAlbumListResult.fromJson(result);
  }

  /**
   * <pre>
   * 获取商品图册
   * https://work.weixin.qq.com/api/doc/90000/90135/95096#获取商品图册
   * </pre>
   *
   * @param productId 商品id
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  @Override
  public WxCpProductAlbumResult getProductAlbum(String productId) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("product_id", productId);
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(GET_PRODUCT_ALBUM);
    final String result = this.mainService.post(url, json.toString());
    return WxCpProductAlbumResult.fromJson(result);
  }

  @Override
  public WxMediaUploadResult uploadAttachment(String mediaType, String fileType, Integer attachmentType,
                                              InputStream inputStream) throws WxErrorException, IOException {
    return uploadAttachment(mediaType, attachmentType, FileUtils.createTmpFile(inputStream,
      UUID.randomUUID().toString(), fileType));
  }

  @Override
  public WxMediaUploadResult uploadAttachment(String mediaType, Integer attachmentType, File file)
    throws WxErrorException {
    String params = "?media_type=" + mediaType + "&attachment_type=" + attachmentType;
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(UPLOAD_ATTACHMENT + params);
    return this.mainService.execute(MediaUploadRequestExecutor.create(
      this.mainService.getRequestHttp()), url, file);
  }

  @Override
  public WxCpInterceptRuleResultResp addInterceptRule(WxCpInterceptRuleResp ruleResp) throws WxErrorException {
    return WxCpInterceptRuleResultResp
      .fromJson(this.mainService.post(this.mainService.getWxCpConfigStorage().getApiUrl(UPDATE_INTERCEPT_RULE), ruleResp.toJson()));
  }

  @Override
  public WxCpInterceptRuleResultResp updateInterceptRule(WxCpInterceptRuleResp ruleResp) throws WxErrorException {
    return WxCpInterceptRuleResultResp
      .fromJson(this.mainService.post(this.mainService.getWxCpConfigStorage().getApiUrl(UPDATE_INTERCEPT_RULE), ruleResp.toJson()));
  }

  @Override
  public WxCpBaseResp delInterceptRule(String rule_id) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("rule_id",rule_id);
    return WxCpBaseResp
      .fromJson(this.mainService.post(this.mainService.getWxCpConfigStorage().getApiUrl(UPDATE_INTERCEPT_RULE), jsonObject));
  }


}

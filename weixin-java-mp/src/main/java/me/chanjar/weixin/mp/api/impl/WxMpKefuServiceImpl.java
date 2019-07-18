package me.chanjar.weixin.mp.api.impl;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.mp.api.WxMpKefuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.kefu.request.WxMpKfAccountRequest;
import me.chanjar.weixin.mp.bean.kefu.request.WxMpKfSessionRequest;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfList;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfMsgList;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfOnlineList;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfSessionGetResult;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfSessionList;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfSessionWaitCaseList;

/**
 * @author Binary Wang
 */
public class WxMpKefuServiceImpl implements WxMpKefuService {
  protected final Logger log = LoggerFactory.getLogger(this.getClass());
  private WxMpService wxMpService;

  public WxMpKefuServiceImpl(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
  }

  @Override
  public boolean sendKefuMessage(WxMpKefuMessage message) throws WxErrorException {
    String responseContent = this.wxMpService.post(MESSAGE_CUSTOM_SEND, message.toJson());
    return responseContent != null;
  }

  @Override
  public WxMpKfList kfList() throws WxErrorException {
    String responseContent = this.wxMpService.get(GET_KF_LIST, null);
    return WxMpKfList.fromJson(responseContent);
  }

  @Override
  public WxMpKfOnlineList kfOnlineList() throws WxErrorException {
    String responseContent = this.wxMpService.get(GET_ONLINE_KF_LIST, null);
    return WxMpKfOnlineList.fromJson(responseContent);
  }

  @Override
  public boolean kfAccountAdd(WxMpKfAccountRequest request) throws WxErrorException {
    String responseContent = this.wxMpService.post(KFACCOUNT_ADD, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfAccountUpdate(WxMpKfAccountRequest request) throws WxErrorException {
    String responseContent = this.wxMpService.post(KFACCOUNT_UPDATE, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfAccountInviteWorker(WxMpKfAccountRequest request) throws WxErrorException {
    String responseContent = this.wxMpService.post(KFACCOUNT_INVITE_WORKER, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfAccountUploadHeadImg(String kfAccount, File imgFile) throws WxErrorException {
    WxMediaUploadResult responseContent = this.wxMpService
      .execute(MediaUploadRequestExecutor.create(this.wxMpService.getRequestHttp()), String.format(KFACCOUNT_UPLOAD_HEAD_IMG, kfAccount), imgFile);
    return responseContent != null;
  }

  @Override
  public boolean kfAccountDel(String kfAccount) throws WxErrorException {
    String responseContent = this.wxMpService.get(String.format(KFACCOUNT_DEL, kfAccount), null);
    return responseContent != null;
  }

  @Override
  public boolean kfSessionCreate(String openid, String kfAccount) throws WxErrorException {
    WxMpKfSessionRequest request = new WxMpKfSessionRequest(kfAccount, openid);
    String responseContent = this.wxMpService.post(KFSESSION_CREATE, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfSessionClose(String openid, String kfAccount) throws WxErrorException {
    WxMpKfSessionRequest request = new WxMpKfSessionRequest(kfAccount, openid);
    String responseContent = this.wxMpService.post(KFSESSION_CLOSE, request.toJson());
    return responseContent != null;
  }

  @Override
  public WxMpKfSessionGetResult kfSessionGet(String openid) throws WxErrorException {
    String responseContent = this.wxMpService.get(String.format(KFSESSION_GET_SESSION, openid), null);
    return WxMpKfSessionGetResult.fromJson(responseContent);
  }

  @Override
  public WxMpKfSessionList kfSessionList(String kfAccount) throws WxErrorException {
    String responseContent = this.wxMpService.get(String.format(KFSESSION_GET_SESSION_LIST, kfAccount), null);
    return WxMpKfSessionList.fromJson(responseContent);
  }

  @Override
  public WxMpKfSessionWaitCaseList kfSessionGetWaitCase() throws WxErrorException {
    String responseContent = this.wxMpService.get(KFSESSION_GET_WAIT_CASE, null);
    return WxMpKfSessionWaitCaseList.fromJson(responseContent);
  }

  @Override
  public WxMpKfMsgList kfMsgList(Date startTime, Date endTime, Long msgId, Integer number) throws WxErrorException {
    if (number > 10000) {
      throw new WxErrorException(WxError.builder().errorCode(-1).errorMsg("非法参数请求，每次最多查询10000条记录！").build());
    }

    if (startTime.after(endTime)) {
      throw new WxErrorException(WxError.builder().errorCode(-1).errorMsg("起始时间不能晚于结束时间！").build());
    }

    JsonObject param = new JsonObject();
    param.addProperty("starttime", startTime.getTime() / 1000);
    param.addProperty("endtime", endTime.getTime() / 1000);
    param.addProperty("msgid", msgId);
    param.addProperty("number", number);

    String responseContent = this.wxMpService.post(MSG_RECORD_LIST, param.toString());

    return WxMpKfMsgList.fromJson(responseContent);
  }

  @Override
  public WxMpKfMsgList kfMsgList(Date startTime, Date endTime) throws WxErrorException {
    int number = 10000;
    WxMpKfMsgList result = this.kfMsgList(startTime, endTime, 1L, number);

    if (result != null && result.getNumber() == number) {
      Long msgId = result.getMsgId();
      WxMpKfMsgList followingResult = this.kfMsgList(startTime, endTime, msgId, number);
      while (followingResult != null && followingResult.getRecords().size() > 0) {
        result.getRecords().addAll(followingResult.getRecords());
        result.setNumber(result.getNumber() + followingResult.getNumber());
        result.setMsgId(followingResult.getMsgId());
        followingResult = this.kfMsgList(startTime, endTime, followingResult.getMsgId(), number);
      }
    }

    return result;
  }

  @Override
  public boolean sendKfTypingState(String openid, String command) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("touser", openid);
    params.addProperty("command", command);
    String responseContent = this.wxMpService.post(CUSTOM_TYPING, params.toString());
    return responseContent != null;
  }

}

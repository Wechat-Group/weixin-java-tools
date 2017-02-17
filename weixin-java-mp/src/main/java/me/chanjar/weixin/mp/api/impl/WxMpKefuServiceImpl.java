package me.chanjar.weixin.mp.api.impl;

import java.io.File;
import java.util.Date;

import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.mp.api.WxMpKefuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.request.WxMpKfAccountRequest;
import me.chanjar.weixin.mp.bean.kefu.request.WxMpKfSessionRequest;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfList;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfMsgList;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfOnlineList;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfSessionGetResult;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfSessionList;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfSessionWaitCaseList;

/**
 *
 * @author Binary Wang
 *
 */
public class WxMpKefuServiceImpl implements WxMpKefuService {
  protected final Logger log = LoggerFactory
      .getLogger(WxMpKefuServiceImpl.class);
  private static final String API_URL_PREFIX = "https://api.weixin.qq.com/customservice";
  private static final String API_URL_PREFIX_WITH_CGI_BIN = "https://api.weixin.qq.com/cgi-bin/customservice";
  private WxMpService wxMpService;

  public WxMpKefuServiceImpl(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
  }

  @Override
  public boolean sendKefuMessage(WxMpKefuMessage message)
      throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
    String responseContent = this.wxMpService.post(url, message.toJson());
    return responseContent != null;
  }

  @Override
  public WxMpKfList kfList() throws WxErrorException {
    String url = API_URL_PREFIX_WITH_CGI_BIN + "/getkflist";
    String responseContent = this.wxMpService.get(url, null);
    return WxMpKfList.fromJson(responseContent);
  }

  @Override
  public WxMpKfOnlineList kfOnlineList() throws WxErrorException {
    String url = API_URL_PREFIX_WITH_CGI_BIN + "/getonlinekflist";
    String responseContent = this.wxMpService.get(url, null);
    return WxMpKfOnlineList.fromJson(responseContent);
  }

  @Override
  public boolean kfAccountAdd(WxMpKfAccountRequest request)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfaccount/add";
    String responseContent = this.wxMpService.post(url, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfAccountUpdate(WxMpKfAccountRequest request)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfaccount/update";
    String responseContent = this.wxMpService.post(url, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfAccountInviteWorker(WxMpKfAccountRequest request) throws WxErrorException {
    String url = API_URL_PREFIX + "/kfaccount/inviteworker";
    String responseContent = this.wxMpService.post(url, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfAccountUploadHeadImg(String kfAccount, File imgFile)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfaccount/uploadheadimg?kf_account=" + kfAccount;
    WxMediaUploadResult responseContent = this.wxMpService
        .execute(new MediaUploadRequestExecutor(), url, imgFile);
    return responseContent != null;
  }

  @Override
  public boolean kfAccountDel(String kfAccount) throws WxErrorException {
    String url = API_URL_PREFIX + "/kfaccount/del?kf_account=" + kfAccount;
    String responseContent = this.wxMpService.get(url, null);
    return responseContent != null;
  }

  @Override
  public boolean kfSessionCreate(String openid, String kfAccount)
      throws WxErrorException {
    WxMpKfSessionRequest request = new WxMpKfSessionRequest(kfAccount, openid);
    String url = API_URL_PREFIX + "/kfsession/create";
    String responseContent = this.wxMpService.post(url, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfSessionClose(String openid, String kfAccount)
      throws WxErrorException {
    WxMpKfSessionRequest request = new WxMpKfSessionRequest(kfAccount, openid);
    String url = API_URL_PREFIX + "/kfsession/close";
    String responseContent = this.wxMpService.post(url, request.toJson());
    return responseContent != null;
  }

  @Override
  public WxMpKfSessionGetResult kfSessionGet(String openid)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfsession/getsession?openid=" + openid;
    String responseContent = this.wxMpService.get(url, null);
    return WxMpKfSessionGetResult.fromJson(responseContent);
  }

  @Override
  public WxMpKfSessionList kfSessionList(String kfAccount)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfsession/getsessionlist?kf_account=" + kfAccount;
    String responseContent = this.wxMpService.get(url, null);
    return WxMpKfSessionList.fromJson(responseContent);
  }

  @Override
  public WxMpKfSessionWaitCaseList kfSessionGetWaitCase()
      throws WxErrorException {
    String url = API_URL_PREFIX + "/kfsession/getwaitcase";
    String responseContent = this.wxMpService.get(url, null);
    return WxMpKfSessionWaitCaseList.fromJson(responseContent);
  }

  @Override
  public WxMpKfMsgList kfMsgList(Date startTime, Date endTime, Long msgId, Integer number) throws WxErrorException {
    if(number > 10000){
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("非法参数请求，每次最多查询10000条记录！").build());
    }

    if(startTime.after(endTime)){
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("起始时间不能晚于结束时间！").build());
    }

    String url = API_URL_PREFIX + "/msgrecord/getmsglist";

    JsonObject param = new JsonObject();
    param.addProperty("starttime", startTime.getTime() / 1000); //starttime	起始时间，unix时间戳
    param.addProperty("endtime", endTime.getTime() / 1000); //endtime	结束时间，unix时间戳，每次查询时段不能超过24小时
    param.addProperty("msgid", msgId); //msgid	消息id顺序从小到大，从1开始
    param.addProperty("number", number); //number	每次获取条数，最多10000条

    String responseContent = this.wxMpService.post(url, param.toString());

    return WxMpKfMsgList.fromJson(responseContent);
  }

  @Override
  public WxMpKfMsgList kfMsgList(Date startTime, Date endTime) throws WxErrorException {
    int number = 10000;
    WxMpKfMsgList result =  this.kfMsgList(startTime,endTime, 1L, number);

    if(result != null && result.getNumber() == number){
      Long msgId = result.getMsgId();
      WxMpKfMsgList followingResult =  this.kfMsgList(startTime,endTime, msgId, number);
      while(followingResult != null  && followingResult.getRecords().size() > 0){
        result.getRecords().addAll(followingResult.getRecords());
        result.setNumber(result.getNumber() + followingResult.getNumber());
        result.setMsgId(followingResult.getMsgId());
        followingResult = this.kfMsgList(startTime,endTime, followingResult.getMsgId(), number);
      }
    }

    return result;
  }

}

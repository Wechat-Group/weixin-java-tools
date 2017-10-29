package me.chanjar.weixin.open.api.impl;

import com.google.gson.JsonObject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMassMessageService;
import me.chanjar.weixin.mp.bean.*;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;
import me.chanjar.weixin.mp.bean.result.WxMpMassUploadResult;
import me.chanjar.weixin.open.api.WxOpenMassMessageService;
import me.chanjar.weixin.open.api.WxOpenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * 群发消息服务类
 * Created by Binary Wang on 2017-8-16.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxOpenMassMessageServiceImpl implements WxOpenMassMessageService {
  protected final Logger log = LoggerFactory.getLogger(this.getClass());
  private WxOpenService wxOpenService;

  public WxOpenMassMessageServiceImpl(WxOpenService wxOpenService) {
    this.wxOpenService = wxOpenService;
  }

  @Override
  public WxMpMassUploadResult massNewsUpload(String appid, WxMpMassNews news) throws WxErrorException {
    String responseContent = this.wxOpenService.post(appid, MEDIA_UPLOAD_NEWS_URL, news.toJson());
    return WxMpMassUploadResult.fromJson(responseContent);
  }

  @Override
  public WxMpMassUploadResult massVideoUpload(String appid, WxMpMassVideo video) throws WxErrorException {
    String responseContent = this.wxOpenService.post(appid, MEDIA_UPLOAD_VIDEO_URL, video.toJson());
    return WxMpMassUploadResult.fromJson(responseContent);
  }

  @Override
  public WxMpMassSendResult massGroupMessageSend(String appid, WxMpMassTagMessage message) throws WxErrorException {
    String responseContent = this.wxOpenService.post(appid, WxMpMassMessageService.MESSAGE_MASS_SENDALL_URL, message.toJson());
    return WxMpMassSendResult.fromJson(responseContent);
  }

  @Override
  public WxMpMassSendResult massOpenIdsMessageSend(String appid, WxMpMassOpenIdsMessage message) throws WxErrorException {
    String responseContent = this.wxOpenService.post(appid, MESSAGE_MASS_SEND_URL, message.toJson());
    return WxMpMassSendResult.fromJson(responseContent);
  }

  @Override
  public WxMpMassSendResult massMessagePreview(String appid, WxMpMassPreviewMessage wxMpMassPreviewMessage) throws Exception {
    String responseContent = this.wxOpenService.post(appid, MESSAGE_MASS_PREVIEW_URL, wxMpMassPreviewMessage.toJson());
    return WxMpMassSendResult.fromJson(responseContent);
  }

  @Override
  public void delete(String appid, Integer msgId, Integer articleIndex) throws Exception {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("msg_id", msgId);
    jsonObject.addProperty("article_idx", articleIndex);
    this.wxOpenService.post(appid, MESSAGE_MASS_DELETE_URL, jsonObject.toString());
  }

}

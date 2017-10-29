package me.chanjar.weixin.open.api.impl;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.MediaDownloadRequestExecutor;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.bean.material.*;
import me.chanjar.weixin.mp.util.http.*;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;
import me.chanjar.weixin.open.api.WxOpenMaterialService;
import me.chanjar.weixin.open.api.WxOpenService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Binary Wang on 2016/7/21.
 */
public class WxOpenMaterialServiceImpl implements WxOpenMaterialService {

  private WxOpenService wxOpenService;

  public WxOpenMaterialServiceImpl(WxOpenService wxOpenService) {
    this.wxOpenService = wxOpenService;
  }

  @Override
  public WxMediaUploadResult mediaUpload(String appid, String mediaType, String fileType, InputStream inputStream) throws WxErrorException {
    try {
      return this.mediaUpload(appid, mediaType, FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType));
    } catch (IOException e) {
      e.printStackTrace();
      throw new WxErrorException(WxError.newBuilder().setErrorMsg(e.getMessage()).build());
    }
  }

  @Override
  public WxMediaUploadResult mediaUpload(String appid, String mediaType, File file) throws WxErrorException {
    String url = String.format(MEDIA_UPLOAD_URL, mediaType);
    return this.wxOpenService.execute(appid, MediaUploadRequestExecutor.create(this.wxOpenService.getRequestHttp()), url, file);
  }

  @Override
  public File mediaDownload(String appid, String mediaId) throws WxErrorException {
    return this.wxOpenService.execute(
      appid,
      MediaDownloadRequestExecutor.create(this.wxOpenService.getRequestHttp(), this.wxOpenService.getWxOpenConfigStorage().getTmpDirFile()),
      MEDIA_GET_URL,
      "media_id=" + mediaId);
  }

  @Override
  public WxMediaImgUploadResult mediaImgUpload(String appid, File file) throws WxErrorException {
    return this.wxOpenService.execute(appid, MediaImgUploadRequestExecutor.create(this.wxOpenService.getRequestHttp()), IMG_UPLOAD_URL, file);
  }

  @Override
  public WxMpMaterialUploadResult materialFileUpload(String appid, String mediaType, WxMpMaterial material) throws WxErrorException {
    String url = String.format(MATERIAL_ADD_URL, mediaType);
    return this.wxOpenService.execute(appid, MaterialUploadRequestExecutor.create(this.wxOpenService.getRequestHttp()), url, material);
  }

  @Override
  public WxMpMaterialUploadResult materialNewsUpload(String appid, WxMpMaterialNews news) throws WxErrorException {
    if (news == null || news.isEmpty()) {
      throw new IllegalArgumentException("news is empty!");
    }
    String responseContent = this.wxOpenService.post(NEWS_ADD_URL, news.toJson());
    return WxMpMaterialUploadResult.fromJson(responseContent);
  }

  @Override
  public InputStream materialImageOrVoiceDownload(String appid, String mediaId) throws WxErrorException {
    return this.wxOpenService.execute(appid,
      MaterialVoiceAndImageDownloadRequestExecutor.create(this.wxOpenService.getRequestHttp(), this.wxOpenService.getWxOpenConfigStorage().getTmpDirFile()),
      MATERIAL_GET_URL,
      mediaId
    );
  }

  @Override
  public WxMpMaterialVideoInfoResult materialVideoInfo(String appid, String mediaId) throws WxErrorException {
    return this.wxOpenService.execute(
      appid,
      MaterialVideoInfoRequestExecutor.create(this.wxOpenService.getRequestHttp()),
      MATERIAL_GET_URL,
      mediaId
    );
  }

  @Override
  public WxMpMaterialNews materialNewsInfo(String appid, String mediaId) throws WxErrorException {
    return this.wxOpenService.execute(
      appid,
      MaterialNewsInfoRequestExecutor.create(this.wxOpenService.getRequestHttp()),
      MATERIAL_GET_URL,
      mediaId
    );
  }

  @Override
  public boolean materialNewsUpdate(String appid, WxMpMaterialArticleUpdate wxMpMaterialArticleUpdate) throws WxErrorException {
    String responseText = this.wxOpenService.post(appid, NEWS_UPDATE_URL, wxMpMaterialArticleUpdate.toJson());
    WxError wxError = WxError.fromJson(responseText);
    if (wxError.getErrorCode() == 0) {
      return true;
    } else {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public boolean materialDelete(String appid, String mediaId) throws WxErrorException {
    return this.wxOpenService.execute(
      appid,
      MaterialDeleteRequestExecutor.create(this.wxOpenService.getRequestHttp()),
      MATERIAL_DEL_URL,
      mediaId
    );
  }

  @Override
  public WxMpMaterialCountResult materialCount(String appid) throws WxErrorException {
    String responseText = this.wxOpenService.get(appid, MATERIAL_GET_COUNT_URL, null);
    WxError wxError = WxError.fromJson(responseText);
    if (wxError.getErrorCode() == 0) {
      return WxMpGsonBuilder.create().fromJson(responseText, WxMpMaterialCountResult.class);
    } else {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxMpMaterialNewsBatchGetResult materialNewsBatchGet(String appid, int offset, int count) throws WxErrorException {
    Map<String, Object> params = new HashMap<>();
    params.put("type", WxConsts.MATERIAL_NEWS);
    params.put("offset", offset);
    params.put("count", count);
    String responseText = this.wxOpenService.post(appid, MATERIAL_BATCHGET_URL, WxGsonBuilder.create().toJson(params));
    WxError wxError = WxError.fromJson(responseText);
    if (wxError.getErrorCode() == 0) {
      return WxMpGsonBuilder.create().fromJson(responseText, WxMpMaterialNewsBatchGetResult.class);
    } else {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxMpMaterialFileBatchGetResult materialFileBatchGet(String appid, String type, int offset, int count) throws WxErrorException {
    Map<String, Object> params = new HashMap<>();
    params.put("type", type);
    params.put("offset", offset);
    params.put("count", count);
    String responseText = this.wxOpenService.post(appid, MATERIAL_BATCHGET_URL, WxGsonBuilder.create().toJson(params));
    WxError wxError = WxError.fromJson(responseText);
    if (wxError.getErrorCode() == 0) {
      return WxMpGsonBuilder.create().fromJson(responseText, WxMpMaterialFileBatchGetResult.class);
    } else {
      throw new WxErrorException(wxError);
    }
  }

}

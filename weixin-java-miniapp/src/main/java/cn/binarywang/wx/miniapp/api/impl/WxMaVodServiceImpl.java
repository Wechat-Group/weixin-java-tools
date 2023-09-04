package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaVodService;
import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import cn.binarywang.wx.miniapp.bean.vod.*;
import cn.binarywang.wx.miniapp.executor.VodSingleUploadRequestExecutor;
import cn.binarywang.wx.miniapp.executor.VodUploadPartRequestExecutor;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;

import java.io.File;
import java.util.List;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Vod.*;


@RequiredArgsConstructor
@Slf4j
public class WxMaVodServiceImpl implements WxMaVodService {

  private final WxMaService service;

  @Override
  public List<WxMaVodMediaInfo> listMedia(WxMaVodListMediaRequest mediaRequest) throws WxErrorException {
    String responseContent = this.service.post(LIST_MEDIA_URL, mediaRequest.toJson());

    JsonObject jsonObject = GsonParser.parse(responseContent);
    boolean hasMediaInfoList = jsonObject.has("media_info_list");
    if (hasMediaInfoList) {
      return WxMaGsonBuilder.create().fromJson(jsonObject.getAsJsonArray("media_info_list"),
        new TypeToken<List<WxMaVodMediaInfo>>() {
        }.getType());
    } else {
      return null;
    }
  }


  @Override
  public List<WxMaVodDramaInfo> listDrama(WxMaVodListDramaRequest mediaRequest) throws WxErrorException {
    String responseContent = this.service.post(LIST_DRAMAS_URL, mediaRequest.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    boolean hasMediaInfoList = jsonObject.has("drama_info_list");
    if (hasMediaInfoList) {
      return WxMaGsonBuilder.create().fromJson(jsonObject.getAsJsonArray("drama_info_list"),
        new TypeToken<List<WxMaVodDramaInfo>>() {
        }.getType());
    } else {
      return null;
    }
  }


  @Override
  public WxMaVodMediaPlaybackInfo getMediaLink(WxMaVodGetMediaLinkRequest request) throws WxErrorException {
    String responseContent = this.service.post(GET_MEDIA_LINK_URL, request.toJson());
    WxMaVodGetMediaLinkResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaVodGetMediaLinkResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return getDetailResponse.getMediaInfo();
  }


  @Override
  public WxMaVodMediaInfo getMedia(WxMaVodGetMediaRequest request) throws WxErrorException {
    String responseContent = this.service.post(GET_MEDIA_URL, request.toJson());
    WxMaVodGetMediaResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaVodGetMediaResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return getDetailResponse.getMediaInfo();
  }

  @Override
  public boolean deleteMedia(WxMaVodDeleteMediaRequest request) throws WxErrorException {
    String responseContent = this.service.post(DELETE_MEDIA_URL, request.toJson());
    WxMaBaseResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaBaseResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return true;
  }

  @Override
  public WxMaVodDramaInfo getDrama(WxMaVodGetDramaRequest request) throws WxErrorException {
    String responseContent = this.service.post(GET_DRAMA_URL, request.toJson());
    WxMaVodGetDramaResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaVodGetDramaResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse.getDramaInfo();

  }

  @Override
  public Integer auditDrama(WxMaVodAuditDramaRequest request) throws WxErrorException {
    String responseContent = this.service.post(AUDIT_DRAMA_URL, request.toJson());
    WxMaVodAuditDramaResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaVodAuditDramaResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(
        new WxError(getDetailResponse.getErrcode(), getDetailResponse.getErrmsg()));
    }

    return getDetailResponse.getDramaId();

  }

  @Override
  public WxMaVodGetCdnUsageResponse getCdnUsageData(WxMaVodGetCdnUsageRequest request) throws WxErrorException {
    String responseContent = this.service.post(GET_CDN_USAGE_DATA_URL, request.toJson());
    WxMaVodGetCdnUsageResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaVodGetCdnUsageResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return getDetailResponse;
  }

  @Override
  public WxMaVodGetCdnLogResponse getCdnLogs(WxMaVodGetCdnLogRequest request) throws WxErrorException {
    String responseContent = this.service.post(GET_CDN_LOGS_URL, request.toJson());
    WxMaVodGetCdnLogResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaVodGetCdnLogResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return getDetailResponse;
  }

  @Override
  public WxMaVodGetTaskResponse getTask(WxMaVodGetTaskRequest request) throws WxErrorException {
    String responseContent = this.service.post(GET_TASK_URL, request.toJson());
    WxMaVodGetTaskResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaVodGetTaskResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return getDetailResponse;
  }

  @Override
  public WxMaVodPullUploadResponse pullUpload(WxMaVodPullUploadRequest request) throws WxErrorException {
    String responseContent = this.service.post(PULL_UPLOAD_URL, request.toJson());
    WxMaVodPullUploadResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaVodPullUploadResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return getDetailResponse;
  }

  @Override
  public WxMaVodSingleFileUploadResult uploadSingleFile(File file, String mediaName, String mediaType) throws WxErrorException {
    WxMaVodSingleFileUploadResult result = this.service.execute(
      VodSingleUploadRequestExecutor.create(this.service.getRequestHttp(), mediaName, mediaType, null, null, null), SINGLE_FILE_UPLOAD_URL, file);
    return result;
  }

  @Override
  public WxMaVodSingleFileUploadResult uploadSingleFile(File file, String mediaName, String mediaType, String coverType, File coverData, String sourceContext) throws WxErrorException {
    WxMaVodSingleFileUploadResult result = this.service.execute(
      VodSingleUploadRequestExecutor.create(this.service.getRequestHttp(), mediaName, mediaType, coverType, coverData, sourceContext), SINGLE_FILE_UPLOAD_URL, file);
    return result;
  }

  @Override
  public WxMaVodApplyUploadResponse applyUpload(WxMaVodApplyUploadRequest request) throws WxErrorException {
    String responseContent = this.service.post(APPLY_UPLOAD_URL, request.toJson());
    WxMaVodApplyUploadResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaVodApplyUploadResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return getDetailResponse;
  }

  @Override
  public WxMaVodCommitUploadResponse commitUpload(WxMaVodCommitUploadRequest request) throws WxErrorException {
    String responseContent = this.service.post(COMMIT_UPLOAD_URL, request.toJson());
    WxMaVodCommitUploadResponse getDetailResponse = WxMaGsonBuilder.create()
      .fromJson(responseContent, WxMaVodCommitUploadResponse.class);

    if (getDetailResponse.getErrcode() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }

    return getDetailResponse;
  }

  @Override
  public WxMaVodUploadPartResult uploadPart(File file, String uploadId, Integer partNumber, Integer resourceType) throws WxErrorException {
    WxMaVodUploadPartResult result = this.service.execute(
      VodUploadPartRequestExecutor.create(this.service.getRequestHttp(), uploadId, partNumber, resourceType), UPLOAD_PART_URL, file);
    return result;
  }
}

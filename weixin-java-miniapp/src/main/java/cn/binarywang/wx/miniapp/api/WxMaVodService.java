package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.vod.*;
import me.chanjar.weixin.common.error.WxErrorException;

import java.io.File;
import java.util.List;

public interface WxMaVodService {
  List<WxMaVodMediaInfo> listMedia(WxMaVodListMediaRequest request) throws WxErrorException;

  List<WxMaVodDramaInfo> listDrama(WxMaVodListDramaRequest request) throws WxErrorException;

  WxMaVodMediaPlaybackInfo getMediaLink(WxMaVodGetMediaLinkRequest request) throws WxErrorException;

  WxMaVodMediaInfo getMedia(WxMaVodGetMediaRequest request) throws WxErrorException;

  boolean deleteMedia(WxMaVodDeleteMediaRequest request) throws WxErrorException;

  WxMaVodDramaInfo getDrama(WxMaVodGetDramaRequest request) throws WxErrorException;

  Integer auditDrama(WxMaVodAuditDramaRequest request) throws WxErrorException;

  WxMaVodGetCdnUsageResponse getCdnUsageData(WxMaVodGetCdnUsageRequest request) throws WxErrorException;

  WxMaVodGetCdnLogResponse getCdnLogs(WxMaVodGetCdnLogRequest request) throws WxErrorException;


  WxMaVodPullUploadResponse pullUpload(WxMaVodPullUploadRequest request) throws WxErrorException;

  WxMaVodGetTaskResponse getTask(WxMaVodGetTaskRequest request) throws WxErrorException;


  WxMaVodSingleFileUploadResult uploadSingleFile(File file, String mediaName, String mediaType) throws WxErrorException;

  WxMaVodSingleFileUploadResult uploadSingleFile(File file, String mediaName, String mediaType, String coverType, File coverData, String sourceContext) throws WxErrorException;

  WxMaVodApplyUploadResponse applyUpload(WxMaVodApplyUploadRequest request) throws WxErrorException;

  WxMaVodCommitUploadResponse commitUpload(WxMaVodCommitUploadRequest request) throws WxErrorException;

  WxMaVodUploadPartResult uploadPart(File file, String uploadId, Integer partNumber, Integer resourceType) throws WxErrorException;

}

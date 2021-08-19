package me.chanjar.weixin.cp.tp.service.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.BaseMediaDownloadBytesRequestExecutor;
import me.chanjar.weixin.common.util.http.MediaUploadBytesRequestExecutor;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.cp.tp.service.WxCpTpMediaService;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Media.*;

/**
 * <pre>
 * 媒体管理接口.
 * Created by Binary Wang on 2017-6-25.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class WxCpTpMediaServiceImpl implements WxCpTpMediaService {
  private final WxCpTpService mainService;

  @Override
  public WxMediaUploadResult upload(String corpId, String mediaType, String fileType, InputStream inputStream)
    throws WxErrorException, IOException {
    return this.upload(corpId, mediaType, FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType));
  }

  @Override
  public WxMediaUploadResult upload(String corpId, String mediaType, byte[] bytes) throws WxErrorException {
    return this.mainService.execute(MediaUploadBytesRequestExecutor.create(this.mainService.getRequestHttp()),
      mainService.getCorpApiUrl(MEDIA_UPLOAD + mediaType, corpId), bytes);
  }

  @Override
  public WxMediaUploadResult upload(String corpId, String mediaType, File file) throws WxErrorException {
    return this.mainService.execute(MediaUploadRequestExecutor.create(this.mainService.getRequestHttp()),
      mainService.getCorpApiUrl(MEDIA_UPLOAD + mediaType, corpId), file);
  }

  @Override
  public String uploadImg(String corpId, File file) throws WxErrorException {
    String url = mainService.getCorpApiUrl(IMG_UPLOAD, corpId);
    return this.mainService.execute(MediaUploadRequestExecutor.create(this.mainService.getRequestHttp()), url, file)
      .getUrl();
  }

  @Override
  public String uploadImg(String corpId, byte[] bytes) throws WxErrorException {
    String url = mainService.getCorpApiUrl(IMG_UPLOAD, corpId);
    return this.mainService.execute(MediaUploadBytesRequestExecutor.create(this.mainService.getRequestHttp()), url, bytes)
      .getUrl();
  }

  @Override
  public byte[] download(String corpId, String mediaId) throws WxErrorException {
    String url = mainService.getCorpApiUrl(MEDIA_GET, corpId);
    return this.mainService.execute(
      BaseMediaDownloadBytesRequestExecutor.create(this.mainService.getRequestHttp()),
      url, "media_id=" + mediaId);
  }

  @Override
  public byte[] getJssdkFile(String corpId, String mediaId) throws WxErrorException {
    String url = mainService.getCorpApiUrl(JSSDK_MEDIA_GET, corpId);
    return this.mainService.execute(
      BaseMediaDownloadBytesRequestExecutor.create(this.mainService.getRequestHttp()),
      url, "media_id=" + mediaId);
  }
}

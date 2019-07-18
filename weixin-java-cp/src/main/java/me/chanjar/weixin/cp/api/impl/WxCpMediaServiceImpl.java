package me.chanjar.weixin.cp.api.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.BaseMediaDownloadRequestExecutor;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.cp.api.WxCpMediaService;
import me.chanjar.weixin.cp.api.WxCpService;

/**
 * <pre>
 * 媒体管理接口.
 * Created by Binary Wang on 2017-6-25.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxCpMediaServiceImpl implements WxCpMediaService {
  private WxCpService mainService;

  public WxCpMediaServiceImpl(WxCpService mainService) {
    this.mainService = mainService;
  }

  @Override
  public WxMediaUploadResult upload(String mediaType, String fileType, InputStream inputStream)
    throws WxErrorException, IOException {
    return this.upload(mediaType, FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType));
  }

  @Override
  public WxMediaUploadResult upload(String mediaType, File file) throws WxErrorException {
    return this.mainService.execute(MediaUploadRequestExecutor.create(this.mainService.getRequestHttp()),
      MEDIA_UPLOAD_URL + mediaType, file);
  }

  @Override
  public File download(String mediaId) throws WxErrorException {
    return this.mainService.execute(
      BaseMediaDownloadRequestExecutor.create(this.mainService.getRequestHttp(),
        this.mainService.getWxCpConfigStorage().getTmpDirFile()),
      MEDIA_GET_URL, "media_id=" + mediaId);
  }

  @Override
  public File getJssdkFile(String mediaId) throws WxErrorException {
    return this.mainService.execute(
      BaseMediaDownloadRequestExecutor.create(this.mainService.getRequestHttp(),
        this.mainService.getWxCpConfigStorage().getTmpDirFile()),
      JSSDK_MEDIA_GET_URL, "media_id=" + mediaId);
  }

  @Override
  public String uploadImg(File file) throws WxErrorException {
    final WxMediaUploadResult result = this.mainService
      .execute(MediaUploadRequestExecutor.create(this.mainService.getRequestHttp()), IMG_UPLOAD_URL, file);
    return result.getUrl();
  }
}

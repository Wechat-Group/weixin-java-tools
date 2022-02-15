package me.chanjar.weixin.cp.api.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.BaseMediaDownloadRequestExecutor;
import me.chanjar.weixin.common.util.http.InputStreamData;
import me.chanjar.weixin.common.util.http.MediaInputStreamUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.cp.api.WxCpMediaService;
import me.chanjar.weixin.cp.api.WxCpService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
public class WxCpMediaServiceImpl implements WxCpMediaService {
  private final WxCpService mainService;

  @Override
  public WxMediaUploadResult upload(String mediaType, String fileType, InputStream inputStream)
    throws WxErrorException, IOException {
    return this.upload(mediaType, FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType));
  }

  @Override
  public WxMediaUploadResult upload(String mediaType, String filename, String url) throws WxErrorException, IOException {
    HttpURLConnection conn = null;
    InputStream inputStream = null;
    try {
      URL remote = new URL(url);
      conn = (HttpURLConnection) remote.openConnection();
      //设置超时间为3秒
      conn.setConnectTimeout(60 * 1000);
      //防止屏蔽程序抓取而返回403错误
      conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
      inputStream = conn.getInputStream();
      return this.mainService.execute(MediaInputStreamUploadRequestExecutor.create(this.mainService.getRequestHttp()), this.mainService.getWxCpConfigStorage().getApiUrl(MEDIA_UPLOAD + mediaType), new InputStreamData(inputStream, filename));
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
        }
      }
      if (conn != null) {
        conn.disconnect();
      }
    }
  }

  @Override
  public WxMediaUploadResult upload(String mediaType, File file) throws WxErrorException {
    return this.mainService.execute(MediaUploadRequestExecutor.create(this.mainService.getRequestHttp()),
      this.mainService.getWxCpConfigStorage().getApiUrl(MEDIA_UPLOAD + mediaType), file);
  }

  @Override
  public File download(String mediaId) throws WxErrorException {
    return this.mainService.execute(
      BaseMediaDownloadRequestExecutor.create(this.mainService.getRequestHttp(),
        this.mainService.getWxCpConfigStorage().getTmpDirFile()),
      this.mainService.getWxCpConfigStorage().getApiUrl(MEDIA_GET), "media_id=" + mediaId);
  }

  @Override
  public File getJssdkFile(String mediaId) throws WxErrorException {
    return this.mainService.execute(
      BaseMediaDownloadRequestExecutor.create(this.mainService.getRequestHttp(),
        this.mainService.getWxCpConfigStorage().getTmpDirFile()),
      this.mainService.getWxCpConfigStorage().getApiUrl(JSSDK_MEDIA_GET), "media_id=" + mediaId);
  }

  @Override
  public String uploadImg(File file) throws WxErrorException {
    final String url = this.mainService.getWxCpConfigStorage().getApiUrl(IMG_UPLOAD);
    return this.mainService.execute(MediaUploadRequestExecutor.create(this.mainService.getRequestHttp()), url, file)
      .getUrl();
  }
}

package me.chanjar.weixin.mp.util.http;

import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.mp.util.http.apache.ApacheMaterialVoiceAndImageDownloadRequestExecutor;
import me.chanjar.weixin.mp.util.http.jodd.JoddMaterialVoiceAndImageDownloadRequestExecutor;
import me.chanjar.weixin.mp.util.http.okhttp.OkhttpMaterialVoiceAndImageDownloadRequestExecutor;

import java.io.File;
import java.io.InputStream;

public abstract class MaterialVoiceAndImageDownloadRequestExecutor<H, P> implements RequestExecutor<InputStream, String> {
  protected RequestHttp<H, P> requestHttp;
  protected File tmpDirFile;

  public MaterialVoiceAndImageDownloadRequestExecutor(RequestHttp requestHttp, File tmpDirFile) {
    this.requestHttp = requestHttp;
    this.tmpDirFile = tmpDirFile;
  }

  public static RequestExecutor<InputStream, String> create(RequestHttp requestHttp, File tmpDirFile) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheMaterialVoiceAndImageDownloadRequestExecutor(requestHttp, tmpDirFile);
      case JODD_HTTP:
        return new JoddMaterialVoiceAndImageDownloadRequestExecutor(requestHttp, tmpDirFile);
      case OK_HTTP:
        return new OkhttpMaterialVoiceAndImageDownloadRequestExecutor(requestHttp, tmpDirFile);
      default:
        return null;
    }
  }


}

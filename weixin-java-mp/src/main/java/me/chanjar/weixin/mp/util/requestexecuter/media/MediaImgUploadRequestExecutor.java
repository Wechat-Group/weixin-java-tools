package me.chanjar.weixin.mp.util.requestexecuter.media;

import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;

import java.io.File;

/**
 * @author miller
 */
public abstract class MediaImgUploadRequestExecutor<H, P> implements RequestExecutor<WxMediaImgUploadResult, File> {
  protected RequestHttp<H, P> requestHttp;

  public MediaImgUploadRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  public static RequestExecutor<WxMediaImgUploadResult, File> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new MediaImgUploadApacheHttpRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new MediaImgUploadHttpRequestExecutor(requestHttp);
      case OK_HTTP:
        return new MediaImgUploadOkhttpRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}

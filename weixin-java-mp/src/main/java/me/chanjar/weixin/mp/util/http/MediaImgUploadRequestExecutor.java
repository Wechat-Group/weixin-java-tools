package me.chanjar.weixin.mp.util.http;

import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;
import me.chanjar.weixin.mp.util.http.apache.ApacheMediaImgUploadRequestExecutor;
import me.chanjar.weixin.mp.util.http.jodd.JoddMediaImgUploadRequestExecutor;
import me.chanjar.weixin.mp.util.http.okhttp.OkhttpMediaImgUploadRequestExecutor;

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
        return new ApacheMediaImgUploadRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddMediaImgUploadRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkhttpMediaImgUploadRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}

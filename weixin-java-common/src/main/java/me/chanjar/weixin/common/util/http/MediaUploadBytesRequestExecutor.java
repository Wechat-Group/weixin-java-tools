package me.chanjar.weixin.common.util.http;

import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.apache.ApacheMediaUploadBytesRequestExecutor;
import me.chanjar.weixin.common.util.http.jodd.JoddHttpMediaUploadBytesRequestExecutor;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpMediaUploadBytesRequestExecutor;

import java.io.IOException;

/**
 * 上传媒体文件请求执行器.
 * 请求的参数是File, 返回的结果是String
 *
 * @author Daniel Qian
 */
public abstract class MediaUploadBytesRequestExecutor<H, P> implements RequestExecutor<WxMediaUploadResult, byte[]> {
  protected RequestHttp<H, P> requestHttp;

  public MediaUploadBytesRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, byte[] data, ResponseHandler<WxMediaUploadResult> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<WxMediaUploadResult, byte[]> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheMediaUploadBytesRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddHttpMediaUploadBytesRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkHttpMediaUploadBytesRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}

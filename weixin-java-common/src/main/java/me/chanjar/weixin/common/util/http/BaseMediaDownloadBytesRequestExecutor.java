package me.chanjar.weixin.common.util.http;

import me.chanjar.weixin.common.bean.result.WxMediaDownloadBytesResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.apache.ApacheMediaDownloadBytesRequestExecutor;
import me.chanjar.weixin.common.util.http.jodd.JoddHttpMediaDownloadBytesRequestExecutor;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpMediaDownloadBytesRequestExecutor;

import java.io.IOException;

/**
 * 下载媒体文件请求执行器.
 * 请求的参数是String, 返回的结果是File
 * 视频文件不支持下载
 *
 * @author Daniel Qian
 */
public abstract class BaseMediaDownloadBytesRequestExecutor<H, P> implements RequestExecutor<WxMediaDownloadBytesResult, String> {
  protected RequestHttp<H, P> requestHttp;

  public BaseMediaDownloadBytesRequestExecutor(RequestHttp<H, P> requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, String data, ResponseHandler<WxMediaDownloadBytesResult> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<WxMediaDownloadBytesResult, String> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheMediaDownloadBytesRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddHttpMediaDownloadBytesRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkHttpMediaDownloadBytesRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}

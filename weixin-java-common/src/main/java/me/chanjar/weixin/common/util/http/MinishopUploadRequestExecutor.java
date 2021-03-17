package me.chanjar.weixin.common.util.http;

import me.chanjar.weixin.common.bean.result.WxMinishopImageUploadResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.apache.ApacheMinishopMediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.jodd.JoddHttpMinishopMediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpMinishopMediaUploadRequestExecutor;

import java.io.File;
import java.io.IOException;

public abstract class MinishopUploadRequestExecutor<H, P> implements RequestExecutor<WxMinishopImageUploadResult, File> {
  protected RequestHttp<H, P> requestHttp;

  public MinishopUploadRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<WxMinishopImageUploadResult> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<WxMinishopImageUploadResult, File> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheMinishopMediaUploadRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddHttpMinishopMediaUploadRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkHttpMinishopMediaUploadRequestExecutor(requestHttp);
      default:
        return null;
    }
  }
}

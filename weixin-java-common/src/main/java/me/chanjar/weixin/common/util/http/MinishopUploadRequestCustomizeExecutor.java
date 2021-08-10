package me.chanjar.weixin.common.util.http;

import me.chanjar.weixin.common.bean.result.WxMinishopImageUploadCustomizeResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.apache.ApacheMinishopMediaUploadRequestCustomizeExecutor;
import me.chanjar.weixin.common.util.http.jodd.JoddHttpMinishopMediaUploadRequestCustomizeExecutor;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpMinishopMediaUploadRequestCustomizeExecutor;

import java.io.File;
import java.io.IOException;

public abstract class MinishopUploadRequestCustomizeExecutor<H, P> implements RequestExecutor<WxMinishopImageUploadCustomizeResult, File> {
  protected RequestHttp<H, P> requestHttp;

  public MinishopUploadRequestCustomizeExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<WxMinishopImageUploadCustomizeResult> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<WxMinishopImageUploadCustomizeResult, File> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheMinishopMediaUploadRequestCustomizeExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddHttpMinishopMediaUploadRequestCustomizeExecutor(requestHttp);
      case OK_HTTP:
        return new OkHttpMinishopMediaUploadRequestCustomizeExecutor(requestHttp);
      default:
        return null;
    }
  }
}

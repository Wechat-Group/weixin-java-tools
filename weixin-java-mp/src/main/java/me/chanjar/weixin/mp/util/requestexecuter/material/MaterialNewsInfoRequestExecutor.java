package me.chanjar.weixin.mp.util.requestexecuter.material;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;

import java.io.IOException;

public abstract class MaterialNewsInfoRequestExecutor<H, P> implements RequestExecutor<WxMpMaterialNews, String> {
  protected RequestHttp<H, P> requestHttp;

  public MaterialNewsInfoRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, String data, ResponseHandler<WxMpMaterialNews> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<WxMpMaterialNews, String> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new MaterialNewsInfoApacheHttpRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new MaterialNewsInfoJoddHttpRequestExecutor(requestHttp);
      case OK_HTTP:
        return new MaterialNewsInfoOkhttpRequestExecutor(requestHttp);
      default:
        //TODO 需要优化抛出异常
        return null;
    }
  }

}

package me.chanjar.weixin.mp.util.http;

import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.util.http.apache.ApacheMaterialNewsInfoRequestExecutor;
import me.chanjar.weixin.mp.util.http.jodd.JoddMaterialNewsInfoRequestExecutor;
import me.chanjar.weixin.mp.util.http.okhttp.OkhttpMaterialNewsInfoRequestExecutor;

public abstract class MaterialNewsInfoRequestExecutor<H, P> implements RequestExecutor<WxMpMaterialNews, String> {
  protected RequestHttp<H, P> requestHttp;

  public MaterialNewsInfoRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  public static RequestExecutor<WxMpMaterialNews, String> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheMaterialNewsInfoRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddMaterialNewsInfoRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkhttpMaterialNewsInfoRequestExecutor(requestHttp);
      default:
        //TODO 需要优化抛出异常
        return null;
    }
  }

}

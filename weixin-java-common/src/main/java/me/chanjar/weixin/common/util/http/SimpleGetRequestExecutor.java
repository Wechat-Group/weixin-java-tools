package me.chanjar.weixin.common.util.http;

import java.io.IOException;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientSimpleGetRequestExecutor;
import me.chanjar.weixin.common.util.http.jodd.JoddHttpSimpleGetRequestExecutor;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpSimpleGetRequestExecutor;

/**
 * 简单的GET请求执行器.
 * 请求的参数是String, 返回的结果也是String
 *
 * @author Daniel Qian
 */
public abstract class SimpleGetRequestExecutor<H, P> implements RequestExecutor<String, String> {
  protected RequestHttp<H, P> requestHttp;

  public SimpleGetRequestExecutor(RequestHttp<H, P> requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, String data, ResponseHandler<String> handler) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data));
  }

  public static RequestExecutor<String, String> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheHttpClientSimpleGetRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddHttpSimpleGetRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkHttpSimpleGetRequestExecutor(requestHttp);
      default:
        throw new IllegalArgumentException("非法请求参数");
    }
  }

}

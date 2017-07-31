package me.chanjar.weixin.common.util.http.okhttp;

import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;
import okhttp3.*;

import java.io.IOException;

/**
 * Created by ecoolper on 2017/5/4.
 */
public class OkHttpSimplePostRequestExecutor extends SimplePostRequestExecutor<ConnectionPool, OkHttpProxyInfo> {

  public OkHttpSimplePostRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public String execute(String uri, String postEntity) throws WxErrorException, IOException {
    ConnectionPool pool = requestHttp.getRequestHttpClient();
    final OkHttpProxyInfo proxyInfo = requestHttp.getRequestHttpProxy();

    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder().connectionPool(pool);
    //设置代理
    if (proxyInfo != null) {
      clientBuilder.proxy(proxyInfo.getProxy());
    }
    //设置授权
    clientBuilder.authenticator(new Authenticator() {
      @Override
      public Request authenticate(Route route, Response response) throws IOException {
        String credential = Credentials.basic(proxyInfo.getProxyUsername(), proxyInfo.getProxyPassword());
        return response.request().newBuilder()
          .header("Authorization", credential)
          .build();
      }
    });
    //得到httpClient
    OkHttpClient client = clientBuilder.build();


    MediaType mediaType = MediaType.parse("text/plain; charset=utf-8");
    RequestBody body = RequestBody.create(mediaType, postEntity);

    Request request = new Request.Builder().url(uri).post(body).build();

    Response response = client.newCall(request).execute();
    String responseContent = response.body().string();
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return responseContent;
  }

}

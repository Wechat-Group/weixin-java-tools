package me.chanjar.weixin.common.util.http;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.apache.Utf8ResponseHandler;

import me.chanjar.weixin.common.util.http.okhttp.OkhttpProxyInfo;
import okhttp3.*;

import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

/**
 * 用装饰模式实现
 * 简单的POST请求执行器，请求的参数是String, 返回的结果也是String
 *
 * @author Daniel Qian
 */
public class SimplePostRequestExecutor extends AbstractRequestExecutor<String, String> {

  /**
   * apache-http实现方式
   *
   * @param httpclient
   * @param httpProxy
   * @param uri
   * @param postEntity
   * @return
   * @throws WxErrorException
   * @throws IOException
   */
  public String executeApache(CloseableHttpClient httpclient, HttpHost httpProxy, String uri, String postEntity) throws WxErrorException, IOException {

    HttpPost httpPost = new HttpPost(uri);
    if (httpProxy != null) {
      RequestConfig config = RequestConfig.custom().setProxy(httpProxy).build();
      httpPost.setConfig(config);
    }

    if (postEntity != null) {
      StringEntity entity = new StringEntity(postEntity, Consts.UTF_8);
      httpPost.setEntity(entity);
    }

    try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
      String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
      if (responseContent.isEmpty()) {
        throw new WxErrorException(
          WxError.newBuilder().setErrorCode(9999).setErrorMsg("无响应内容")
            .build());
      }

      if (responseContent.startsWith("<xml>")) {
        //xml格式输出直接返回
        return responseContent;
      }

      WxError error = WxError.fromJson(responseContent);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      }
      return responseContent;
    } finally {
      httpPost.releaseConnection();
    }
  }


  /**
   * jodd-http实现方式
   *
   * @param provider
   * @param proxyInfo
   * @param uri
   * @param postEntity
   * @return
   * @throws WxErrorException
   * @throws IOException
   */
  public String executeJodd(HttpConnectionProvider provider, ProxyInfo proxyInfo, String uri, String postEntity) throws WxErrorException, IOException {
    HttpRequest request = HttpRequest.post(uri);
    if (proxyInfo != null) {
      provider.useProxy(proxyInfo);
    }
    request.withConnectionProvider(provider);
    if (postEntity != null) {
      request.bodyText(postEntity);
    }
    HttpResponse response = request.send();

    String responseContent = response.bodyText();
    if (responseContent.isEmpty()) {
      throw new WxErrorException(
        WxError.newBuilder().setErrorCode(9999).setErrorMsg("无响应内容")
          .build());
    }

    if (responseContent.startsWith("<xml>")) {
      //xml格式输出直接返回
      return responseContent;
    }

    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return responseContent;
  }


  /**
   * okHttp实现方式
   *
   * @param pool
   * @param proxyInfo
   * @param uri
   * @param postEntity
   * @return
   * @throws WxErrorException
   * @throws IOException
   */
  public String executeOkhttp(ConnectionPool pool, final OkhttpProxyInfo proxyInfo, String uri, String postEntity) throws WxErrorException, IOException {
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
    String responseContent = response.body().toString();
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return responseContent;
  }


}

package me.chanjar.weixin.mp.util.http;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;

import jodd.util.MimeTypes;

import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.AbstractRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.apache.Utf8ResponseHandler;

import me.chanjar.weixin.common.util.http.okhttp.OkhttpProxyInfo;

import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;
import okhttp3.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author miller
 */
public class MediaImgUploadRequestExecutor extends AbstractRequestExecutor<WxMediaImgUploadResult, File> {

  @Override
  public WxMediaImgUploadResult executeJodd(HttpConnectionProvider provider, ProxyInfo proxyInfo, String uri, File data) throws WxErrorException, IOException {
    if (data == null) {
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("文件对象为空").build());
    }

    HttpRequest request = HttpRequest.post(uri);
    if (proxyInfo != null) {
      provider.useProxy(proxyInfo);
    }
    request.withConnectionProvider(provider);

    request.form("media", data);
    HttpResponse response = request.send();
    String responseContent = response.bodyText();
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }

    return WxMediaImgUploadResult.fromJson(responseContent);
  }

  @Override
  public WxMediaImgUploadResult executeOkhttp(ConnectionPool pool, final OkhttpProxyInfo proxyInfo, String uri, File data) throws WxErrorException, IOException {
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

    RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), data);
    RequestBody body = new MultipartBody.Builder().addFormDataPart("media", null, fileBody).build();
    Request request = new Request.Builder().url(uri).post(body).build();

    Response response = client.newCall(request).execute();
    String responseContent = response.body().toString();
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }

    return WxMediaImgUploadResult.fromJson(responseContent);
  }

  @Override
  public WxMediaImgUploadResult executeApache(CloseableHttpClient httpclient, HttpHost httpProxy, String uri,
                                              File data) throws WxErrorException, IOException {

    if (data == null) {
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("文件对象为空").build());
    }

    HttpPost httpPost = new HttpPost(uri);
    if (httpProxy != null) {
      RequestConfig config = RequestConfig.custom().setProxy(httpProxy).build();
      httpPost.setConfig(config);
    }

    HttpEntity entity = MultipartEntityBuilder
      .create()
      .addBinaryBody("media", data)
      .setMode(HttpMultipartMode.RFC6532)
      .build();
    httpPost.setEntity(entity);
    httpPost.setHeader("Content-Type", ContentType.MULTIPART_FORM_DATA.toString());

    try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
      String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
      WxError error = WxError.fromJson(responseContent);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      }

      return WxMediaImgUploadResult.fromJson(responseContent);
    }
  }

}

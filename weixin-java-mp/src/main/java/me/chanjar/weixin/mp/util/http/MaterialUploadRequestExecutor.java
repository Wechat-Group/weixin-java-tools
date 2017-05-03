package me.chanjar.weixin.mp.util.http;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.AbstractRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.apache.Utf8ResponseHandler;

import me.chanjar.weixin.common.util.http.okhttp.OkhttpProxyInfo;

import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;
import okhttp3.*;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class MaterialUploadRequestExecutor extends AbstractRequestExecutor<WxMpMaterialUploadResult, WxMpMaterial> {

  @Override
  public WxMpMaterialUploadResult executeJodd(HttpConnectionProvider provider, ProxyInfo httpProxy, String uri, WxMpMaterial material) throws WxErrorException, IOException {
    HttpRequest request = HttpRequest.post(uri);
    if (httpProxy != null) {
      provider.useProxy(httpProxy);
    }
    request.withConnectionProvider(provider);

    if (material == null) {
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("非法请求，material参数为空").build());
    }

    File file = material.getFile();
    if (file == null || !file.exists()) {
      throw new FileNotFoundException();
    }
    request.form("media", file);
    Map<String, String> form = material.getForm();
    if (material.getForm() != null) {
      request.form("description", WxGsonBuilder.create().toJson(form));
    }

    HttpResponse response = request.send();
    String responseContent = response.bodyText();
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    } else {
      return WxMpMaterialUploadResult.fromJson(responseContent);
    }
  }

  @Override
  public WxMpMaterialUploadResult executeOkhttp(ConnectionPool pool, final OkhttpProxyInfo proxyInfo, String uri, WxMpMaterial material) throws WxErrorException, IOException {
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


    if (material == null) {
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("非法请求，material参数为空").build());
    }

    File file = material.getFile();
    if (file == null || !file.exists()) {
      throw new FileNotFoundException();
    }
    RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
    MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().addFormDataPart("media", null, fileBody);
    Map<String, String> form = material.getForm();
    if (material.getForm() != null) {
      bodyBuilder.addFormDataPart("description", WxGsonBuilder.create().toJson(form));
    }
    RequestBody body =bodyBuilder.build();
    Request request = new Request.Builder().url(uri).post(body).build();
    Response response = client.newCall(request).execute();
    String responseContent = response.body().toString();
    WxError error = WxError.fromJson(responseContent);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    } else {
      return WxMpMaterialUploadResult.fromJson(responseContent);
    }
  }

  @Override
  public WxMpMaterialUploadResult executeApache(CloseableHttpClient httpclient, HttpHost httpProxy, String uri,
                                                WxMpMaterial material) throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (httpProxy != null) {
      RequestConfig response = RequestConfig.custom().setProxy(httpProxy).build();
      httpPost.setConfig(response);
    }

    if (material == null) {
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("非法请求，material参数为空").build());
    }

    File file = material.getFile();
    if (file == null || !file.exists()) {
      throw new FileNotFoundException();
    }

    MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
    multipartEntityBuilder
      .addBinaryBody("media", file)
      .setMode(HttpMultipartMode.RFC6532);
    Map<String, String> form = material.getForm();
    if (material.getForm() != null) {
      multipartEntityBuilder.addTextBody("description", WxGsonBuilder.create().toJson(form));
    }

    httpPost.setEntity(multipartEntityBuilder.build());
    httpPost.setHeader("Content-Type", ContentType.MULTIPART_FORM_DATA.toString());

    try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
      String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
      WxError error = WxError.fromJson(responseContent);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      } else {
        return WxMpMaterialUploadResult.fromJson(responseContent);
      }
    } finally {
      httpPost.releaseConnection();
    }
  }

}

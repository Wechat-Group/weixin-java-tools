package me.chanjar.weixin.common.util.http.apache;

import java.io.File;
import java.io.IOException;

import me.chanjar.weixin.common.bean.result.WxAuditMediaUploadResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.AuditMediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author yangyh22
 * @since 2020/11/14
 */
public class ApacheAuditMediaUploadRequestExecutor extends AuditMediaUploadRequestExecutor<CloseableHttpClient, HttpHost> {

  public ApacheAuditMediaUploadRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxAuditMediaUploadResult execute(String uri, File file, WxType wxType) throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpPost.setConfig(config);
    }
    if (file != null) {
      HttpEntity entity = MultipartEntityBuilder
        .create()
        .addBinaryBody("media", file)
        .setMode(HttpMultipartMode.RFC6532)
        .build();
      httpPost.setEntity(entity);
    }
    try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpPost)) {
      String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
      WxError error = WxError.fromJson(responseContent, wxType);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      }
      return WxAuditMediaUploadResult.fromJson(responseContent);
    } finally {
      httpPost.releaseConnection();
    }
  }
}

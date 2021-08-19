package me.chanjar.weixin.common.util.http.apache;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.BaseMediaDownloadBytesRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * .
 *
 * @author ecoolper
 * @date 2017/5/5
 */
public class ApacheMediaDownloadBytesRequestExecutor extends BaseMediaDownloadBytesRequestExecutor<CloseableHttpClient, HttpHost> {
  public ApacheMediaDownloadBytesRequestExecutor(RequestHttp<CloseableHttpClient, HttpHost> requestHttp) {
    super(requestHttp);
  }

  @Override
  public byte[] execute(String uri, String queryParam, WxType wxType) throws WxErrorException, IOException {
    if (queryParam != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
    }

    HttpGet httpGet = new HttpGet(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig config = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpGet.setConfig(config);
    }

    try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpGet);
         InputStream inputStream = InputStreamResponseHandler.INSTANCE.handleResponse(response)) {
      Header[] contentTypeHeader = response.getHeaders("Content-Type");
      if (contentTypeHeader != null && contentTypeHeader.length > 0) {
        if (contentTypeHeader[0].getValue().startsWith(ContentType.APPLICATION_JSON.getMimeType())) {
          // application/json; encoding=utf-8 下载媒体文件出错
          String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
          throw new WxErrorException(WxError.fromJson(responseContent, wxType));
        }
      }

      return IOUtils.toByteArray(inputStream);

    } finally {
      httpGet.releaseConnection();
    }
  }

}

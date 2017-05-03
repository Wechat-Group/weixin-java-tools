package me.chanjar.weixin.common.util.http;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.apache.InputStreamResponseHandler;
import me.chanjar.weixin.common.util.http.apache.Utf8ResponseHandler;
import me.chanjar.weixin.common.util.http.okhttp.OkhttpProxyInfo;
import okhttp3.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 下载媒体文件请求执行器，请求的参数是String, 返回的结果是File
 * 视频文件不支持下载
 *
 * @author Daniel Qian
 */
public class MediaDownloadRequestExecutor extends AbstractRequestExecutor<File, String> {

  private File tmpDirFile;

  public MediaDownloadRequestExecutor(File tmpDirFile) {
    this.tmpDirFile = tmpDirFile;
  }

  private String getFileName(HttpResponse response) throws WxErrorException {
    String content = response.header("Content-disposition");
    if (content == null || content.length() == 0) {
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("无法获取到文件名").build());
    }

    Pattern p = Pattern.compile(".*filename=\"(.*)\"");
    Matcher m = p.matcher(content);
    if (m.matches()) {
      return m.group(1);
    }
    throw new WxErrorException(WxError.newBuilder().setErrorMsg("无法获取到文件名").build());
  }

  private String getFileName(CloseableHttpResponse response) throws WxErrorException {
    Header[] contentDispositionHeader = response.getHeaders("Content-disposition");
    if (contentDispositionHeader == null || contentDispositionHeader.length == 0) {
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("无法获取到文件名").build());
    }

    Pattern p = Pattern.compile(".*filename=\"(.*)\"");
    Matcher m = p.matcher(contentDispositionHeader[0].getValue());
    if (m.matches()) {
      return m.group(1);
    }
    throw new WxErrorException(WxError.newBuilder().setErrorMsg("无法获取到文件名").build());
  }


  /**
   * apache-http实现方式
   *
   * @param httpclient
   * @param httpProxy
   * @param uri
   * @param queryParam
   * @return
   * @throws WxErrorException
   * @throws IOException
   */
  public File executeApache(CloseableHttpClient httpclient, HttpHost httpProxy, String uri, String queryParam) throws WxErrorException, IOException {
    if (queryParam != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
    }

    HttpGet httpGet = new HttpGet(uri);
    if (httpProxy != null) {
      RequestConfig config = RequestConfig.custom().setProxy(httpProxy).build();
      httpGet.setConfig(config);
    }

    try (CloseableHttpResponse response = httpclient.execute(httpGet);
         InputStream inputStream = InputStreamResponseHandler.INSTANCE
           .handleResponse(response)) {
      Header[] contentTypeHeader = response.getHeaders("Content-Type");
      if (contentTypeHeader != null && contentTypeHeader.length > 0) {
        if (contentTypeHeader[0].getValue().startsWith(ContentType.APPLICATION_JSON.getMimeType())) {
          // application/json; encoding=utf-8 下载媒体文件出错
          String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
          throw new WxErrorException(WxError.fromJson(responseContent));
        }
      }

      String fileName = getFileName(response);
      if (StringUtils.isBlank(fileName)) {
        return null;
      }

      String[] nameAndExt = fileName.split("\\.");
      return FileUtils.createTmpFile(inputStream, nameAndExt[0], nameAndExt[1], this.tmpDirFile);

    } finally {
      httpGet.releaseConnection();
    }

  }


  /**
   * jodd-http实现方式
   *
   * @param provider
   * @param proxyInfo
   * @param uri
   * @param queryParam
   * @return
   * @throws WxErrorException
   * @throws IOException
   */
  public File executeJodd(HttpConnectionProvider provider, ProxyInfo proxyInfo, String uri, String queryParam) throws WxErrorException, IOException {
    if (queryParam != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
    }

    HttpRequest request = HttpRequest.get(uri);
    if (proxyInfo != null) {
      provider.useProxy(proxyInfo);
    }
    request.withConnectionProvider(provider);

    HttpResponse response = request.send();
    String contentType = response.header("Content-Type");
    if (contentType != null && contentType.startsWith("application/json")) {
      // application/json; encoding=utf-8 下载媒体文件出错
      throw new WxErrorException(WxError.fromJson(response.bodyText()));
    }

    String fileName = getFileName(response);
    if (StringUtils.isBlank(fileName)) {
      return null;
    }

    InputStream inputStream = new ByteArrayInputStream(response.bodyBytes());
    String[] nameAndExt = fileName.split("\\.");
    return FileUtils.createTmpFile(inputStream, nameAndExt[0], nameAndExt[1], this.tmpDirFile);
  }


  /**
   * okhttp现实方式
   *
   * @param pool
   * @param proxyInfo
   * @param uri
   * @param queryParam
   * @return
   * @throws WxErrorException
   * @throws IOException
   */
  public File executeOkhttp(ConnectionPool pool, final OkhttpProxyInfo proxyInfo, String uri, String queryParam) throws WxErrorException, IOException {
    if (queryParam != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
    }

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

    Request request = new Request.Builder().url(uri).get().build();

    Response response = client.newCall(request).execute();

    String contentType = response.header("Content-Type");
    if (contentType != null && contentType.startsWith("application/json")) {
      // application/json; encoding=utf-8 下载媒体文件出错
      throw new WxErrorException(WxError.fromJson(response.body().toString()));
    }

    String fileName = getFileName(response);
    if (StringUtils.isBlank(fileName)) {
      return null;
    }

    InputStream inputStream = new ByteArrayInputStream(response.body().bytes());
    String[] nameAndExt = fileName.split("\\.");
    return FileUtils.createTmpFile(inputStream, nameAndExt[0], nameAndExt[1], this.tmpDirFile);
  }

  private String getFileName(Response response) throws WxErrorException {
    String content = response.header("Content-disposition");
    if (content == null || content.length() == 0) {
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("无法获取到文件名").build());
    }

    Pattern p = Pattern.compile(".*filename=\"(.*)\"");
    Matcher m = p.matcher(content);
    if (m.matches()) {
      return m.group(1);
    }
    throw new WxErrorException(WxError.newBuilder().setErrorMsg("无法获取到文件名").build());
  }

}

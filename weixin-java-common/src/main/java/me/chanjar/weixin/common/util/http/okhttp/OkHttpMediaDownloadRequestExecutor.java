package me.chanjar.weixin.common.util.http.okhttp;

import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.MediaDownloadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ecoolper on 2017/5/5.
 */
public class OkHttpMediaDownloadRequestExecutor extends MediaDownloadRequestExecutor<ConnectionPool, OkHttpProxyInfo> {


  public OkHttpMediaDownloadRequestExecutor(RequestHttp requestHttp, File tmpDirFile) {
    super(requestHttp, tmpDirFile);
  }

  @Override
  public File execute(String uri, String queryParam) throws WxErrorException, IOException {
    if (queryParam != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
    }

    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder().connectionPool(requestHttp.getRequestHttpClient());
    //设置代理
    if (requestHttp.getRequestHttpProxy() != null) {
      clientBuilder.proxy(requestHttp.getRequestHttpProxy().getProxy());
    }
    //设置授权
    clientBuilder.authenticator(new Authenticator() {
      @Override
      public Request authenticate(Route route, Response response) throws IOException {
        String credential = Credentials.basic(requestHttp.getRequestHttpProxy().getProxyUsername(), requestHttp.getRequestHttpProxy().getProxyPassword());
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
      throw new WxErrorException(WxError.fromJson(response.body().string()));
    }

    String fileName = getFileName(response);
    if (StringUtils.isBlank(fileName)) {
      return null;
    }

    InputStream inputStream = new ByteArrayInputStream(response.body().bytes());
    String[] nameAndExt = fileName.split("\\.");
    return FileUtils.createTmpFile(inputStream, nameAndExt[0], nameAndExt[1], super.tmpDirFile);
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

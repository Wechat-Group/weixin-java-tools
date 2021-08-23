package me.chanjar.weixin.common.util.http.okhttp;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.result.WxMediaDownloadBytesResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.BaseMediaDownloadBytesRequestExecutor;
import me.chanjar.weixin.common.util.http.HttpResponseProxy;
import me.chanjar.weixin.common.util.http.RequestHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * @author caiqiyuan
 */
@Slf4j
public class OkHttpMediaDownloadBytesRequestExecutor extends BaseMediaDownloadBytesRequestExecutor<OkHttpClient, OkHttpProxyInfo> {

  public OkHttpMediaDownloadBytesRequestExecutor(RequestHttp<OkHttpClient, OkHttpProxyInfo> requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMediaDownloadBytesResult execute(String uri, String queryParam, WxType wxType) throws WxErrorException, IOException {
    if (queryParam != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
    }

    //得到httpClient
    OkHttpClient client = requestHttp.getRequestHttpClient();

    Request request = new Request.Builder().url(uri).get().build();

    Response response = client.newCall(request).execute();

    String contentType = response.header("Content-Type");
    if (contentType != null && contentType.startsWith("application/json")) {
      // application/json; encoding=utf-8 下载媒体文件出错
      throw new WxErrorException(WxError.fromJson(response.body().string(), wxType));
    }

    String fileName = new HttpResponseProxy(response).getFileName();
    if (StringUtils.isBlank(fileName)) {
      return null;
    }

    String baseName = FilenameUtils.getBaseName(fileName);
    if (StringUtils.isBlank(fileName) || baseName.length() < 3) {
      baseName = String.valueOf(System.currentTimeMillis());
    }

    return WxMediaDownloadBytesResult.builder()
      .bytes(response.body().source().readByteArray())
      .extension(FilenameUtils.getExtension(fileName))
      .baseName(baseName)
      .fileName(fileName)
      .build();
  }
}

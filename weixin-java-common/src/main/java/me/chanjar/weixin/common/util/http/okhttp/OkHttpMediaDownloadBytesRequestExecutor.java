package me.chanjar.weixin.common.util.http.okhttp;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.BaseMediaDownloadBytesRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * .
 *
 * @author ecoolper
 * @date 2017/5/5
 */
@Slf4j
public class OkHttpMediaDownloadBytesRequestExecutor extends BaseMediaDownloadBytesRequestExecutor<OkHttpClient, OkHttpProxyInfo> {

  public OkHttpMediaDownloadBytesRequestExecutor(RequestHttp<OkHttpClient, OkHttpProxyInfo> requestHttp) {
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

    //得到httpClient
    OkHttpClient client = requestHttp.getRequestHttpClient();

    Request request = new Request.Builder().url(uri).get().build();

    Response response = client.newCall(request).execute();

    String contentType = response.header("Content-Type");
    if (contentType != null && contentType.startsWith("application/json")) {
      // application/json; encoding=utf-8 下载媒体文件出错
      throw new WxErrorException(WxError.fromJson(response.body().string(), wxType));
    }

    return response.body().source().readByteArray();
  }

}

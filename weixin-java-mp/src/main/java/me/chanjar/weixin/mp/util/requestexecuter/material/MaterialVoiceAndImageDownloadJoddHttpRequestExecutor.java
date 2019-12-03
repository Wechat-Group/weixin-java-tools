package me.chanjar.weixin.mp.util.requestexecuter.material;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.util.StringPool;

import me.chanjar.weixin.common.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by ecoolper on 2017/5/5.
 */
public class MaterialVoiceAndImageDownloadJoddHttpRequestExecutor extends MaterialVoiceAndImageDownloadRequestExecutor<HttpConnectionProvider, ProxyInfo> {
  public MaterialVoiceAndImageDownloadJoddHttpRequestExecutor(RequestHttp requestHttp, File tmpDirFile) {
    super(requestHttp, tmpDirFile);
  }

  @Override
  public InputStream execute(String uri, String materialId, WxType wxType) throws WxErrorException, IOException {
    HttpRequest request = HttpRequest.post(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }
    request.withConnectionProvider(requestHttp.getRequestHttpClient());

    request.query("media_id", materialId);
    HttpResponse response = request.send();
    response.charset(StringPool.UTF_8);
    try (InputStream inputStream = new ByteArrayInputStream(response.bodyBytes())) {
      // 下载媒体文件出错
      byte[] responseContent = IOUtils.toByteArray(inputStream);
      String responseContentString = new String(responseContent, StandardCharsets.UTF_8);
      if (responseContentString.length() < 100) {
        try {
          WxError wxError = WxGsonBuilder.create().fromJson(responseContentString, WxError.class);
          if (wxError.getErrorCode() != 0) {
            throw new WxErrorException(wxError);
          }
        } catch (com.google.gson.JsonSyntaxException ex) {
          return new ByteArrayInputStream(responseContent);
        }
      }
      return new ByteArrayInputStream(responseContent);
    }
  }
}

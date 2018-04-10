package me.chanjar.weixin.mp.util.http.okhttp;

import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.util.http.MaterialVoiceAndImageDownloadRequestExecutor;
import okhttp3.*;
import okio.BufferedSink;
import okio.Okio;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by ecoolper on 2017/5/5.
 */
public class OkhttpMaterialVoiceAndImageDownloadRequestExecutor extends MaterialVoiceAndImageDownloadRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public OkhttpMaterialVoiceAndImageDownloadRequestExecutor(RequestHttp requestHttp, File tmpDirFile) {
    super(requestHttp, tmpDirFile);
  }

  @Override
  public InputStream execute(String uri, String materialId) throws WxErrorException, IOException {
    logger.debug("OkhttpMaterialVoiceAndImageDownloadRequestExecutor is running");
    OkHttpClient client = requestHttp.getRequestHttpClient();
    RequestBody requestBody = new FormBody.Builder().add("media_id", materialId).build();
    Request request = new Request.Builder().url(uri).get().post(requestBody).build();
    Response response = client.newCall(request).execute();
    String contentTypeHeader = response.header("Content-Type");
    if ("text/plain".equals(contentTypeHeader)) {
      String responseContent = response.body().string();
      throw new WxErrorException(WxError.fromJson(responseContent));
    }
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); BufferedSink sink = Okio.buffer(Okio.sink(outputStream))) {
      sink.writeAll(response.body().source());
      return new ByteArrayInputStream(outputStream.toByteArray());
    }
  }
}

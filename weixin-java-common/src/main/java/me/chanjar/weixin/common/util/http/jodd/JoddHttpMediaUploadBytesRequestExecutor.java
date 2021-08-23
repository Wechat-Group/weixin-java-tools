package me.chanjar.weixin.common.util.http.jodd;

import cn.hutool.core.util.RandomUtil;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.http.up.ByteArrayUploadable;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.ByteUtil;
import me.chanjar.weixin.common.util.http.MediaUploadBytesRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author caiqiyuan
 */
public class JoddHttpMediaUploadBytesRequestExecutor extends MediaUploadBytesRequestExecutor<HttpConnectionProvider, ProxyInfo> {
  public JoddHttpMediaUploadBytesRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMediaUploadResult execute(String uri, byte[] bytes, WxType wxType) throws WxErrorException, IOException {
    HttpRequest request = HttpRequest.post(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }
    request.withConnectionProvider(requestHttp.getRequestHttpClient());
    String ext = ByteUtil.getExt(bytes);
    request.form("media", new ByteArrayUploadable(ByteUtil.getBody(bytes), RandomUtil.randomString(16) + "." + ext));
    HttpResponse response = request.send();
    response.charset(StandardCharsets.UTF_8.name());

    String responseContent = response.bodyText();
    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return WxMediaUploadResult.fromJson(responseContent);
  }
}

package cn.binarywang.wx.miniapp.executor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import cn.binarywang.wx.miniapp.bean.WxMaAuditMediaUploadResult;

/**
 * @author yangyh22
 * @since 2020/11/14
 */
public class JoddHttpAuditMediaUploadRequestExecutor extends AuditMediaUploadRequestExecutor<HttpConnectionProvider, ProxyInfo> {

  public JoddHttpAuditMediaUploadRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMaAuditMediaUploadResult execute(String uri, File file, WxType wxType) throws WxErrorException, IOException {
    HttpRequest request = HttpRequest.post(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }
    request.withConnectionProvider(requestHttp.getRequestHttpClient());
    request.form("media", file);
    HttpResponse response = request.send();
    response.charset(StandardCharsets.UTF_8.name());

    String responseContent = response.bodyText();
    WxError error = WxError.fromJson(responseContent, wxType);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    return WxMaAuditMediaUploadResult.fromJson(responseContent);
  }
}

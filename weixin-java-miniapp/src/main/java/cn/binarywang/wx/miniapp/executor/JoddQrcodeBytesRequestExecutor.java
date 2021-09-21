package cn.binarywang.wx.miniapp.executor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import cn.binarywang.wx.miniapp.bean.AbstractWxMaQrcodeWrapper;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import jodd.net.MimeTypes;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;

/**
 * @author vania
 * @since 2021/09/06
 */
public class JoddQrcodeBytesRequestExecutor extends QrcodeBytesRequestExecutor<HttpConnectionProvider, ProxyInfo> {


  public JoddQrcodeBytesRequestExecutor(RequestHttp<HttpConnectionProvider, ProxyInfo> requestHttp) {
    super(requestHttp);
  }

  /**
   * 执行http请求.
   *
   * @param uri           uri
   * @param qrcodeWrapper 数据
   * @param wxType        微信模块类型
   * @return 响应结果
   * @throws WxErrorException 自定义异常
   * @throws IOException      io异常
   */
  @Override
  public byte[] execute(String uri, AbstractWxMaQrcodeWrapper qrcodeWrapper, WxType wxType) throws WxErrorException, IOException {
      HttpRequest request = HttpRequest.get(uri);
      if (requestHttp.getRequestHttpProxy() != null) {
        requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
      }
      request.withConnectionProvider(requestHttp.getRequestHttpClient());

      HttpResponse response = request.send();
      response.charset(StandardCharsets.UTF_8.name());
      String contentTypeHeader = response.header("Content-Type");
      if (MimeTypes.MIME_APPLICATION_JSON.equals(contentTypeHeader)) {
        String responseContent = response.bodyText();
        throw new WxErrorException(WxError.fromJson(responseContent, wxType));
      }
      return response.bodyBytes();
  }
}

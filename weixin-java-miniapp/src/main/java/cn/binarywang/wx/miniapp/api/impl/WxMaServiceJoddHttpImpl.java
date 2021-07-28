package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.ProxyInfo;
import jodd.http.net.SocketHttpConnectionProvider;
import me.chanjar.weixin.common.util.http.HttpType;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * jodd-http方式实现.
 *
 * @author someone
 */
public class WxMaServiceJoddHttpImpl extends BaseWxMaServiceImpl<HttpConnectionProvider, ProxyInfo> {
  private HttpConnectionProvider httpClient;
  private ProxyInfo httpProxy;

  @Override
  public void initHttp() {
    WxMaConfig configStorage = this.getWxMaConfig();
    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      this.httpProxy = new ProxyInfo(ProxyInfo.ProxyType.HTTP, configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort(), configStorage.getHttpProxyUsername(), configStorage.getHttpProxyPassword());
    }
    this.httpClient = new SocketHttpConnectionProvider();
  }

  @Override
  public HttpConnectionProvider getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public ProxyInfo getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpType getRequestType() {
    return HttpType.JODD_HTTP;
  }

  @Override
  protected String doGetAccessTokenRequest() throws IOException {
    String url = StringUtils.isNotEmpty(this.getWxMaConfig().getApiHostUrl()) ?
      WxMaService.GET_ACCESS_TOKEN_URL.replace("https://api.weixin.qq.com", this.getWxMaConfig().getApiHostUrl()) :
      WxMaService.GET_ACCESS_TOKEN_URL;

    url = String.format(url, this.getWxMaConfig().getAppid(), this.getWxMaConfig().getSecret());
    HttpRequest request = HttpRequest.get(url);
    if (this.getRequestHttpProxy() != null) {
      SocketHttpConnectionProvider provider = new SocketHttpConnectionProvider();
      provider.useProxy(getRequestHttpProxy());

      request.withConnectionProvider(provider);
    }
    return request.send().bodyText();
  }

}

package me.chanjar.weixin.mp.api.impl;

import jodd.http.*;
import jodd.http.net.SocketHttpConnectionProvider;
import me.chanjar.weixin.common.WxType;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;

import java.util.concurrent.locks.Lock;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Other.GET_ACCESS_TOKEN_URL;

/**
 * jodd-http方式实现.
 *
 * @author someone
 */
public class WxMpServiceJoddHttpImpl extends BaseWxMpServiceImpl<HttpConnectionProvider, ProxyInfo> {
  private HttpConnectionProvider httpClient;
  private ProxyInfo httpProxy;

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
  public void initHttp() {

    WxMpConfigStorage configStorage = this.getWxMpConfigStorage();

    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      httpProxy = new ProxyInfo(ProxyInfo.ProxyType.HTTP, configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort(), configStorage.getHttpProxyUsername(), configStorage.getHttpProxyPassword());
    }

    httpClient = JoddHttp.httpConnectionProvider;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    final WxMpConfigStorage config = this.getWxMpConfigStorage();
    if (!config.isAccessTokenExpired() && !forceRefresh) {
      return config.getAccessToken();
    }

    Lock lock = config.getAccessTokenLock();
    lock.lock();
    try {
      if (!config.isAccessTokenExpired() && !forceRefresh) {
        return config.getAccessToken();
      }
      String url = String.format(GET_ACCESS_TOKEN_URL.getUrl(config), config.getAppId(), config.getSecret());

      HttpRequest request = HttpRequest.get(url);

      if (this.getRequestHttpProxy() != null) {
        SocketHttpConnectionProvider provider = new SocketHttpConnectionProvider();
        provider.useProxy(getRequestHttpProxy());

        request.withConnectionProvider(provider);
      }
      HttpResponse response = request.send();
      String resultContent = response.bodyText();
      WxError error = WxError.fromJson(resultContent, WxType.MP);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      }
      WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
      config.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());

      return config.getAccessToken();
    } finally {
      lock.unlock();
    }
  }

}

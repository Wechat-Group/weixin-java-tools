package me.chanjar.weixin.cp.api.impl;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.JoddHttp;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.WxType;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.cp.WxCpConsts;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;

public class WxCpServiceJoddHttpImpl extends BaseWxCpServiceImpl<HttpConnectionProvider, ProxyInfo> {
  protected HttpConnectionProvider httpClient;
  protected ProxyInfo httpProxy;


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
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    if (!this.configStorage.isAccessTokenExpired() && !forceRefresh) {
      return this.configStorage.getAccessToken();
    }

    synchronized (this.globalAccessTokenRefreshLock) {
      String url = this.configStorage.getApiUrl("/cgi-bin/gettoken?"
        + "&corpid=" + this.configStorage.getCorpId()
        + "&corpsecret=" + this.configStorage.getCorpSecret()
      );

      HttpRequest request = HttpRequest.get(url);
      if (this.httpProxy != null) {
        httpClient.useProxy(this.httpProxy);
      }
      request.withConnectionProvider(httpClient);
      HttpResponse response = request.send();

      String resultContent = response.bodyText();
      WxError error = WxError.fromJson(resultContent, WxType.CP);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      }
      WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
      this.configStorage.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
    }
    return this.configStorage.getAccessToken();
  }

  @Override
  public void initHttp() {
    if (this.configStorage.getHttpProxyHost() != null && this.configStorage.getHttpProxyPort() > 0) {
      httpProxy = new ProxyInfo(ProxyInfo.ProxyType.HTTP, configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort(), configStorage.getHttpProxyUsername(), configStorage.getHttpProxyPassword());
    }

    httpClient = JoddHttp.httpConnectionProvider;
  }

  @Override
  public WxCpConfigStorage getWxCpConfigStorage() {
    return this.configStorage;
  }
}

package me.chanjar.weixin.mp.api.impl;

import me.chanjar.weixin.common.WxType;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.locks.Lock;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Other.GET_ACCESS_TOKEN_URL;

/**
 * okhttp实现.
 *
 * @author someone
 */
public class WxMpServiceOkHttpImpl extends BaseWxMpServiceImpl<OkHttpClient, OkHttpProxyInfo> {
  private OkHttpClient httpClient;
  private OkHttpProxyInfo httpProxy;

  @Override
  public OkHttpClient getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public OkHttpProxyInfo getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpType getRequestType() {
    return HttpType.OK_HTTP;
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

      Request request = new Request.Builder().url(url).get().build();
      Response response = getRequestHttpClient().newCall(request).execute();
      String resultContent = response.body().string();
      WxError error = WxError.fromJson(resultContent, WxType.MP);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      }
      WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
      config.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());

      return config.getAccessToken();
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void initHttp() {
    WxMpConfigStorage wxMpConfigStorage = getWxMpConfigStorage();
    //设置代理
    if (wxMpConfigStorage.getHttpProxyHost() != null && wxMpConfigStorage.getHttpProxyPort() > 0) {
      httpProxy = OkHttpProxyInfo.httpProxy(wxMpConfigStorage.getHttpProxyHost(),
        wxMpConfigStorage.getHttpProxyPort(),
        wxMpConfigStorage.getHttpProxyUsername(),
        wxMpConfigStorage.getHttpProxyPassword());
    }

    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
    if (httpProxy != null) {
      clientBuilder.proxy(getRequestHttpProxy().getProxy());

      //设置授权
      clientBuilder.authenticator(new Authenticator() {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
          String credential = Credentials.basic(httpProxy.getProxyUsername(), httpProxy.getProxyPassword());
          return response.request().newBuilder()
            .header("Authorization", credential)
            .build();
        }
      });
    }
    httpClient = clientBuilder.build();
  }

}

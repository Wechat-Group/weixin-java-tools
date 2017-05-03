package me.chanjar.weixin.cp.api.impl.okhttp;

import java.io.IOException;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.okhttp.OkhttpProxyInfo;
import me.chanjar.weixin.cp.api.WxCpConfigStorage;
import me.chanjar.weixin.cp.api.impl.AbstractWxCpServiceImpl;
import okhttp3.*;

public class WxCpServiceImpl extends AbstractWxCpServiceImpl<ConnectionPool, OkhttpProxyInfo> {
  protected ConnectionPool httpClient;
  protected OkhttpProxyInfo httpProxy;


  @Override
  public ConnectionPool getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public OkhttpProxyInfo getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    if (forceRefresh) {
      this.configStorage.expireAccessToken();
    }
    if (this.configStorage.isAccessTokenExpired()) {
      synchronized (this.globalAccessTokenRefreshLock) {
        if (this.configStorage.isAccessTokenExpired()) {
          String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?"
            + "&corpid=" + this.configStorage.getCorpId()
            + "&corpsecret=" + this.configStorage.getCorpSecret();

          OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder().connectionPool(httpClient);
          //设置代理
          if (httpProxy != null) {
            clientBuilder.proxy(getRequestHttpProxy().getProxy());
          }
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
          //得到httpClient
          OkHttpClient client = clientBuilder.build();
          //请求的request
          Request request = new Request.Builder().url(url).get().build();
          Response response = null;
          try {
            response = client.newCall(request).execute();
          } catch (IOException e) {
            e.printStackTrace();
          }
          String resultContent = response.body().toString();
          WxError error = WxError.fromJson(resultContent);
          if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
          }
          WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
          this.configStorage.updateAccessToken(accessToken.getAccessToken(),
            accessToken.getExpiresIn());
        }
      }
    }
    return this.configStorage.getAccessToken();
  }

  @Override
  public void initHttp() {
    WxCpConfigStorage configStorage = this.configStorage;

    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      httpProxy = new OkhttpProxyInfo(OkhttpProxyInfo.ProxyType.SOCKS5, configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort(), configStorage.getHttpProxyUsername(), configStorage.getHttpProxyPassword());
    }

    httpClient = new ConnectionPool();
  }

  @Override
  public WxCpConfigStorage getWxCpConfigStorage() {
    return this.configStorage;
  }
}

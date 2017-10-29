package me.chanjar.weixin.open.api.impl;

import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.bean.resp.ComponentAccessTokenResp;
import me.chanjar.weixin.open.bean.req.ComponentAccessTokenReq;

import java.io.IOException;
import java.util.concurrent.locks.Lock;

/**
 * Created by JasonY on 17/10/24.
 */
public abstract class WxOpenServiceAbstractApacheHttpClientImpl extends WxOpenServiceAbstractImpl<CloseableHttpClient, HttpHost> {
  private CloseableHttpClient httpClient;
  private HttpHost httpProxy;

  @Override
  public CloseableHttpClient getRequestHttpClient() {
    return this.httpClient;
  }

  @Override
  public HttpHost getRequestHttpProxy() {
    return this.httpProxy;
  }

  @Override
  public HttpType getRequestType() {
    return HttpType.APACHE_HTTP;
  }

  @Override
  public String getComponentAccessToken(boolean forceRefresh) throws WxErrorException {
    Lock lock = this.getWxOpenConfigStorage().getComponentAccessTokenLock();

    try {
      lock.lock();

      if (this.getWxOpenConfigStorage().isComponentAccessTokenExpired() || forceRefresh) {
        HttpPost httpPost = new HttpPost(WxOpenService.COMPONENT_API_COMPONENT_TOKEN_URL);

        if (this.getRequestHttpProxy() != null) {
          RequestConfig config = RequestConfig.custom().setProxy(this.getRequestHttpProxy()).build();
          httpPost.setConfig(config);
        }

        ComponentAccessTokenReq request = ComponentAccessTokenReq.builder()
          .componentAppid(this.getWxOpenConfigStorage().getAppId())
          .componentAppsecret(this.getWxOpenConfigStorage().getSecret())
          .componentVerifyTicket(this.getWxOpenConfigStorage().getComponentVerifyTicket())
          .build();
        httpPost.setEntity(new StringEntity(request.toJson(), Consts.UTF_8));

        try (CloseableHttpResponse response = getRequestHttpClient().execute(httpPost)){
          String resultContent = new BasicResponseHandler().handleResponse(response);
          WxError error = WxError.fromJson(resultContent);
          if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
          }

          ComponentAccessTokenResp componentAccessTokenResp = ComponentAccessTokenResp.fromJson(resultContent);
          this.getWxOpenConfigStorage().updateComponentAccessToken(componentAccessTokenResp);
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          httpPost.releaseConnection();
        }
      }

    } finally {
      lock.unlock();
    }

    return this.getWxOpenConfigStorage().getComponentAccessToken();
  }

  @Override
  public void initHttp() {
    WxOpenConfigStorage configStorage = this.getWxOpenConfigStorage();

    ApacheHttpClientBuilder apacheHttpClientBuilder = configStorage.getApacheHttpClientBuilder();
    if (null == apacheHttpClientBuilder) {
      apacheHttpClientBuilder = DefaultApacheHttpClientBuilder.get();
    }

    apacheHttpClientBuilder.httpProxyHost(configStorage.getHttpProxyHost())
      .httpProxyPort(configStorage.getHttpProxyPort())
      .httpProxyUsername(configStorage.getHttpProxyUsername())
      .httpProxyPassword(configStorage.getHttpProxyPassword());

    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      this.httpProxy = new HttpHost(configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort());
    }

    this.httpClient = apacheHttpClientBuilder.build();
  }

}

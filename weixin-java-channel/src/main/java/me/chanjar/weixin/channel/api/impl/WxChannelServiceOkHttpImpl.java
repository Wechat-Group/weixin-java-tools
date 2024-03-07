package me.chanjar.weixin.channel.api.impl;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.bean.token.StableToken;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.common.util.http.okhttp.DefaultOkHttpClientBuilder;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.entity.StringEntity;

import java.io.IOException;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.GET_ACCESS_TOKEN_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.GET_STABLE_ACCESS_TOKEN_URL;

/**
 * @author : zhenyun.su
 * @since : 2024/2/27
 */
@Slf4j
public class WxChannelServiceOkHttpImpl extends BaseWxChannelServiceImpl<OkHttpClient, OkHttpProxyInfo> {
  private OkHttpClient httpClient;
  private OkHttpProxyInfo httpProxy;
  private Boolean stabled = false;
  private Boolean forceRefresh = false;
  protected final Object globalAccessTokenRefreshLock = new Object();

  /**
   * 设置调用接口参数.
   *
   * @param stabled      false 表示调用AccessToken接口， true调用稳定版接口
   * @param forceRefresh stabled=true使用， true表示强制刷新模式
   */
  public WxChannelServiceOkHttpImpl(Boolean stabled, Boolean forceRefresh) {
    this.stabled = stabled;
    this.forceRefresh = forceRefresh;
  }

  @Override
  public void initHttp() {
    log.debug("WxChannelServiceOkHttpImpl initHttp");
    if (this.config.getHttpProxyHost() != null && this.config.getHttpProxyPort() > 0) {
      this.httpProxy = OkHttpProxyInfo.httpProxy(this.config.getHttpProxyHost(), this.config.getHttpProxyPort(), this.config.getHttpProxyUsername(), this.config.getHttpProxyPassword());
      okhttp3.OkHttpClient.Builder clientBuilder = new okhttp3.OkHttpClient.Builder();
      clientBuilder.proxy(this.getRequestHttpProxy().getProxy());
      clientBuilder.authenticator(new Authenticator() {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
          String credential = Credentials.basic(WxChannelServiceOkHttpImpl.this.httpProxy.getProxyUsername(), WxChannelServiceOkHttpImpl.this.httpProxy.getProxyPassword());
          return response.request().newBuilder().header("Authorization", credential).build();
        }
      });
      this.httpClient = clientBuilder.build();
    } else {
      this.httpClient = DefaultOkHttpClientBuilder.get().build();
    }
  }

  @Override
  public OkHttpClient getRequestHttpClient() {
    return this.httpClient;
  }

  @Override
  public OkHttpProxyInfo getRequestHttpProxy() {
    return this.httpProxy;
  }

  @Override
  public HttpType getRequestType() {
    return HttpType.OK_HTTP;
  }

  @Override
  protected String doGetAccessTokenRequest() throws WxErrorException {
    if (stabled) {
      return internalGetStableAccessToken(this.forceRefresh);
    } else{
      return internalGetAccessToken(forceRefresh);
    }
  }

  public String internalGetStableAccessToken(boolean forceRefresh) throws WxErrorException {
    if (!this.config.isAccessTokenExpired() && !forceRefresh) {
      return this.config.getAccessToken();
    } else {
      synchronized(this.globalAccessTokenRefreshLock) {
        OkHttpClient client = this.getRequestHttpClient();

        String url = String.format(GET_STABLE_ACCESS_TOKEN_URL, config.getAppid(), config.getSecret());

        StableToken stableToken = new StableToken("client_credential", config.getAppid(),config.getSecret(), forceRefresh);

        RequestBody body = RequestBody.Companion.create(JsonUtils.encode(stableToken), MediaType.parse("application/json; charset=utf-8"));

        Request request = (new Request.Builder()).url(url).post(body).build();
        String resultContent = null;
        try {
          Response response = client.newCall(request).execute();
          resultContent = response.body().string();
        } catch (IOException var9) {
          log.error(var9.getMessage(), var9);
        }

        WxError error = WxError.fromJson(resultContent, WxType.CP);
        if (error.getErrorCode() != 0) {
          throw new WxErrorException(error);
        }

        WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
        this.config.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
      }
      return this.config.getAccessToken();
    }
  }

  public String internalGetAccessToken(boolean forceRefresh) throws WxErrorException {
    if (!this.config.isAccessTokenExpired() && !forceRefresh) {
      return this.config.getAccessToken();
    } else {
      synchronized(this.globalAccessTokenRefreshLock) {
        OkHttpClient client = this.getRequestHttpClient();

        String url = String.format(GET_ACCESS_TOKEN_URL, config.getAppid(), config.getSecret());

        Request request = (new Request.Builder()).url(url).get().build();

        String resultContent = null;
        try {
          Response response = client.newCall(request).execute();
          resultContent = response.body().string();
        } catch (IOException var9) {
          log.error(var9.getMessage(), var9);
        }

        WxError error = WxError.fromJson(resultContent, WxType.CP);
        if (error.getErrorCode() != 0) {
          throw new WxErrorException(error);
        }

        WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
        this.config.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
      }
      return this.config.getAccessToken();
    }
  }
}

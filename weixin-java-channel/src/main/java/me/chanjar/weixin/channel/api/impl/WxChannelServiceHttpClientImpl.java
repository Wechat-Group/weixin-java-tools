package me.chanjar.weixin.channel.api.impl;

import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.GET_ACCESS_TOKEN_URL;
import static me.chanjar.weixin.channel.constant.WxChannelApiUrlConstants.GET_STABLE_ACCESS_TOKEN_URL;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.channel.api.WxChannelVipService;
import me.chanjar.weixin.channel.bean.token.StableToken;
import me.chanjar.weixin.channel.config.WxChannelConfig;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Slf4j
public class WxChannelServiceHttpClientImpl extends BaseWxChannelServiceImpl<HttpClient, HttpHost> {

  private CloseableHttpClient httpClient;
  private HttpHost httpProxy;
  private Boolean stabled = false;
  private Boolean forceRefresh = false;

  /**
   * 设置调用接口参数.
   *
   * @param stabled      false 表示调用AccessToken接口， true调用稳定版接口
   * @param forceRefresh stabled=true使用， true表示强制刷新模式
   */
  public WxChannelServiceHttpClientImpl(Boolean stabled, Boolean forceRefresh) {
    this.stabled = stabled;
    this.forceRefresh = forceRefresh;
  }

  @Override
  public void initHttp() {
    WxChannelConfig config = this.getConfig();
    ApacheHttpClientBuilder apacheHttpClientBuilder = config.getApacheHttpClientBuilder();
    if (null == apacheHttpClientBuilder) {
      apacheHttpClientBuilder = DefaultApacheHttpClientBuilder.get();
    }

    apacheHttpClientBuilder.httpProxyHost(config.getHttpProxyHost())
      .httpProxyPort(config.getHttpProxyPort())
      .httpProxyUsername(config.getHttpProxyUsername())
      .httpProxyPassword(config.getHttpProxyPassword());

    if (config.getHttpProxyHost() != null && config.getHttpProxyPort() > 0) {
      this.httpProxy = new HttpHost(config.getHttpProxyHost(), config.getHttpProxyPort());
    }

    this.httpClient = apacheHttpClientBuilder.build();
  }

  @Override
  public CloseableHttpClient getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public HttpHost getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpType getRequestType() {
    return HttpType.APACHE_HTTP;
  }

  @Override
  protected String doGetAccessTokenRequest() throws WxErrorException {
    try {
      if (stabled) {
        return internalGetStableAccessToken(this.forceRefresh);
      } else{
        return  internalGetAccessToken();
      }
    } catch(Exception e) {
      throw new WxErrorException(e);
    }

  }

  /**
   * 获取access_token
   *
   * @return 返回json的字符串
   * @throws IOException the io exception
   */
  protected String internalGetAccessToken() throws IOException {
    WxChannelConfig config = this.getConfig();
    String url = StringUtils.isNotEmpty(config.getAccessTokenUrl()) ? config.getAccessTokenUrl() :
      StringUtils.isNotEmpty(config.getApiHostUrl()) ?
        GET_ACCESS_TOKEN_URL.replace("https://api.weixin.qq.com", config.getApiHostUrl()) : GET_ACCESS_TOKEN_URL;

    url = String.format(url, config.getAppid(), config.getSecret());

    HttpGet httpGet = null;
    CloseableHttpResponse response = null;
    try {
      httpGet = new HttpGet(url);
      if (this.getRequestHttpProxy() != null) {
        RequestConfig requestConfig = RequestConfig.custom().setProxy(this.getRequestHttpProxy()).build();
        httpGet.setConfig(requestConfig);
      }
      response = getRequestHttpClient().execute(httpGet);
      return new BasicResponseHandler().handleResponse(response);
    } finally {
      if (httpGet != null) {
        httpGet.releaseConnection();
      }
      if (response != null) {
        try {
          response.close();
        } catch (IOException ignored) {
        }
      }
    }
  }


  /**
   * 获取稳定版接口调用凭据
   *
   * @param forceRefresh false 为普通模式， true为强制刷新模式
   * @return 返回json的字符串
   * @throws IOException the io exception
   */
  protected String internalGetStableAccessToken(Boolean forceRefresh) throws IOException {
    WxChannelConfig config = this.getConfig();
    String url = GET_STABLE_ACCESS_TOKEN_URL;

    HttpPost httpPost = new HttpPost(url);
    if (this.getRequestHttpProxy() != null) {
      RequestConfig requestConfig = RequestConfig.custom().setProxy(this.getRequestHttpProxy()).build();
      httpPost.setConfig(requestConfig);
    }

    StableToken stableToken = new StableToken("client_credential", config.getAppid(),config.getSecret(), forceRefresh);

    StringEntity entity = new StringEntity(JsonUtils.encode(stableToken), Consts.UTF_8);
    entity.setContentType("application/json; charset=utf-8");
    httpPost.setEntity(entity);

    try (CloseableHttpResponse response = getRequestHttpClient().execute(httpPost)) {
      return new BasicResponseHandler().handleResponse(response);
    } finally {
      httpPost.releaseConnection();
    }
  }


}

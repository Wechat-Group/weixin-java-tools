package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaCodeService;
import cn.binarywang.wx.miniapp.api.WxMaMediaService;
import cn.binarywang.wx.miniapp.api.WxMaMsgService;
import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaTemplateService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import com.google.common.base.Joiner;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.SimpleGetRequestExecutor;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaServiceImpl implements WxMaService, RequestHttp<CloseableHttpClient, HttpHost> {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private CloseableHttpClient httpClient;
  private HttpHost httpProxy;
  private WxMaConfig wxMaConfig;

  private WxMaMsgService kefuService = new WxMaMsgServiceImpl(this);
  private WxMaMediaService materialService = new WxMaMediaServiceImpl(this);
  private WxMaUserService userService = new WxMaUserServiceImpl(this);
  private WxMaQrcodeService qrCodeService = new WxMaQrcodeServiceImpl(this);
  private WxMaTemplateService templateService = new WxMaTemplateServiceImpl(this);
  private WxMaCodeService codeService = new WxMaCodeServiceImpl(this);

  private int retrySleepMillis = 1000;
  private int maxRetryTimes = 5;

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
  public void initHttp() {
    WxMaConfig configStorage = this.getWxMaConfig();
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

  @Override
  public RequestHttp getRequestHttp() {
    return this;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    Lock lock = this.getWxMaConfig().getAccessTokenLock();
    try {
      lock.lock();

      if (this.getWxMaConfig().isAccessTokenExpired() || forceRefresh) {
        String url = String.format(WxMaService.GET_ACCESS_TOKEN_URL, this.getWxMaConfig().getAppid(),
          this.getWxMaConfig().getSecret());
        try {
          HttpGet httpGet = new HttpGet(url);
          if (this.getRequestHttpProxy() != null) {
            RequestConfig config = RequestConfig.custom().setProxy(this.getRequestHttpProxy()).build();
            httpGet.setConfig(config);
          }
          try (CloseableHttpResponse response = getRequestHttpClient().execute(httpGet)) {
            String resultContent = new BasicResponseHandler().handleResponse(response);
            WxError error = WxError.fromJson(resultContent);
            if (error.getErrorCode() != 0) {
              throw new WxErrorException(error);
            }
            WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
            this.getWxMaConfig().updateAccessToken(accessToken.getAccessToken(),
              accessToken.getExpiresIn());
          } finally {
            httpGet.releaseConnection();
          }
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    } finally {
      lock.unlock();
    }

    return this.getWxMaConfig().getAccessToken();
  }

  @Override
  public WxMaJscode2SessionResult jsCode2SessionInfo(String jsCode) throws WxErrorException {
    final WxMaConfig config = getWxMaConfig();
    Map<String, String> params = new HashMap<>(8);
    params.put("appid", config.getAppid());
    params.put("secret", config.getSecret());
    params.put("js_code", jsCode);
    params.put("grant_type", "authorization_code");

    String result = get(JSCODE_TO_SESSION_URL, Joiner.on("&").withKeyValueSeparator("=").join(params));
    return WxMaJscode2SessionResult.fromJson(result);
  }

  @Override
  public boolean checkSignature(String timestamp, String nonce, String signature) {
    try {
      return SHA1.gen(this.getWxMaConfig().getToken(), timestamp, nonce).equals(signature);
    } catch (Exception e) {
      this.log.error("Checking signature failed, and the reason is :" + e.getMessage());
      return false;
    }
  }

  @Override
  public String getAccessToken() throws WxErrorException {
    return getAccessToken(false);
  }

  @Override
  public String get(String url, String queryParam) throws WxErrorException {
    return execute(SimpleGetRequestExecutor.create(this), url, queryParam);
  }

  @Override
  public String post(String url, String postData) throws WxErrorException {
    return execute(SimplePostRequestExecutor.create(this), url, postData);
  }

  /**
   * 向微信端发送请求，在这里执行的策略是当发生access_token过期时才去刷新，然后重新执行请求，而不是全局定时请求
   */
  @Override
  public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    int retryTimes = 0;
    do {
      try {
        return this.executeInternal(executor, uri, data);
      } catch (WxErrorException e) {
        if (retryTimes + 1 > this.maxRetryTimes) {
          this.log.warn("重试达到最大次数【{}】", maxRetryTimes);
          //最后一次重试失败后，直接抛出异常，不再等待
          throw new RuntimeException("微信服务端异常，超出重试次数");
        }

        WxError error = e.getError();
        // -1 系统繁忙, 1000ms后重试
        if (error.getErrorCode() == -1) {
          int sleepMillis = this.retrySleepMillis * (1 << retryTimes);
          try {
            this.log.warn("微信系统繁忙，{} ms 后重试(第{}次)", sleepMillis, retryTimes + 1);
            Thread.sleep(sleepMillis);
          } catch (InterruptedException e1) {
            throw new RuntimeException(e1);
          }
        } else {
          throw e;
        }
      }
    } while (retryTimes++ < this.maxRetryTimes);

    this.log.warn("重试达到最大次数【{}】", this.maxRetryTimes);
    throw new RuntimeException("微信服务端异常，超出重试次数");
  }

  public <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    if (uri.contains("access_token=")) {
      throw new IllegalArgumentException("uri参数中不允许有access_token: " + uri);
    }
    String accessToken = getAccessToken(false);

    String uriWithAccessToken = uri + (uri.contains("?") ? "&" : "?") + "access_token=" + accessToken;

    try {
      T result = executor.execute(uriWithAccessToken, data);
      this.log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uriWithAccessToken, data, result);
      return result;
    } catch (WxErrorException e) {
      WxError error = e.getError();
      /*
       * 发生以下情况时尝试刷新access_token
       */
      if (error.getErrorCode() == WxMaConstants.ErrorCode.ERR_40001
        || error.getErrorCode() == WxMaConstants.ErrorCode.ERR_42001
        || error.getErrorCode() == WxMaConstants.ErrorCode.ERR_40014) {
        // 强制设置wxMpConfigStorage它的access token过期了，这样在下一次请求里就会刷新access token
        this.getWxMaConfig().expireAccessToken();
        if (this.getWxMaConfig().autoRefreshToken()) {
          return this.execute(executor, uri, data);
        }
      }

      if (error.getErrorCode() != 0) {
        this.log.error("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uriWithAccessToken, data, error);
        throw new WxErrorException(error, e);
      }
      return null;
    } catch (IOException e) {
      this.log.error("\n【请求地址】: {}\n【请求参数】：{}\n【异常信息】：{}", uriWithAccessToken, data, e.getMessage());
      throw new RuntimeException(e);
    }
  }

  @Override
  public WxMaConfig getWxMaConfig() {
    return this.wxMaConfig;
  }

  @Override
  public void setWxMaConfig(WxMaConfig wxConfigProvider) {
    this.wxMaConfig = wxConfigProvider;
    this.initHttp();
  }

  @Override
  public void setRetrySleepMillis(int retrySleepMillis) {
    this.retrySleepMillis = retrySleepMillis;
  }

  @Override
  public void setMaxRetryTimes(int maxRetryTimes) {
    this.maxRetryTimes = maxRetryTimes;
  }

  @Override
  public WxMaMsgService getMsgService() {
    return this.kefuService;
  }

  @Override
  public WxMaMediaService getMediaService() {
    return this.materialService;
  }

  @Override
  public WxMaUserService getUserService() {
    return this.userService;
  }

  @Override
  public WxMaQrcodeService getQrcodeService() {
    return this.qrCodeService;
  }

  @Override
  public WxMaTemplateService getTemplateService() {
    return this.templateService;
  }

  @Override
  public WxMaCodeService getCodeService() {
    return this.codeService;
  }
}

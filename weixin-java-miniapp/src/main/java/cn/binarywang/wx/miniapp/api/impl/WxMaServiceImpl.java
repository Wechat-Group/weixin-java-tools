package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.*;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.WxType;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.DataUtils;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.http.*;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import static cn.binarywang.wx.miniapp.constant.WxMaConstants.ErrorCode.*;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Slf4j
public class WxMaServiceImpl implements WxMaService, RequestHttp<CloseableHttpClient, HttpHost> {
  private static final JsonParser JSON_PARSER = new JsonParser();
  private CloseableHttpClient httpClient;
  private HttpHost httpProxy;
  private WxMaConfig wxMaConfig;

  private WxMaMsgService kefuService = new WxMaMsgServiceImpl(this);
  private WxMaMediaService materialService = new WxMaMediaServiceImpl(this);
  private WxMaUserService userService = new WxMaUserServiceImpl(this);
  private WxMaQrcodeService qrCodeService = new WxMaQrcodeServiceImpl(this);
  private WxMaTemplateService templateService = new WxMaTemplateServiceImpl(this);
  private WxMaAnalysisService analysisService = new WxMaAnalysisServiceImpl(this);
  private WxMaCodeService codeService = new WxMaCodeServiceImpl(this);
  private WxMaSettingService settingService = new WxMaSettingServiceImpl(this);
  private WxMaJsapiService jsapiService = new WxMaJsapiServiceImpl(this);
  private WxMaShareService shareService = new WxMaShareServiceImpl(this);
  private WxMaRunService runService = new WxMaRunServiceImpl(this);
  private WxMaSecCheckService secCheckService = new WxMaSecCheckServiceImpl(this);
  private WxMaPluginService pluginService = new WxMaPluginServiceImpl(this);

  private int retrySleepMillis = 1000;
  private int maxRetryTimes = 5;

  protected static final Gson GSON = new Gson();

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
    if (!this.getWxMaConfig().isAccessTokenExpired() && !forceRefresh) {
      return this.getWxMaConfig().getAccessToken();
    }

    Lock lock = this.getWxMaConfig().getAccessTokenLock();
    lock.lock();
    try {
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
          WxError error = WxError.fromJson(resultContent, WxType.MiniApp);
          if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
          }
          WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
          this.getWxMaConfig().updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());

          return this.getWxMaConfig().getAccessToken();
        } finally {
          httpGet.releaseConnection();
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } finally {
      lock.unlock();
    }

  }

  @Override
  public String getPaidUnionId(String openid, String transactionId, String mchId, String outTradeNo)
    throws WxErrorException {
    Map<String, String> params = new HashMap<>(8);
    params.put("openid", openid);

    if (StringUtils.isNotEmpty(transactionId)) {
      params.put("transaction_id", transactionId);
    }

    if (StringUtils.isNotEmpty(mchId)) {
      params.put("mch_id", mchId);
    }

    if (StringUtils.isNotEmpty(outTradeNo)) {
      params.put("out_trade_no", outTradeNo);
    }

    String responseContent = this.get(GET_PAID_UNION_ID_URL, Joiner.on("&").withKeyValueSeparator("=").join(params));
    WxError error = WxError.fromJson(responseContent, WxType.MiniApp);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }

    return JSON_PARSER.parse(responseContent).getAsJsonObject().get("unionid").getAsString();
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
      log.error("Checking signature failed, and the reason is :" + e.getMessage());
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
          log.warn("重试达到最大次数【{}】", maxRetryTimes);
          //最后一次重试失败后，直接抛出异常，不再等待
          throw new RuntimeException("微信服务端异常，超出重试次数");
        }

        WxError error = e.getError();
        // -1 系统繁忙, 1000ms后重试
        if (error.getErrorCode() == -1) {
          int sleepMillis = this.retrySleepMillis * (1 << retryTimes);
          try {
            log.warn("微信系统繁忙，{} ms 后重试(第{}次)", sleepMillis, retryTimes + 1);
            Thread.sleep(sleepMillis);
          } catch (InterruptedException e1) {
            Thread.currentThread().interrupt();
          }
        } else {
          throw e;
        }
      }
    } while (retryTimes++ < this.maxRetryTimes);

    log.warn("重试达到最大次数【{}】", this.maxRetryTimes);
    throw new RuntimeException("微信服务端异常，超出重试次数");
  }

  private <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    E dataForLog = DataUtils.handleDataWithSecret(data);

    if (uri.contains("access_token=")) {
      throw new IllegalArgumentException("uri参数中不允许有access_token: " + uri);
    }
    String accessToken = getAccessToken(false);

    String uriWithAccessToken = uri + (uri.contains("?") ? "&" : "?") + "access_token=" + accessToken;

    try {
      T result = executor.execute(uriWithAccessToken, data, WxType.MiniApp);
      log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uriWithAccessToken, dataForLog, result);
      return result;
    } catch (WxErrorException e) {
      WxError error = e.getError();
      /*
       * 发生以下情况时尝试刷新access_token
       */
      if (error.getErrorCode() == ERR_40001
        || error.getErrorCode() == ERR_42001
        || error.getErrorCode() == ERR_40014) {
        // 强制设置WxMaConfig的access token过期了，这样在下一次请求里就会刷新access token
        this.getWxMaConfig().expireAccessToken();
        if (this.getWxMaConfig().autoRefreshToken()) {
          return this.execute(executor, uri, data);
        }
      }

      if (error.getErrorCode() != 0) {
        log.error("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uriWithAccessToken, dataForLog, error);
        throw new WxErrorException(error, e);
      }
      return null;
    } catch (IOException e) {
      log.error("\n【请求地址】: {}\n【请求参数】：{}\n【异常信息】：{}", uriWithAccessToken, dataForLog, e.getMessage());
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
  public WxMaAnalysisService getAnalysisService() {
    return this.analysisService;
  }

  @Override
  public WxMaCodeService getCodeService() {
    return this.codeService;
  }

  @Override
  public WxMaJsapiService getJsapiService() {
    return this.jsapiService;
  }

  @Override
  public WxMaSettingService getSettingService() {
    return this.settingService;
  }

  @Override
  public WxMaShareService getShareService() {
    return this.shareService;
  }

  @Override
  public WxMaRunService getRunService() {
    return this.runService;
  }

  @Override
  public WxMaSecCheckService getSecCheckService() {
    return this.secCheckService;
  }

  @Override
  public WxMaPluginService getPluginService() {
    return this.pluginService;
  }
}

package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.*;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.WxType;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.DataUtils;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.SimpleGetRequestExecutor;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import static cn.binarywang.wx.miniapp.constant.WxMaConstants.ErrorCode.*;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @see #doGetAccessTokenRequest
 */
@Slf4j
public abstract class BaseWxMaServiceImpl<H, P> implements WxMaService, RequestHttp<H, P> {

  private WxMaConfig wxMaConfig;

  private final WxMaMsgService kefuService = new WxMaMsgServiceImpl(this);
  private final WxMaMediaService materialService = new WxMaMediaServiceImpl(this);
  private final WxMaUserService userService = new WxMaUserServiceImpl(this);
  private final WxMaQrcodeService qrCodeService = new WxMaQrcodeServiceImpl(this);
  private final WxMaTemplateService templateService = new WxMaTemplateServiceImpl(this);
  private final WxMaAnalysisService analysisService = new WxMaAnalysisServiceImpl(this);
  private final WxMaCodeService codeService = new WxMaCodeServiceImpl(this);
  private final WxMaSettingService settingService = new WxMaSettingServiceImpl(this);
  private final WxMaJsapiService jsapiService = new WxMaJsapiServiceImpl(this);
  private final WxMaShareService shareService = new WxMaShareServiceImpl(this);
  private final WxMaRunService runService = new WxMaRunServiceImpl(this);
  private final WxMaSecCheckService secCheckService = new WxMaSecCheckServiceImpl(this);
  private final WxMaPluginService pluginService = new WxMaPluginServiceImpl(this);
  private final WxMaExpressService expressService = new WxMaExpressServiceImpl(this);
  private final WxMaSubscribeService subscribeService = new WxMaSubscribeServiceImpl(this);
  private final WxMaCloudService cloudService = new WxMaCloudServiceImpl(this);
  private final WxMaLiveService liveService = new WxMaLiveServiceImpl(this);

  private int retrySleepMillis = 1000;
  private int maxRetryTimes = 5;

  protected static final Gson GSON = new Gson();

  @Override
  public RequestHttp getRequestHttp() {
    return this;
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

    return GsonParser.parse(responseContent).get("unionid").getAsString();
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
  public void setDynamicData(int lifespan, String type, int scene, String data) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("lifespan", lifespan);
    jsonObject.addProperty("query", WxGsonBuilder.create().toJson(ImmutableMap.of("type", type)));
    jsonObject.addProperty("data", data);
    jsonObject.addProperty("scene", scene);

    this.post(SET_DYNAMIC_DATA_URL, jsonObject.toString());
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
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    if (!forceRefresh && !this.getWxMaConfig().isAccessTokenExpired()) {
      return this.getWxMaConfig().getAccessToken();
    }

    Lock lock = this.getWxMaConfig().getAccessTokenLock();
    boolean locked = false;
    try {
      do {
        locked = lock.tryLock(100, TimeUnit.MILLISECONDS);
        if (!forceRefresh && !this.getWxMaConfig().isAccessTokenExpired()) {
          return this.getWxMaConfig().getAccessToken();
        }
      } while (!locked);
      String response = doGetAccessTokenRequest();
      return extractAccessToken(response);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    } finally {
      if (locked) {
        lock.unlock();
      }
    }
  }

  /**
   * 通过网络请求获取AccessToken
   *
   * @return
   * @throws IOException
   */
  protected abstract String doGetAccessTokenRequest() throws IOException;

  @Override
  public String get(String url, String queryParam) throws WxErrorException {
    return execute(SimpleGetRequestExecutor.create(this), url, queryParam);
  }

  @Override
  public String post(String url, String postData) throws WxErrorException {
    return execute(SimplePostRequestExecutor.create(this), url, postData);
  }

  @Override
  public String post(String url, Object obj) throws WxErrorException {
    return this.execute(SimplePostRequestExecutor.create(this), url, WxGsonBuilder.create().toJson(obj));
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
          throw new WxErrorException(WxError.builder()
            .errorCode(e.getError().getErrorCode())
            .errorMsg("微信服务端异常，超出重试次数！")
            .build());
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
        Lock lock = this.getWxMaConfig().getAccessTokenLock();
        lock.lock();
        try {
          if (StringUtils.equals(this.getWxMaConfig().getAccessToken(), accessToken)) {
            this.getWxMaConfig().expireAccessToken();
          }
        } catch (Exception ex) {
          this.getWxMaConfig().expireAccessToken();
        } finally {
          lock.unlock();
        }
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

  /**
   * 设置当前的AccessToken
   *
   * @param resultContent
   * @return
   * @throws WxErrorException
   */
  protected String extractAccessToken(String resultContent) throws WxErrorException {
    WxMaConfig config = this.getWxMaConfig();
    WxError error = WxError.fromJson(resultContent, WxType.MiniApp);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
    config.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
    return accessToken.getAccessToken();
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
  public WxMaSubscribeService getSubscribeService() {
    return this.subscribeService;
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

  @Override
  public WxMaExpressService getExpressService() {
    return this.expressService;
  }

  @Override
  public WxMaCloudService getCloudService() {
    return this.cloudService;
  }

  @Override
  public WxMaLiveService getLiveService() {
    return this.liveService;
  }
}

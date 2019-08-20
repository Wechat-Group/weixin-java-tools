package me.chanjar.weixin.cp.api.impl;

import com.google.common.base.Joiner;
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
import me.chanjar.weixin.cp.api.WxCpTpService;
import me.chanjar.weixin.cp.bean.WxCpMaJsCode2SessionResult;
import me.chanjar.weixin.cp.bean.WxCpTpCorp;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.constant.WxCpApiPathConsts;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhenjun cai
 */
@Slf4j
public abstract class BaseWxCpTpServiceImpl<H, P> implements WxCpTpService, RequestHttp<H, P> {

  /**
   * 全局的是否正在刷新access token的锁.
   */
  protected final Object globalSuiteAccessTokenRefreshLock = new Object();

  /**
   * 全局的是否正在刷新jsapi_ticket的锁.
   */
  protected final Object globalSuiteTicketRefreshLock = new Object();

  protected WxCpTpConfigStorage configStorage;

  /**
   * 临时文件目录.
   */
  private File tmpDirFile;
  private int retrySleepMillis = 1000;
  private int maxRetryTimes = 5;

  @Override
  public boolean checkSignature(String msgSignature, String timestamp, String nonce, String data) {
    try {
      return SHA1.gen(this.configStorage.getToken(), timestamp, nonce, data)
        .equals(msgSignature);
    } catch (Exception e) {
      log.error("Checking signature failed, and the reason is :" + e.getMessage());
      return false;
    }
  }

  @Override
  public String getSuiteAccessToken() throws WxErrorException {
    return getSuiteAccessToken(false);
  }

  @Override
  public String getSuiteTicket() throws WxErrorException {
    return getSuiteTicket(false);
  }

  @Override
  public String getSuiteTicket(boolean forceRefresh) throws WxErrorException {
//	  suite ticket由微信服务器推送，不能强制刷新
//    if (forceRefresh) {
//      this.configStorage.expireSuiteTicket();
//    }

    if (this.configStorage.isSuiteTicketExpired()) {
      // 本地suite ticket 不存在或者过期
      WxError wxError = WxError.fromJson("{\"errcode\":40085, \"errmsg\":\"invaild suite ticket\"}", WxType.CP);
      throw new WxErrorException(wxError);
    }
    return this.configStorage.getSuiteTicket();
  }


  @Override
  public WxCpMaJsCode2SessionResult jsCode2Session(String jsCode) throws WxErrorException {
    Map<String, String> params = new HashMap<>(2);
    params.put("js_code", jsCode);
    params.put("grant_type", "authorization_code");

    String result = this.get(configStorage.getApiUrl(WxCpApiPathConsts.Tp.JSCODE_TO_SESSION), Joiner.on("&").withKeyValueSeparator("=").join(params));
    return WxCpMaJsCode2SessionResult.fromJson(result);
  }


  @Override
  public WxAccessToken getCorpToken(String authCorpid, String permanentCode) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("auth_corpid", authCorpid);
    jsonObject.addProperty("permanent_code", permanentCode);
    String result = post(configStorage.getApiUrl(WxCpApiPathConsts.Tp.GET_CORP_TOKEN), jsonObject.toString());

    return WxAccessToken.fromJson(result);
  }


  @Override
  public WxCpTpCorp getPermanentCode(String authCode) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("auth_code", authCode);

    String result = post(configStorage.getApiUrl(WxCpApiPathConsts.Tp.GET_PERMANENT_CODE), jsonObject.toString());
    jsonObject = new JsonParser().parse(result).getAsJsonObject();
    WxCpTpCorp wxCpTpCorp = WxCpTpCorp.fromJson(jsonObject.get("auth_corp_info").getAsString());
    wxCpTpCorp.setPermanentCode(jsonObject.get("permanent_code").getAsString());
    return wxCpTpCorp;
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
   * 向微信端发送请求，在这里执行的策略是当发生access_token过期时才去刷新，然后重新执行请求，而不是全局定时请求.
   */
  @Override
  public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    int retryTimes = 0;
    do {
      try {
        return this.executeInternal(executor, uri, data);
      } catch (WxErrorException e) {
        if (retryTimes + 1 > this.maxRetryTimes) {
          log.warn("重试达到最大次数【{}】", this.maxRetryTimes);
          //最后一次重试失败后，直接抛出异常，不再等待
          throw new RuntimeException("微信服务端异常，超出重试次数");
        }

        WxError error = e.getError();
        /*
         * -1 系统繁忙, 1000ms后重试
         */
        if (error.getErrorCode() == -1) {
          int sleepMillis = this.retrySleepMillis * (1 << retryTimes);
          try {
            log.debug("微信系统繁忙，{} ms 后重试(第{}次)", sleepMillis, retryTimes + 1);
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

  protected <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    E dataForLog = DataUtils.handleDataWithSecret(data);

    if (uri.contains("suite_access_token=")) {
      throw new IllegalArgumentException("uri参数中不允许有suite_access_token: " + uri);
    }
    String suiteAccessToken = getSuiteAccessToken(false);

    String uriWithAccessToken = uri + (uri.contains("?") ? "&" : "?") + "suite_access_token=" + suiteAccessToken;

    try {
      T result = executor.execute(uriWithAccessToken, data);
      log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uriWithAccessToken, dataForLog, result);
      return result;
    } catch (WxErrorException e) {
      WxError error = e.getError();
      /*
       * 发生以下情况时尝试刷新suite_access_token
       * 42009 suite_access_token已过期
       */
      if (error.getErrorCode() == 42009) {
        // 强制设置wxCpTpConfigStorage它的suite access token过期了，这样在下一次请求里就会刷新suite access token
        this.configStorage.expireSuiteAccessToken();
        return execute(executor, uri, data);
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
  public void setWxCpTpConfigStorage(WxCpTpConfigStorage wxConfigProvider) {
    this.configStorage = wxConfigProvider;
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

  public File getTmpDirFile() {
    return this.tmpDirFile;
  }

  public void setTmpDirFile(File tmpDirFile) {
    this.tmpDirFile = tmpDirFile;
  }

  @Override
  public RequestHttp<?, ?> getRequestHttp() {
    return this;
  }

}

package us.wili.jason.weixin.open.api.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.RandomUtils;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.http.*;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpCurrentAutoReplyInfo;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.wili.jason.weixin.open.api.*;
import us.wili.jason.weixin.open.bean.req.AuthorizerTokenReq;
import us.wili.jason.weixin.open.bean.resp.AuthorizerInfoResp;
import us.wili.jason.weixin.open.bean.resp.AuthorizerTokenResp;

import java.io.IOException;
import java.util.concurrent.locks.Lock;

/**
 * Created by JasonY on 17/10/24.
 */
public abstract class WxOpenServiceAbstractImpl<H, P> implements WxOpenService, RequestHttp<H, P>{

  private static final JsonParser JSON_PARSER = new JsonParser();

  protected final Logger log = LoggerFactory.getLogger(this.getClass());
  private WxOpenConfigStorage wxOpenConfigStorage;

  private WxOpenKefuService kefuService = new WxOpenKefuServiceImpl(this);
  private WxOpenMaterialService materialService = new WxOpenMaterialServiceImpl(this);
  private WxOpenMenuService menuService = new WxOpenMenuServiceImpl(this);
  private WxOpenUserService userService = new WxOpenUserServiceImpl(this);
  private WxOpenUserTagService tagService = new WxOpenUserTagServiceImpl(this);
  private WxOpenQrcodeService qrCodeService = new WxOpenQrcodeServiceImpl(this);
  private WxOpenCardService cardService = new WxOpenCardServiceImpl(this);
  private WxOpenStoreService storeService = new WxOpenStoreServiceImpl(this);
  private WxOpenDataCubeService dataCubeService = new WxOpenDataCubeServiceImpl(this);
  private WxOpenUserBlacklistService blackListService = new WxOpenUserBlacklistServiceImpl(this);
  private WxOpenTemplateMsgService templateMsgService = new WxOpenTemplateMsgServiceImpl(this);
  private WxOpenDeviceService deviceService = new WxOpenDeviceServiceImpl(this);
  private WxOpenShakeService shakeService = new WxOpenShakeServiceImpl(this);
  private WxOpenMemberCardService memberCardService = new WxOpenMemberCardServiceImpl(this);
  private WxOpenMassMessageService massMessageService = new WxOpenMassMessageServiceImpl(this);

  private int retrySleepMillis = 1000;
  private int maxRetryTimes = 5;

  @Override
  public void setComponentVerifyTicket(String componentVerifyTicket) {
    this.getWxOpenConfigStorage().setComponentVerifyTicket(componentVerifyTicket);
  }

  @Override
  public String getComponentVerifyTicket() {
    return this.getWxOpenConfigStorage().getComponentVerifyTicket();
  }

  @Override
  public String getComponentAccessToken() throws WxErrorException {
    return getComponentAccessToken(false);
  }

  @Override
  public String getPreAuthCode() throws WxErrorException {
    return getPreAuthCode(false);
  }

  @Override
  public String getPreAuthCode(boolean forceRefresh) throws WxErrorException {
    Lock lock = this.getWxOpenConfigStorage().getPreAuthCodeLock();

    try {
      lock.lock();

      if (forceRefresh) {
        this.getWxOpenConfigStorage().expirePreAuthCode();
      }

      if (this.getWxOpenConfigStorage().isPreAuthCodeExpired()) {
        JsonObject o = new JsonObject();
        o.addProperty("component_appid", this.getWxOpenConfigStorage().getAppId());
        String responseContent = this.post(WxOpenService.COMPONENT_API_CREATE_PREAUTHCODE_URL, o.toString());
        JsonElement tmpJsonElement = JSON_PARSER.parse(responseContent);
        String preAuthCode = tmpJsonElement.getAsJsonObject().get("pre_auth_code").getAsString();
        int expiresIn = tmpJsonElement.getAsJsonObject().get("expires_in").getAsInt();

        this.getWxOpenConfigStorage().updatePreAuthCode(preAuthCode, expiresIn);
      }
    } finally {
      lock.unlock();
    }

    return this.getWxOpenConfigStorage().getPreAuthCode();
  }

  @Override
  public String getJsapiTicket(String appid) throws WxErrorException {
    return getJsapiTicket(appid, false);
  }

  @Override
  public String getJsapiTicket(String appid, boolean forceRefresh) throws WxErrorException {
    Lock lock = this.getWxOpenConfigStorage().getJsapiTicketLock();
    try {
      lock.lock();
      if (forceRefresh) {
        this.getWxOpenConfigStorage().expireJsapiTicket(appid);
      }

      if (this.getWxOpenConfigStorage().isJsapiTicketExpired(appid)) {
        String responseContent = execute(appid, SimpleGetRequestExecutor.create(this), WxMpService.GET_JSAPI_TICKET_URL, null);
        JsonElement tmpJsonElement = JSON_PARSER.parse(responseContent);
        JsonObject tmpJsonObject = tmpJsonElement.getAsJsonObject();
        String jsapiTicket = tmpJsonObject.get("ticket").getAsString();
        int expiresInSeconds = tmpJsonObject.get("expires_in").getAsInt();
        this.getWxOpenConfigStorage().updateJsapiTicket(appid, jsapiTicket, expiresInSeconds);
      }
    } finally {
      lock.unlock();
    }
    return this.getWxOpenConfigStorage().getJsapiTicket(appid);
  }

  @Override
  public WxJsapiSignature createJsapiSignature(String appid, String url) throws WxErrorException {
    long timestamp = System.currentTimeMillis() / 1000;
    String noncestr = RandomUtils.getRandomStr();
    String jsapiTicket = getJsapiTicket(appid, false);
    String signature = SHA1.genWithAmple("jsapi_ticket=" + jsapiTicket,
      "noncestr=" + noncestr, "timestamp=" + timestamp, "url=" + url);
    WxJsapiSignature jsapiSignature = new WxJsapiSignature();
    jsapiSignature.setAppId(appid);
    jsapiSignature.setTimestamp(timestamp);
    jsapiSignature.setNonceStr(noncestr);
    jsapiSignature.setUrl(url);
    jsapiSignature.setSignature(signature);
    return jsapiSignature;
  }

  @Override
  public String shortUrl(String appid, String long_url) throws WxErrorException {
    JsonObject o = new JsonObject();
    o.addProperty("action", "long2short");
    o.addProperty("long_url", long_url);
    String responseContent = this.post(appid, WxMpService.SHORTURL_API_URL, o.toString());
    JsonElement tmpJsonElement = JSON_PARSER.parse(responseContent);
    return tmpJsonElement.getAsJsonObject().get("short_url").getAsString();
  }

  @Override
  public String buildComponentLoginPageUrl(String redirectURI) {
    return String.format(
      WxOpenService.COMPONENT_LOGIN_PAGE_URL,
      this.getWxOpenConfigStorage().getAppId(),
      this.getWxOpenConfigStorage().getPreAuthCode(),
      URIUtil.encodeURIComponent(redirectURI)
    );
  }

  @Override
  public AuthorizerInfoResp queryAuth(String authCode) throws WxErrorException {
    JsonObject req = new JsonObject();
    req.addProperty("component_appid", this.getWxOpenConfigStorage().getAppId());
    req.addProperty("authorization_code", authCode);

    String responseContent = this.post(WxOpenService.COMPONENT_API_QUERY_AUTH_URL, req.toString());
    AuthorizerInfoResp authorizerInfo = AuthorizerInfoResp.fromJson(responseContent);

    return authorizerInfo;
  }

  @Override
  public String oauth2buildAuthorizationUrl(String appid, String redirectURI, String scope, String state) {
    return String.format(WxOpenService.COMPONENT_CONNECT_OAUTH2_URL,
      appid, URIUtil.encodeURIComponent(redirectURI), scope, StringUtils.trimToEmpty(state), this.getWxOpenConfigStorage().getAppId());
  }

  @Override
  public WxMpOAuth2AccessToken oauth2getAccessToken(String appid, String code) throws WxErrorException {
    String url = String.format(
      WxOpenService.COMPONENT_CONNECT_OAUTH2_ACCESS_TOKEN_URL,
      appid, code, this.getWxOpenConfigStorage().getAppId(), this.getWxOpenConfigStorage().getComponentAccessToken());
    return this.getOAuth2AccessToken(url);
  }

  @Override
  public WxMpOAuth2AccessToken oauth2refreshAccessToken(String appid, String refreshToken) throws WxErrorException {
    String url = String.format(
      WxOpenService.COMPONENT_CONNECT_OAUTH2_REFRESH_TOKEN_URL,
      appid, this.getWxOpenConfigStorage().getAppId(), this.getWxOpenConfigStorage().getComponentAccessToken(), refreshToken);
    return this.getOAuth2AccessToken(url);
  }

  private WxMpOAuth2AccessToken getOAuth2AccessToken(String url) throws WxErrorException {
    try {
      RequestExecutor<String, String> executor = SimpleGetRequestExecutor.create(this);
      String responseText = executor.execute(url, null);
      return WxMpOAuth2AccessToken.fromJson(responseText);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public WxMpUser oauth2getUserInfo(WxMpOAuth2AccessToken oAuth2AccessToken, String lang) throws WxErrorException {
    if (lang == null) {
      lang = "zh_CN";
    }

    String url = String.format(WxMpService.OAUTH2_USERINFO_URL, oAuth2AccessToken.getAccessToken(), oAuth2AccessToken.getOpenId(), lang);

    try {
      RequestExecutor<String, String> executor = SimpleGetRequestExecutor.create(this);
      String responseText = executor.execute(url, null);
      return WxMpUser.fromJson(responseText);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean oauth2validateAccessToken(WxMpOAuth2AccessToken oAuth2AccessToken) {
    String url = String.format(WxMpService.OAUTH2_VALIDATE_TOKEN_URL, oAuth2AccessToken.getAccessToken(), oAuth2AccessToken.getOpenId());

    try {
      SimpleGetRequestExecutor.create(this).execute(url, null);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (WxErrorException e) {
      return false;
    }
    return true;
  }

  @Override
  public WxMpCurrentAutoReplyInfo getCurrentAutoReplyInfo(String appid) throws WxErrorException {
    return WxMpCurrentAutoReplyInfo.fromJson(this.get(appid, WxMpService.GET_CURRENT_AUTOREPLY_INFO_URL, null));
  }

  @Override
  public String getAuthorizerAccessToken(String appid, boolean forceRefresh) throws WxErrorException {
    String refreshToken = getAuthorizerRefreshToken(appid);
    Lock lock = this.getWxOpenConfigStorage().getAuthorizerAccessTokenLock();

    try {
      lock.lock();

      if (this.getWxOpenConfigStorage().isAuthorizerAccessTokenExpired(appid) || forceRefresh) {
        AuthorizerTokenReq request = AuthorizerTokenReq.builder()
          .componentAppid(this.getWxOpenConfigStorage().getAppId())
          .authorizerAppid(appid)
          .authorizerRefreshToken(this.getAuthorizerRefreshToken(appid))
          .build();

        String resultContent = post(WxOpenService.COMPONENT_API_AUTHORIZER_TOKEN_URL, request.toJson());
        AuthorizerTokenResp authorizerTokenResp = AuthorizerTokenResp.fromJson(resultContent);

        this.getWxOpenConfigStorage().updateAuthorizerAccessToken(appid, authorizerTokenResp);
      }
    } finally {
      lock.unlock();
    }

    return this.getWxOpenConfigStorage().getAuthorizerAccessToken(appid);
  }

  @Override
  public String getAuthorizerAccessToken(String appid) throws WxErrorException {
    return getAuthorizerAccessToken(appid, false);
  }

  @Override
  public String get(String url, String queryParam) throws WxErrorException {
    return execute(SimpleGetRequestExecutor.create(this), url, queryParam);
  }

  @Override
  public String post(String url, String postData) throws WxErrorException {
    return execute(SimplePostRequestExecutor.create(this), url, postData);
  }

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
    if (uri.contains("component_access_token=")) {
      throw new IllegalArgumentException("uri参数中不允许有component_access_token: " + uri);
    }
    String componentAccessToken = getComponentAccessToken(false);

    String uriWithAccessToken = uri + (uri.contains("?") ? "&" : "?") + "component_access_token=" + componentAccessToken;

    try {
      T result = executor.execute(uriWithAccessToken, data);
      this.log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uriWithAccessToken, data, result);
      return result;
    } catch (WxErrorException e) {
      WxError error = e.getError();
      /*
       * 发生以下情况时尝试刷新access_token
       * 40001 获取access_token时AppSecret错误，或者access_token无效
       * 42001 access_token超时
       * 40014 不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口
       */
      if (error.getErrorCode() == 42001 || error.getErrorCode() == 40001 || error.getErrorCode() == 40014) {
        // 强制设置wxMpConfigStorage它的access token过期了，这样在下一次请求里就会刷新access token
        this.getWxOpenConfigStorage().expireComponentAccessToken();
        if (this.getWxOpenConfigStorage().autoRefreshToken()) {
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
  public String get(String appid, String url, String queryParam) throws WxErrorException {
    return execute(appid, SimpleGetRequestExecutor.create(this), url, queryParam);
  }

  @Override
  public String post(String appid, String url, String postData) throws WxErrorException {
    return execute(appid, SimplePostRequestExecutor.create(this), url, postData);
  }

  @Override
  public <T, E> T execute(String appid, RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    int retryTimes = 0;
    do {
      try {
        return this.executeInternal(appid, executor, uri, data);
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

  public <T, E> T executeInternal(String appid, RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    if (uri.contains("access_token=")) {
      throw new IllegalArgumentException("uri参数中不允许有access_token: " + uri);
    }
    String accessToken = getAuthorizerAccessToken(appid, false);

    String uriWithAccessToken = uri + (uri.contains("?") ? "&" : "?") + "access_token=" + accessToken;

    try {
      T result = executor.execute(uriWithAccessToken, data);
      this.log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uriWithAccessToken, data, result);
      return result;
    } catch (WxErrorException e) {
      WxError error = e.getError();
      /*
       * 发生以下情况时尝试刷新access_token
       * 40001 获取access_token时AppSecret错误，或者access_token无效
       * 42001 access_token超时
       * 40014 不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口
       */
      if (error.getErrorCode() == 42001 || error.getErrorCode() == 40001 || error.getErrorCode() == 40014) {
        // 强制设置wxMpConfigStorage它的access token过期了，这样在下一次请求里就会刷新access token
        this.getWxOpenConfigStorage().expireComponentAccessToken();
        if (this.getWxOpenConfigStorage().autoRefreshToken()) {
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
  public RequestHttp getRequestHttp() {
    return this;
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
  public void setWxOpenConfigStorage(WxOpenConfigStorage wxOpenConfigStorage) {
    this.wxOpenConfigStorage = wxOpenConfigStorage;
    this.initHttp();
  }

  @Override
  public WxOpenConfigStorage getWxOpenConfigStorage() {
    return this.wxOpenConfigStorage;
  }

  @Override
  public WxOpenTemplateMsgService getTemplateMsgService() {
    return this.templateMsgService;
  }

  @Override
  public WxOpenKefuService getKefuService() {
    return this.kefuService;
  }

  @Override
  public WxOpenMaterialService getMaterialService() {
    return this.materialService;
  }

  @Override
  public WxOpenMenuService getMenuService() {
    return this.menuService;
  }

  @Override
  public WxOpenUserService getUserService() {
    return this.userService;
  }

  @Override
  public WxOpenUserTagService getUserTagService() {
    return this.tagService;
  }

  @Override
  public WxOpenQrcodeService getQrcodeService() {
    return this.qrCodeService;
  }

  @Override
  public WxOpenCardService getCardService() {
    return this.cardService;
  }

  @Override
  public WxOpenDataCubeService getDataCubeService() {
    return this.dataCubeService;
  }

  @Override
  public WxOpenUserBlacklistService getBlackListService() {
    return this.blackListService;
  }

  @Override
  public WxOpenStoreService getStoreService() {
    return this.storeService;
  }

  @Override
  public WxOpenDeviceService getDeviceService() {
    return this.deviceService;
  }

  @Override
  public WxOpenShakeService getShakeService() {
    return this.shakeService;
  }

  @Override
  public WxOpenMemberCardService getMemberCardService() {
    return this.memberCardService;
  }

  @Override
  public WxOpenMassMessageService getMassMessageService() {
    return this.massMessageService;
  }

  @Override
  public void setKefuService(WxOpenKefuService kefuService) {
    this.kefuService = kefuService;
  }

  @Override
  public void setMaterialService(WxOpenMaterialService materialService) {
    this.materialService = materialService;
  }

  @Override
  public void setMenuService(WxOpenMenuService menuService) {
    this.menuService = menuService;
  }

  @Override
  public void setUserService(WxOpenUserService userService) {
    this.userService = userService;
  }

  @Override
  public void setTagService(WxOpenUserTagService tagService) {
    this.tagService = tagService;
  }

  @Override
  public void setQrCodeService(WxOpenQrcodeService qrCodeService) {
    this.qrCodeService = qrCodeService;
  }

  @Override
  public void setCardService(WxOpenCardService cardService) {
    this.cardService = cardService;
  }

  @Override
  public void setStoreService(WxOpenStoreService storeService) {
    this.storeService = storeService;
  }

  @Override
  public void setDataCubeService(WxOpenDataCubeService dataCubeService) {
    this.dataCubeService = dataCubeService;
  }

  @Override
  public void setBlackListService(WxOpenUserBlacklistService blackListService) {
    this.blackListService = blackListService;
  }

  @Override
  public void setTemplateMsgService(WxOpenTemplateMsgService templateMsgService) {
    this.templateMsgService = templateMsgService;
  }

  @Override
  public void setDeviceService(WxOpenDeviceService deviceService) {
    this.deviceService = deviceService;
  }

  @Override
  public void setShakeService(WxOpenShakeService shakeService) {
    this.shakeService = shakeService;
  }

  @Override
  public void setMemberCardService(WxOpenMemberCardService memberCardService) {
    this.memberCardService = memberCardService;
  }

  @Override
  public void setMassMessageService(WxOpenMassMessageService massMessageService) {
    this.massMessageService = massMessageService;
  }
}

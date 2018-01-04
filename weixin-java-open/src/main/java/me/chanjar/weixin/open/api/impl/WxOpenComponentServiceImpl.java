package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.http.URIUtil;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.bean.WxOpenAuthorizerAccessToken;
import me.chanjar.weixin.open.bean.WxOpenComponentAccessToken;
import me.chanjar.weixin.open.bean.auth.WxOpenAuthorizationInfo;
import me.chanjar.weixin.open.bean.message.WxOpenXmlMessage;
import me.chanjar.weixin.open.bean.result.WxOpenAuthorizerInfoResult;
import me.chanjar.weixin.open.bean.result.WxOpenAuthorizerOptionResult;
import me.chanjar.weixin.open.bean.result.WxOpenQueryAuthResult;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenComponentServiceImpl implements WxOpenComponentService {

  private static final Map<String, WxMaService> WX_OPEN_MA_SERVICE_MAP = new Hashtable<>();
  private static final Map<String, WxMpService> WX_OPEN_MP_SERVICE_MAP = new Hashtable<>();

  protected final Logger log = LoggerFactory.getLogger(this.getClass());
  private WxOpenService wxOpenService;

  public WxOpenComponentServiceImpl(WxOpenService wxOpenService) {
    this.wxOpenService = wxOpenService;
  }

  @Override
  public WxMpService getWxMpServiceByAppid(String appId) {
    WxMpService wxMpService = WX_OPEN_MP_SERVICE_MAP.get(appId);
    if (wxMpService == null) {
      synchronized (WX_OPEN_MP_SERVICE_MAP) {
        wxMpService = WX_OPEN_MP_SERVICE_MAP.get(appId);
        if (wxMpService == null) {
          wxMpService = new WxOpenMpServiceImpl(this, appId, getWxOpenConfigStorage().getWxMpConfigStorage(appId));

          WX_OPEN_MP_SERVICE_MAP.put(appId, wxMpService);
        }
      }
    }
    return wxMpService;
  }

  @Override
  public WxMaService getWxMaServiceByAppid(String appId) {
    WxMaService wxMaService = WX_OPEN_MA_SERVICE_MAP.get(appId);
    if (wxMaService == null) {
      synchronized (WX_OPEN_MA_SERVICE_MAP) {
        wxMaService = WX_OPEN_MA_SERVICE_MAP.get(appId);
        if (wxMaService == null) {
          wxMaService = new WxOpenMaServiceImpl(this, appId, getWxOpenConfigStorage().getWxMaConfig(appId));
          WX_OPEN_MA_SERVICE_MAP.put(appId, wxMaService);
        }
      }
    }
    return wxMaService;
  }
  public WxOpenService getWxOpenService() {
    return wxOpenService;
  }

  @Override
  public WxOpenConfigStorage getWxOpenConfigStorage() {
    return wxOpenService.getWxOpenConfigStorage();
  }

  @Override
  public boolean checkSignature(String timestamp, String nonce, String signature) {
    try {
      return SHA1.gen(getWxOpenConfigStorage().getComponentToken(), timestamp, nonce)
        .equals(signature);
    } catch (Exception e) {
      this.log.error("Checking signature failed, and the reason is :" + e.getMessage());
      return false;
    }
  }

  @Override
  public String getComponentAccessToken(boolean forceRefresh) throws WxErrorException {

    if (this.getWxOpenConfigStorage().isComponentAccessTokenExpired() || forceRefresh) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
      jsonObject.addProperty("component_appsecret", getWxOpenConfigStorage().getComponentAppSecret());
      jsonObject.addProperty("component_verify_ticket", getWxOpenConfigStorage().getComponentVerifyTicket());

      String responseContent = this.getWxOpenService().post(API_COMPONENT_TOKEN_URL, jsonObject.toString());
      WxOpenComponentAccessToken componentAccessToken = WxOpenComponentAccessToken.fromJson(responseContent);
      getWxOpenConfigStorage().updateComponentAccessTokent(componentAccessToken);
    }
    return this.getWxOpenConfigStorage().getComponentAccessToken();
  }

  private String post(String uri, String postData) throws WxErrorException {
    String componentAccessToken = getComponentAccessToken(false);
    String uriWithComponentAccessToken = uri + (uri.contains("?") ? "&" : "?") + "component_access_token=" + componentAccessToken;
    return getWxOpenService().post(uriWithComponentAccessToken, postData);
  }

  private String get(String uri) throws WxErrorException {
    String componentAccessToken = getComponentAccessToken(false);
    String uriWithComponentAccessToken = uri + (uri.contains("?") ? "&" : "?") + "component_access_token=" + componentAccessToken;
    return getWxOpenService().get(uriWithComponentAccessToken, null);
  }

  @Override
  public String getPreAuthUrl(String redirectURI) throws WxErrorException {

    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
    String responseContent = post(API_CREATE_PREAUTHCODE_URL, jsonObject.toString());
    jsonObject = WxGsonBuilder.create().fromJson(responseContent, JsonObject.class);
    return String.format(COMPONENT_LOGIN_PAGE_URL, getWxOpenConfigStorage().getComponentAppId(), jsonObject.get("pre_auth_code").getAsString(), URIUtil.encodeURIComponent(redirectURI));
  }

  @Override
  public String route(final WxOpenXmlMessage wxMessage) throws WxErrorException {
    if (wxMessage == null) {
      throw new NullPointerException("message is empty");
    }
    if (StringUtils.equalsIgnoreCase(wxMessage.getInfoType(), "component_verify_ticket")) {
      getWxOpenConfigStorage().setComponentVerifyTicket(wxMessage.getComponentVerifyTicket());
      return "success";
    }
    //新增、跟新授权
    if (StringUtils.equalsAnyIgnoreCase(wxMessage.getInfoType(), "authorized", "updateauthorized")) {
      WxOpenQueryAuthResult queryAuth = wxOpenService.getWxOpenComponentService().getQueryAuth(wxMessage.getAuthorizationCode());
      if (queryAuth == null || queryAuth.getAuthorizationInfo() == null || queryAuth.getAuthorizationInfo().getAuthorizerAppid() == null) {
        throw new NullPointerException("getQueryAuth");
      }
      WxOpenAuthorizationInfo authorizationInfo = queryAuth.getAuthorizationInfo();
      if (authorizationInfo.getAuthorizerAccessToken() != null) {
        getWxOpenConfigStorage().updateAuthorizerAccessToken(authorizationInfo.getAuthorizerAppid(),
          authorizationInfo.getAuthorizerAccessToken(), authorizationInfo.getExpiresIn());
      }
      if (authorizationInfo.getAuthorizerRefreshToken() != null) {
        getWxOpenConfigStorage().setAuthorizerRefreshToken(authorizationInfo.getAuthorizerAppid(), authorizationInfo.getAuthorizerRefreshToken());
      }
      return "success";
    }
    return "";
  }

  @Override
  public WxOpenQueryAuthResult getQueryAuth(String authorizationCode) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
    jsonObject.addProperty("authorization_code", authorizationCode);
    String responseContent = post(API_QUERY_AUTH_URL, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(responseContent, WxOpenQueryAuthResult.class);
  }

  @Override
  public WxOpenAuthorizerInfoResult getAuthorizerInfo(String authorizerAppid) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
    jsonObject.addProperty("authorizer_appid", authorizerAppid);
    String responseContent = post(API_GET_AUTHORIZER_INFO_URL, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(responseContent, WxOpenAuthorizerInfoResult.class);
  }

  @Override
  public WxOpenAuthorizerOptionResult getAuthorizerOption(String authorizerAppid, String optionName) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
    jsonObject.addProperty("authorizer_appid", authorizerAppid);
    jsonObject.addProperty("option_name", optionName);
    String responseContent = post(API_GET_AUTHORIZER_OPTION_URL, jsonObject.toString());
    return WxOpenGsonBuilder.create().fromJson(responseContent, WxOpenAuthorizerOptionResult.class);
  }

  @Override
  public void setAuthorizerOption(String authorizerAppid, String optionName, String optionValue) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
    jsonObject.addProperty("authorizer_appid", authorizerAppid);
    jsonObject.addProperty("option_name", optionName);
    jsonObject.addProperty("option_value", optionValue);
    post(API_SET_AUTHORIZER_OPTION_URL, jsonObject.toString());
  }

  @Override
  public String getAuthorizerAccessToken(String appId, boolean forceRefresh) throws WxErrorException {

    if (this.getWxOpenConfigStorage().isAuthorizerAccessTokenExpired(appId) || forceRefresh) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("component_appid", getWxOpenConfigStorage().getComponentAppId());
      jsonObject.addProperty("authorizer_appid", appId);
      jsonObject.addProperty("authorizer_refresh_token", getWxOpenConfigStorage().getAuthorizerRefreshToken(appId));
      String responseContent = post(API_AUTHORIZER_TOKEN_URL, jsonObject.toString());

      WxOpenAuthorizerAccessToken wxOpenAuthorizerAccessToken = WxOpenAuthorizerAccessToken.fromJson(responseContent);
      getWxOpenConfigStorage().updateAuthorizerAccessToken(appId, wxOpenAuthorizerAccessToken);
    }
    return this.getWxOpenConfigStorage().getAuthorizerAccessToken(appId);
  }

  @Override
  public WxMpOAuth2AccessToken oauth2getAccessToken(String appId, String code) throws WxErrorException {
    String url = String.format(OAUTH2_ACCESS_TOKEN_URL, appId, code, getWxOpenConfigStorage().getComponentAppId());
    String responseContent = get(url);
    return WxMpOAuth2AccessToken.fromJson(responseContent);
  }

  @Override
  public boolean checkSignature(String appid, String timestamp, String nonce, String signature) {
    return false;
  }

  @Override
  public WxMpOAuth2AccessToken oauth2refreshAccessToken(String appId, String refreshToken) throws WxErrorException {
    String url = String.format(OAUTH2_REFRESH_TOKEN_URL, appId, refreshToken, getWxOpenConfigStorage().getComponentAppId());
    String responseContent = get(url);
    return WxMpOAuth2AccessToken.fromJson(responseContent);
  }

  @Override
  public String oauth2buildAuthorizationUrl(String appId, String redirectURI, String scope, String state) {
    return String.format(CONNECT_OAUTH2_AUTHORIZE_URL,
      appId, URIUtil.encodeURIComponent(redirectURI), scope, StringUtils.trimToEmpty(state), getWxOpenConfigStorage().getComponentAppId());
  }

  @Override
  public WxMaJscode2SessionResult miniappJscode2Session(String appid, String jsCode, String appId) throws WxErrorException  {
    String url = String.format(MINIAPP_JSCODE_2_SESSION, appId, jsCode, getWxOpenConfigStorage().getComponentAppId());
    String responseContent = get(url);
    return WxMaJscode2SessionResult.fromJson(responseContent);
  }

}

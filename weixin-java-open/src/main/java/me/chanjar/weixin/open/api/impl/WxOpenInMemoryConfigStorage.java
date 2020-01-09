package me.chanjar.weixin.open.api.impl;


import cn.binarywang.wx.miniapp.config.WxMaConfig;
import lombok.Data;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.mp.bean.WxMpHostConfig;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.enums.TicketType;
import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.bean.WxOpenAuthorizerAccessToken;
import me.chanjar.weixin.open.bean.WxOpenComponentAccessToken;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于内存的微信配置provider，在实际生产环境中应该将这些配置持久化
 *
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class WxOpenInMemoryConfigStorage implements WxOpenConfigStorage {
  private String componentAppId;
  private String componentAppSecret;
  private String componentToken;
  private String componentAesKey;
  private String componentVerifyTicket;
  private String componentAccessToken;
  private long componentExpiresTime;

  private String httpProxyHost;
  private int httpProxyPort;
  private String httpProxyUsername;
  private String httpProxyPassword;
  private ApacheHttpClientBuilder apacheHttpClientBuilder;

  private Map<String, Token> authorizerRefreshTokens = new ConcurrentHashMap<>();
  private Map<String, Token> authorizerAccessTokens = new ConcurrentHashMap<>();
  private Map<String, Token> jsapiTickets = new ConcurrentHashMap<>();
  private Map<String, Token> cardApiTickets = new ConcurrentHashMap<>();



  @Override
  public boolean isComponentAccessTokenExpired() {
    return System.currentTimeMillis() > componentExpiresTime;
  }

  @Override
  public void expireComponentAccessToken() {
    this.componentExpiresTime = 0L;
  }

  @Override
  public void updateComponentAccessToken(WxOpenComponentAccessToken componentAccessToken) {
    updateComponentAccessToken(componentAccessToken.getComponentAccessToken(), componentAccessToken.getExpiresIn());
  }

  @Override
  public WxMpConfigStorage getWxMpConfigStorage(String appId) {
    return new WxOpenInnerConfigStorage(this, appId);
  }

  @Override
  public WxMaConfig getWxMaConfig(String appId) {
    return new WxOpenInnerConfigStorage(this, appId);
  }

  @Override
  public void updateComponentAccessToken(String componentAccessToken, int expiresInSeconds) {
    this.componentAccessToken = componentAccessToken;
    this.componentExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
  }

  @Override
  public void setWxOpenInfo(String componentAppId, String componentAppSecret, String componentToken, String componentAesKey) {
    setComponentAppId(componentAppId);
    setComponentAppSecret(componentAppSecret);
    setComponentToken(componentToken);
    setComponentAesKey(componentAesKey);
  }

  @Override
  public boolean autoRefreshToken() {
    return true;
  }

  private String getTokenString(Map<String, Token> map, String key) {
    Token token = map.get(key);
    if (token == null || (token.expiresTime != null && System.currentTimeMillis() > token.expiresTime)) {
      return null;
    }
    return token.token;
  }

  private void expireToken(Map<String, Token> map, String key) {
    Token token = map.get(key);
    if (token != null) {
      token.expiresTime = 0L;
    }
  }

  private void updateToken(Map<String, Token> map, String key, String tokenString, Integer expiresInSeconds) {
    Token token = map.get(key);
    if (token == null) {
      token = new Token();
      map.put(key, token);
    }
    token.token = tokenString;
    if (expiresInSeconds != null) {
      token.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
    }
  }

  @Override
  public String getAuthorizerRefreshToken(String appId) {
    return getTokenString(authorizerRefreshTokens, appId);
  }

  @Override
  public void setAuthorizerRefreshToken(String appId, String authorizerRefreshToken) {
    updateToken(authorizerRefreshTokens, appId, authorizerRefreshToken, null);
  }

  @Override
  public String getAuthorizerAccessToken(String appId) {
    return getTokenString(authorizerAccessTokens, appId);
  }


  @Override
  public boolean isAuthorizerAccessTokenExpired(String appId) {
    return getTokenString(authorizerAccessTokens, appId) == null;
  }

  @Override
  public void expireAuthorizerAccessToken(String appId) {
    expireToken(authorizerAccessTokens, appId);
  }

  @Override
  public void updateAuthorizerAccessToken(String appId, WxOpenAuthorizerAccessToken authorizerAccessToken) {
    updateAuthorizerAccessToken(appId, authorizerAccessToken.getAuthorizerAccessToken(), authorizerAccessToken.getExpiresIn());
  }

  @Override
  public void updateAuthorizerAccessToken(String appId, String authorizerAccessToken, int expiresInSeconds) {
    updateToken(authorizerAccessTokens, appId, authorizerAccessToken, expiresInSeconds);
  }

  @Override
  public String getJsapiTicket(String appId) {
    return getTokenString(jsapiTickets, appId);
  }

  @Override
  public boolean isJsapiTicketExpired(String appId) {
    return getTokenString(jsapiTickets, appId) == null;
  }

  @Override
  public void expireJsapiTicket(String appId) {
    expireToken(jsapiTickets, appId);
  }

  @Override
  public void updateJsapiTicket(String appId, String jsapiTicket, int expiresInSeconds) {
    updateToken(jsapiTickets, appId, jsapiTicket, expiresInSeconds);
  }

  @Override
  public String getCardApiTicket(String appId) {
    return getTokenString(cardApiTickets, appId);
  }

  @Override
  public boolean isCardApiTicketExpired(String appId) {
    return getTokenString(cardApiTickets, appId) == null;
  }

  @Override
  public void expireCardApiTicket(String appId) {
    expireToken(cardApiTickets, appId);
  }

  @Override
  public void updateCardApiTicket(String appId, String cardApiTicket, int expiresInSeconds) {
    updateToken(cardApiTickets, appId, cardApiTicket, expiresInSeconds);
  }

  private static class Token {
    private String token;
    private Long expiresTime;
  }

  private static class WxOpenInnerConfigStorage implements WxMpConfigStorage, WxMaConfig {
    private WxOpenConfigStorage wxOpenConfigStorage;
    private String appId;
    private Lock accessTokenLock = new ReentrantLock();
    private Lock jsapiTicketLock = new ReentrantLock();
    private Lock cardApiTicketLock = new ReentrantLock();

    private WxOpenInnerConfigStorage(WxOpenConfigStorage wxOpenConfigStorage, String appId) {
      this.wxOpenConfigStorage = wxOpenConfigStorage;
      this.appId = appId;
    }

    @Override
    public String getAccessToken() {
      return wxOpenConfigStorage.getAuthorizerAccessToken(appId);
    }

    @Override
    public Lock getAccessTokenLock() {
      return this.accessTokenLock;
    }

    @Override
    public boolean isAccessTokenExpired() {
      return wxOpenConfigStorage.isAuthorizerAccessTokenExpired(appId);
    }

    @Override
    public synchronized void updateAccessToken(WxAccessToken accessToken) {
      updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
    }

    @Override
    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
      wxOpenConfigStorage.updateAuthorizerAccessToken(appId, accessToken, expiresInSeconds);
    }

    @Override
    public String getTicket(TicketType type) {
      switch (type) {
        case JSAPI: {
          return wxOpenConfigStorage.getJsapiTicket(appId);
        }
        case WX_CARD: {
          return wxOpenConfigStorage.getCardApiTicket(appId);
        }
        default: {
          // do nothing
        }
      }
      return null;
    }

    @Override
    public Lock getTicketLock(TicketType type) {
      switch (type) {
        case JSAPI: {
          return this.jsapiTicketLock;
        }
        case WX_CARD: {
          return this.cardApiTicketLock;
        }
        default: {
          // do nothing
        }
      }
      return null;
    }

    @Override
    public boolean isTicketExpired(TicketType type) {
      switch (type) {
        case JSAPI: {
          return wxOpenConfigStorage.isJsapiTicketExpired(appId);
        }
        case WX_CARD: {
          return wxOpenConfigStorage.isCardApiTicketExpired(appId);
        }
        default: {
          // do nothing
        }
      }

      return false;
    }

    @Override
    public void expireTicket(TicketType type) {
      switch (type) {
        case JSAPI: {
          wxOpenConfigStorage.expireJsapiTicket(appId);
          break;
        }
        case WX_CARD: {
          wxOpenConfigStorage.expireCardApiTicket(appId);
          break;
        }
        default: {
          // do nothing
        }
      }
    }

    @Override
    public void updateTicket(TicketType type, String ticket, int expiresInSeconds) {
      switch (type) {
        case JSAPI: {
          wxOpenConfigStorage.updateJsapiTicket(appId, ticket, expiresInSeconds);
          break;
        }
        case WX_CARD: {
          wxOpenConfigStorage.updateCardApiTicket(appId, ticket, expiresInSeconds);
          break;
        }
        default: {
          // do nothing
        }
      }

    }

    @Override
    public String getAppid() {
      return this.appId;
    }

    @Override
    public void expireAccessToken() {
      wxOpenConfigStorage.expireAuthorizerAccessToken(appId);
    }

    @Override
    public String getJsapiTicket() {
      return wxOpenConfigStorage.getJsapiTicket(appId);
    }

    @Override
    public Lock getJsapiTicketLock() {
      return this.jsapiTicketLock;
    }

    @Override
    public boolean isJsapiTicketExpired() {
      return wxOpenConfigStorage.isJsapiTicketExpired(appId);
    }

    @Override
    public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
      wxOpenConfigStorage.updateJsapiTicket(appId, jsapiTicket, expiresInSeconds);
    }

    @Override
    public void expireJsapiTicket() {
      wxOpenConfigStorage.expireJsapiTicket(appId);
    }

    @Override
    public String getCardApiTicket() {
      return wxOpenConfigStorage.getCardApiTicket(appId);
    }

    @Override
    public Lock getCardApiTicketLock() {
      return this.cardApiTicketLock;
    }

    @Override
    public boolean isCardApiTicketExpired() {
      return wxOpenConfigStorage.isCardApiTicketExpired(appId);
    }

    @Override
    public synchronized void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
      wxOpenConfigStorage.updateCardApiTicket(appId, cardApiTicket, expiresInSeconds);
    }

    @Override
    public void expireCardApiTicket() {
      wxOpenConfigStorage.expireCardApiTicket(appId);
    }

    @Override
    public String getAppId() {
      return this.appId;
    }

    @Override
    public String getSecret() {
      return null;
    }

    @Override
    public String getToken() {
      return wxOpenConfigStorage.getComponentToken();
    }

    @Override
    public String getTemplateId() {
      return null;
    }

    @Override
    public long getExpiresTime() {
      return 0;
    }


    @Override
    public String getAesKey() {
      return wxOpenConfigStorage.getComponentAesKey();
    }

    @Override
    public String getMsgDataFormat() {
      return null;
    }

    @Override
    public String getOauth2redirectUri() {
      return null;
    }

    @Override
    public String getHttpProxyHost() {
      return this.wxOpenConfigStorage.getHttpProxyHost();
    }

    @Override
    public int getHttpProxyPort() {
      return this.wxOpenConfigStorage.getHttpProxyPort();
    }

    @Override
    public String getHttpProxyUsername() {
      return this.wxOpenConfigStorage.getHttpProxyUsername();
    }

    @Override
    public String getHttpProxyPassword() {
      return this.wxOpenConfigStorage.getHttpProxyPassword();
    }

    @Override
    public String toString() {
      return WxOpenGsonBuilder.create().toJson(this);
    }

    @Override
    public File getTmpDirFile() {
      return null;
    }

    @Override
    public ApacheHttpClientBuilder getApacheHttpClientBuilder() {
      return wxOpenConfigStorage.getApacheHttpClientBuilder();
    }

    @Override
    public boolean autoRefreshToken() {
      return wxOpenConfigStorage.autoRefreshToken();
    }

    @Override
    public WxMpHostConfig getHostConfig() {
      return null;
    }
  }
}

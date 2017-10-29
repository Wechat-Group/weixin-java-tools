package me.chanjar.weixin.open.api;


import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.open.bean.resp.AuthorizerTokenResp;
import me.chanjar.weixin.open.bean.resp.ComponentAccessTokenResp;
import me.chanjar.weixin.open.bean.resp.PreAuthCodeResp;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by JasonY on 17/10/24.
 */
public class WxOpenInMemoryConfigStorage implements WxOpenConfigStorage {

  protected volatile String appId;
  protected volatile String secret;
  protected volatile String token;
  protected volatile String aesKey;
  protected volatile String componentAccessToken;
  protected volatile long expiresTime;

  protected volatile String httpProxyHost;
  protected volatile int httpProxyPort;
  protected volatile String httpProxyUsername;
  protected volatile String httpProxyPassword;

  protected volatile String componentVerifyTicket;
  protected volatile String preAuthCode;
  protected volatile long preAuthCodeExpiresTime;
  protected ConcurrentHashMap<String, String> authorizerAccessTokenMap = new ConcurrentHashMap<>();
  protected ConcurrentHashMap<String, Long> authorizerAccessTokenExpiresTimeMap = new ConcurrentHashMap<>();
  protected ConcurrentHashMap<String, String> jsapiTicketMap = new ConcurrentHashMap<>();
  protected ConcurrentHashMap<String, Long> jsapiTicketExpiresTimeMap = new ConcurrentHashMap<>();
  protected ConcurrentHashMap<String, String> cardApiTicketMap = new ConcurrentHashMap<>();
  protected ConcurrentHashMap<String, Long> cardApiTicketExpiresTimeMap = new ConcurrentHashMap<>();

  protected Lock preAuthCodeLock = new ReentrantLock();
  protected Lock componentAccessTokenLock = new ReentrantLock();
  protected Lock authorizerAccessTokenLock = new ReentrantLock();
  protected Lock jsapiTicketLock = new ReentrantLock();
  protected Lock cardApiTicketLock = new ReentrantLock();

  /**
   * 临时文件目录
   */
  protected volatile File tmpDirFile;

  protected volatile ApacheHttpClientBuilder apacheHttpClientBuilder;

  @Override
  public String getComponentVerifyTicket() {
    return this.componentVerifyTicket;
  }

  @Override
  public void updateComponentVerifyTicket(String componentVerifyTicket) {
    this.componentVerifyTicket = componentVerifyTicket;
  }

  @Override
  public String getComponentAccessToken() {
    return this.componentAccessToken;
  }

  @Override
  public Lock getComponentAccessTokenLock() {
    return this.componentAccessTokenLock;
  }

  @Override
  public boolean isComponentAccessTokenExpired() {
    return System.currentTimeMillis() > this.expiresTime;
  }

  @Override
  public void expireComponentAccessToken() {
    this.expiresTime = 0;
  }

  @Override
  public void updateComponentAccessToken(ComponentAccessTokenResp componentAccessTokenResp) {
    updateComponentAccessToken(componentAccessTokenResp.getComponentAccessToken(), componentAccessTokenResp.getExpiresIn());
  }

  @Override
  public void updateComponentAccessToken(String componentAccessToken, int expiresInSeconds) {
    this.componentAccessToken = componentAccessToken;
    this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
  }

  @Override
  public String getPreAuthCode() {
    return this.preAuthCode;
  }

  @Override
  public Lock getPreAuthCodeLock() {
    return preAuthCodeLock;
  }

  @Override
  public boolean isPreAuthCodeExpired() {
    return System.currentTimeMillis() > preAuthCodeExpiresTime;
  }

  @Override
  public void expirePreAuthCode() {
    this.preAuthCodeExpiresTime = 0;
  }

  @Override
  public void updatePreAuthCode(PreAuthCodeResp preAuthCodeResp) {
    updatePreAuthCode(preAuthCodeResp.getPreAuthCode(), preAuthCodeResp.getExpiresIn());
  }

  @Override
  public void updatePreAuthCode(String preAuthCode, int expiresInSeconds) {
    this.preAuthCode = preAuthCode;
    this.preAuthCodeExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 100) * 1000L;
  }

  @Override
  public String getAuthorizerAccessToken(String appid) {
    return authorizerAccessTokenMap.get(appid);
  }

  @Override
  public boolean isAuthorizerAccessTokenExpired(String appid) {
    Long expiresIn = authorizerAccessTokenExpiresTimeMap.get(appid);
    return expiresIn == null || System.currentTimeMillis() > expiresIn;
  }

  @Override
  public void expireAuthorizerAccessToken(String appid) {
    if (authorizerAccessTokenExpiresTimeMap.get(appid) != null) {
      authorizerAccessTokenExpiresTimeMap.remove(appid);
    }

    if (authorizerAccessTokenMap.get(appid) != null) {
      authorizerAccessTokenMap.remove(appid);
    }
  }

  @Override
  public void updateAuthorizerAccessToken(String appid, String authorizerAccessToken, int expiresInSeconds) {
    authorizerAccessTokenMap.put(appid, authorizerAccessToken);
    authorizerAccessTokenExpiresTimeMap.put(appid, System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L);
  }

  @Override
  public void updateAuthorizerAccessToken(String appid, AuthorizerTokenResp authorizerTokenResp) {
    updateAuthorizerAccessToken(appid, authorizerTokenResp.getAuthorizerAccessToken(), authorizerTokenResp.getExpiresIn());
  }

  @Override
  public String getJsapiTicket(String appid) {
    return jsapiTicketMap.get(appid);
  }

  @Override
  public Lock getJsapiTicketLock() {
    return jsapiTicketLock;
  }

  @Override
  public boolean isJsapiTicketExpired(String appid) {
    Long expiresIn = jsapiTicketExpiresTimeMap.get(appid);
    return expiresIn == null || System.currentTimeMillis() > expiresIn;
  }

  @Override
  public void updateJsapiTicket(String appid, String jsapiTicket, int expiresInSeconds) {
    jsapiTicketMap.put(appid, jsapiTicket);
    jsapiTicketExpiresTimeMap.put(appid, System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L);
  }

  @Override
  public void expireJsapiTicket(String appid) {
    if (jsapiTicketMap.get(appid) != null) {
      jsapiTicketMap.remove(appid);
    }

    if (jsapiTicketExpiresTimeMap.get(appid) != null) {
      jsapiTicketExpiresTimeMap.remove(appid);
    }
  }

  @Override
  public String getCardApiTicket(String appid) {
    return cardApiTicketMap.get(appid);
  }

  @Override
  public Lock getCardApiTicketLock() {
    return cardApiTicketLock;
  }

  @Override
  public boolean isCardApiTicketExpired(String appid) {
    Long expiresIn = cardApiTicketExpiresTimeMap.get(appid);
    return expiresIn == null || System.currentTimeMillis() > expiresIn;
  }

  @Override
  public void expireCardApiTicket(String appid) {
    if (cardApiTicketMap.get(appid) != null) {
      cardApiTicketMap.remove(appid);
    }

    if (cardApiTicketExpiresTimeMap.get(appid) != null) {
      cardApiTicketExpiresTimeMap.remove(appid);
    }
  }

  @Override
  public void updateCardApiTicket(String appid, String cardApiTicket, int expiresInSeconds) {
    cardApiTicketMap.put(appid, cardApiTicket);
    cardApiTicketExpiresTimeMap.put(appid, System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L);
  }

  @Override
  public String getAppId() {
    return this.appId;
  }

  @Override
  public String getSecret() {
    return this.secret;
  }

  @Override
  public String getToken() {
    return this.token;
  }

  @Override
  public String getAesKey() {
    return this.aesKey;
  }

  @Override
  public long getExpiresTime() {
    return this.expiresTime;
  }

  @Override
  public String getHttpProxyHost() {
    return this.httpProxyHost;
  }

  @Override
  public int getHttpProxyPort() {
    return this.httpProxyPort;
  }

  @Override
  public String getHttpProxyUsername() {
    return this.httpProxyUsername;
  }

  @Override
  public String getHttpProxyPassword() {
    return this.httpProxyPassword;
  }

  @Override
  public File getTmpDirFile() {
    return this.tmpDirFile;
  }

  @Override
  public ApacheHttpClientBuilder getApacheHttpClientBuilder() {
    return this.apacheHttpClientBuilder;
  }

  @Override
  public boolean autoRefreshToken() {
    return true;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void setAesKey(String aesKey) {
    this.aesKey = aesKey;
  }

  public void setExpiresTime(long expiresTime) {
    this.expiresTime = expiresTime;
  }

  public void setHttpProxyHost(String httpProxyHost) {
    this.httpProxyHost = httpProxyHost;
  }

  public void setHttpProxyPort(int httpProxyPort) {
    this.httpProxyPort = httpProxyPort;
  }

  public void setHttpProxyUsername(String httpProxyUsername) {
    this.httpProxyUsername = httpProxyUsername;
  }

  public void setHttpProxyPassword(String httpProxyPassword) {
    this.httpProxyPassword = httpProxyPassword;
  }

  public ConcurrentHashMap<String, String> getAuthorizerAccessTokenMap() {
    return authorizerAccessTokenMap;
  }

  public void setAuthorizerAccessTokenMap(ConcurrentHashMap<String, String> authorizerAccessTokenMap) {
    this.authorizerAccessTokenMap = authorizerAccessTokenMap;
  }

  public ConcurrentHashMap<String, Long> getAuthorizerAccessTokenExpiresTimeMap() {
    return authorizerAccessTokenExpiresTimeMap;
  }

  public void setAuthorizerAccessTokenExpiresTimeMap(ConcurrentHashMap<String, Long> authorizerAccessTokenExpiresTimeMap) {
    this.authorizerAccessTokenExpiresTimeMap = authorizerAccessTokenExpiresTimeMap;
  }

  public void setComponentAccessTokenLock(Lock componentAccessTokenLock) {
    this.componentAccessTokenLock = componentAccessTokenLock;
  }

  @Override
  public Lock getAuthorizerAccessTokenLock() {
    return authorizerAccessTokenLock;
  }

  public void setAuthorizerAccessTokenLock(Lock authorizerAccessTokenLock) {
    this.authorizerAccessTokenLock = authorizerAccessTokenLock;
  }

  public void setTmpDirFile(File tmpDirFile) {
    this.tmpDirFile = tmpDirFile;
  }

  public void setApacheHttpClientBuilder(ApacheHttpClientBuilder apacheHttpClientBuilder) {
    this.apacheHttpClientBuilder = apacheHttpClientBuilder;
  }
}

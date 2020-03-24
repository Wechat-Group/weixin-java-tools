package me.chanjar.weixin.cp.config.impl;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.File;
import java.io.Serializable;

/**
 * 基于内存的微信配置provider，在实际生产环境中应该将这些配置持久化.
 *
 * @author someone
 */
public class WxCpTpDefaultConfigImpl implements WxCpTpConfigStorage, Serializable {
  private static final long serialVersionUID = 6678780920621872824L;

  private volatile String corpId;
  private volatile String corpSecret;

  private volatile String suiteId;
  private volatile String suiteSecret;

  private volatile String token;
  private volatile String suiteAccessToken;
  private volatile String aesKey;
  private volatile long expiresTime;

  private volatile String oauth2redirectUri;

  private volatile String httpProxyHost;
  private volatile int httpProxyPort;
  private volatile String httpProxyUsername;
  private volatile String httpProxyPassword;

  private volatile String suiteTicket;
  private volatile long suiteTicketExpiresTime;

  private volatile File tmpDirFile;

  private volatile ApacheHttpClientBuilder apacheHttpClientBuilder;

  private volatile String baseApiUrl;

  @Override
  public void setBaseApiUrl(String baseUrl) {
    this.baseApiUrl = baseUrl;
  }

  @Override
  public String getApiUrl(String path) {
    if (baseApiUrl == null) {
      baseApiUrl = "https://qyapi.weixin.qq.com";
    }
    return baseApiUrl + path;
  }

  @Override
  public String getSuiteAccessToken() {
    return this.suiteAccessToken;
  }

  public void setSuiteAccessToken(String suiteAccessToken) {
    this.suiteAccessToken = suiteAccessToken;
  }

  @Override
  public boolean isSuiteAccessTokenExpired() {
    return System.currentTimeMillis() > this.expiresTime;
  }

  @Override
  public void expireSuiteAccessToken() {
    this.expiresTime = 0;
  }

  @Override
  public synchronized void updateSuiteAccessToken(WxAccessToken suiteAccessToken) {
	  updateSuiteAccessToken(suiteAccessToken.getAccessToken(), suiteAccessToken.getExpiresIn());
  }

  @Override
  public synchronized void updateSuiteAccessToken(String suiteAccessToken, int expiresInSeconds) {
    this.suiteAccessToken = suiteAccessToken;
    this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
  }

  @Override
  public String getCorpId() {
    return this.corpId;
  }

  public void setCorpId(String corpId) {
    this.corpId = corpId;
  }

  @Override
  public String getCorpSecret() {
    return this.corpSecret;
  }

  public void setCorpSecret(String corpSecret) {
    this.corpSecret = corpSecret;
  }

  @Override
  public String getSuiteTicket() {
    return this.suiteTicket;
  }

  public void setSuiteTicket(String suiteTicket) {
    this.suiteTicket = suiteTicket;
  }

  public long getSuiteTicketExpiresTime() {
    return this.suiteTicketExpiresTime;
  }

  public void setSuiteTicketExpiresTime(long suiteTicketExpiresTime) {
    this.suiteTicketExpiresTime = suiteTicketExpiresTime;
  }

  @Override
  public boolean isSuiteTicketExpired() {
    return System.currentTimeMillis() > this.suiteTicketExpiresTime;
  }

  @Override
  public synchronized void updateSuiteTicket(String suiteTicket, int expiresInSeconds) {
    this.suiteTicket = suiteTicket;
    // 预留200秒的时间
    this.suiteTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
  }

  @Override
  public void expireSuiteTicket() {
    this.suiteTicketExpiresTime = 0;
  }

  @Override
  public String getSuiteId() {
    return this.suiteId;
  }

  public void setSuiteId(String corpId) {
    this.suiteId = corpId;
  }

  @Override
  public String getSuiteSecret() {
    return this.suiteSecret;
  }

  public void setSuiteSecret(String corpSecret) {
    this.suiteSecret = corpSecret;
  }

  @Override
  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public long getExpiresTime() {
    return this.expiresTime;
  }

  public void setExpiresTime(long expiresTime) {
    this.expiresTime = expiresTime;
  }

  @Override
  public String getAesKey() {
    return this.aesKey;
  }

  public void setAesKey(String aesKey) {
    this.aesKey = aesKey;
  }

  public void setOauth2redirectUri(String oauth2redirectUri) {
    this.oauth2redirectUri = oauth2redirectUri;
  }

  @Override
  public String getHttpProxyHost() {
    return this.httpProxyHost;
  }

  public void setHttpProxyHost(String httpProxyHost) {
    this.httpProxyHost = httpProxyHost;
  }

  @Override
  public int getHttpProxyPort() {
    return this.httpProxyPort;
  }

  public void setHttpProxyPort(int httpProxyPort) {
    this.httpProxyPort = httpProxyPort;
  }

  @Override
  public String getHttpProxyUsername() {
    return this.httpProxyUsername;
  }

  public void setHttpProxyUsername(String httpProxyUsername) {
    this.httpProxyUsername = httpProxyUsername;
  }

  @Override
  public String getHttpProxyPassword() {
    return this.httpProxyPassword;
  }

  public void setHttpProxyPassword(String httpProxyPassword) {
    this.httpProxyPassword = httpProxyPassword;
  }

  @Override
  public String toString() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  @Override
  public File getTmpDirFile() {
    return this.tmpDirFile;
  }

  public void setTmpDirFile(File tmpDirFile) {
    this.tmpDirFile = tmpDirFile;
  }

  @Override
  public ApacheHttpClientBuilder getApacheHttpClientBuilder() {
    return this.apacheHttpClientBuilder;
  }

  @Override
  public boolean autoRefreshToken() {
    return true;
  }

  public void setApacheHttpClientBuilder(ApacheHttpClientBuilder apacheHttpClientBuilder) {
    this.apacheHttpClientBuilder = apacheHttpClientBuilder;
  }
}

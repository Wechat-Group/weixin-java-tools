package me.chanjar.weixin.cp.config;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.File;

/**
 * 基于内存的微信配置provider，在实际生产环境中应该将这些配置持久化
 *
 * @author Daniel Qian
 */
public class WxCpTpInMemoryConfigStorage implements WxCpTpConfigStorage {

  protected volatile String suiteId;
  protected volatile String suiteSecret;

  protected volatile String token;
  protected volatile String suiteAccessToken;
  protected volatile String aesKey;
  protected volatile long expiresTime;

  protected volatile String oauth2redirectUri;

  protected volatile String httpProxyHost;
  protected volatile int httpProxyPort;
  protected volatile String httpProxyUsername;
  protected volatile String httpProxyPassword;

  protected volatile String suiteTicket;
  protected volatile long suiteTicketExpiresTime;


  protected volatile File tmpDirFile;

  private volatile ApacheHttpClientBuilder apacheHttpClientBuilder;

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

  public void setApacheHttpClientBuilder(ApacheHttpClientBuilder apacheHttpClientBuilder) {
    this.apacheHttpClientBuilder = apacheHttpClientBuilder;
  }
}

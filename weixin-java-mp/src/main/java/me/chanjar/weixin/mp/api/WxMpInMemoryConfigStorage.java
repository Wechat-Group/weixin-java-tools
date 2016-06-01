package me.chanjar.weixin.mp.api;

import java.io.File;

import javax.net.ssl.SSLContext;

import me.chanjar.weixin.common.bean.WxAccessToken;

/**
 * 基于内存的微信配置provider，在实际生产环境中应该将这些配置持久化
 * @author chanjarster
 *
 */
public class WxMpInMemoryConfigStorage implements WxMpConfigStorage {

  protected volatile String appId;
  protected volatile String secret;
  protected volatile String partnerId;
  protected volatile String partnerKey;
  protected volatile String token;
  protected volatile String accessToken;
  protected volatile String aesKey;
  protected volatile long expiresTime;

  protected volatile String oauth2redirectUri;

  protected volatile String http_proxy_host;
  protected volatile int http_proxy_port;
  protected volatile String http_proxy_username;
  protected volatile String http_proxy_password;

  protected volatile String jsapiTicket;
  protected volatile long jsapiTicketExpiresTime;

  /**
   * 临时文件目录
   */
  protected volatile File tmpDirFile;
  
  protected volatile SSLContext sslContext;
  
  public String getAccessToken() {
    return this.accessToken;
  }

  public boolean isAccessTokenExpired() {
    return System.currentTimeMillis() > this.expiresTime;
  }

  public synchronized void updateAccessToken(WxAccessToken accessToken) {
    updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
  }
  
  public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
    this.accessToken = accessToken;
    this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000l;
  }

  public void expireAccessToken() {
    this.expiresTime = 0;
  }

  public String getJsapiTicket() {
    return jsapiTicket;
  }

  public void setJsapiTicket(String jsapiTicket) {
    this.jsapiTicket = jsapiTicket;
  }

  public long getJsapiTicketExpiresTime() {
    return jsapiTicketExpiresTime;
  }

  public void setJsapiTicketExpiresTime(long jsapiTicketExpiresTime) {
    this.jsapiTicketExpiresTime = jsapiTicketExpiresTime;
  }

  public boolean isJsapiTicketExpired() {
    return System.currentTimeMillis() > this.jsapiTicketExpiresTime;
  }

  public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
    this.jsapiTicket = jsapiTicket;
    // 预留200秒的时间
    this.jsapiTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000l;
  }

  public void expireJsapiTicket() {
    this.jsapiTicketExpiresTime = 0;
  }

  public String getAppId() {
    return this.appId;
  }

  public String getSecret() {
    return this.secret;
  }

  public String getToken() {
    return this.token;
  }

  public long getExpiresTime() {
    return this.expiresTime;
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

  public String getAesKey() {
    return aesKey;
  }

  public void setAesKey(String aesKey) {
    this.aesKey = aesKey;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public void setExpiresTime(long expiresTime) {
    this.expiresTime = expiresTime;
  }

  @Override
  public String getOauth2redirectUri() {
    return this.oauth2redirectUri;
  }

  public void setOauth2redirectUri(String oauth2redirectUri) {
    this.oauth2redirectUri = oauth2redirectUri;
  }

  public String getHttp_proxy_host() {
    return http_proxy_host;
  }

  public void setHttp_proxy_host(String http_proxy_host) {
    this.http_proxy_host = http_proxy_host;
  }

  public int getHttp_proxy_port() {
    return http_proxy_port;
  }

  public void setHttp_proxy_port(int http_proxy_port) {
    this.http_proxy_port = http_proxy_port;
  }

  public String getHttp_proxy_username() {
    return http_proxy_username;
  }

  public void setHttp_proxy_username(String http_proxy_username) {
    this.http_proxy_username = http_proxy_username;
  }

  public String getHttp_proxy_password() {
    return http_proxy_password;
  }

  public void setHttp_proxy_password(String http_proxy_password) {
    this.http_proxy_password = http_proxy_password;
  }

  @Override
  public String toString() {
    return "WxMpInMemoryConfigStorage{" +
        "appId='" + appId + '\'' +
        ", secret='" + secret + '\'' +
        ", token='" + token + '\'' +
        ", partnerId='" + partnerId + '\'' +
        ", partnerKey='" + partnerKey + '\'' +
        ", accessToken='" + accessToken + '\'' +
        ", aesKey='" + aesKey + '\'' +
        ", expiresTime=" + expiresTime +
        ", http_proxy_host='" + http_proxy_host + '\'' +
        ", http_proxy_port=" + http_proxy_port +
        ", http_proxy_username='" + http_proxy_username + '\'' +
        ", http_proxy_password='" + http_proxy_password + '\'' +
        ", jsapiTicket='" + jsapiTicket + '\'' +
        ", jsapiTicketExpiresTime='" + jsapiTicketExpiresTime + '\'' +
        ", tmpDirFile='" + tmpDirFile + '\'' +
        '}';
  }

  @Override
  public String getPartnerId() {
      return partnerId;
  }

  public void setPartnerId(String partnerId) {
      this.partnerId = partnerId;
  }

  @Override
  public String getPartnerKey() {
      return partnerKey;
  }

  public void setPartnerKey(String partnerKey) {
      this.partnerKey = partnerKey;
  }

  @Override
  public File getTmpDirFile() {
    return this.tmpDirFile;
  }

  public void setTmpDirFile(File tmpDirFile) {
    this.tmpDirFile = tmpDirFile;
  }

  @Override
  public SSLContext getSSLContext() {
    return sslContext;
  }
  
  public void setSSLContext(SSLContext context) {
    sslContext = context;
  }

}

package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.common.util.http.ApacheHttpClientBuilder;
import redis.clients.jedis.Jedis;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于Redis的微信配置provider
 *
 * @author lly835
 */
public class WxMpInRedisConfigStorage implements WxMpConfigStorage {

  private final static String ACCESS_TOKEN_KEY = "wechat_access_token_";

  private final static String JSAPI_TICKET_KEY = "wechat_jsapi_ticket_";

  private final static String CARDAPI_TICKET_KEY = "wechat_cardapi_ticket_";

  protected volatile String appId;
  protected volatile String secret;
  protected volatile String partnerId;
  protected volatile String partnerKey;
  protected volatile String notifyURL;
  protected volatile String tradeType;
  protected volatile String token;
  protected volatile String accessToken;
  protected volatile String aesKey;
  protected volatile long expiresTime;

  protected volatile String oauth2redirectUri;

  protected volatile String httpProxyHost;
  protected volatile int httpProxyPort;
  protected volatile String httpProxyUsername;
  protected volatile String httpProxyPassword;

  protected volatile String jsapiTicket;
  protected volatile long jsapiTicketExpiresTime;

  protected volatile String cardApiTicket;
  protected volatile long cardApiTicketExpiresTime;

  protected Lock accessTokenLock = new ReentrantLock();
  protected Lock jsapiTicketLock = new ReentrantLock();
  protected Lock cardApiTicketLock = new ReentrantLock();

  /**
   * 临时文件目录
   */
  protected volatile File tmpDirFile;

  protected volatile SSLContext sslContext;

  protected volatile ApacheHttpClientBuilder apacheHttpClientBuilder;

  protected Jedis jedis;

  @Override
  public String getAccessToken() {
    return jedis.get(ACCESS_TOKEN_KEY.concat(appId));
  }

  @Override
  public Lock getAccessTokenLock() {
    return this.accessTokenLock;
  }

  @Override
  public boolean isAccessTokenExpired() {
      return getAccessToken() == null ? true : false;
  }

  @Override
  public synchronized void updateAccessToken(WxAccessToken accessToken) {
    updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
  }

  @Override
  public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
    this.accessToken = accessToken;
    this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
    jedis.set(ACCESS_TOKEN_KEY.concat(appId), accessToken);
    jedis.expire(ACCESS_TOKEN_KEY.concat(appId), expiresInSeconds - 200);
  }

  @Override
  public void expireAccessToken() {
    this.expiresTime = 0;
  }

  @Override
  public String getJsapiTicket() {
    return this.jsapiTicket;
  }

  public void setJsapiTicket(String jsapiTicket) {
    this.jsapiTicket = jsapiTicket;
  }

  @Override
  public Lock getJsapiTicketLock() {
    return this.jsapiTicketLock;
  }

  @Override
  public boolean isJsapiTicketExpired() {
    return getJsapiTicket() == null ? true : false;
  }

  @Override
  public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
    this.jsapiTicket = jsapiTicket;
    // 预留200秒的时间
    this.jsapiTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
    jedis.set(JSAPI_TICKET_KEY.concat(appId), accessToken);
    jedis.expire(JSAPI_TICKET_KEY.concat(appId), expiresInSeconds - 200);
  }

  @Override
  public void expireJsapiTicket() {
    this.jsapiTicketExpiresTime = 0;
  }

  /**
   * 卡券api_ticket
   */
  @Override
  public String getCardApiTicket() {
    return this.cardApiTicket;
  }

  @Override
  public Lock getCardApiTicketLock() {
    return this.cardApiTicketLock;
  }

  @Override
  public boolean isCardApiTicketExpired() {
    return System.currentTimeMillis() > this.cardApiTicketExpiresTime;
  }

  @Override
  public synchronized void updateCardApiTicket(String cardApiTicket, int expiresInSeconds) {
    this.cardApiTicket = cardApiTicket;
    // 预留200秒的时间
    this.cardApiTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
    jedis.set(CARDAPI_TICKET_KEY.concat(appId), accessToken);
    jedis.expire(CARDAPI_TICKET_KEY.concat(appId), expiresInSeconds - 200);
  }

  @Override
  public void expireCardApiTicket() {
    this.cardApiTicketExpiresTime = 0;
  }

  @Override
  public String getAppId() {
    return this.appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  @Override
  public String getSecret() {
    return this.secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
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

  @Override
  public String getOauth2redirectUri() {
    return this.oauth2redirectUri;
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
    return ToStringUtils.toSimpleString(this);
  }

  public void setPartnerId(String partnerId) {
    this.partnerId = partnerId;
  }

  public void setPartnerKey(String partnerKey) {
    this.partnerKey = partnerKey;
  }


  public String getNotifyURL() {
    return notifyURL;
  }

  public void setNotifyURL(String notifyURL) {
    this.notifyURL = notifyURL;
  }

  public String getTradeType() {
    return tradeType;
  }

  public void setTradeType(String tradeType) {
    this.tradeType = tradeType;
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

  @Override
  public boolean autoRefreshToken() {
    return true;
  }

  public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }
}

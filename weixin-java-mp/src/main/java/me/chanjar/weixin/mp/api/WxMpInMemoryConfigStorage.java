package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.common.util.http.ApacheHttpClientBuilder;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于内存的微信配置provider，在实际生产环境中应该将这些配置持久化
 *
 * @author chanjarster
 */
public class WxMpInMemoryConfigStorage implements WxMpConfigStorage {

  protected volatile String appId;
  protected volatile String subAppId;
  protected volatile String subMchId;
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

  @Override
  public String getAccessToken() {
    return this.accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  @Override
  public Lock getAccessTokenLock() {
    return this.accessTokenLock;
  }

  @Override
  public boolean isAccessTokenExpired() {
    return System.currentTimeMillis() > this.expiresTime;
  }

  @Override
  public synchronized void updateAccessToken(WxAccessToken accessToken) {
    updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
  }

  @Override
  public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
    this.accessToken = accessToken;
    this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
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

  public long getJsapiTicketExpiresTime() {
    return this.jsapiTicketExpiresTime;
  }

  public void setJsapiTicketExpiresTime(long jsapiTicketExpiresTime) {
    this.jsapiTicketExpiresTime = jsapiTicketExpiresTime;
  }

  @Override
  public boolean isJsapiTicketExpired() {
    return System.currentTimeMillis() > this.jsapiTicketExpiresTime;
  }

  @Override
  public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
    this.jsapiTicket = jsapiTicket;
    // 预留200秒的时间
    this.jsapiTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
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

  @Override
  public String getPartnerId() {
    return this.partnerId;
  }

  public void setPartnerId(String partnerId) {
    this.partnerId = partnerId;
  }

  @Override
  public String getPartnerKey() {
    return this.partnerKey;
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
  public SSLContext getSslContext() {
    return this.sslContext;
  }

  @Override
  public void setSslContext(SSLContext context) {
    this.sslContext = context;
  }

  @Override
  public void setSslContextFilePath(String filePath) {
    if (null == partnerId) {
      throw new IllegalArgumentException("请设置partnerId的值");
    }

    File file = new File(filePath);
    if (!file.exists()) {
      throw new RuntimeException("证书文件：【" + file.getPath() + "】不存在！");
    }

    try {
      FileInputStream inputStream = new FileInputStream(file);
      KeyStore keystore = KeyStore.getInstance("PKCS12");
      char[] partnerId2charArray = partnerId.toCharArray();
      keystore.load(inputStream, partnerId2charArray);
      this.sslContext = SSLContexts.custom().loadKeyMaterial(keystore, partnerId2charArray).build();
    } catch (Exception e) {
      throw new RuntimeException("证书文件有问题，请核实！", e);
    }
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

  @Override
  public boolean useSandboxForWxPay() {
    return false;
  }

  @Override
  public String getSubAppId() {
    return subAppId;
  }

  public void setSubAppId(String subAppId) {
    this.subAppId = subAppId;
  }

  @Override
  public String getSubMchId() {
    return subMchId;
  }

  public void setSubMchId(String subMchId) {
    this.subMchId = subMchId;
  }
}

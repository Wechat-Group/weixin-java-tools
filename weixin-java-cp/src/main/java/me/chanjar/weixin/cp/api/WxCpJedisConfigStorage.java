package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.ApacheHttpClientBuilder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.File;

/**
 * Jedis client implementor for wechat config storage
 *
 * @author gaigeshen
 */
public class WxCpJedisConfigStorage implements WxCpConfigStorage {

  /* Redis keys here */
  private static final String ACCESS_TOKEN_KEY = "WX_CP_ACCESS_TOKEN";
  private static final String ACCESS_TOKEN_EXPIRES_TIME_KEY = "WX_CP_ACCESS_TOKEN_EXPIRES_TIME";
  private static final String JS_API_TICKET_KEY = "WX_CP_JS_API_TICKET";
  private static final String JS_API_TICKET_EXPIRES_TIME_KEY = "WX_CP_JS_API_TICKET_EXPIRES_TIME";
  /* Redis clients pool */
  private final JedisPool jedisPool;
  private volatile String corpId;
  private volatile String corpSecret;
  private volatile String token;
  private volatile String aesKey;
  private volatile Integer agentId;
  private volatile String oauth2redirectUri;
  private volatile String httpProxyHost;
  private volatile int httpProxyPort;
  private volatile String httpProxyUsername;
  private volatile String httpProxyPassword;
  private volatile File tmpDirFile;
  private volatile ApacheHttpClientBuilder apacheHttpClientBuilder;

  public WxCpJedisConfigStorage(String host, int port) {
    this.jedisPool = new JedisPool(host, port);
  }

  /**
   * This method will be destroy jedis pool
   */
  public void destroy() {
    this.jedisPool.destroy();
  }

  @Override
  public String getAccessToken() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(ACCESS_TOKEN_KEY);
    }
  }

  @Override
  public boolean isAccessTokenExpired() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      String expiresTimeStr = jedis.get(ACCESS_TOKEN_EXPIRES_TIME_KEY);

      if (expiresTimeStr != null) {
        Long expiresTime = Long.parseLong(expiresTimeStr);
        return System.currentTimeMillis() > expiresTime;
      }

      return true;

    }
  }

  @Override
  public void expireAccessToken() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.set(ACCESS_TOKEN_EXPIRES_TIME_KEY, "0");
    }
  }

  @Override
  public synchronized void updateAccessToken(WxAccessToken accessToken) {
    this.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
  }

  @Override
  public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.set(ACCESS_TOKEN_KEY, accessToken);

      jedis.set(ACCESS_TOKEN_EXPIRES_TIME_KEY,
        (System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L) + "");
    }
  }

  @Override
  public String getJsapiTicket() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(JS_API_TICKET_KEY);
    }
  }

  @Override
  public boolean isJsapiTicketExpired() {

    try (Jedis jedis = this.jedisPool.getResource()) {
      String expiresTimeStr = jedis.get(JS_API_TICKET_EXPIRES_TIME_KEY);

      if (expiresTimeStr != null) {
        Long expiresTime = Long.parseLong(expiresTimeStr);
        return System.currentTimeMillis() > expiresTime;
      }

      return true;

    }
  }

  @Override
  public void expireJsapiTicket() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.set(JS_API_TICKET_EXPIRES_TIME_KEY, "0");
    }
  }

  @Override
  public synchronized void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {

    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.set(JS_API_TICKET_KEY, jsapiTicket);

      jedis.set(JS_API_TICKET_EXPIRES_TIME_KEY,
        (System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L + ""));
    }

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
  public Integer getAgentId() {
    return this.agentId;
  }

  public void setAgentId(Integer agentId) {
    this.agentId = agentId;
  }

  @Override
  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public String getAesKey() {
    return this.aesKey;
  }

  public void setAesKey(String aesKey) {
    this.aesKey = aesKey;
  }

  @Override
  public long getExpiresTime() {
    try (Jedis jedis = this.jedisPool.getResource()) {
      String expiresTimeStr = jedis.get(ACCESS_TOKEN_EXPIRES_TIME_KEY);

      if (expiresTimeStr != null) {
        Long expiresTime = Long.parseLong(expiresTimeStr);
        return expiresTime;
      }

      return 0L;

    }
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

  // ============================ Setters below

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

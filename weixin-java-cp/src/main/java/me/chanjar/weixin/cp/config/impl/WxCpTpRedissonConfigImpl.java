package me.chanjar.weixin.cp.config.impl;


import lombok.Builder;
import lombok.NonNull;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.redis.WxRedisOps;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.cp.config.WxCpTpConfigStorage;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.File;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 企业微信各种固定、授权配置的Redisson存储实现
 */
@Builder
public class WxCpTpRedissonConfigImpl implements WxCpTpConfigStorage, Serializable {

  @NonNull
  private final WxRedisOps wxRedisOps;

  //redis里面key的统一前缀
  private final String keyPrefix = "";

  private final String suiteAccessTokenKey = ":suiteAccessTokenKey:";

  private final String suiteTicketKey = ":suiteTicketKey:";

  private final String accessTokenKey = ":accessTokenKey:";

  private final String authCorpJsApiTicketKey = ":authCorpJsApiTicketKey:";

  private final String authSuiteJsApiTicketKey = ":authSuiteJsApiTicketKey:";

  private final String providerTokenKey = ":providerTokenKey:";

  private volatile String baseApiUrl;
  private volatile String httpProxyHost;
  private volatile int httpProxyPort;
  private volatile String httpProxyUsername;
  private volatile String httpProxyPassword;
  private volatile ApacheHttpClientBuilder apacheHttpClientBuilder;
  private volatile File tmpDirFile;

  /**
   * 第三方应用的其他配置，来自于企微配置
   */
  private volatile String suiteId;
  private volatile String suiteSecret;
  // 第三方应用的token，用来检查应用的签名
  private volatile String token;
  //第三方应用的EncodingAESKey，用来检查签名
  private volatile String aesKey;

  /**
   * 企微服务商企业ID & 企业secret，来自于企微配置
   */
  private volatile String corpId;
  private volatile String corpSecret;

  /**
   * 服务商secret
   */
  private volatile String providerSecret;

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


  /**
   * 第三方应用的suite access token相关
   */
  @Override
  public String getSuiteAccessToken() {
    return wxRedisOps.getValue(keyWithPrefix(suiteAccessTokenKey));
  }

  @Override
  public boolean isSuiteAccessTokenExpired() {
    //remain time to live in seconds, or key not exist
    return wxRedisOps.getExpire(keyWithPrefix(suiteAccessTokenKey)) == 0L || wxRedisOps.getExpire(keyWithPrefix(suiteAccessTokenKey)) == -2;
  }

  @Override
  public void expireSuiteAccessToken() {
    wxRedisOps.expire(keyWithPrefix(suiteAccessTokenKey), 0, TimeUnit.SECONDS);
  }

  @Override
  public void updateSuiteAccessToken(WxAccessToken suiteAccessToken) {
    updateSuiteAccessToken(suiteAccessToken.getAccessToken(), suiteAccessToken.getExpiresIn());
  }

  @Override
  public void updateSuiteAccessToken(String suiteAccessToken, int expiresInSeconds) {
    wxRedisOps.setValue(keyWithPrefix(suiteAccessTokenKey), suiteAccessToken, expiresInSeconds, TimeUnit.SECONDS);
  }

  /**
   * 第三方应用的suite ticket相关
   */
  @Override
  public String getSuiteTicket() {
    return wxRedisOps.getValue(keyWithPrefix(suiteTicketKey));
  }

  @Override
  public boolean isSuiteTicketExpired() {
    //remain time to live in seconds, or key not exist
    return wxRedisOps.getExpire(keyWithPrefix(suiteTicketKey)) == 0L || wxRedisOps.getExpire(keyWithPrefix(suiteTicketKey)) == -2;
  }

  @Override
  public void expireSuiteTicket() {
    wxRedisOps.expire(keyWithPrefix(suiteTicketKey), 0, TimeUnit.SECONDS);
  }

  @Override
  public void updateSuiteTicket(String suiteTicket, int expiresInSeconds) {
    wxRedisOps.setValue(keyWithPrefix(suiteTicketKey), suiteTicket, expiresInSeconds, TimeUnit.SECONDS);
  }

  /**
   * 第三方应用的其他配置，来自于企微配置
   */
  @Override
  public String getSuiteId() {
    return suiteId;
  }

  @Override
  public String getSuiteSecret() {
    return suiteSecret;
  }

  // 第三方应用的token，用来检查应用的签名
  @Override
  public String getToken() {
    return token;
  }

  //第三方应用的EncodingAESKey，用来检查签名
  @Override
  public String getAesKey() {
    return aesKey;
  }


  /**
   * 企微服务商企业ID & 企业secret, 来自于企微配置
   */
  @Override
  public String getCorpId() {
    return corpId;
  }

  @Override
  public String getCorpSecret() {
    return corpSecret;
  }

  @Override
  public String getProviderSecret() {
    return providerSecret;
  }

  /**
   * 授权企业的access token相关
   */
  @Override
  public String getAccessToken(String authCorpId) {
    return wxRedisOps.getValue(keyWithPrefix(authCorpId) + accessTokenKey);
  }

  @Override
  public boolean isAccessTokenExpired(String authCorpId) {
    //没有设置或者TTL为0，都是过期
    return wxRedisOps.getExpire(keyWithPrefix(authCorpId) + accessTokenKey) == 0L
      || wxRedisOps.getExpire(keyWithPrefix(authCorpId) + accessTokenKey) == -2;
  }

  @Override
  public void updateAccessToken(String authCorpId, String accessToken, int expiredInSeconds) {
    wxRedisOps.setValue(keyWithPrefix(authCorpId) + accessTokenKey, accessToken, expiredInSeconds, TimeUnit.SECONDS);
  }


  /**
   * 授权企业的js api ticket相关
   */
  @Override
  public String getAuthCorpJsApiTicket(String authCorpId) {
    return wxRedisOps.getValue(keyWithPrefix(authCorpId) + authCorpJsApiTicketKey);
  }

  @Override
  public boolean isAuthCorpJsApiTicketExpired(String authCorpId) {
    //没有设置或TTL为0,都是过期
    return wxRedisOps.getExpire(keyWithPrefix(authCorpId) + authCorpJsApiTicketKey) == 0L
      || wxRedisOps.getExpire(keyWithPrefix(authCorpId) + authCorpJsApiTicketKey) == -2;
  }

  @Override
  public void updateAuthCorpJsApiTicket(String authCorpId, String jsApiTicket, int expiredInSeconds) {
    wxRedisOps.setValue(keyWithPrefix(authCorpId) + authCorpJsApiTicketKey, jsApiTicket, expiredInSeconds,
      TimeUnit.SECONDS);
  }


  /**
   * 授权企业的第三方应用js api ticket相关
   */
  @Override
  public String getAuthSuiteJsApiTicket(String authCorpId) {
    return wxRedisOps.getValue(keyWithPrefix(authCorpId) + authSuiteJsApiTicketKey);
  }

  @Override
  public boolean isAuthSuiteJsApiTicketExpired(String authCorpId) {
    //没有设置或者TTL为0，都是过期
    return wxRedisOps.getExpire(keyWithPrefix(authCorpId) + authSuiteJsApiTicketKey) == 0L
      || wxRedisOps.getExpire(keyWithPrefix(authCorpId) + authSuiteJsApiTicketKey) == -2;
  }

  @Override
  public void updateAuthSuiteJsApiTicket(String authCorpId, String jsApiTicket, int expiredInSeconds) {
    wxRedisOps.setValue(keyWithPrefix(authCorpId) + authSuiteJsApiTicketKey, jsApiTicket, expiredInSeconds,
      TimeUnit.SECONDS);
  }

  @Override
  public boolean isProviderTokenExpired() {
    //remain time to live in seconds, or key not exist
    return wxRedisOps.getExpire(keyWithPrefix(providerTokenKey)) == 0L || wxRedisOps.getExpire(keyWithPrefix(providerTokenKey)) == -2;
  }

  @Override
  public void updateProviderToken(String providerToken, int expiredInSeconds) {
    wxRedisOps.setValue(keyWithPrefix(providerTokenKey), providerToken, expiredInSeconds, TimeUnit.SECONDS);
  }

  @Override
  public String getProviderToken() {
    return wxRedisOps.getValue(keyWithPrefix(providerTokenKey));
  }


  /**
   * 网络代理相关
   */
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
    return tmpDirFile;
  }

  @Override
  public ApacheHttpClientBuilder getApacheHttpClientBuilder() {
    return this.apacheHttpClientBuilder;
  }

  @Override
  public boolean autoRefreshToken() {
    return false;
  }

  @Override
  public String toString() {
    //TODO:
    return WxCpGsonBuilder.create().toJson(this);
  }

  private String keyWithPrefix(String key) {
    return keyPrefix + key;
  }
}

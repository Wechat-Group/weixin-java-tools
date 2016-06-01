package me.chanjar.weixin.mp.bean.result;

import java.io.Serializable;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

public class WxMpOAuth2AccessToken implements Serializable {

  private String accessToken;

  private int expiresIn = -1;

  private String refreshToken;

  private String openId;

  private String scope;

  private String unionId;

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public int getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }

  public String getUnionId() {
    return unionId;
  }

  public void setUnionId(String unionId) {
    this.unionId = unionId;
  }

  public static WxMpOAuth2AccessToken fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpOAuth2AccessToken.class);
  }

  @Override
  public String toString() {
    return "WxMpOAuth2AccessToken{" +
        "accessToken='" + accessToken + '\'' +
        ", expiresTime=" + expiresIn +
        ", refreshToken='" + refreshToken + '\'' +
        ", openId='" + openId + '\'' +
        ", scope='" + scope + '\'' +
        ", unionId='" + unionId + '\'' +
        '}';
  }
}

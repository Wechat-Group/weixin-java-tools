package me.chanjar.weixin.open.bean.resp;


import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;

/**
 * Created by JasonY on 17/10/24.
 */
public class AuthorizerTokenResp implements Serializable {
  private static final long serialVersionUID = 5239757100435628837L;
  private String authorizerAccessToken;
  private String authorizerRefreshToken;
  private int expiresIn = -1;

  public static AuthorizerTokenResp fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, AuthorizerTokenResp.class);
  }


  public String getAuthorizerAccessToken() {
    return authorizerAccessToken;
  }

  public void setAuthorizerAccessToken(String authorizerAccessToken) {
    this.authorizerAccessToken = authorizerAccessToken;
  }

  public String getAuthorizerRefreshToken() {
    return authorizerRefreshToken;
  }

  public void setAuthorizerRefreshToken(String authorizerRefreshToken) {
    this.authorizerRefreshToken = authorizerRefreshToken;
  }

  public int getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }

}

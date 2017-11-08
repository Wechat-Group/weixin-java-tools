package me.chanjar.weixin.open.bean;

import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenComponentAccessToken implements Serializable{

  private String componentAccessToken;

  private int expiresIn = -1;

  public static WxOpenComponentAccessToken fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, WxOpenComponentAccessToken.class);
  }

  public String getComponentAccessToken() {
    return componentAccessToken;
  }

  public void setComponentAccessToken(String componentAccessToken) {
    this.componentAccessToken = componentAccessToken;
  }

  public int getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }
}

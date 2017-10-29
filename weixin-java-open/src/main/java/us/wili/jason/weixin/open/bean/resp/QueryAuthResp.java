package us.wili.jason.weixin.open.bean.resp;

import us.wili.jason.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by JasonY on 17/10/26.
 */
public class QueryAuthResp implements Serializable {
  private String authorizerAppid;
  private String authorizerAccessToken;
  private String authorizerRefreshToken;
  private int expiresIn = -1;
  private List<Integer> funcInfoIds;

  public static QueryAuthResp fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, QueryAuthResp.class);
  }

  public String getAuthorizerAppid() {
    return authorizerAppid;
  }

  public void setAuthorizerAppid(String authorizerAppid) {
    this.authorizerAppid = authorizerAppid;
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

  public List<Integer> getFuncInfoIds() {
    return funcInfoIds;
  }

  public void setFuncInfoIds(List<Integer> funcInfoIds) {
    this.funcInfoIds = funcInfoIds;
  }
}

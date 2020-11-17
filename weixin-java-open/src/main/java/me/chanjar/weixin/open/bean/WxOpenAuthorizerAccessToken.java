package me.chanjar.weixin.open.bean;

import lombok.Data;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class WxOpenAuthorizerAccessToken implements Serializable {
  private static final long serialVersionUID = -4069745419280727420L;

  private String authorizerAccessToken;

  private String authorizerRefreshToken;

  private int expiresIn = -1;

  public static WxOpenAuthorizerAccessToken fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, WxOpenAuthorizerAccessToken.class);
  }
}

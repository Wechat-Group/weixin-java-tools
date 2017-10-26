package us.wili.jason.weixin.open.bean.req;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * Created by JasonY on 17/10/25.
 */
public class AuthorizerTokenReq implements Serializable {
  private static final long serialVersionUID = 8252961589572773483L;

  @SerializedName("component_appid")
  private String componentAppid;

  @SerializedName("authorizer_appid")
  private String authorizerAppid;

  @SerializedName("authorizer_refresh_token")
  private String authorizerRefreshToken;

  public static Builder builder() {
    return new Builder();
  }

  public String toJson() {
    return WxGsonBuilder.INSTANCE.create().toJson(this);
  }

  public static class Builder {
    private String componentAppid;
    private String authorizerAppid;
    private String authorizerRefreshToken;

    public Builder componentAppid(String componentAppid) {
      this.componentAppid = componentAppid;
      return this;
    }

    public Builder authorizerAppid(String authorizerAppid) {
      this.authorizerAppid = authorizerAppid;
      return this;
    }

    public Builder authorizerRefreshToken(String authorizerRefreshToken) {
      this.authorizerRefreshToken = authorizerRefreshToken;
      return this;
    }

    public Builder from(AuthorizerTokenReq origin) {
      this.componentAppid(origin.componentAppid);
      this.authorizerAppid(origin.authorizerAppid);
      this.authorizerRefreshToken(origin.authorizerRefreshToken);
      return this;
    }

    public AuthorizerTokenReq build() {
      AuthorizerTokenReq request = new AuthorizerTokenReq();
      request.componentAppid = this.componentAppid;
      request.authorizerAppid = this.authorizerAppid;
      request.authorizerRefreshToken= this.authorizerRefreshToken;

      return request;
    }
  }
}

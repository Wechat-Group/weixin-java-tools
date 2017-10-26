package us.wili.jason.weixin.open.bean.req;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * Created by JasonY on 17/10/25.
 */
public class ComponentAccessTokenReq implements Serializable {
  private static final long serialVersionUID = 8252961589572773483L;

  @SerializedName("component_appid")
  private String componentAppid;

  @SerializedName("component_appsecret")
  private String componentAppsecret;

  @SerializedName("component_verify_ticket")
  private String componentVerifyTicket;

  public static Builder builder() {
    return new Builder();
  }

  public String toJson() {
    return WxGsonBuilder.INSTANCE.create().toJson(this);
  }

  public static class Builder {
    private String componentAppid;
    private String componentAppsecret;
    private String componentVerifyTicket;

    public Builder componentAppid(String componentAppid) {
      this.componentAppid = componentAppid;
      return this;
    }

    public Builder componentAppsecret(String componentAppsecret) {
      this.componentAppsecret = componentAppsecret;
      return this;
    }

    public Builder componentVerifyTicket(String componentVerifyTicket) {
      this.componentVerifyTicket = componentVerifyTicket;
      return this;
    }

    public Builder from(ComponentAccessTokenReq origin) {
      this.componentAppid(origin.componentAppid);
      this.componentAppsecret(origin.componentAppsecret);
      this.componentVerifyTicket(origin.componentVerifyTicket);
      return this;
    }

    public ComponentAccessTokenReq build() {
      ComponentAccessTokenReq request = new ComponentAccessTokenReq();
      request.componentAppid = this.componentAppid;
      request.componentAppsecret = this.componentAppsecret;
      request.componentVerifyTicket = this.componentVerifyTicket;

      return request;
    }
  }
}

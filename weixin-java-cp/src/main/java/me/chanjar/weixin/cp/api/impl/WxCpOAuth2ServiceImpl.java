package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.URIUtil;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.cp.api.WxCpOAuth2Service;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpOauth2UserInfo;
import me.chanjar.weixin.cp.bean.WxCpUserDetail;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import static me.chanjar.weixin.common.api.WxConsts.OAuth2Scope.SNSAPI_PRIVATEINFO;
import static me.chanjar.weixin.common.api.WxConsts.OAuth2Scope.SNSAPI_USERINFO;

/**
 * <pre>
 * oauth2相关接口实现类.
 * Created by Binary Wang on 2017-6-25.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@AllArgsConstructor
public class WxCpOAuth2ServiceImpl implements WxCpOAuth2Service {
  private final WxCpService mainService;

  @Override
  public String buildAuthorizationUrl(String state) {
    return this.buildAuthorizationUrl(
      this.mainService.getWxCpConfigStorage().getOauth2redirectUri(),
      state
    );
  }

  @Override
  public String buildAuthorizationUrl(String redirectUri, String state) {
    return this.buildAuthorizationUrl(redirectUri, state, WxConsts.OAuth2Scope.SNSAPI_BASE);
  }

  @Override
  public String buildAuthorizationUrl(String redirectUri, String state, String scope) {
    StringBuilder url = new StringBuilder(WxCpOAuth2Service.URL_OAUTH_2_AUTHORIZE);
    url.append("?appid=").append(this.mainService.getWxCpConfigStorage().getCorpId());
    url.append("&redirect_uri=").append(URIUtil.encodeURIComponent(redirectUri));
    url.append("&response_type=code");
    url.append("&scope=").append(scope);

    if (SNSAPI_PRIVATEINFO.equals(scope) || SNSAPI_USERINFO.equals(scope)) {
      url.append("&agentid=").append(this.mainService.getWxCpConfigStorage().getAgentId());
    }

    if (state != null) {
      url.append("&state=").append(state);
    }

    url.append("#wechat_redirect");
    return url.toString();
  }

  @Override
  public WxCpOauth2UserInfo getUserInfo(String code) throws WxErrorException {
    return this.getUserInfo(this.mainService.getWxCpConfigStorage().getAgentId(), code);
  }

  @Override
  public WxCpOauth2UserInfo getUserInfo(Integer agentId, String code) throws WxErrorException {
    String responseText = this.mainService.get(String.format(this.mainService.getWxCpConfigStorage().getApiUrl(WxCpOAuth2Service.URL_GET_USER_INFO), code, agentId), null);
    JsonElement je = new JsonParser().parse(responseText);
    JsonObject jo = je.getAsJsonObject();

    return WxCpOauth2UserInfo.builder()
      .userId(GsonHelper.getString(jo, "UserId"))
      .deviceId(GsonHelper.getString(jo, "DeviceId"))
      .openId(GsonHelper.getString(jo, "OpenId"))
      .userTicket(GsonHelper.getString(jo, "user_ticket"))
      .expiresIn(GsonHelper.getString(jo, "expires_in"))
      .build();
  }

  @Override
  public WxCpUserDetail getUserDetail(String userTicket) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("user_ticket", userTicket);
    String responseText = this.mainService.post(this.mainService.getWxCpConfigStorage().getApiUrl(WxCpOAuth2Service.URL_GET_USER_DETAIL), param.toString());
    return WxCpGsonBuilder.create().fromJson(responseText, WxCpUserDetail.class);
  }
}

package me.chanjar.weixin.cp.tp.service.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.util.http.URIUtil;
import me.chanjar.weixin.cp.tp.service.WxCpTpOAuth2Service;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;

import static me.chanjar.weixin.common.api.WxConsts.OAuth2Scope.SNSAPI_BASE;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.OAuth2.URL_OAUTH2_AUTHORIZE;

@RequiredArgsConstructor
public class WxCpTpOAuth2ServiceImpl implements WxCpTpOAuth2Service {

  private final WxCpTpService mainService;


  @Override
  public String buildAuthorizeUrl(String redirectUri, String state) {
    return this.buildAuthorizeUrl(redirectUri, state, SNSAPI_BASE);
  }

  @Override
  public String buildAuthorizeUrl(String redirectUri, String state, String scope) {
    StringBuilder url = new StringBuilder(URL_OAUTH2_AUTHORIZE);
    url.append("?appid=").append(this.mainService.getWxCpTpConfigStorage().getSuiteId());
    url.append("&redirect_uri=").append(URIUtil.encodeURIComponent(redirectUri));
    url.append("&response_type=code");
    url.append("&scope=").append(scope);
    if (state != null) {
      url.append("&state=").append(state);
    }
    url.append("#wechat_redirect");
    return url.toString();
  }
}

package me.chanjar.weixin.open.api.impl;

import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.service.WxOAuth2Service;
import me.chanjar.weixin.common.util.http.SimpleGetRequestExecutor;
import me.chanjar.weixin.common.util.http.URIUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.OAuth2.*;
import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Other.QRCONNECT_URL;

/**
 * oauth2接口实现.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-10-19
 */
@AllArgsConstructor
public class WxOpenOAuth2ServiceImpl extends WxOpenServiceImpl implements WxOAuth2Service {
  private final String appId;
  private final String appSecret;

  @Override
  public String buildAuthorizationUrl(String redirectUri, String scope, String state) {
    return String.format(QRCONNECT_URL.getUrl(null),
      this.appId, URIUtil.encodeURIComponent(redirectUri), scope, StringUtils.trimToEmpty(state));
  }

  private WxOAuth2AccessToken getOAuth2AccessToken(String url) throws WxErrorException {
    return WxOAuth2AccessToken.fromJson(this.get(url, null));
  }

  @Override
  public WxOAuth2AccessToken getAccessToken(String code) throws WxErrorException {
    return this.getAccessToken(this.appId, this.appSecret, code);
  }

  @Override
  public WxOAuth2AccessToken getAccessToken(String appId, String appSecret, String code) throws WxErrorException {
    return this.getOAuth2AccessToken(String.format(OAUTH2_ACCESS_TOKEN_URL.getUrl(null), appId, appSecret, code));
  }

  @Override
  public WxOAuth2AccessToken refreshAccessToken(String refreshToken) throws WxErrorException {
    String url = String.format(OAUTH2_REFRESH_TOKEN_URL.getUrl(null), this.appId, refreshToken);
    return this.getOAuth2AccessToken(url);
  }

  @Override
  public WxOAuth2UserInfo getUserInfo(WxOAuth2AccessToken token, String lang) throws WxErrorException {
    if (lang == null) {
      lang = "zh_CN";
    }

    String url = String.format(OAUTH2_USERINFO_URL.getUrl(null), token.getAccessToken(), token.getOpenId(), lang);

    return WxOAuth2UserInfo.fromJson(this.get(url, null));
  }

  @Override
  public boolean validateAccessToken(WxOAuth2AccessToken token) {
    String url = String.format(OAUTH2_VALIDATE_TOKEN_URL.getUrl(null), token.getAccessToken(), token.getOpenId());

    try {
      SimpleGetRequestExecutor.create(this).execute(url, null, WxType.MP);
    } catch (IOException e) {
      throw new WxRuntimeException(e);
    } catch (WxErrorException e) {
      return false;
    }
    return true;
  }
}

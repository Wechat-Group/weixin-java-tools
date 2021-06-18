package me.chanjar.weixin.open.api.impl;

import com.google.common.collect.ImmutableMap;
import lombok.SneakyThrows;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.api.WxOpenMpService;
import me.chanjar.weixin.open.bean.mp.FastRegisterResult;

import java.net.URLEncoder;
import java.util.Objects;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenMpServiceImpl extends WxMpServiceImpl implements WxOpenMpService {
  private WxOpenComponentService wxOpenComponentService;
  private WxMpConfigStorage wxMpConfigStorage;
  private String appId;

  public WxOpenMpServiceImpl(WxOpenComponentService wxOpenComponentService, String appId, WxMpConfigStorage wxMpConfigStorage) {
//    wxOpenComponentService.oauth2getAccessToken(appId)
    this.wxOpenComponentService = wxOpenComponentService;
    this.appId = appId;
    this.wxMpConfigStorage = wxMpConfigStorage;
    setOAuth2Service(new WxOpenMpOAuth2ServiceImpl(wxOpenComponentService, getOAuth2Service(), wxMpConfigStorage));
    initHttp();
  }

  @Override
  public WxMpConfigStorage getWxMpConfigStorage() {
    return wxMpConfigStorage;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    return wxOpenComponentService.getAuthorizerAccessToken(appId, forceRefresh);
  }

  @SneakyThrows
  @Override
  public String getFastRegisterAuthUrl(String redirectUri, Boolean copyWxVerify) {
    String copyInfo = Objects.equals(copyWxVerify, false) ? "0" : "1";
    String componentAppId = wxOpenComponentService.getWxOpenConfigStorage().getComponentAppId();
    String encoded = URLEncoder.encode(redirectUri, "UTF-8");
    return String.format(URL_FAST_REGISTER_AUTH, appId, componentAppId, copyInfo, encoded);
  }

  @Override
  public FastRegisterResult fastRegister(String ticket) throws WxErrorException {
    String json = post(API_FAST_REGISTER, ImmutableMap.of("ticket", ticket));
    return FastRegisterResult.fromJson(json);
  }
}

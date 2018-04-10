package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.URIUtil;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.cp.api.WxCpOAuth2Service;
import me.chanjar.weixin.cp.api.WxCpService;

/**
 * <pre>
 *
 * Created by Binary Wang on 2017-6-25.
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * </pre>
 */
public class WxCpOAuth2ServiceImpl implements WxCpOAuth2Service {
  private WxCpService mainService;

  public WxCpOAuth2ServiceImpl(WxCpService mainService) {
    this.mainService = mainService;
  }

  @Override
  public String buildAuthorizationUrl(String state) {
    return this.buildAuthorizationUrl(
      this.mainService.getWxCpConfigStorage().getOauth2redirectUri(),
      state
    );
  }

  @Override
  public String buildAuthorizationUrl(String redirectUri, String state) {
    String url = "https://open.weixin.qq.com/connect/oauth2/authorize?";
    url += "appid=" + this.mainService.getWxCpConfigStorage().getCorpId();
    url += "&redirect_uri=" + URIUtil.encodeURIComponent(redirectUri);
    url += "&response_type=code";
    url += "&scope=snsapi_base";
    if (state != null) {
      url += "&state=" + state;
    }
    url += "#wechat_redirect";
    return url;
  }

  @Override
  public String[] getUserInfo(String code) throws WxErrorException {
    return this.getUserInfo(this.mainService.getWxCpConfigStorage().getAgentId(), code);
  }

  @Override
  public String[] getUserInfo(Integer agentId, String code) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?"
      + "code=" + code
      + "&agentid=" + agentId;
    String responseText = this.mainService.get(url, null);
    JsonElement je = new JsonParser().parse(responseText);
    JsonObject jo = je.getAsJsonObject();
    return new String[]{GsonHelper.getString(jo, "UserId"),
      GsonHelper.getString(jo, "DeviceId"),
      GsonHelper.getString(jo, "OpenId")};
  }

}

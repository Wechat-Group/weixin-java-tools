package me.chanjar.weixin.open.api.impl;

import com.google.gson.JsonObject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.WxMpUserQuery;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.WxOpenUserService;

import java.util.List;

/**
 * Created by Binary Wang on 2016/7/21.
 */
public class WxOpenUserServiceImpl implements WxOpenUserService {
  private static final String API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/user";
  private WxOpenService wxOpenService;

  public WxOpenUserServiceImpl(WxOpenService wxOpenService) {
    this.wxOpenService = wxOpenService;
  }
  @Override
  public void userUpdateRemark(String appid, String openid, String remark) throws WxErrorException {
    String url = API_URL_PREFIX + "/info/updateremark";
    JsonObject json = new JsonObject();
    json.addProperty("openid", openid);
    json.addProperty("remark", remark);
    this.wxOpenService.post(appid, url, json.toString());
  }

  @Override
  public WxMpUser userInfo(String appid, String openid) throws WxErrorException {
    return this.userInfo(appid, openid, null);
  }

  @Override
  public WxMpUser userInfo(String appid, String openid, String lang) throws WxErrorException {
    String url = API_URL_PREFIX + "/info";
    lang = lang == null ? "zh_CN" : lang;
    String responseContent = this.wxOpenService.get(appid, url,
      "openid=" + openid + "&lang=" + lang);
    return WxMpUser.fromJson(responseContent);
  }

  @Override
  public WxMpUserList userList(String appid, String next_openid) throws WxErrorException {
    String url = API_URL_PREFIX + "/get";
    String responseContent = this.wxOpenService.get(appid, url,
      next_openid == null ? null : "next_openid=" + next_openid);
    return WxMpUserList.fromJson(responseContent);
  }

  @Override
  public List<WxMpUser> userInfoList(String appid, List<String> openids)
    throws WxErrorException {
    return this.userInfoList(appid, new WxMpUserQuery(openids));
  }

  @Override
  public List<WxMpUser> userInfoList(String appid, WxMpUserQuery userQuery) throws WxErrorException {
    String url = API_URL_PREFIX + "/info/batchget";
    String responseContent = this.wxOpenService.post(appid, url,
      userQuery.toJsonString());
    return WxMpUser.fromJsonList(responseContent);
  }

}

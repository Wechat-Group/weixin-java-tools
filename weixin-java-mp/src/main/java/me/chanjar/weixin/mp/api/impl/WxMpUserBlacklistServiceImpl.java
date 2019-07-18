package me.chanjar.weixin.mp.api.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserBlacklistService;
import me.chanjar.weixin.mp.bean.result.WxMpUserBlacklistGetResult;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * @author miller
 */
public class WxMpUserBlacklistServiceImpl implements WxMpUserBlacklistService {
  private static final String API_BLACKLIST_PREFIX = "https://api.weixin.qq.com/cgi-bin/tags/members";
  private WxMpService wxMpService;

  public WxMpUserBlacklistServiceImpl(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
  }

  @Override
  public WxMpUserBlacklistGetResult getBlacklist(String nextOpenid) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("begin_openid", nextOpenid);
    String url = API_BLACKLIST_PREFIX + "/getblacklist";
    String responseContent = this.wxMpService.execute(SimplePostRequestExecutor.create(this.wxMpService.getRequestHttp()), url, jsonObject.toString());
    return WxMpUserBlacklistGetResult.fromJson(responseContent);
  }

  @Override
  public void pushToBlacklist(List<String> openidList) throws WxErrorException {
    Map<String, Object> map = new HashMap<>();
    map.put("openid_list", openidList);
    String url = API_BLACKLIST_PREFIX + "/batchblacklist";
    this.wxMpService.execute(SimplePostRequestExecutor.create(this.wxMpService.getRequestHttp()), url,
      WxMpGsonBuilder.create().toJson(map));
  }

  @Override
  public void pullFromBlacklist(List<String> openidList) throws WxErrorException {
    Map<String, Object> map = new HashMap<>(2);
    map.put("openid_list", openidList);
    String url = API_BLACKLIST_PREFIX + "/batchunblacklist";
    this.wxMpService.execute(SimplePostRequestExecutor.create(this.wxMpService.getRequestHttp()), url, WxMpGsonBuilder.create().toJson(map));
  }
}

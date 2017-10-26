package us.wili.jason.weixin.open.api.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserBlacklistService;
import me.chanjar.weixin.mp.bean.result.WxMpUserBlacklistGetResult;
import us.wili.jason.weixin.open.api.WxOpenService;
import us.wili.jason.weixin.open.api.WxOpenUserBlacklistService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author miller
 */
public class WxOpenUserBlacklistServiceImpl implements WxOpenUserBlacklistService {
  private static final String API_BLACKLIST_PREFIX = "https://api.weixin.qq.com/cgi-bin/tags/members";
  private WxOpenService wxOpenService;

  public WxOpenUserBlacklistServiceImpl(WxOpenService wxOpenService) {
    this.wxOpenService = wxOpenService;
  }
  @Override
  public WxMpUserBlacklistGetResult getBlacklist(String appid, String nextOpenid) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("begin_openid", nextOpenid);
    String url = API_BLACKLIST_PREFIX + "/getblacklist";
    String responseContent = this.wxOpenService.execute(appid, SimplePostRequestExecutor.create(this.wxOpenService.getRequestHttp()), url, jsonObject.toString());
    return WxMpUserBlacklistGetResult.fromJson(responseContent);
  }

  @Override
  public void pushToBlacklist(String appid, List<String> openidList) throws WxErrorException {
    Map<String, Object> map = new HashMap<>();
    map.put("openid_list", openidList);
    String url = API_BLACKLIST_PREFIX + "/batchblacklist";
    this.wxOpenService.execute(appid, SimplePostRequestExecutor.create(this.wxOpenService.getRequestHttp()), url, new Gson().toJson(map));
  }

  @Override
  public void pullFromBlacklist(String appid, List<String> openidList) throws WxErrorException {
    Map<String, Object> map = new HashMap<>();
    map.put("openid_list", openidList);
    String url = API_BLACKLIST_PREFIX + "/batchunblacklist";
    this.wxOpenService.execute(appid, SimplePostRequestExecutor.create(this.wxOpenService.getRequestHttp()), url, new Gson().toJson(map));
  }
}

package us.wili.jason.weixin.open.api.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.wili.jason.weixin.open.api.WxOpenMenuService;
import us.wili.jason.weixin.open.api.WxOpenService;

/**
 * Created by Binary Wang on 2016/7/21.
 */
public class WxOpenMenuServiceImpl implements WxOpenMenuService {
  private static final String API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/menu";
  private static Logger log = LoggerFactory.getLogger(WxOpenMenuServiceImpl.class);

  private WxOpenService wxOpenService;

  public WxOpenMenuServiceImpl(WxOpenService wxOpenService) {
    this.wxOpenService = wxOpenService;
  }

  @Override
  public String menuCreate(String appid, WxMenu menu) throws WxErrorException {
    String menuJson = menu.toJson();
    String url = API_URL_PREFIX + "/create";
    if (menu.getMatchRule() != null) {
      url = API_URL_PREFIX + "/addconditional";
    }

    log.debug("开始创建菜单：{}", menuJson);

    String result = this.wxOpenService.post(appid, url, menuJson);
    log.debug("创建菜单：{},结果：{}", menuJson, result);

    if (menu.getMatchRule() != null) {
      return new JsonParser().parse(result).getAsJsonObject().get("menuid").getAsString();
    }

    return null;
  }

  @Override
  public String menuCreate(String appid, String json) throws WxErrorException {
    JsonParser jsonParser = new JsonParser();
    JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
    String url = API_URL_PREFIX + "/create";
    if (jsonObject.get("matchrule") != null) {
      url = API_URL_PREFIX + "/addconditional";
    }

    String result = this.wxOpenService.post(appid, url, json);
    if (jsonObject.get("matchrule") != null) {
      return jsonParser.parse(result).getAsJsonObject().get("menuid").getAsString();
    }

    return null;
  }

  @Override
  public void menuDelete(String appid) throws WxErrorException {
    String url = API_URL_PREFIX + "/delete";
    String result = this.wxOpenService.get(appid, url, null);
    log.debug("删除菜单结果：{}", result);
  }

  @Override
  public void menuDelete(String appid, String menuId) throws WxErrorException {
    String url = API_URL_PREFIX + "/delconditional";
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("menuid", menuId);
    String result = this.wxOpenService.post(appid, url, jsonObject.toString());
    log.debug("根据MeunId({})删除个性化菜单结果：{}", menuId, result);
  }

  @Override
  public WxMpMenu menuGet(String appid) throws WxErrorException {
    String url = API_URL_PREFIX + "/get";
    try {
      String resultContent = this.wxOpenService.get(appid, url, null);
      return WxMpMenu.fromJson(resultContent);
    } catch (WxErrorException e) {
      // 46003 不存在的菜单数据
      if (e.getError().getErrorCode() == 46003) {
        return null;
      }
      throw e;
    }
  }

  @Override
  public WxMenu menuTryMatch(String appid, String userid) throws WxErrorException {
    String url = API_URL_PREFIX + "/trymatch";
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("user_id", userid);
    try {
      String resultContent = this.wxOpenService.post(appid, url, jsonObject.toString());
      return WxMenu.fromJson(resultContent);
    } catch (WxErrorException e) {
      // 46003 不存在的菜单数据；46002 不存在的菜单版本
      if (e.getError().getErrorCode() == 46003
        || e.getError().getErrorCode() == 46002) {
        return null;
      }
      throw e;
    }
  }

  @Override
  public WxMpGetSelfMenuInfoResult getSelfMenuInfo(String appid) throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info";
    String resultContent = this.wxOpenService.get(appid, url, null);
    return WxMpGetSelfMenuInfoResult.fromJson(resultContent);
  }
}

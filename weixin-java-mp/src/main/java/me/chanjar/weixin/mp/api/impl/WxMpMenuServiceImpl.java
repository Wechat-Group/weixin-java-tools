package me.chanjar.weixin.mp.api.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Binary Wang on 2016/7/21.
 */
public class WxMpMenuServiceImpl implements WxMpMenuService {
  private static final String API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/menu";
  private static Logger log = LoggerFactory.getLogger(WxMpMenuServiceImpl.class);

  private WxMpService wxMpService;

  public WxMpMenuServiceImpl(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
  }

  @Override
  public String menuCreate(WxMenu menu) throws WxErrorException {
    String menuJson = menu.toJson();
    String url = API_URL_PREFIX + "/create";
    if (menu.getMatchRule() != null) {
      url = API_URL_PREFIX + "/addconditional";
    }

    log.debug("开始创建菜单：{}", menuJson);

    String result = this.wxMpService.post(url, menuJson);
    log.debug("创建菜单：{},结果：{}", menuJson, result);

    if (menu.getMatchRule() != null) {
      return new JsonParser().parse(result).getAsJsonObject().get("menuid").getAsString();
    }

    return null;
  }

  @Override
  public String menuCreate(String json) throws WxErrorException {
    JsonParser jsonParser = new JsonParser();
    JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
    String url = API_URL_PREFIX + "/create";
    if (jsonObject.get("matchrule") != null) {
      url = API_URL_PREFIX + "/addconditional";
    }

    String result = this.wxMpService.post(url, json);
    if (jsonObject.get("matchrule") != null) {
      return jsonParser.parse(result).getAsJsonObject().get("menuid").getAsString();
    }

    return null;
  }

  @Override
  public void menuDelete() throws WxErrorException {
    String url = API_URL_PREFIX + "/delete";
    String result = this.wxMpService.get(url, null);
    log.debug("删除菜单结果：{}", result);
  }

  @Override
  public void menuDelete(String menuId) throws WxErrorException {
    String url = API_URL_PREFIX + "/delconditional";
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("menuid", menuId);
    String result = this.wxMpService.post(url, jsonObject.toString());
    log.debug("根据MeunId({})删除个性化菜单结果：{}", menuId, result);
  }

  @Override
  public WxMpMenu menuGet() throws WxErrorException {
    String url = API_URL_PREFIX + "/get";
    try {
      String resultContent = this.wxMpService.get(url, null);
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
  public WxMenu menuTryMatch(String userid) throws WxErrorException {
    String url = API_URL_PREFIX + "/trymatch";
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("user_id", userid);
    try {
      String resultContent = this.wxMpService.post(url, jsonObject.toString());
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
  public WxMpGetSelfMenuInfoResult getSelfMenuInfo() throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info";
    String resultContent = this.wxMpService.get(url, null);
    return WxMpGetSelfMenuInfoResult.fromJson(resultContent);
  }
}

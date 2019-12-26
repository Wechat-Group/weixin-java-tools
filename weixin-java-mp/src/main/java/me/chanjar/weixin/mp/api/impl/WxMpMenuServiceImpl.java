package me.chanjar.weixin.mp.api.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpMenuService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpGetSelfMenuInfoResult;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import me.chanjar.weixin.mp.enums.WxMpApiUrl;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Menu.*;

/**
 * Created by Binary Wang on 2016/7/21.
 *
 * @author Binary Wang
 */
@Slf4j
@RequiredArgsConstructor
public class WxMpMenuServiceImpl implements WxMpMenuService {
  private final WxMpService wxMpService;

  @Override
  public String menuCreate(WxMenu menu) throws WxErrorException {
    String menuJson = menu.toJson();
    WxMpApiUrl.Menu url = MENU_CREATE;
    if (menu.getMatchRule() != null) {
      url = MENU_ADDCONDITIONAL;
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
    // 因${code WxMenuGsonAdapter}中重写了反序列化方式，固在此使用原始的方式生成菜单对象
    WxMenu wxMenu = new Gson().fromJson(json, WxMenu.class);
    WxMpApiUrl.Menu url = MENU_CREATE;
    if (wxMenu.getMatchRule() != null) {
      url = MENU_ADDCONDITIONAL;
    }

    String result = this.wxMpService.post(url, wxMenu.toJson());
    if (wxMenu.getMatchRule() != null) {
      return new JsonParser().parse(result).getAsJsonObject().get("menuid").getAsString();
    }

    return null;
  }

  @Override
  public void menuDelete() throws WxErrorException {
    String result = this.wxMpService.get(MENU_DELETE, null);
    log.debug("删除菜单结果：{}", result);
  }

  @Override
  public void menuDelete(String menuId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("menuid", menuId);
    String result = this.wxMpService.post(MENU_DELCONDITIONAL, jsonObject.toString());
    log.debug("根据MeunId({})删除个性化菜单结果：{}", menuId, result);
  }

  @Override
  public WxMpMenu menuGet() throws WxErrorException {
    try {
      String resultContent = this.wxMpService.get(MENU_GET, null);
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
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("user_id", userid);
    try {
      String resultContent = this.wxMpService.post(MENU_TRYMATCH, jsonObject.toString());
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
    String resultContent = this.wxMpService.get(GET_CURRENT_SELFMENU_INFO, null);
    return WxMpGetSelfMenuInfoResult.fromJson(resultContent);
  }
}

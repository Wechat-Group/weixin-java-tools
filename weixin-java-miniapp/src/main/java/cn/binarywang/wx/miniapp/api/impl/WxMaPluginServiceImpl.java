package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaPluginService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaPluginListResult;
import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.HashMap;
import java.util.Map;

public class WxMaPluginServiceImpl implements WxMaPluginService {

  private WxMaService wxMaService;

  public WxMaPluginServiceImpl(WxMaService wxMaService) {
    this.wxMaService = wxMaService;
  }

  @Override
  public void applyPlugin(String pluginAppId, String reason) throws WxErrorException {
    Map<String, String> params = new HashMap<>();
    params.put("action", "apply");
    params.put("plugin_appid", pluginAppId);
    params.put("reason", reason);

    this.wxMaService.post(PLUGIN_URL, WxMaGsonBuilder.create().toJson(params));
  }

  @Override
  public WxMaPluginListResult getPluginList() throws WxErrorException {
    Map<String, String> params = new HashMap<>();
    params.put("action", "list");

    String responseContent = this.wxMaService.post(PLUGIN_URL, WxMaGsonBuilder.create().toJson(params));
    return WxMaPluginListResult.fromJson(responseContent);
  }

  @Override
  public void unbindPlugin(String pluginAppId) throws WxErrorException {
    Map<String, String> params = new HashMap<>();
    params.put("action", "unbind");
    params.put("plugin_appid", pluginAppId);

    this.wxMaService.post(PLUGIN_URL, WxMaGsonBuilder.create().toJson(params));
  }

  @Override
  public void updatePlugin(String pluginAppId, String userVersion) throws WxErrorException {
    Map<String, String> params = new HashMap<>();
    params.put("action", "update");
    params.put("plugin_appid", pluginAppId);
    params.put("user_version", userVersion);

    this.wxMaService.post(PLUGIN_URL, WxMaGsonBuilder.create().toJson(params));
  }
}

package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.WxMaPluginListResult;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 小程序插件管理 API
 * <p>
 * 详情请见：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/plugin-management/pluginManager.applyPlugin.html
 * 或者：https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/Mini_Programs/Plug-ins_Management.html
 *
 * @author ArBing
 */
public interface WxMaPluginService {

  /**
   * 向插件开发者发起使用插件的申请
   *
   * @param pluginAppId 插件 appId
   * @param reason      申请使用理由
   * @throws WxErrorException 异常
   */
  void applyPlugin(String pluginAppId, String reason) throws WxErrorException;

  /**
   * 查询已添加的插件
   *
   * @return plugin list
   * @throws WxErrorException the wx error exception
   */
  WxMaPluginListResult getPluginList() throws WxErrorException;

  /**
   * 删除已添加的插件
   *
   * @param pluginAppId 插件 appId
   * @throws WxErrorException the wx error exception
   */
  void unbindPlugin(String pluginAppId) throws WxErrorException;

  /**
   * 快速更新插件版本号(第三方平台代小程序管理插件)
   *
   * @param pluginAppId 插件 appid
   * @param userVersion 升级至版本号，要求此插件版本支持快速更新
   * @throws WxErrorException the wx error exception
   */
  void updatePlugin(String pluginAppId, String userVersion) throws WxErrorException;

}

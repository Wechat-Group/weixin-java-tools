package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import com.google.gson.JsonArray;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.api.WxOpenFastMaService;
import me.chanjar.weixin.open.bean.fastma.WxAccountBasicInfo;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.util.List;

/**
 * @author Hipple
 * @description
 * @since 2019/1/23 15:27
 */
public class WxOpenFastMaServiceImpl extends WxMaServiceImpl implements WxOpenFastMaService {

  private WxOpenComponentService wxOpenComponentService;
  private WxMaConfig wxMaConfig;
  private String appId;

  public WxOpenFastMaServiceImpl(WxOpenComponentService wxOpenComponentService, String appId, WxMaConfig wxMaConfig) {
    this.wxOpenComponentService = wxOpenComponentService;
    this.appId = appId;
    this.wxMaConfig = wxMaConfig;
    initHttp();
  }

  @Override
  public WxMaConfig getWxMaConfig() {
    return wxMaConfig;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    return wxOpenComponentService.getAuthorizerAccessToken(appId, forceRefresh);
  }

  /**
   * 获取小程序的信息,GET请求
   * <pre>
   *     注意：这里不能直接用小程序的access_token
   *
   * </pre>
   *
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxAccountBasicInfo getAccountBasicInfo() throws WxErrorException {
    String response = get(OPEN_GET_ACCOUNT_BASIC_INFO, "");
    return WxOpenGsonBuilder.create().fromJson(response, WxAccountBasicInfo.class);
  }

  /**
   * 将字符串对象转化为GsonArray对象
   *
   * @param strList
   * @return
   */
  private JsonArray toJsonArray(List<String> strList) {
    JsonArray jsonArray = new JsonArray();
    if (strList != null && !strList.isEmpty()) {
      for (String str : strList) {
        jsonArray.add(str);
      }
    }
    return jsonArray;
  }
}

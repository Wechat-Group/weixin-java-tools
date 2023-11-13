package cn.binarywang.wx.miniapp.bean.express.result;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;

public class WxMaExpressInfoResult {
  /**
   * 错误码
   */
  private Integer errcode;

  /**
   * 错误信息
   */
  private String errmsg;

  public static WxMaExpressInfoResult fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaExpressInfoResult.class);
  }
}

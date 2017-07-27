package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;

/**
 * 微信二维码（小程序码）包装器
 * Created by Element on 2017/7/27.
 */
public abstract class WxMaQrcodeWrapper {

  @Override
  public String toString() {
    return WxMaGsonBuilder.create().toJson(this);
  }

}

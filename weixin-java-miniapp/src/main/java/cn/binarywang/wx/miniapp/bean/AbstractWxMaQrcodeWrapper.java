package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;

/**
 * 微信二维码（小程序码）包装器
 *
 * @author Element
 */
public abstract class AbstractWxMaQrcodeWrapper {

  @Override
  public String toString() {
    return WxMaGsonBuilder.create().toJson(this);
  }

}

package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxMaQrcode extends WxMaQrcodeWrapper implements Serializable {
  private static final long serialVersionUID = 5777119669111011584L;
  private String path;
  private int width = 430;

  public WxMaQrcode(String path, int width) {
    this.path = path;
    this.width = width;
  }

  public static WxMaQrcode fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaQrcode.class);
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  @Override
  public String toString() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}

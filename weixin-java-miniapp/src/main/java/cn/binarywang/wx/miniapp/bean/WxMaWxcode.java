package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *
 * @author Element
 * @date 2017/7/27
 */
public class WxMaWxcode extends AbstractWxMaQrcodeWrapper implements Serializable {

  private static final long serialVersionUID = 1287399621649210322L;
  private String path;
  private int width = 430;

  @SerializedName("auto_color")
  private boolean autoColor = true;

  @SerializedName("line_color")
  private WxMaCodeLineColor lineColor = new WxMaCodeLineColor("0", "0", "0");

  public static WxMaWxcode fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaWxcode.class);
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
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

  public boolean isAutoColor() {
    return autoColor;
  }

  public void setAutoColor(boolean autoColor) {
    this.autoColor = autoColor;
  }

  public WxMaCodeLineColor getLineColor() {
    return lineColor;
  }

  public void setLineColor(WxMaCodeLineColor lineColor) {
    this.lineColor = lineColor;
  }

}

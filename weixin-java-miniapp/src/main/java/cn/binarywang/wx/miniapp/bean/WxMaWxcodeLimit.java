package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Element on 2017/7/27.
 */
public class WxMaWxcodeLimit extends WxMaQrcodeWrapper implements Serializable{
  private static final long serialVersionUID = 4782193774524960401L;
  private String scene;
  private String page;

  private int width = 430;

  @SerializedName("auto_color")
  private boolean autoColor = true;

  @SerializedName("line_color")
  private WxMaQrcodeService.LineColor lineColor = new WxMaQrcodeService.LineColor("0","0","0");

  public String getPage() {
    return page;
  }

  public void setPage(String page) {
    this.page = page;
  }

  public static WxMaWxcodeLimit fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaWxcodeLimit.class);
  }

  public String getScene() {
    return scene;
  }

  public void setScene(String scene) {
    this.scene = scene;
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

  public WxMaQrcodeService.LineColor getLineColor() {
    return lineColor;
  }

  public void setLineColor(WxMaQrcodeService.LineColor lineColor) {
    this.lineColor = lineColor;
  }
}

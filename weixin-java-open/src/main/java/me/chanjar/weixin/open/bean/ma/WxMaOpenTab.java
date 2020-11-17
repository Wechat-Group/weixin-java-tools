package me.chanjar.weixin.open.bean.ma;

import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

/**
 * @author yqx
 * @date 2018/9/13
 */
@Data
public class WxMaOpenTab implements Serializable {
  @NonNull
  private String pagePath;

  @NonNull
  private String text;
  private String iconPath;
  private String selectedIconPath;

  public WxMaOpenTab(@NonNull String pagePath, @NonNull String text) {
    this.pagePath = pagePath;
    this.text = text;
  }


  public WxMaOpenTab(@NonNull String pagePath, @NonNull String text, String iconPath, String selectedIconPath) {
    this.pagePath = pagePath;
    this.text = text;
    this.iconPath = iconPath;
    this.selectedIconPath = selectedIconPath;
  }
}

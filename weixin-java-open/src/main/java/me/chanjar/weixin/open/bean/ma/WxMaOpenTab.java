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
}

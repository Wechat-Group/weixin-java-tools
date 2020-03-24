package me.chanjar.weixin.open.bean.ma;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * tabBar对象
 *
 * @author yqx
 * @date 2018/9/13
 */
@Data
@NoArgsConstructor
public class WxMaOpenTabBar implements Serializable {
  @NonNull
  private String color;

  @NonNull
  private String selectedColor;

  @NonNull
  private String backgroundColor;

  private String borderStyle;

  @NonNull
  @SerializedName("list")
  private List<WxMaOpenTab> tabList;

  private String position;

  /**
   * 添加tab
   *
   * @param text
   * @param pagePath
   */
  public void addTab(String text, String pagePath) {
    if (tabList == null)
      tabList = new ArrayList<>();
    tabList.add(new WxMaOpenTab(pagePath, text));
  }
}

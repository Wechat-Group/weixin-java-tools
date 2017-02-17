package me.chanjar.weixin.mp.bean.menu;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

/**
 * <pre>
 * Created by Binary Wang on 2016-11-25.
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 * </pre>
 */
public class WxMpGetSelfMenuInfoResult {
  @SerializedName("selfmenu_info")
  private WxMpSelfMenuInfo selfMenuInfo;

  @SerializedName("is_menu_open")
  private Integer isMenuOpen;

  public static WxMpGetSelfMenuInfoResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGetSelfMenuInfoResult.class);
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public WxMpSelfMenuInfo getSelfMenuInfo() {
    return selfMenuInfo;
  }

  public void setSelfMenuInfo(WxMpSelfMenuInfo selfMenuInfo) {
    this.selfMenuInfo = selfMenuInfo;
  }

  public Integer getIsMenuOpen() {
    return isMenuOpen;
  }

  public void setIsMenuOpen(Integer isMenuOpen) {
    this.isMenuOpen = isMenuOpen;
  }
}

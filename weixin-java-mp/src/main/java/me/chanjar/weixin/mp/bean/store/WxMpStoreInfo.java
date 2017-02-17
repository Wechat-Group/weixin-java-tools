package me.chanjar.weixin.mp.bean.store;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.util.ToStringUtils;

public class WxMpStoreInfo {
  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  @SerializedName("base_info")
  private WxMpStoreBaseInfo baseInfo;

  public WxMpStoreBaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public void setBaseInfo(WxMpStoreBaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

}

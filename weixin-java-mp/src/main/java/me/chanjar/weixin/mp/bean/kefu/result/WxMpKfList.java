package me.chanjar.weixin.mp.bean.kefu.result;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.util.List;
/**
 *
 * @author Binary Wang
 *
 */
public class WxMpKfList {
  @SerializedName("kf_list")
  private List<WxMpKfInfo> kfList;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public List<WxMpKfInfo> getKfList() {
    return this.kfList;
  }

  public void setKfList(List<WxMpKfInfo> kfList) {
    this.kfList = kfList;
  }

  public static WxMpKfList fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxMpKfList.class);
  }
}

package me.chanjar.weixin.mp.bean.result;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 关注者列表
 * @author chanjarster
 *
 */
public class WxMpUserList {

  protected long total = -1;
  protected int count = -1;
  protected List<String> openids = new ArrayList<>();
  protected String nextOpenid;
  public long getTotal() {
    return this.total;
  }
  public void setTotal(long total) {
    this.total = total;
  }
  public int getCount() {
    return this.count;
  }
  public void setCount(int count) {
    this.count = count;
  }
  public List<String> getOpenids() {
    return this.openids;
  }
  public void setOpenids(List<String> openids) {
    this.openids = openids;
  }
  public String getNextOpenid() {
    return this.nextOpenid;
  }
  public void setNextOpenid(String nextOpenid) {
    this.nextOpenid = nextOpenid;
  }

  public static WxMpUserList fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxMpUserList.class);
  }

  @Override
  public String toString() {
    return  WxMpGsonBuilder.INSTANCE.create().toJson(this);
  }
}

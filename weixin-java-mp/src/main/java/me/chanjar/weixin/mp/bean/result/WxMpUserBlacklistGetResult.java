package me.chanjar.weixin.mp.bean.result;

import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author miller
 */
@Data
public class WxMpUserBlacklistGetResult implements Serializable {
  private static final long serialVersionUID = -8780216463588687626L;

  protected int total = -1;
  protected int count = -1;
  protected List<String> openidList = new ArrayList<>();
  protected String nextOpenid;

  public static WxMpUserBlacklistGetResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpUserBlacklistGetResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}

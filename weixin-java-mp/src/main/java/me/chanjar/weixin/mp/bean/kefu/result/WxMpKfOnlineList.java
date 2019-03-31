package me.chanjar.weixin.mp.bean.kefu.result;

import java.io.Serializable;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * @author Binary Wang
 */
@Data
public class WxMpKfOnlineList implements Serializable {
  private static final long serialVersionUID = -6154705288500854617L;

  @SerializedName("kf_online_list")
  private List<WxMpKfInfo> kfOnlineList;

  public static WxMpKfOnlineList fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpKfOnlineList.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}

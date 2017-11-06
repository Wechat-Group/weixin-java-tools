package me.chanjar.weixin.mp.bean.device;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * @author keungtung
 * @date 10/12/2016
 */
@Data
public class WxDeviceBindResult extends AbstractDeviceBean {
  private static final long serialVersionUID = 4687725146279339359L;

  @SerializedName("base_resp")
  private BaseResp baseResp;

  public static WxDeviceBindResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxDeviceBindResult.class);
  }

}

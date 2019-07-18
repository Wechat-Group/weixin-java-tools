package me.chanjar.weixin.mp.bean.device;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * @author keungtung.
 * @date 10/12/2016
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxDeviceQrCodeResult extends AbstractDeviceBean {
  private static final long serialVersionUID = -4312858303060918266L;

  @SerializedName("deviceid")
  private String deviceId;
  @SerializedName("qrticket")
  private String qrTicket;
  @SerializedName("devicelicence")
  private String deviceLicence;
  @SerializedName("base_resp")
  private BaseResp baseResp;

  public static WxDeviceQrCodeResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxDeviceQrCodeResult.class);
  }

}

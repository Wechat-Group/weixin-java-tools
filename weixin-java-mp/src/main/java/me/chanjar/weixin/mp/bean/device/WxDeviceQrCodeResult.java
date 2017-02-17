package me.chanjar.weixin.mp.bean.device;

import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * Created by keungtung on 10/12/2016.
 */
public class WxDeviceQrCodeResult extends AbstractDeviceBean{
  @SerializedName("deviceid")
  private String deviceId;
  @SerializedName("qrticket")
  private String qrTicket;
  @SerializedName("devicelicence")
  private String deviceLicence;
  @SerializedName("resp_msg")
  private RespMsg respMsg;

  public static WxDeviceQrCodeResult fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxDeviceQrCodeResult.class);
  }

  public String getDeviceLicence() {
    return deviceLicence;
  }

  public void setDeviceLicence(String deviceLicence) {
    this.deviceLicence = deviceLicence;
  }

  public RespMsg getRespMsg() {
    return respMsg;
  }

  public void setRespMsg(RespMsg respMsg) {
    this.respMsg = respMsg;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public String getQrTicket() {
    return qrTicket;
  }

  public void setQrTicket(String qrTicket) {
    this.qrTicket = qrTicket;
  }
}

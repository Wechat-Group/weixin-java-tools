package me.chanjar.weixin.mp.bean.device;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author keungtung
 * @date 10/12/2016
 */
@Data
public class WxDeviceBind extends AbstractDeviceBean {
  private static final long serialVersionUID = 467559769037590880L;

  private String ticket;
  @SerializedName("device_id")
  private String deviceId;
  @SerializedName("openid")
  private String openId;

}

package me.chanjar.weixin.qidian.bean.device;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author keungtung.
 * @date 10/12/2016
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxDeviceBind extends AbstractDeviceBean {
  private static final long serialVersionUID = 467559769037590880L;

  private String ticket;
  @SerializedName("device_id")
  private String deviceId;
  @SerializedName("openid")
  private String openId;

}

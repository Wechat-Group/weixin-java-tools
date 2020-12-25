package me.chanjar.weixin.qidian.bean.device;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.qidian.util.json.WxMpGsonBuilder;

/**
 * @author keungtung.
 * @date 10/12/2016
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxDeviceMsg extends AbstractDeviceBean {
  private static final long serialVersionUID = -5567110858455277963L;

  @SerializedName("device_type")
  private String deviceType;
  @SerializedName("device_id")
  private String deviceId;
  @SerializedName("open_id")
  private String openId;
  private String content;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}

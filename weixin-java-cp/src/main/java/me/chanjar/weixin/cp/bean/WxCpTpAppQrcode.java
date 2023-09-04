package me.chanjar.weixin.cp.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 应用的管理员
 *
 * @author huangxiaoming
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpTpAppQrcode extends WxCpBaseResp {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("qrcode")
  private String qrcode;

  /**
   * From json wx cp tp admin.
   *
   * @param json the json
   * @return the wx cp tp admin
   */
  public static WxCpTpAppQrcode fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpAppQrcode.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}

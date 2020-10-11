package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 「联系我」方式 处理结果
 */
@Data
public class WxCpContactWayResult extends WxCpBaseResp {
  @SerializedName("config_id")
  private String configId;
  
  @SerializedName("qr_code")
  private String qrCode;
  
  public static WxCpContactWayResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpContactWayResult.class);
  }
}

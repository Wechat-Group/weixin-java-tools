package me.chanjar.weixin.cp.bean.external.acquisition;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 剩余使用量
 *
 * @author alien_zyl
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpCustomerAcquisitionQuota extends WxCpBaseResp {
  private static final long serialVersionUID = -3816540607590841079L;

  /**
   * 历史累计使用量
   */
  @SerializedName("total")
  private Integer total;

  /**
   * 剩余使用量
   */
  @SerializedName("balance")
  private Integer balance;

  public static WxCpCustomerAcquisitionQuota fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpCustomerAcquisitionQuota.class);
  }

}

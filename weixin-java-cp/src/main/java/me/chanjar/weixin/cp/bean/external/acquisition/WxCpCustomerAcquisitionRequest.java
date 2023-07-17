package me.chanjar.weixin.cp.bean.external.acquisition;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * 创建/更新获客链接请求体
 *
 * @author alien_zyl
 */
@Data
public class WxCpCustomerAcquisitionRequest {

  /**
   * 获客链接的id
   */
  @SerializedName("link_id")
  private String linkId;
  /**
   * 链接名称
   */
  @SerializedName("link_name")
  private String linkName;

  @SerializedName("range")
  private WxCpCustomerAcquisitionInfo.Range range;

  /**
   * 是否无需验证，默认为true
   */
  @SerializedName("skip_verify")
  private Boolean skipVerify;

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}

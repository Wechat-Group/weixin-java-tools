package me.chanjar.weixin.cp.bean.license.order;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.license.WxCpTpLicenseAccountCount;
import me.chanjar.weixin.cp.bean.license.WxCpTpLicenseAccountDuration;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 下单购买帐号
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95644
 *
 * @author Totoro  created on  2022/6/27 10:52
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpTpLicenseNewOrderRequest implements Serializable {
  private static final long serialVersionUID = 6644560301282598903L;

  /**
   * 企业ID
   */
  @SerializedName("corpid")
  private String corpId;

  /**
   * 购买者ID
   */
  @SerializedName("buyer_userid")
  private String buyerUserId;

  /**
   * 账号个数
   */
  @SerializedName("account_count")
  private WxCpTpLicenseAccountCount accountCount;

  /**
   * 购买市场
   */
  @SerializedName("account_duration")
  private WxCpTpLicenseAccountDuration accountDuration;


  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }


}

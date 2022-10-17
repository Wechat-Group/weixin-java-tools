package me.chanjar.weixin.cp.bean.license.order;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.license.WxCpTpLicenseAccountDuration;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 续期帐号订单
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95646
 *
 * @author Totoro  created on  2022-6-27 11:21:51
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpTpLicenseRenewOrderRequest implements Serializable {
  private static final long serialVersionUID = 8709132346969663049L;

  @SerializedName("buyer_userid")
  private String buyerUserId;
  @SerializedName("jobid")
  private String jobId;
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

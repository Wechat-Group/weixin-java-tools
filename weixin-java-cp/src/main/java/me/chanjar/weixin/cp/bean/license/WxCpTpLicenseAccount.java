package me.chanjar.weixin.cp.bean.license;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 订单账号信息
 *
 * @author Totoro  created on  2022/6/27 14:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpTpLicenseAccount extends WxCpTpLicenseBaseAccount {
  private static final long serialVersionUID = 8225061160406054730L;

  /**
   * 激活码
   */
  @SerializedName("active_code")
  private String activeCode;


}

package me.chanjar.weixin.cp.bean.license;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Wx cp tp license invalid account.
 *
 * @author Totoro  created on  2022-6-27 15:35:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpTpLicenseInvalidAccount extends WxCpTpLicenseBaseAccount {
  private static final long serialVersionUID = -3706481243147500720L;

  @SerializedName("errcode")
  private Integer errorCode;
  @SerializedName("errmsg")
  private String errMsg;


}

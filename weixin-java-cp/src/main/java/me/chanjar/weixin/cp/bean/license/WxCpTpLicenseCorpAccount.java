package me.chanjar.weixin.cp.bean.license;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The type Wx cp tp license corp account.
 *
 * @author Totoro  created on  2022/6/27 15:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpTpLicenseCorpAccount extends WxCpTpLicenseBaseAccount {

  private static final long serialVersionUID = -5856054486686123753L;

  @SerializedName("active_time")
  private Long activeTime;

  @SerializedName("expire_time")
  private Long expireTime;


}

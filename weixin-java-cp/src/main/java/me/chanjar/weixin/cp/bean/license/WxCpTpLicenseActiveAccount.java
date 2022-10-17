package me.chanjar.weixin.cp.bean.license;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Wx cp tp license active account.
 *
 * @author Totoro  created on  2022-6-27 16:26:35
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpTpLicenseActiveAccount implements Serializable {
  private static final long serialVersionUID = -2382681430861137803L;

  /**
   * 用户ID
   */
  private String userid;

  /**
   * 激活码
   */
  @SerializedName("active_code")
  private String activeCode;

  /**
   * 激活状态 0为成功
   * 此值在请求激活时无需传入
   */
  @SerializedName("errcode")
  private Integer errCode;
}

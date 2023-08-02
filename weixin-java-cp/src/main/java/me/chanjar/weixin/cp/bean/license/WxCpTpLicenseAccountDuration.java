package me.chanjar.weixin.cp.bean.license;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Wx cp tp license account duration.
 *
 * @author Totoro  created on  2022-6-27 11:22:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpTpLicenseAccountDuration implements Serializable {
  private static final long serialVersionUID = 7960912263908286975L;

  private Integer months;

}

package com.github.binarywang.wxpay.bean.profitsharing.result;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 微信V3接口-查询特约商户设置的允许服务商分账的最大比例结果类
 *
 * @author 狂龙骄子
 * @since 4.4.0
 * @date 2022-12-09
 */
@Data
public class ProfitSharingMerchantRatioQueryV3Result implements Serializable {
  private static final long serialVersionUID = -6259241881199571683L;

  /** 子商户号 */
  @SerializedName("sub_mchid")
  private String subMchId;

  /** 子商户允许服务商分账的最大比例，单位万分比，比如 2000表示20% */
  @SerializedName("max_ratio")
  private Integer maxRatio;

}

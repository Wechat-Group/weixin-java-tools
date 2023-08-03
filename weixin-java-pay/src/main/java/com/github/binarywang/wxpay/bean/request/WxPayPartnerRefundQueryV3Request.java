package com.github.binarywang.wxpay.bean.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <pre>
 * 微信支付-查询单笔退款API
 * </pre>
 *
 * @author thinsstar
 */
@Data
@NoArgsConstructor
public class WxPayPartnerRefundQueryV3Request extends WxPayRefundQueryV3Request implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * <pre>
   * 字段名：子商户号
   * 变量名：sub_mchid
   * 是否必填：是
   * 类型：string[1, 32]
   * 描述：子商户的商户号，由微信支付生成并下发。
   * 示例值：1900000109
   * </pre>
   */
  @SerializedName(value = "sub_mchid")
  private String subMchid;
}

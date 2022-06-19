package com.github.binarywang.wxpay.bean.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenliang
 * @date 2021-08-02 5:20 下午
 *
 * <pre>
 *   微信预扣款请求参数
 * </pre>
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class WxPreWithholdRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 委托代扣协议ID
   */
  @SerializedName(value = "contract_id")
  private transient String contractId;

  /**
   * 直连商户号
   */
  @SerializedName(value = "mchid")
  private String mchId;

  /**
   * 公众号ID
   */
  @SerializedName(value = "appid")
  private String appId;

  /**
   * 预计扣款的金额信息
   */
  @SerializedName(value = "estimated_amount")
  private EstimateAmount estimateAmount;


  @Data
  public static class EstimateAmount implements Serializable {
    /**
     * 预计扣费金额
     */
    private Integer amount;

    /**
     * 人民币：CNY
     * 非必填
     */
    private String currency;
  }
}

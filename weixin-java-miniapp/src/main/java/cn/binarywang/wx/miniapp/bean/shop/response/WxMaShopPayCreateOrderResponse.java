package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liming1019
 * created on  2022/8/10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaShopPayCreateOrderResponse  extends WxMaShopBaseResponse implements Serializable {
  private static final long serialVersionUID = -375471325664721192L;

  @SerializedName("payment_params")
  private PaymentParamsDTO paymentParams;

  @NoArgsConstructor
  @Data
  public static class PaymentParamsDTO {
    @SerializedName("timeStamp")
    private Integer timeStamp;
    @SerializedName("nonceStr")
    private String nonceStr;
    @SerializedName("package")
    private String packageX;
    @SerializedName("paySign")
    private String paySign;
    @SerializedName("signType")
    private String signType;
  }
}


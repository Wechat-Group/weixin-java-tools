package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 生成支付参数响应
 *
 * @author zhongjun
 * created on  2022/5/17
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaShopGetPaymentParamsResponse extends WxMaShopBaseResponse implements Serializable {
  private static final long serialVersionUID = -8796836131438585559L;

  @SerializedName("payment_params")
  private PaymentParams paymentParams;

  @Getter
  @Setter
  public static class PaymentParams {

    private String timeStamp;

    private String nonceStr;

    @SerializedName("package")
    private String packageValue;

    private String signType;

    private String paySign;
  }

}

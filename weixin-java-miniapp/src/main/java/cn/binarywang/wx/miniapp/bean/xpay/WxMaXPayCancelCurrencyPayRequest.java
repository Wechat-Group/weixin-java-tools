package cn.binarywang.wx.miniapp.bean.xpay;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaXPayCancelCurrencyPayRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("openid")
  private String openid;
  @SerializedName("env")
  private Integer env;
  @SerializedName("user_ip")
  private String userIp;

  /*
   * 退款单的单号
   */
  @SerializedName("order_id")
  private String orderId;
  /*
   * 代币支付时传的order_id
   */
  @SerializedName("pay_order_id")
  private String payOrderId;
  /*
   * 退款金额
   */
  @SerializedName("amount")
  private Long amount;
  @SerializedName("device_type")
  private Integer deviceType;


  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}

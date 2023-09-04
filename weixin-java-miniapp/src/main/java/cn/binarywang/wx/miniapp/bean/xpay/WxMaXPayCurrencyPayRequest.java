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
public class WxMaXPayCurrencyPayRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("openid")
  private String openid;
  @SerializedName("env")
  private Integer env;
  @SerializedName("user_ip")
  private String userIp;

  @SerializedName("amount")
  private Long amount;

  @SerializedName("order_id")
  private String orderId;

  @SerializedName("device_type")
  private Integer deviceType;

  @SerializedName("payitem")
  private String payitem;//物品信息。记录到账户流水中。如:[{"productid":"物品id", "unit_price": 单价, "quantity": 数量}]

  @SerializedName("remark")
  private String remark;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}

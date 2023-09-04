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
public class WxMaXPayPresentCurrencyRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("openid")
  private String openid;
  @SerializedName("env")
  private Integer env;

  @SerializedName("order_id")
  private String orderId;

  @SerializedName("device_type")
  private Integer deviceType;

  @SerializedName("amount")
  private Long amount;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}

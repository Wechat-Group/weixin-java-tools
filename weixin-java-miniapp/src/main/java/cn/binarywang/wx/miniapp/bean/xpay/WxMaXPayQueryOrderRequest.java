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
public class WxMaXPayQueryOrderRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("openid")
  private String openid;

  @SerializedName("env")
  private Integer env;

  /*
   * 创建的订单号
   */
  @SerializedName("order_id")
  private String orderId;
  /*
   * 微信内部单号(与order_id二选一)
   */
  @SerializedName("wx_order_id")
  private String wxOrderId;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}

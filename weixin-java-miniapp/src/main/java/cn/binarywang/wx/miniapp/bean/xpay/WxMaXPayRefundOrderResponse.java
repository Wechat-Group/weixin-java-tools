package cn.binarywang.wx.miniapp.bean.xpay;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
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
public class WxMaXPayRefundOrderResponse extends WxMaBaseResponse implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("refund_order_id")
  private String refundOrderId;
  @SerializedName("refund_wx_order_id")
  private String refundWxOrderId;
  @SerializedName("pay_order_id")
  private String payOrderId;

  @SerializedName("pay_wx_order_id")
  private String payWxOrderId;


  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}

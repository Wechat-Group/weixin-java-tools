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
public class WxMaXPayRefundOrderRequest implements Serializable {
  private static final long serialVersionUID = 7495157056049312108L;
  @SerializedName("openid")
  private String openid;
  @SerializedName("env")
  private Integer env;
  @SerializedName("order_id")
  private String orderId;
  @SerializedName("wx_order_id")
  private String wxOrderId;
  @SerializedName("refund_order_id")
  private String refundOrderId;


  @SerializedName("left_fee")
  private Long leftFee;
  @SerializedName("refund_fee")
  private Long refundFee;
  @SerializedName("biz_meta")
  private String bizMeta;

  @SerializedName("refund_reason")
  private String refundReason;

  @SerializedName("req_from")
  private String reqFrom;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }
}

package cn.binarywang.wx.miniapp.bean.shop.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liming1019
 * created on  2022/8/31
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaShopPayOrderRefundRequest implements Serializable {
  private static final long serialVersionUID = -5850024411710741165L;

  @SerializedName("openid")
  private String openid;
  @SerializedName("mchid")
  private String mchid;
  @SerializedName("trade_no")
  private String tradeNo;
  @SerializedName("transaction_id")
  private String transactionId;
  @SerializedName("refund_no")
  private String refundNo;
  @SerializedName("total_amount")
  private int totalAmount;
  @SerializedName("refund_amount")
  private int refundAmount;
}


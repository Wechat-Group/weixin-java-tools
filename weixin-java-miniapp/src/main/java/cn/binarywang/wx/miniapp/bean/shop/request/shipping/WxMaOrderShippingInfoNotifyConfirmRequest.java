package cn.binarywang.wx.miniapp.bean.shop.request.shipping;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xzh
 * created on  2023/5/17 17:01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaOrderShippingInfoNotifyConfirmRequest implements Serializable {

  private static final long serialVersionUID = -6868378515860675152L;

  /**
   * 原支付交易对应的微信订单号
   */
  @SerializedName("transaction_id")
  private String transactionId;
  /**
   * 支付下单商户的商户号，由微信支付生成并下发
   */
  @SerializedName("merchant_id")
  private String merchantId;
  /**
   * 二级商户号
   */
  @SerializedName("sub_merchant_id")
  private String subMerchantId;
  /**
   * 商户系统内部订单号，只能是数字、大小写字母`_-*`且在同一个商户号下唯一。
   */
  @SerializedName("merchant_trade_no")
  private String merchantTradeNo;

  /**
   * 快递签收时间，时间戳形式。
   */
  @SerializedName("received_time")
  private Long receivedTime;
}

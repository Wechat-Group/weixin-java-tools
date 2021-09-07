package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * @date 2021/3/23
 */
@Data
public class WxMaShopAddOrderResult implements Serializable {
  private static final long serialVersionUID = -6574489801942310752L;

  /**
   * 交易组件平台订单ID
   */
  @SerializedName("order_id")
  private Long orderId;
  /**
   * 交易组件平台订单ID
   */
  @SerializedName("out_order_id")
  private String outOrderId;
  /**
   * 拉起收银台的ticket
   */
  @SerializedName("ticket")
  private String ticket;
  /**
   * ticket有效截止时间
   */
  @SerializedName("ticket_expire_time")
  private String ticketExpireTime;
  /**
   * 订单最终价格（单位：分）
   */
  @SerializedName("final_price")
  private Integer finalPrice;

}


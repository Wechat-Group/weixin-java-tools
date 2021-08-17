package cn.binarywang.wx.miniapp.bean.shop.request;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liming1019
 * @date 2021/8/12
 */
@Data
@Builder
public class WxMaShopDeliveryRecieveRequest implements Serializable {
  private static final long serialVersionUID = 1540854758624081221L;

  /**
   * order_id : 123456
   * out_order_id : xxxxx
   * openid : oTVP50O53a7jgmawAmxKukNlq3XI
   */

  @SerializedName("order_id")
  private Long orderId;
  @SerializedName("out_order_id")
  private String outOrderId;
  @SerializedName("openid")
  private String openid;
}

package cn.binarywang.wx.miniapp.bean.shop.request;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liming1019
 * @date 2021/8/12
 */
@Data
@Builder
public class WxMaShopDeliverySendRequest implements Serializable {
  private static final long serialVersionUID = -4034672301224469057L;

  /**
   * order_id : 123456
   * out_order_id : xxxxx
   * openid : oTVP50O53a7jgmawAmxKukNlq3XI
   * finish_all_delivery : 0
   * delivery_list : [{"delivery_id":"SF","waybill_id":"23424324253"}]
   */

  @SerializedName("order_id")
  private Long orderId;
  @SerializedName("out_order_id")
  private String outOrderId;
  @SerializedName("openid")
  private String openid;
  @SerializedName("finish_all_delivery")
  private Integer finishAllDelivery;
  @SerializedName("delivery_list")
  private List<DeliveryListBean> deliveryList;

  @Data
  @Builder
  public static class DeliveryListBean implements Serializable {
    /**
     * delivery_id : SF
     * waybill_id : 23424324253
     */

    @SerializedName("delivery_id")
    private String deliveryId;
    @SerializedName("waybill_id")
    private String waybillId;
  }
}

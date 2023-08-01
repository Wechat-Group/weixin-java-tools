package me.chanjar.weixin.open.bean.shoppingOrders;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CombinedShippingInfo implements Serializable {
  private static final long serialVersionUID = -2338140924295957062L;
  /**
   * 必填
   * 合单订单，需要上传物流详情的合单订单，根据订单类型二选一
   */
  @SerializedName("order_key")
  private OrderKeyBean orderKey;

  /**
   * 子单物流详情
   */
  @SerializedName("sub_orders")
  private List<SubOrderListBean> subOrders;

  /**
   * 必填
   * 支付者，支付者信息
   */
  @SerializedName("payer")
  private PayerBean payer;


  /**
   * 必填
   * 上传时间，用于标识请求的先后顺序 示例值: `2022-12-15T13:29:35.120+08:00
   */
  @SerializedName("upload_time")
  private String uploadTime;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class SubOrderListBean implements Serializable {
    private static final long serialVersionUID = -8792281478692710237L;

    /**
     * 必填
     * 订单，需要上传购物详情的订单，根据订单类型二选一
     */
    @SerializedName("order_key")
    private OrderKeyBean orderKey;


    /**
     * 必填
     * 发货模式，发货模式枚举值：1、UNIFIED_DELIVERY（统一发货）2、SPLIT_DELIVERY（分拆发货）
     * 示例值: UNIFIED_DELIVERY
     */
    @SerializedName("delivery_mode")
    private int deliveryMode;

    /**
     * 必填
     * 物流信息列表，发货物流单列表，支持统一发货（单个物流单）和分拆发货（多个物流单）两种模式，多重性: [1, 10]
     */
    @SerializedName("shipping_list")
    private List<ShippingListBean> shippingList;

  }
}

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
public class ShippingInfo implements Serializable {

  private static final long serialVersionUID = 2105037984591600658L;
  /**
   * 必填
   * 订单，需要上传物流信息的订单
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

  /**
   * 必填
   * 上传时间，用于标识请求的先后顺序 示例值: `2022-12-15T13:29:35.120+08:00
   */
  @SerializedName("upload_time")
  private String uploadTime;
}

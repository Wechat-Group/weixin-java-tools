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
public class CombinedShoppingInfo implements Serializable {
  private static final long serialVersionUID = 8325009858985444023L;
  /**
   * 必填
   * 合单订单，需要上传购物详情的合单订单，根据订单类型二选一
   */
  @SerializedName("order_key")
  private OrderKeyBean orderKey;

  /**
   * 子单购物详情
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
     * 商户交易订单编号，商户侧的交易订单详情页向用户展示的订单编号
     * 示例值: 232457563423 字符字节限制: [1, 64]
     */
    @SerializedName("merchant_order_no")
    private String merchantOrderNo;

    /**
     * 必填
     * 商户交易订单详情页链接，用户查看“商城订单”时，跳转至商户侧查看交易订单详情页的链接。详情页类别可以为H5或小程序
     */
    @SerializedName("order_detail_jump_link")
    private ShoppingInfo.OrderDetailBean orderDetailJumpLink;

    /**
     * 订单购买的商品列表，用户在订单中购买的全部商品明细的列表，最多可以上传50个商品
     * 多重性: [1, 50]
     */
    @SerializedName("item_list")
    private List<ShoppingInfo.OrderItemListBean> itemList;
    /**
     * 物流形式，订单商品配送的物流形式，默认为实体物流
     * 物流模式，发货方式枚举值：1、实体物流配送采用快递公司进行实体物流配送形式 2、同城配送 3、虚拟商品，虚拟商品，例如话费充值，点卡等，无实体配送形式 4、用户自提
     */
    @SerializedName("logistics_type")
    private int logisticsType;
  }
}

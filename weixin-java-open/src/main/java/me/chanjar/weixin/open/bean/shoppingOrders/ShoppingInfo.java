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
public class ShoppingInfo implements Serializable {

  private static final long serialVersionUID = 2105037984591600658L;
  /**
   * 必填
   * 订单，需要上传物流信息的订单
   */
  @SerializedName("order_key")
  private OrderKeyBean orderKey;

  /**
   * 购物详情列表
   */
  @SerializedName("order_list")
  private List<OrderListBean> orderList;

  /**
   * 必填
   * 支付者，支付者信息
   */
  @SerializedName("payer")
  private PayerBean payer;

  /**
   * 物流形式，订单商品配送的物流形式，默认为实体物流
   * 物流模式，发货方式枚举值：1、实体物流配送采用快递公司进行实体物流配送形式 2、同城配送 3、虚拟商品，虚拟商品，例如话费充值，点卡等，无实体配送形式 4、用户自提
   */
  @SerializedName("logistics_type")
  private int logisticsType;

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
  public static class OrderListBean implements Serializable {
    private static final long serialVersionUID = -7690807867756471672L;
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
    private OrderDetailBean orderDetailJumpLink;

    /**
     * 订单购买的商品列表，用户在订单中购买的全部商品明细的列表，最多可以上传50个商品
     * 多重性: [1, 50]
     */
    @SerializedName("item_list")
    private List<OrderItemListBean> itemList;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class OrderDetailBean implements Serializable {
    private static final long serialVersionUID = -8002249022516272034L;
    /**
     * 链接地址（链接类型为H5时必填）
     * 示例值: https://www.weixin.qq.com/wxpay/pay.php
     * 字符字节限制: [1, 1024]
     * 匹配正则表达式: ^https?😕/([^\s/?#[]@]+@)?([^\s/?#@:]+)(?::\d{2,5})?([^[]]*)$
     */
    @SerializedName("url")
    private String url;
    /**
     * 小程序appid（链接类型为MINIAPP时必填）
     * 示例值: wxd678efh567hg6787 字符字节限制: [1, 32]
     */
    @SerializedName("appid")
    private String appId;
    /**
     * 小程序path（链接类型为MINIAPP时必填）
     * 示例值: /path/index/index 字符字节限制: [1, 512]
     */
    @SerializedName("path")
    private String path;
    /**
     * 必填
     * 链接类型枚举值：1、URL；2、MINI_PROGRAM
     * 示例值: MINI_PROGRAM
     */
    @SerializedName("type")
    private int type;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class OrderItemListBean implements Serializable {
    private static final long serialVersionUID = -2989527770771246748L;
    /**
     * 商户侧商品ID，商户系统内部商品编码，用于标识不同的商品。请注意，当发货模式选择“分拆发货”时，需要使用商户侧商品ID来标记各物流单中包含的具体商品
     * 示例值: 1246464644 字符字节限制: [1, 64]
     */
    @SerializedName("merchant_item_id")
    private String merchantItemId;
    /**
     * 必填
     * 商品名称
     * 示例值: iPhoneX 256G 字符长度限制: [1, 256]
     */
    @SerializedName("name")
    private String name;
    /**
     * 商品描述
     * 示例值: Image形象店-深圳腾大-QQ公仔 字符长度限制: [1, 512]
     */
    @SerializedName("description")
    private String description;
    /**
     * 必填
     * 商品单价（单位：分）
     */
    @SerializedName("unit_price")
    private long unitPrice;
    /**
     * 必填
     * 购买数量
     * 示例值: 2
     */
    @SerializedName("quantity")
    private long quantity;
    /**
     * 商品图片链接
     * 示例值: https://qpic.cn/xxx
     * 多重性: [1, 3]
     * 字符字节限制: [1, 1024]
     * 匹配正则表达式: ^https?😕/([^\s/?#[]@]+@)?([^\s/?#@:]+)(?::\d{2,5})?([^[]]*)$
     */
    @SerializedName("image_url")
    private List<String> imageUrl;
  }

}

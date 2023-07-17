package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xzh
 * created on  2023/5/17 17:01
 */
@Data
public class WxMaOrderShippingInfoBaseResponse implements Serializable {
  private static final long serialVersionUID = -5414031943436195493L;
  /**
   * 错误码
   */
  @SerializedName("errcode")
  private Integer errCode;

  /**
   * 错误原因
   */
  @SerializedName("errmsg")
  private String errMsg;

  //region 类型定义
  @Data
  public static class Order implements Serializable {
    private static final long serialVersionUID = -1390355751615987663L;
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
     * 以分号连接的该支付单的所有商品描述，当超过120字时自动截断并以 “...” 结尾。
     */
    @SerializedName("description")
    private String description;

    /**
     * 支付单实际支付金额，整型，单位：分钱。
     */
    @SerializedName("paid_amount")
    private Long paidAmount;
    /**
     * 支付者openid
     */
    @SerializedName("openid")
    private String openId;

    /**
     * 交易创建时间，时间戳形式
     */
    @SerializedName("trade_create_time")
    private Long tradeCreateTime;
    /**
     * 支付时间，时间戳形式。
     */
    @SerializedName("pay_time")
    private Long payTime;

    /**
     * 订单状态枚举：(1) 待发货；(2) 已发货；(3) 确认收货；(4) 交易完成；(5) 已退款。
     */
    @SerializedName("order_state")
    private Integer orderState;
    /**
     * 是否处在交易纠纷中
     */
    @SerializedName("in_complaint")
    private Boolean inComplaint;

    /**
     * 发货信息
     */
    @SerializedName("shipping")
    private Shipping shipping;
  }

  @Data
  public static class Shipping implements Serializable {

    private static final long serialVersionUID = -3527308640256070121L;

    /**
     * 发货模式，发货模式枚举值：1、UNIFIED_DELIVERY（统一发货）2、SPLIT_DELIVERY（分拆发货） 示例值: UNIFIED_DELIVERY
     */
    @SerializedName("delivery_mode")
    private Integer deliveryMode;
    /**
     * 物流模式，发货方式枚举值：1、实体物流配送采用快递公司进行实体物流配送形式 2、同城配送 3、虚拟商品，虚拟商品，例如话费充值，点卡等，无实体配送形式 4、用户自提
     */
    @SerializedName("logistics_type")
    private Integer logisticsType;
    /**
     * 是否已完成全部发货
     */
    @SerializedName("finish_shipping")
    private Boolean finishShipping;
    /**
     * 在小程序后台发货信息录入页录入的商品描述
     */
    @SerializedName("goods_desc")
    private String goodsDesc;
    /**
     * 已完成全部发货的次数，未完成时为 0，完成时为 1，重新发货并完成后为 2。
     */
    @SerializedName("finish_shipping_count")
    private Integer finishShippingCount;
    /**
     * 物流信息列表，发货物流单列表，支持统一发货（单个物流单）和分拆发货（多个物流单）两种模式。
     */
    @SerializedName("shipping_list")
    private List<ShippingItem> shippingList;

  }

  @Data
  public static class ShippingItem implements Serializable {

    private static final long serialVersionUID = 7064368114873624112L;

    /**
     * 物流单号，示例值: "323244567777"。
     */
    @SerializedName("tracking_no")
    private String trackingNo;
    /**
     * 同城配送公司名或物流公司编码，快递公司ID，参见「查询物流公司编码列表」 示例值: "DHL"。
     */
    @SerializedName("express_company")
    private String expressCompany;
    /**
     * 使用上传物流信息 API 录入的该物流信息的商品描述。
     */
    @SerializedName("goods_desc")
    private String goodsDesc;
    /**
     * 该物流信息的上传时间，时间戳形式。
     */
    @SerializedName("upload_time")
    private Long uploadTime;

    /**
     * 联系方式
     */
    @SerializedName("contact")
    private Contact contact;
  }

  @Data
  public static class Contact implements Serializable {
    private static final long serialVersionUID = -320914533207502380L;

    /**
     * 寄件人联系方式
     */
    @SerializedName("consignor_contact")
    private String consignorContact;

    /**
     * 收件人联系方式
     */
    @SerializedName("receiver_contact")
    private String receiverContact;
  }
  //endregion
}

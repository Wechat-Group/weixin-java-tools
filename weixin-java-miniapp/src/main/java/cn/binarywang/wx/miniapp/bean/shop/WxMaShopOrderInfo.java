package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author Boris
 * created on  2021/3/23
 */
@Data
public class WxMaShopOrderInfo implements Serializable {
  private static final long serialVersionUID = -159624260640727372L;

  /**
   * 创建时间
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 商家自定义订单ID
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("out_order_id")
  private String outOrderId;

  /**
   * 用户的openid
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 商家小程序该订单的页面path，用于微信侧订单中心跳转
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("path")
  private String path;

  /**
   * 商家小程序该订单的用户id
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("out_user_id")
  private String outUserId;

  /**
   * 订单详情
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("order_detail")
  private WxMaShopOrderDetail orderDetail;

  /**
   * 快递信息
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("delivery_detail")
  private WxMaShopDeliveryDetail deliveryDetail;

  /**
   * 地址信息
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("address_info")
  private WxMaShopAddressInfo addressInfo;

  /**
   * 订单类型：0，普通单，1，二级商户单
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("fund_type")
  private Integer fundType; // 订单类型：0，普通单，1，二级商户单
  /**
   * unix秒级时间戳，订单超时时间，取值：[15min, 1d]
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("expire_time")
  private Long expireTime; // unix秒级时间戳，订单超时时间，取值：[15min, 1d]
  /**
   * 取值范围，[7，3 * 365]，单位：天
   * <pre>
   * 是否必填：选填
   * </pre>
   */
  @SerializedName("aftersale_duration")
  private Integer aftersaleDuration; // 取值范围，[7，3 * 365]，单位：天
  /**
   * 会影响主播归因、分享员归因等，从下单前置检查获取
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("trace_id")
  private String traceId; // 会影响主播归因、分享员归因等，从下单前置检查获取
  /**
   * 默认退货地址，退货售后超时时，会让用户将货物寄往此地址
   * <pre>
   * 是否必填：选填
   * </pre>œ
   */
  @SerializedName("default_receiving_address")
  private WxMaShopAddressInfo defaultReceivingAddress; // 默认退货地址，退货售后超时时，会让用户将货物寄往此地址
  /**
   * 生成的order_id以字符串形式返回
   * <pre>
   * 是否必填：选填
   * </pre>
   */
  @SerializedName("stringify_64bits_number")
  private Boolean stringify64bitsNumber; // 生成的order_id以字符串形式返回

}

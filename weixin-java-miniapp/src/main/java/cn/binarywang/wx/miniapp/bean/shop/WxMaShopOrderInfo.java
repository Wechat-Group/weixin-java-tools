package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author Boris
 * @date 2021/3/23
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

}

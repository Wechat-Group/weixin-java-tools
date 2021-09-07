package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * @date 2021/3/23
 */
@Data
public class WxMaShopOrderResult implements Serializable {
  private static final long serialVersionUID = -2665426592693969921L;

  /**
   * 交易组件平台订单ID
   */
  @SerializedName("order_id")
  private Long orderId;

  /**
   * 商家自定义订单ID
   */
  @SerializedName("out_order_id")
  private String outOrderId;

  /**
   * 订单状态
   */
  @SerializedName("status")
  private Integer status;

  /**
   * 商家小程序该订单的页面path，用于微信侧订单中心跳转
   */
  @SerializedName("path")
  private String path;

  /**
   * 商家小程序该订单的用户id
   */
  @SerializedName("out_user_id")
  private String outUserId;

  /**
   * 订单详情
   */
  @SerializedName("order_detail")
  private WxMaShopOrderDetail orderDetail;
}

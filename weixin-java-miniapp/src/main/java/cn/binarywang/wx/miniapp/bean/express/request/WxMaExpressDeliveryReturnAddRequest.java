package cn.binarywang.wx.miniapp.bean.express.request;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 创建退货ID
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxMaExpressDeliveryReturnAddRequest implements Serializable {
  private static final long serialVersionUID = -9111430627246688840L;

  /**
   * 商家内部使用的退货编号
   * <pre>
   * 是否必填： 是
   * 描述：
   * </pre>
   */
  @SerializedName("shop_order_id")
  private String shopOrderId;

  /**
   * 商家退货地址
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("biz_addr")
  private WxMaExpressOrderPerson bizAddr;

  /**
   * 用户购物时的收货地址
   * <pre>
   * 是否必填： 否
   * </pre>
   */
  @SerializedName("user_addr")
  private WxMaExpressOrderPerson userAddr;

  /**
   * 退货用户的openid
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("openid")
  private String openid;


  /**
   * 退货订单在小程序中的path
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("order_path")
  private String orderPath;

  /**
   * 退货订单的金额（单位：分）
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("goods_list")
  private List<WxMaExpressReturnOrder> goodsList;


  /**
   * 退货订单的金额（单位：分）
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("order_price")
  private Integer orderPrice;



  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

}

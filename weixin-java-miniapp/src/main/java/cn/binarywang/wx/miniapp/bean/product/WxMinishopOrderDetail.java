package cn.binarywang.wx.miniapp.bean.product;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * created on  2021/3/23
 */
@Data
public class WxMinishopOrderDetail implements Serializable {
  private static final long serialVersionUID = 3325843289672341160L;

  /**
   * 下单商品信息
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("product_infos")
  private List<WxMinishopProductInfo> productInfos;

  /**
   * 支付信息 (当作为返回结果，payorder时action_type!=6时存在)
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("pay_info")
  private WxMinishopPayInfo payInfo;

  /**
   * 价格信息
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("price_info")
  private WxMinishopPriceInfo priceInfo;

  /**
   * 必须调过发货接口才会存在这个字段
   */
  @SerializedName("delivery_info")
  private WxMinishopDeliveryInfo deliveryInfo;
}

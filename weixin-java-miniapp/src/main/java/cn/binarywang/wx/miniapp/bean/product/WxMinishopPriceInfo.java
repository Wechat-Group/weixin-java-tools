package cn.binarywang.wx.miniapp.bean.product;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * created on  2021/3/23
 */
@Data
public class WxMinishopPriceInfo implements Serializable {
  private static final long serialVersionUID = 1588840927992523263L;

  @SerializedName("product_price")
  private Integer productPrice;
  /**
   * 该订单最终的金额（单位：分）
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("order_price")
  private Integer orderPrice;
  /**
   * 运费（单位：分）
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("freight")
  private Integer freight;
  /**
   * 优惠金额（单位：分）
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("discounted_price")
  private Integer discountedPrice;

  @SerializedName("is_discounted")
  private Boolean isDiscounted;
}

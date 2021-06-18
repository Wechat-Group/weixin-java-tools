package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * @date 2021/3/23
 * @description:
 */
@Data
public class WxMaShopPriceInfo implements Serializable {

  private static final long serialVersionUID = 1588840927992523263L;
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
  /**
   * 附加金额（单位：分）
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("additional_price")
  private Integer additionalPrice;
  /**
   * 附加金额备注
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("additional_remarks")
  private String additionalRemarks;
}

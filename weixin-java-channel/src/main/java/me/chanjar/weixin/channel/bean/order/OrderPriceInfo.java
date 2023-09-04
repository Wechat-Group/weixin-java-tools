package me.chanjar.weixin.channel.bean.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商店订单价格信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class OrderPriceInfo implements Serializable {

  private static final long serialVersionUID = 5216506688949493432L;
  /** 商品总价，单位为分 */
  @JsonProperty("product_price")
  private Integer productPrice;

  /** 订单金额，单位为分 */
  @JsonProperty("order_price")
  private Integer orderPrice;

  /** 运费，单位为分 */
  @JsonProperty("freight")
  private Integer freight;

  /** 优惠金额，单位为分 */
  @JsonProperty("discounted_price")
  private Integer discountedPrice;

  /** 是否有优惠 */
  @JsonProperty("is_discounted")
  private Boolean isDiscounted;

  /** 订单原始价格，单位为分 */
  @JsonProperty("original_order_price")
  private Integer originalOrderPrice;

  /** 商品预估价格，单位为分 */
  @JsonProperty("estimate_product_price")
  private Integer estimateProductPrice;

  /** 改价后降低金额，单位为分 */
  @JsonProperty("change_down_price")
  private Integer changeDownPrice;

  /** 改价后运费，单位为分 */
  @JsonProperty("change_freight")
  private Integer changeFreight;

  /** 是否修改运费 */
  @JsonProperty("is_change_freight")
  private Boolean changeFreighted;

}

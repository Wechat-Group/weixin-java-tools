package me.chanjar.weixin.cp.bean.kf.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Wx cp kf channels shop order msg.
 *
 * @author dalin created on 2023/1/10 17:28
 *
 */
@NoArgsConstructor
@Data
public class WxCpKfChannelsShopOrderMsg {

  /**
   * 订单号
   */
  @SerializedName("order_id")
  private String orderId;

  /**
   * 商品标题
   */
  @SerializedName("product_titles")
  private String productTitles;

  /**
   * 订单价格描述
   */
  @SerializedName("price_wording")
  private String priceWording;

  /**
   * 订单状态
   */
  @SerializedName("state")
  private String state;

  /**
   * 订单缩略图
   */
  @SerializedName("image_url")
  private String imageUrl;

  /**
   * 店铺名称
   */
  @SerializedName("shop_nickname")
  private String shopNickname;

}

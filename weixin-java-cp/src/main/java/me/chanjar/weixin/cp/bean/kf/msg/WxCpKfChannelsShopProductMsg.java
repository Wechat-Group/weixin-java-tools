package me.chanjar.weixin.cp.bean.kf.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Wx cp kf channels shop product msg.
 *
 * @author dalin created on 2023/1/10 17:26
 *
 */
@NoArgsConstructor
@Data
public class WxCpKfChannelsShopProductMsg {

  /**
   * 商品ID
   */
  @SerializedName("product_id")
  private String productId;

  /**
   * 商品图片
   */
  @SerializedName("head_img")
  private String headImg;

  /**
   * 商品标题
   */
  @SerializedName("title")
  private String title;

  /**
   * 商品价格，以分为单位
   */
  @SerializedName("sales_price")
  private String salesPrice;

  /**
   * 店铺名称
   */
  @SerializedName("shop_nickname")
  private String shopNickname;

  /**
   * 店铺头像
   */
  @SerializedName("shop_head_img")
  private String shopHeadImg;

}

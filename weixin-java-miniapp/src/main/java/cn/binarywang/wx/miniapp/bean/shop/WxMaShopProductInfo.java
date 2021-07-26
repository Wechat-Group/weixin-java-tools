package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author leiin
 * @date 2021/3/23
 */
@Data
public class WxMaShopProductInfo implements Serializable {
  private static final long serialVersionUID = 8979181840150112093L;

  /**
   * 商家自定义商品ID
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("out_product_id")
  private String outProductId;
  /**
   * 商家自定义商品skuID，可填空字符串（如果这个product_id下没有sku）
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("out_sku_id")
  private String outSkuId;
  /**
   * 购买的数量
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("product_cnt")
  private Integer productCnt;
  /**
   * 生成订单时商品的售卖价（单位：分），可以跟上传商品接口的价格不一致
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("sale_price")
  private Integer salePrice;
  /**
   * 生成订单时商品的头图
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("head_img")
  private String headImg;
  /**
   * 生成订单时商品的标题
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("title")
  private String title;
  /**
   * 绑定的小程序商品路径
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("path")
  private String path;

  // 以下字段仅作为订单信息返回字段
  /**
   * 交易组件平台内部商品ID
   */
  @SerializedName("product_id")
  private Integer productId;

  /**
   * 交易组件平台内部skuID，可填0（如果这个product_id下没有sku）
   */
  @SerializedName("sku_id")
  private Integer skuId;

  /**
   * 扣除优惠后单件sku的分摊价格（单位：分），如果没优惠则与sale_price一致
   */
  @SerializedName("real_price")
  private Integer realPrice;
}


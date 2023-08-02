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
public class WxMinishopProductInfo implements Serializable {
  private static final long serialVersionUID = 8979181840150112093L;
  /**
   * 交易组件平台内部商品ID
   */
  @SerializedName("product_id")
  private Integer productId;

  @SerializedName("out_product_id")
  private String outProductId;

  /**
   * 交易组件平台内部skuID，可填0（如果这个product_id下没有sku）
   */
  @SerializedName("sku_id")
  private Integer skuId;

  @SerializedName("out_sku_id")
  private String outSkuId;
  /**
   * 购买的数量
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("sku_cnt")
  private Integer skuCnt;

  @SerializedName("on_aftersale_sku_cnt")
  private Integer onAftersaleSkuCnt;

  @SerializedName("finish_aftersale_sku_cnt")
  private Integer finishAftersaleSkuCnt;
  /**
   * 生成订单时商品的标题
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("title")
  private String title;
  @SerializedName("thumb_img")
  private String thumbImg;

  @SerializedName("sku_attrs")
  private List<WxMinishopGoodsSkuAttr> skuAttrs;
  /**
   * 生成订单时商品的售卖价（单位：分）
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("sale_price")
  private Integer salePrice;

  @SerializedName("market_price")
  private Integer marketPrice;
}


package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * sku对象
 * </pre>
 *
 * @author <a href="https://github.com/borisbao">boris</a>
 * @since 2021-03-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaShopSkuInfo implements Serializable {


  private static final long serialVersionUID = -3617077838017818865L;

  /**
   * 商家自定义商品ID
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("out_product_id")
  private String outProductId;

  /**
   * 商家自定义商品ID
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("out_sku_id")
  private String outSkuId;


  /**
   * sku小图
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("thumb_img")
  private String thumbImg;

  /**
   * 售卖价格,以分为单位
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("sale_price")
  private Integer salePrice;

  /**
   * 售卖价格,以分为单位
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("market_price")
  private Integer marketPrice;

  /**
   * 库存
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("stock_num")
  private Integer stockNum;


  /**
   * 条形码
   * <pre>
   * 是否必填： 否
   * </pre>
   */
  @SerializedName("barcode")
  private String barcode;


  /**
   * 商品编码
   * <pre>
   * 是否必填： 否
   * </pre>
   */
  @SerializedName("sku_code")
  private String skuCode;

  /**
   * 销售属性
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("sku_attrs")
  private List<WxMaShopSkuAttribute> skuAttributeList;
}

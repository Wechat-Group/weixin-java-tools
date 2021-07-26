package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * @date 2021/3/23
 */
@Data
public class WxMaShopSkuWithoutAuditInfo implements Serializable {
  private static final long serialVersionUID = 3354108922805323888L;

  /**
   * 商家自定义skuID
   * <pre>
   * 是否必填： 是
   * </pre>
   */
  @SerializedName("out_sku_id")
  private String outSkuId;

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
}

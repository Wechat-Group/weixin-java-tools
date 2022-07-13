package cn.binarywang.wx.miniapp.bean.product;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class WxMinishopSku implements Serializable {
  private static final long serialVersionUID = 12373392723136246L;

  @SerializedName("product_id")
  private Long productId;

  @SerializedName("out_product_id")
  private String outProductId;

  @SerializedName("out_sku_id")
  private String outSkuId;

  @SerializedName("sku_id")
  private Long skuId;

  @SerializedName("thumb_img")
  private String thumbImg;

  @SerializedName("sale_price")
  private Integer salePrice;

  @SerializedName("market_price")
  private Integer marketPrice;

  @SerializedName("stock_num")
  private Integer stockNum;

  @SerializedName("sku_code")
  private String skuCode;

  @SerializedName("barcode")
  private String barCode;

  @SerializedName("sku_attrs")
  private List<WxMinishopGoodsSkuAttr> skuAttrs;
}

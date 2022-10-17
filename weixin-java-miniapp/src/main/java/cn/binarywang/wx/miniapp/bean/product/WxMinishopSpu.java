package cn.binarywang.wx.miniapp.bean.product;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class WxMinishopSpu implements Serializable {
  private static final long serialVersionUID = 6689040014027161007L;

  @SerializedName("product_id")
  private String productId;

  @SerializedName("out_product_id")
  private String outProductId;

  private String title;

  @SerializedName("sub_title")
  private String subTitle;

  @SerializedName("head_img")
  private List<String> headImgs;

  @SerializedName("desc_info")
  private DescInfo descInfo;

  @SerializedName("brand_id")
  private Long brandId;

  @SerializedName("cats")
  private List<MinishopShopCat> shopCats;

  private List<WxMinishopGoodsSkuAttr> attrs;

  private String model;

  @SerializedName("express_info")
  private ExpressInfo expressInfo;

  private List<WxMinishopSku> skus;

  @Data
  public static class DescInfo {
    private List<String> imgs;
  }

  @Data
  public static class ExpressInfo {
    @SerializedName("template_id")
    private Long templateId;
  }
}

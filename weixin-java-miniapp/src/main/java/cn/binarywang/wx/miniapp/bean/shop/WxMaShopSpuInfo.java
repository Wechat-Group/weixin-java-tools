package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class WxMaShopSpuInfo implements Serializable {

  private static final long serialVersionUID = 7237829277693177420L;

  /**
   * 交易组件平台内部商品ID，修改时与out_product_id二选一
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("product_id")
  private String productId;

  /**
   * 商家自定义商品ID，新建必填，修改时与product_id二选一
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("out_product_id")
  private String outProductId;

  /**
   * 标题
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

  /**
   * 主图,多张,列表
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("head_img")
  private List<String> headImg;

  /**
   * 商品资质图片
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("qualification_pics")
  private List<String> qualificationPics;

  /**
   * 商品详情
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("desc_info")
  private WxMaShopSpuDescInfo descInfo;

  /**
   * 第三级类目ID
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("third_cat_id")
  private Integer thirdCatId;

  /**
   * 品牌id
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("brand_id")
  private Integer brandId;

  /**
   * sku数组
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("skus")
  private List<WxMaShopSkuInfo> skus;
}

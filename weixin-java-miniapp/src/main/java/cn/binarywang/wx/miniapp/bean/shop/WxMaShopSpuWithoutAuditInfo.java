package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * @date 2021/3/23
 * @description:
 */
@Data
public class WxMaShopSpuWithoutAuditInfo {

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
   * 绑定的小程序商品路径
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("path")
  private String path;

  /**
   * sku数组
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("skus")
  private List<WxMaShopSkuWithoutAuditInfo> skus;
}

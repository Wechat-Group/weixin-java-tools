package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author leiin
 * @date 2021/3/23
 * 添加商品参数返回
 */
@Data
public class WxMaShopAddSpuResult implements Serializable {
  private static final long serialVersionUID = 2520459849240776617L;

  /**
   * 交易组件平台内部商品ID
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("product_id")
  private String productId;

  /**
   * 商家自定义商品ID
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("out_product_id")
  private String outProductId;

  /**
   * 创建时间，新建时返回
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * 更新时间，修改时返回
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("update_time")
  private String updateTime;
  /**
   * sku数组
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("skus")
  private List<WxMaShopSkuResult> skus;
}

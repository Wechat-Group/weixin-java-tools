package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author leiin
 * @date 2021/3/23
 */
@Data
public class WxMaShopSkuResult implements Serializable {
  private static final long serialVersionUID = 7127892618805299305L;

  /**
   * 交易组件平台自定义skuID
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("sku_id")
  private String skuId;

  /**
   * 商家自定义skuID
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("out_sku_id")
  private String outSkuId;
}

package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
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
public class WxMaShopSkuAttribute implements Serializable {


  private static final long serialVersionUID = -3617077838017818865L;


  /**
   * 销售属性key（自定义）
   * <pre>
   * 是否必填： 否
   * </pre>
   */
  @SerializedName("attr_key")
  private String attrKey;

  @SerializedName("attr_value")
  private String attrValue;
}

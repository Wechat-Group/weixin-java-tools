package cn.binarywang.wx.miniapp.bean.shop.response;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopGetSpuResult;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author leiin
 * @date 2021/3/23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaShopGetSpuResponse extends WxMaShopBaseResponse implements Serializable {
  private static final long serialVersionUID = -3781992184787152637L;

  /**
   * spu信息
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("spu")
  private WxMaShopGetSpuResult spu;
}

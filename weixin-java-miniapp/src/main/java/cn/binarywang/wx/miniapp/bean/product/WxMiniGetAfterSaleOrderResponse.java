package cn.binarywang.wx.miniapp.bean.product;

import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/7/11 20:58
 */
@Data
public class WxMiniGetAfterSaleOrderResponse extends WxMaShopBaseResponse {
  @SerializedName("after_sale_order")
  private WxMiniAfterSaleOrder afterSaleOrder;
}

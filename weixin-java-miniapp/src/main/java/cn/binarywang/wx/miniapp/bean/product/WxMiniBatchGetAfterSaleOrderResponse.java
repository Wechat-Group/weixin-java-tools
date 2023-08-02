package cn.binarywang.wx.miniapp.bean.product;

import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopBaseResponse;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/7/11 20:59
 */
@Data
public class WxMiniBatchGetAfterSaleOrderResponse extends WxMaShopBaseResponse {
  @SerializedName("after_sale_order_list")
  private List<WxMiniAfterSaleOrder> afterSaleOrderList;
}

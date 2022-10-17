package cn.binarywang.wx.miniapp.bean.shop.response;

import cn.binarywang.wx.miniapp.bean.shop.response.WxMaShopAfterSaleGetResponse.AftersaleInfosBean;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leiin
 * created on  2022/6/28 11:39 上午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaShopAfterSaleListResponse extends WxMaShopBaseResponse implements Serializable {
  @SerializedName("has_more")
  private Boolean hasMore;
  @SerializedName("after_sales_orders")
  private List<AftersaleInfosBean> afterSalesOrders;
}

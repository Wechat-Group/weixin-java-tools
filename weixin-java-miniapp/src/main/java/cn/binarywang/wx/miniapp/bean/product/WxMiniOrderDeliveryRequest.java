package cn.binarywang.wx.miniapp.bean.product;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/7/14 19:05
 */
@Data
public class WxMiniOrderDeliveryRequest {
  @SerializedName("order_id")
  private Long orderId;
  @SerializedName("delivery_list")
  private List<DeliveryListBean> deliveryList;

  @Data
  public static class DeliveryListBean implements Serializable {
    @SerializedName("delivery_id")
    private String deliveryId;
    @SerializedName("is_all_product")
    private Boolean isAllProduct;
    @SerializedName("waybill_id")
    private String waybillId;
    @SerializedName("product_infos")
    private List<ProductInfosBean> productInfoList;
  }

  @Data
  public static class ProductInfosBean implements Serializable {

    @SerializedName("product_id")
    private String productId;
    @SerializedName("sku_id")
    private String skuId;
    @SerializedName("product_cnt")
    private Integer productCnt;
  }
}

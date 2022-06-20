package cn.binarywang.wx.miniapp.bean.product;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * @date 2022/6/20 7:28 下午
 */
@Data
public class WxMinishopDeliveryInfo {
  @SerializedName("address_info")
  private WxMinishopAddressInfo addressInfo;
  @SerializedName("delivery_method")
  private String deliveryMethod;
  @SerializedName("delivery_product_info")
  private List<DeliveryProductInfo> deliveryProductInfo;
  @SerializedName("ship_done_time")
  private Long ship_done_time;
  @SerializedName("insurance_info")
  private InsuranceInfo insuranceInfo;
  @SerializedName("deliver_type")
  private String deliverType;
  @SerializedName("offline_delivery_time")
  private Long offlineDeliveryTime;
  @SerializedName("offline_pickup_time")
  private Long offlinePickupTime;

  @Data
  public static class DeliveryProductInfo {
    @SerializedName("waybill_id")
    private String waybillId;
    @SerializedName("delivery_id")
    private String deliveryId;
    @SerializedName("delivery_time")
    private String deliveryTime;
    @SerializedName("deliver_type")
    private String deliverType;
    @SerializedName("delivery_address")
    private WxMinishopAddressInfo deliveryAddress;
    @SerializedName("product_infos")
    private List<ProductInfo> productInfos;
  }

  @Data
  public static class InsuranceInfo {
    private String type;
    @SerializedName("insurance_price")
    private Long insurancePrice;
  }

  @Data
  public static class ProductInfo {
    @SerializedName("product_id")
    private Long product_id;
    @SerializedName("sku_id")
    private Long sku_id;
    @SerializedName("product_cnt")
    private Long product_cnt;
  }
}

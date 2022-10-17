package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author liming1019
 * created on  2022/8/25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaShopEcAfterSaleGetResponse extends WxMaShopBaseResponse implements Serializable {

  private static final long serialVersionUID = -338753264205536337L;

  @SerializedName("after_sales_order")
  private AfterSalesOrderDTO afterSalesOrder;

  @Data
  public static class AfterSalesOrderDTO implements Serializable {
    @SerializedName("out_aftersale_id")
    private String outAftersaleId;
    @SerializedName("aftersale_id")
    private Long aftersaleId;
    @SerializedName("out_order_id")
    private String outOrderId;
    @SerializedName("order_id")
    private Long orderId;
    @SerializedName("product_info")
    private ProductInfoDTO productInfo;
    @SerializedName("type")
    private Integer type;
    @SerializedName("return_info")
    private ReturnInfoDTO returnInfo;
    @SerializedName("orderamt")
    private Integer orderamt;
    @SerializedName("refund_reason_type")
    private Integer refundReasonType;
    @SerializedName("refund_reason")
    private String refundReason;
    @SerializedName("status")
    private Integer status;
    @SerializedName("create_time")
    private String createTime;
    @SerializedName("update_time")
    private String updateTime;

    @Data
    public static class ProductInfoDTO implements Serializable {
      @SerializedName("out_product_id")
      private String outProductId;
      @SerializedName("out_sku_id")
      private String outSkuId;
      @SerializedName("product_cnt")
      private Integer productCnt;
    }

    @Data
    public static class ReturnInfoDTO implements Serializable {
      @SerializedName("order_return_time")
      private String orderReturnTime;
      @SerializedName("delivery_id")
      private String deliveryId;
      @SerializedName("waybill_id")
      private String waybillId;
      @SerializedName("delivery_name")
      private String deliveryName;
    }
  }
}

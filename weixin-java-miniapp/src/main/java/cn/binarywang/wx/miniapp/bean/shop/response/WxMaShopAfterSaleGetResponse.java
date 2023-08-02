package cn.binarywang.wx.miniapp.bean.shop.response;

import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAfterSaleAddRequest.UploadMediaList;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaShopAfterSaleGetResponse extends WxMaShopBaseResponse implements Serializable {
  private static final long serialVersionUID = 213666907103837748L;

  @SerializedName("aftersale_infos")
  private List<AftersaleInfosBean> aftersaleInfos;

  @Data
  public static class AftersaleInfosBean implements Serializable {
    /**
     * out_order_id : xxxxx
     * out_aftersale_id : xxxxxx
     * openid : oTVP50O53a7jgmawAmxKukNlq3XI
     * type : 1
     * create_time : 2020-12-01 00:00:00
     * path : /pages/order.html?out_order_id=xxxxx
     * status : 1
     * refund : 100
     * product_infos : [{"out_product_id":"234245","out_sku_id":"23424","product_cnt":5}]
     */

    @SerializedName("aftersale_id")
    private Long aftersaleId;
    @SerializedName("out_aftersale_id")
    private String outAftersaleId;
    @SerializedName("out_order_id")
    private String outOrderId;
    @SerializedName("order_id")
    private Long orderId;
    @SerializedName("product_info")
    private List<ProductInfosBean> productInfo;
    @SerializedName("type")
    private Integer type;
    @SerializedName("return_info")
    private ReturnInfo returnInfo;
    @SerializedName("orderamt")
    private Long orderamt;
    @SerializedName("refund_reason")
    private String refundReason;
    @SerializedName("refund_reason_type")
    private Integer refundReasonType;
    @SerializedName("media_list")
    private UploadMediaList mediaList;
    @SerializedName("status")
    private Integer status;
    @SerializedName("create_time")
    private String createTime;
    @SerializedName("update_time")
    private String updateTime;
    @SerializedName("openid")
    private String openid;
    @SerializedName("refund_pay_detail")
    private RefundPayDetail refund_pay_detail;
    @SerializedName("return_id")
    private String returnId;
    @SerializedName("complaint_order_id_list")
    private List<Long> complaintOrderIdList;


    @Data
    public static class ProductInfosBean implements Serializable {
      /**
       * out_product_id : 234245
       * out_sku_id : 23424
       * product_cnt : 5
       */

      @SerializedName("out_product_id")
      private String outProductId;
      @SerializedName("product_id")
      private Long productId;
      @SerializedName("out_sku_id")
      private String outSkuId;
      @SerializedName("sku_id")
      private Long skuId;
      @SerializedName("product_cnt")
      private Integer productCnt;
    }

    @Data
    public static class ReturnInfo {
      @SerializedName("order_return_time")
      private Long orderReturnTime;
      @SerializedName("delivery_id")
      private String deliveryId;
      @SerializedName("waybill_id")
      private String waybillId;
      @SerializedName("delivery_name")
      private String deliveryName;
    }

    @Data
    public static class RefundPayDetail {
      @SerializedName("refund_id")
      private String refundId;
    }
  }
}

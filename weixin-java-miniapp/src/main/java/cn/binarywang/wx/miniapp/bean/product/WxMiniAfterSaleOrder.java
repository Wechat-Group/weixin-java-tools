package cn.binarywang.wx.miniapp.bean.product;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/7/11 20:33
 */
@Data
public class WxMiniAfterSaleOrder {
  @SerializedName("order_id")
  private Long orderId;
  @SerializedName("status")
  private String status;
  @SerializedName("openid")
  private String openid;
  @SerializedName("original_order_id")
  private Long originalOrderId;
  @SerializedName("product_info")
  private AfterSaleProductInfo productInfo;

  private AfterSaleDetails details;
  @SerializedName("refund_info")
  private RefundInfo refundInfo;
  @SerializedName("return_info")
  private ReturnInfo returnInfo;
  @SerializedName("merchant_upload_info")
  private MerchantUploadInfo merchantUploadInfo;
  @SerializedName("create_time")
  private Long createTime;
  @SerializedName("update_time")
  private Long updateTime;
  @SerializedName("reason")
  private String reason;
  @SerializedName("refund_resp")
  private RefundResp refundResp;
  private String type;

  @Data
  public static class AfterSaleProductInfo {
    @SerializedName("product_id")
    private Long productId;
    @SerializedName("sku_id")
    private Long skuId;
    @SerializedName("count")
    private Integer count;
  }

  @Data
  public static class AfterSaleDetails {

    @SerializedName("num")
    private Integer num;
    @SerializedName("desc")
    private String desc;
    @SerializedName("cancel_time")
    private Long cancelTime;
    @SerializedName("prove_imgs")
    private List<String> proveImgs;
    @SerializedName("tel_number")
    private String telNumber;
  }

  @Data
  public static class RefundInfo {
    private Long amount;
  }

  @Data
  public static class ReturnInfo {
    @SerializedName("delivery_id")
    private String deliveryId;
    @SerializedName("waybill_id")
    private String waybillId;
    @SerializedName("delivery_name")
    private String deliveryName;
  }

  @Data
  public static class MerchantUploadInfo {
    @SerializedName("reject_reason")
    private String rejectReason;
    @SerializedName("refund_certificates")
    private List<String> refundCertificates;
  }

  @Data
  public static class RefundResp {
    private String code;
    private Integer ret;
    private String message;
  }
}

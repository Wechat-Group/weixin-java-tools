package cn.binarywang.wx.miniapp.bean.shop.response;

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

    @SerializedName("out_order_id")
    private String outOrderId;
    @SerializedName("out_aftersale_id")
    private String outAftersaleId;
    @SerializedName("openid")
    private String openid;
    @SerializedName("type")
    private Integer type;
    @SerializedName("create_time")
    private String createTime;
    @SerializedName("path")
    private String path;
    @SerializedName("status")
    private Integer status;
    @SerializedName("refund")
    private Long refund;
    @SerializedName("product_infos")
    private List<ProductInfosBean> productInfos;

    @Data
    public static class ProductInfosBean implements Serializable {
      /**
       * out_product_id : 234245
       * out_sku_id : 23424
       * product_cnt : 5
       */

      @SerializedName("out_product_id")
      private String outProductId;
      @SerializedName("out_sku_id")
      private String outSkuId;
      @SerializedName("product_cnt")
      private Integer productCnt;
    }
  }
}

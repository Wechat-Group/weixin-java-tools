package cn.binarywang.wx.miniapp.bean.shop.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author liming1019
 * @date 2021/8/12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaShopAfterSaleAddRequest implements Serializable {
  private static final long serialVersionUID = 6652525413062887786L;

  /**
   * out_order_id : xxxxx
   * out_aftersale_id : xxxxxx
   * openid : oTVP50O53a7jgmawAmxKukNlq3XI
   * type : 1
   * create_time : 2020-12-01 00:00:00
   * status : 1
   * finish_all_aftersale : 0
   * path : /pages/aftersale.html?out_aftersale_id=xxxxx
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
  @SerializedName("status")
  private Integer status;
  @SerializedName("finish_all_aftersale")
  private Integer finishAllAftersale;
  @SerializedName("path")
  private String path;
  @SerializedName("refund")
  private Long refund;
  @SerializedName("product_infos")
  private List<ProductInfosBean> productInfos;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
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

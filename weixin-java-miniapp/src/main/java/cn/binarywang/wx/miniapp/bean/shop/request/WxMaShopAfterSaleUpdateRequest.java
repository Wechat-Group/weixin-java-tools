package cn.binarywang.wx.miniapp.bean.shop.request;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liming1019
 * @date 2021/8/12
 */
@Data
@Builder
public class WxMaShopAfterSaleUpdateRequest implements Serializable {
  private static final long serialVersionUID = 2712027510252221370L;

  /**
   * out_order_id : xxxxx
   * openid : oTVP50O53a7jgmawAmxKukNlq3XI
   * out_aftersale_id : xxxxxx
   * status : 1
   * finish_all_aftersale : 0
   */

  @SerializedName("out_order_id")
  private String outOrderId;
  @SerializedName("openid")
  private String openid;
  @SerializedName("out_aftersale_id")
  private String outAftersaleId;
  @SerializedName("status")
  private Integer status;
  @SerializedName("finish_all_aftersale")
  private Integer finishAllAftersale;
}

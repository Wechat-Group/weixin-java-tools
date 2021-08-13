package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author liming1019
 * @date 2021/8/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaShopAuditResultResponse extends WxMaShopBaseResponse implements Serializable {
  private static final long serialVersionUID = -1068201722686667490L;

  /**
   * data : {"status":9,"brand_id":0,"reject_reason":"请重新提交审核"}
   */

  @SerializedName("data")
  private DataBean data;

  @Data
  public static class DataBean implements Serializable {
    /**
     * status : 9
     * brand_id : 0
     * reject_reason : 请重新提交审核
     */

    @SerializedName("status")
    private Integer status;
    @SerializedName("brand_id")
    private Integer brandId;
    @SerializedName("reject_reason")
    private String rejectReason;
  }
}

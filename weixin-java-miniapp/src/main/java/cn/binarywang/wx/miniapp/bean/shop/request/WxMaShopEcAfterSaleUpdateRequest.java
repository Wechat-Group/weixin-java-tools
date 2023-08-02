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
 * created on  2022/8/26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaShopEcAfterSaleUpdateRequest implements Serializable {
  private static final long serialVersionUID = 349486861004919697L;

  @SerializedName("out_aftersale_id")
  private String outAftersaleId;
  @SerializedName("aftersale_id")
  private String aftersaleId;
  @SerializedName("openid")
  private String openid;
  @SerializedName("type")
  private int type;
  @SerializedName("orderamt")
  private int orderamt;
  @SerializedName("refund_reason")
  private String refundReason;
  @SerializedName("refund_reason_type")
  private int refundReasonType;
  @SerializedName("media_list")
  private List<MediaListBean> mediaList;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MediaListBean implements Serializable {
    @SerializedName("type")
    private int type;
    @SerializedName("url")
    private String url;
  }
}

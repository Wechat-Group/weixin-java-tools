package cn.binarywang.wx.miniapp.bean.product;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * created on  2021/3/23
 */
@Data
public class WxMinishopOrderResult implements Serializable {
  private static final long serialVersionUID = -2665426592693969921L;

  /**
   * 交易组件平台订单ID
   */
  @SerializedName("order_id")
  private Long orderId;

  /**
   * 订单状态
   */
  @SerializedName("status")
  private Integer status;

  @SerializedName("create_time")
  private String createTime;

  @SerializedName("update_time")
  private String updateTime;
  /**
   * 订单详情
   */
  @SerializedName("order_detail")
  private WxMinishopOrderDetail orderDetail;

  @SerializedName("aftersale_detail")
  private WxMiniOrderAfterSaleDetail afterSaleDetail;

  /**
   * 商家小程序该订单的用户id
   */
  @SerializedName("openid")
  private String openid;

  @SerializedName("ext_info")
  private ExtInfo extInfo;

  @SerializedName("order_type")
  private Integer orderType;

  @Data
  public static class ExtInfo {
    @SerializedName("customer_notes")
    private String customerNotes;
    @SerializedName("merchant_notes")
    private String merchantNotes;
  }
}

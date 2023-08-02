package cn.binarywang.wx.miniapp.bean.shop.response;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopOrderDetail;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/6/18 2:56 下午
 */
@Data
public class WxMaShopSharerLiveOrderListResponse extends WxMaShopBaseResponse implements Serializable {

  private static final long serialVersionUID = -4190199778148290127L;

  private List<WxMaShopOrderItem> orders;

  @SerializedName("total_num")
  private Integer totalNum;

  @Data
  public static class WxMaShopOrderItem {
    @SerializedName("order_id")
    private Long orderId;
    @SerializedName("out_order_id")
    private String outOrderId;
    private Integer status;
    private String path;
    @SerializedName("order_detail")
    private WxMaShopOrderDetail orderDetail;
  }
}

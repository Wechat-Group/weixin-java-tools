package cn.binarywang.wx.miniapp.bean.product;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/6/20 7:16 下午
 */
@Data
public class WxMiniAfterSaleDetail {
  @SerializedName("aftersale_order_list")
  private List<AfterSaleOrder> aftersaleOrderList;
  @SerializedName("on_aftersale_order_cnt")
  private Integer onAftersaleOrderCnt;

  @Data
  public static class AfterSaleOrder {
    @SerializedName("aftersale_order_id")
    private Long aftersaleOrderId;
    private Integer status;
  }
}

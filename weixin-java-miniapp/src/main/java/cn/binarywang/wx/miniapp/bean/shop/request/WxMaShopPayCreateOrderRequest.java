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
 * created on  2022/8/10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaShopPayCreateOrderRequest implements Serializable {
  private static final long serialVersionUID = -5597409427574429095L;

  @SerializedName("openid")
  private String openid;
  @SerializedName("combine_trade_no")
  private String combineTradeNo;
  @SerializedName("expire_time")
  private Long expireTime;
  @SerializedName("sub_orders")
  private List<SubOrdersDTO> subOrders;

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  @Builder
  public static class SubOrdersDTO {
    @SerializedName("mchid")
    private String mchid;
    @SerializedName("amount")
    private Integer amount;
    @SerializedName("trade_no")
    private String tradeNo;
    @SerializedName("description")
    private String description;
  }
}


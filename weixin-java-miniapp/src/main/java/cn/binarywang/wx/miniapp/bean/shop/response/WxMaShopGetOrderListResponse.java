package cn.binarywang.wx.miniapp.bean.shop.response;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopOrderResult;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author leiin
 * created on  2021/3/23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaShopGetOrderListResponse extends WxMaShopBaseResponse implements Serializable {
  private static final long serialVersionUID = -81207907908726897L;

  /**
   * 订单满足条件的总数
   */
  @SerializedName("total_num")
  private Integer totalNum;

  /**
   * 订单列表
   */
  @SerializedName("order")
  private WxMaShopOrderResult order;
}

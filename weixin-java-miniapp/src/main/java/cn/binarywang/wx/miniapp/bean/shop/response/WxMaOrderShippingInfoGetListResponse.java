package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xzh
 * created on  2023/5/17 17:01
 */
@Data
public class WxMaOrderShippingInfoGetListResponse extends WxMaOrderShippingInfoBaseResponse implements Serializable {
  private static final long serialVersionUID = -5414031943436195493L;

  /**
   * 翻页时使用
   */
  @SerializedName("last_index")
  private String lastIndex;
  /**
   * 是否还有更多支付单
   */
  @SerializedName("has_more")
  private Boolean hasMore;

  /**
   * 支付单信息列表。
   */
  @SerializedName("order_list")
  private List<Order> orderList;

}

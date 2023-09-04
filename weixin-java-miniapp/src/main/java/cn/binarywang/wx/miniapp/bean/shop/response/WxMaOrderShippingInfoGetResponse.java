package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xzh
 * created on  2023/5/17 17:01
 */
@Data
public class WxMaOrderShippingInfoGetResponse extends WxMaOrderShippingInfoBaseResponse implements Serializable {
  private static final long serialVersionUID = -5414031943436195493L;

  /**
   * 支付单信息
   */
  @SerializedName("order")
  private Order order;

}

package cn.binarywang.wx.miniapp.bean.shop.response;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopOrderResult;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author leiin
 * @date 2021/3/23
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaShopGetOrderResponse extends WxMaShopBaseResponse implements Serializable {
  private static final long serialVersionUID = -5036075669789800464L;

  @SerializedName("order")
  private WxMaShopOrderResult order;
}

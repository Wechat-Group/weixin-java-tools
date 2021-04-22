package cn.binarywang.wx.miniapp.bean.shop.response;

import cn.binarywang.wx.miniapp.bean.shop.WxMaShopAddOrderResult;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * @date 2021/3/23
 * @description:
 */
@Data
public class WxMaShopAddOrderResponse extends WxMaShopBaseResponse implements Serializable {

  private static final long serialVersionUID = -8923439859095040010L;

  @SerializedName("data")
  private WxMaShopAddOrderResult date;
}

package cn.binarywang.wx.miniapp.bean.shop.response;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/6/28 11:29 上午
 */
@Data
public class WxMaShopAfterSaleAddResponse extends WxMaShopBaseResponse implements Serializable {
  @SerializedName("aftersale_id")
  private String aftersaleId;
}

package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * @date 2021/3/23
 * @description:
 */
@Data
public class WxMaShopDeliveryItem implements Serializable {

  private static final long serialVersionUID = -161617470937369136L;

  /**
   * 快递公司ID，通过获取快递公司列表获取
   */
  @SerializedName("delivery_id")
  private String deliveryId;

  /**
   * 快递单号
   */
  @SerializedName("waybill_id")
  private String waybillId;
}

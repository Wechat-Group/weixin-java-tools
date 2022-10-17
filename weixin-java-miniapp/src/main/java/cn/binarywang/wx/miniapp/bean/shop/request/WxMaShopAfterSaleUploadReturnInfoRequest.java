package cn.binarywang.wx.miniapp.bean.shop.request;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leiin
 * created on  2022/6/28 11:39 上午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaShopAfterSaleUploadReturnInfoRequest implements Serializable {
  @SerializedName("out_aftersale_id")
  private String outAftersaleId;
  @SerializedName("aftersale_id")
  private Long aftersaleId;
  @SerializedName("openid")
  private String openid;
  @SerializedName("delivery_id")
  private String deliveryId;
  @SerializedName("waybill_id")
  private String waybillId;
  @SerializedName("delivery_name")
  private String deliveryName;
}

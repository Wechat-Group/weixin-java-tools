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
public class WxMaShopAcceptReturnRequest implements Serializable {
  @SerializedName("out_aftersale_id")
  private String outAftersaleId;
  @SerializedName("aftersale_id")
  private Long aftersaleId;
  @SerializedName("address_info")
  private RefundAddressInfo addressInfo;

  @Data
  public static class RefundAddressInfo {
    @SerializedName("receiver_name")
    private String receiverName;
    @SerializedName("detailed_address")
    private String detailedAddress;
    @SerializedName("tel_number")
    private String telNumber;
    @SerializedName("country")
    private String country;
    @SerializedName("province")
    private String province;
    @SerializedName("city")
    private String city;
    @SerializedName("town")
    private String town;
  }
}

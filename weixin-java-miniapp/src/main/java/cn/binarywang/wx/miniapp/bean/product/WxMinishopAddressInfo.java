package cn.binarywang.wx.miniapp.bean.product;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author leiin
 * created on  2022/6/20 7:32 下午
 */
@Data
public class WxMinishopAddressInfo {
  @SerializedName("user_name")
  private String userName;
  @SerializedName("postal_code")
  private String postalCode;
  @SerializedName("province_name")
  private String provinceName;
  @SerializedName("city_name")
  private String cityName;
  @SerializedName("county_name")
  private String countyName;
  @SerializedName("detail_info")
  private String detailInfo;
  @SerializedName("national_code")
  private String nationalCode;
  @SerializedName("tel_number")
  private String telNumber;
}

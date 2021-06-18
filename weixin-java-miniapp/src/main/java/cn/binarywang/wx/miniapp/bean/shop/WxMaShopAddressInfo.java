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
public class WxMaShopAddressInfo implements Serializable {
  /**
   * 收件人姓名
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("receiver_name")
  private String receiverName;
  /**
   * 详细收货地址信息
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("detailed_address")
  private String detailedAddress;
  /**
   * 收件人手机号码
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("tel_number")
  private String telNumber;
  /**
   * 国家
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("country")
  private String country;
  /**
   * 省份
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("province")
  private String province;
  /**
   * 城市
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("city")
  private String city;
  /**
   * 乡镇
   * <pre>
   * 是否必填：否
   * </pre>
   */
  @SerializedName("town")
  private String town;
}

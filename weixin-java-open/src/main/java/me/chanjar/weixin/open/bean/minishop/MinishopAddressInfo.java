package me.chanjar.weixin.open.bean.minishop;

import com.google.gson.JsonObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class MinishopAddressInfo implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 收货人姓名
   */
  private String userName;

  /**
   * 邮政编码
   */
  private String postalCode;

  /**
   * 省份，格式：广东省 北京市
   */
  private String province;

  /**
   * 城市，格式：广州市
   */
  private String cityName;

  /**
   * 区，格式：海珠区
   */
  private String countyName;

  /**
   * 详细地址
   */
  private String detailInfo;

  /**
   * 国家码
   */
  private String nationalCode;

  /**
   * 电话号码
   */
  private String telNumber;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("user_name", userName);
    jsonObject.addProperty("postal_code", postalCode);
    jsonObject.addProperty("province_name", province);
    jsonObject.addProperty("city_name", cityName);
    jsonObject.addProperty("county_name", countyName);
    jsonObject.addProperty("detail_info", detailInfo);
    jsonObject.addProperty("national_code", nationalCode);
    jsonObject.addProperty("tel_number", telNumber);
    return jsonObject;
  }
}

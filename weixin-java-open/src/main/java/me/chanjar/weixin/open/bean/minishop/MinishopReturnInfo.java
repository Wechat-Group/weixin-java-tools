package me.chanjar.weixin.open.bean.minishop;

import com.google.gson.JsonObject;
import lombok.Data;

import java.io.Serializable;

@Data
public class MinishopReturnInfo implements Serializable {

  /**
   * 退货地址
   */
  private MinishopAddressInfo addressInfo;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 公司地址
   */
  private MinishopAddressInfo companyAddress;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("address_info", addressInfo.toJsonObject());
    jsonObject.addProperty("mail", email);
    jsonObject.add("company_address", companyAddress.toJsonObject());
    return jsonObject;
  }
}

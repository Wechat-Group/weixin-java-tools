package me.chanjar.weixin.open.bean.minishop;

import com.google.gson.JsonObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("小商店退货信息")
public class MinishopReturnInfo implements Serializable {

  /**
   * 退货地址
   */
  @ApiModelProperty(value = "退货地址 必填", required = true)
  private MinishopAddressInfo addressInfo;

  /**
   * 邮箱
   */
  @ApiModelProperty(value = "邮箱 必填", required = true)
  private String email;

  /**
   * 公司地址
   */
  @ApiModelProperty(value = "公司地址信息 必填", required = true)
  private MinishopAddressInfo companyAddress;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("address_info", addressInfo.toJsonObject());
    jsonObject.addProperty("mail", email);
    jsonObject.add("company_address", companyAddress.toJsonObject());
    return jsonObject;
  }
}

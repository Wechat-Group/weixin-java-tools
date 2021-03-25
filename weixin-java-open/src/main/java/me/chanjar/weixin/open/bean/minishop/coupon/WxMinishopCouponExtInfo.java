package me.chanjar.weixin.open.bean.minishop.coupon;

import com.google.gson.JsonObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("小商店优惠券的扩展信息")
public class WxMinishopCouponExtInfo implements Serializable {
  @ApiModelProperty("备注信息")
  private String notes;

  @ApiModelProperty(value = "优惠券有效时间， valid_type=0时与valid_info.start_time一致, valid_type=1时商家自己填一个绝对开始时间", required = true)
  private Long validTime;

  @ApiModelProperty(value = "优惠券失效时间， valid_type=0时与valid_info.end_time一致, valid_type=1时商家自己填一个绝对结束时间", required = true)
  private Long invalidTime;

  @ApiModelProperty(value = "商品券可以填，领取后跳转")
  private Long jumpProductId;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("jump_product_id", jumpProductId);
    jsonObject.addProperty("notes", notes);
    jsonObject.addProperty("valid_time", validTime);
    jsonObject.addProperty("invalid_time", invalidTime);
    return jsonObject;
  }
}

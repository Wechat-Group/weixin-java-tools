package me.chanjar.weixin.open.bean.minishop.coupon;

import com.google.gson.JsonObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("小商店优惠券推广渠道")
public class WxMinishopCouponPromoteInfo implements Serializable {
  @ApiModelProperty(value = "用户自定义推广渠道", required = true)
  private String customizeChannel;

  @ApiModelProperty(value = "推广类型, 1:店铺内推广,2:自定义推广渠道", required = true)
  private Integer promotionType;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("customize_channel", customizeChannel);
    jsonObject.addProperty("promote_type", promotionType);
    return jsonObject;
  }
}
